package bog.lbpsru;

import bog.lbpsru.components.ControllerListener;
import bog.lbpsru.components.Skin;
import bog.lbpsru.components.TasServer;
import bog.lbpsru.components.utils.Utils;
import bog.lbpsru.gui.LBPSRUtil;
import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.DarculaTheme;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 * @author Bog
 */
public class Main {

    public static ControllerListener listener;
    public static TasServer server;
    public static LBPSRUtil lbpsrUtil = new LBPSRUtil();
    public static JFrame mainFrame;
    private static long lastMillis = 0;
    public static Thread statKeeper;
    public static ArrayList<Skin> skins;

    public static void main(String args[]) {

        mainFrame = new JFrame("LBP Speedrun Utility");
        mainFrame.setContentPane(lbpsrUtil.mainForm);
        try {
            mainFrame.setIconImage(ImageIO.read(Main.class.getResourceAsStream("/icon.png")));
        }catch (Exception e){e.printStackTrace();}
        LafManager.setTheme(new DarculaTheme());
        LafManager.install();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        mainFrame.pack();

        listener = new ControllerListener(1337);
        server = new TasServer("0.0.0.0", 420);

        lbpsrUtil.init(listener, server);

        statKeeper = new Thread() {
            public void run() {
                try
                {
                    while (true)
                    {
                        if(System.currentTimeMillis() - lastMillis > 250)
                        {
                            lbpsrUtil.statKeeper(listener, server);
                            listener.statKeeper();
                            lastMillis = System.currentTimeMillis();
                        }
                    }
                } catch(Exception v) {
                    v.printStackTrace();
                }
            }
        };
        statKeeper.start();
    }

}