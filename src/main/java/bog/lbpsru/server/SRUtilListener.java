package bog.lbpsru.server;

import bog.lbpsru.Main;
import bog.lbpsru.components.Listener;
import bog.lbpsru.components.structs.PlayerInputs;
import bog.lbpsru.components.structs.tas.TasInput;
import bog.lbpsru.components.utils.Utils;
import bog.lbpsru.gui.LBPSRUtil;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.util.ArrayList;

/**
 * @author Bog
 */
public class SRUtilListener extends Listener {

    public int playerCount = 0;
    public int frame = 0;

    public PlayerInputs Player1 = new PlayerInputs();

    public PlayerInputs Player2 = new PlayerInputs();

    public PlayerInputs Player3 = new PlayerInputs();

    public PlayerInputs Player4 = new PlayerInputs();

    public SRUtilListener(int port) {
        super(port);
    }

    @Override
    public void update(DatagramPacket packet){
        byte[] data = packet.getData();

        int packetID = Utils.getIntFromBuffer(data, 0);

        switch (packetID)
        {
            case 1: // input

                frame = Utils.getIntFromBuffer(data, 1);
                playerCount = Utils.getIntFromBuffer(data, 2);

                if(playerCount >= 1)
                    Player1.update(Utils.getIntFromBuffer(data, 4),
                            Float.intBitsToFloat(Utils.getIntFromBuffer(data, 5)),
                            Float.intBitsToFloat(Utils.getIntFromBuffer(data, 6)),
                            Float.intBitsToFloat(Utils.getIntFromBuffer(data, 7)),
                            Float.intBitsToFloat(Utils.getIntFromBuffer(data, 8)));

                if(playerCount >= 2)
                    Player2.update(Utils.getIntFromBuffer(data, 10),
                            Float.intBitsToFloat(Utils.getIntFromBuffer(data, 11)),
                            Float.intBitsToFloat(Utils.getIntFromBuffer(data, 12)),
                            Float.intBitsToFloat(Utils.getIntFromBuffer(data, 13)),
                            Float.intBitsToFloat(Utils.getIntFromBuffer(data, 14)));

                if(playerCount >= 3)
                    Player3.update(Utils.getIntFromBuffer(data, 16),
                            Float.intBitsToFloat(Utils.getIntFromBuffer(data, 17)),
                            Float.intBitsToFloat(Utils.getIntFromBuffer(data, 18)),
                            Float.intBitsToFloat(Utils.getIntFromBuffer(data, 19)),
                            Float.intBitsToFloat(Utils.getIntFromBuffer(data, 20)));

                if(playerCount >= 4)
                    Player4.update(Utils.getIntFromBuffer(data, 22),
                            Float.intBitsToFloat(Utils.getIntFromBuffer(data, 23)),
                            Float.intBitsToFloat(Utils.getIntFromBuffer(data, 24)),
                            Float.intBitsToFloat(Utils.getIntFromBuffer(data, 25)),
                            Float.intBitsToFloat(Utils.getIntFromBuffer(data, 26)));

                break;
            case 2: // level start

                if(LBPSRUtil.running)
                {
                    Main.currentSegment++;
                    Main.lastIndex = 0;

                    if(Main.currentSegment > LBPSRUtil.loadedReplay.player1.size() &&
                            Main.currentSegment > LBPSRUtil.loadedReplay.player2.size() &&
                            Main.currentSegment > LBPSRUtil.loadedReplay.player3.size() &&
                            Main.currentSegment > LBPSRUtil.loadedReplay.player4.size())
                    {
                        LBPSRUtil.running = false;
                        LBPSRUtil.endRun = true;
                        LBPSRUtil.readyForInputPacket = false;
                        Main.lbpsrUtil.playReplayButton.setText("Play");
                    }

                    LBPSRUtil.readyForInputPacket = true;
                    System.out.println(Main.currentSegment);
                }

                int currentLevel = Utils.getIntFromBuffer(data, 1);
                int lastLevel = Utils.getIntFromBuffer(data, 2);

                break;
            case 3: // inputs received
                LBPSRUtil.readyForInputPacket = true;
                break;
            case 4: // level finished

                break;
        }
    }

    @Override
    public void statKeeper() {
        if(!isReceivingData())
        {
            playerCount = 0;
            Player1.update(0, 0, 0, 0, 0);
            Player2.update(0, 0, 0, 0, 0);
            Player3.update(0, 0, 0, 0, 0);
            Player4.update(0, 0, 0, 0, 0);
        }
    }
}
