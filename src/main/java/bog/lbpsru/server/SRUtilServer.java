package bog.lbpsru.server;

import bog.lbpsru.Main;
import bog.lbpsru.components.Server;
import bog.lbpsru.components.structs.tas.TasInput;
import bog.lbpsru.components.utils.Utils;
import bog.lbpsru.gui.LBPSRUtil;

import java.util.ArrayList;

/**
 * @author Bog
 */
public class SRUtilServer extends Server {

    public SRUtilServer(String ip, int port) {
        super(ip, port);
    }

    @Override
    public boolean update(byte[] buffer) {
        for(int i = 0; i < 256; i++)
            Utils.putIntIntoBuffer(-1, buffer, i);

        if(LBPSRUtil.endRun)
        {
            Utils.putIntIntoBuffer(1, buffer, 0);

            int currentIndex = storeArrayInBuffer(buffer, 1, 0, new ArrayList<>());
            currentIndex = storeArrayInBuffer(buffer, currentIndex, 0, new ArrayList<>());
            currentIndex = storeArrayInBuffer(buffer, currentIndex, 0, new ArrayList<>());
            currentIndex = storeArrayInBuffer(buffer, currentIndex, 0, new ArrayList<>());

            LBPSRUtil.endRun = false;
            return true;
        }
        else if(LBPSRUtil.running)
        {
            if(LBPSRUtil.readyForInputPacket)
            {
                if(Main.lastIndex > LBPSRUtil.loadedReplay.player1.get(Main.currentSegment).size() &&
                        Main.lastIndex > LBPSRUtil.loadedReplay.player2.get(Main.currentSegment).size() &&
                        Main.lastIndex > LBPSRUtil.loadedReplay.player3.get(Main.currentSegment).size() &&
                        Main.lastIndex > LBPSRUtil.loadedReplay.player4.get(Main.currentSegment).size())
                    return false;

                LBPSRUtil.readyForInputPacket = false;

                Utils.putIntIntoBuffer(1, buffer, 0);

                int currentIndex = storeArrayInBuffer(buffer, 1, Main.lastIndex, LBPSRUtil.loadedReplay.player1.get(Main.currentSegment));
                currentIndex = storeArrayInBuffer(buffer, currentIndex, Main.lastIndex, LBPSRUtil.loadedReplay.player2.get(Main.currentSegment));
                currentIndex = storeArrayInBuffer(buffer, currentIndex, Main.lastIndex, LBPSRUtil.loadedReplay.player3.get(Main.currentSegment));
                currentIndex = storeArrayInBuffer(buffer, currentIndex, Main.lastIndex, LBPSRUtil.loadedReplay.player4.get(Main.currentSegment));

                Main.lastIndex += 7;
//                for(int i = 0; i < 100; i++)
//                System.out.println(Utils.getIntFromBuffer(buffer, i));
                return true;
            }
        }
        return false;
    }

    public static int storeArrayInBuffer(byte[] buffer, int bufferIndex, int startIdx, ArrayList<TasInput> array) {
        Utils.putIntIntoBuffer(array.size(), buffer, bufferIndex++);

        int chunkSize = 7;
        int endIdx = Math.min(startIdx + chunkSize, array.size());

        for (int i = startIdx; i < endIdx; i++) {
            TasInput input = array.get(i);
            Utils.putIntIntoBuffer(i + 1, buffer, bufferIndex++);
            Utils.putIntIntoBuffer(input.frame, buffer, bufferIndex++);
            Utils.putIntIntoBuffer(input.controller ? 1 : 0, buffer, bufferIndex++);
            input.inputs.putPlayerInputsIntoBuffer(buffer, bufferIndex++);
            bufferIndex += 4;
        }

        return bufferIndex;
    }
}
