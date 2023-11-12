package bog.lbpsru.components;

import bog.lbpsru.components.structs.PlayerInputs;
import bog.lbpsru.components.structs.tas.TasInput;
import bog.lbpsru.components.utils.Utils;
import bog.lbpsru.gui.LBPSRUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author Bog
 */
public abstract class Listener {
    private int port;
    public long lastMillis = 0;
    private Thread thread;
    private boolean running = false;

    private DatagramSocket socket;
    private DatagramPacket packet;

    private byte[] buffer = new byte[1024];
    public Listener(int port)
    {
        this.port = port;
    }

    public void setPort(int port)
    {
        this.port = port;

        if(!isStopped())
        {
            stopListener();
            startListener();
        }
    }

    public int getPort()
    {
        return port;
    }

    public abstract void update(DatagramPacket packet);

    public void startListener()
    {
        thread = new Thread() {
            public void run() {
                try
                {
                    while (running)
                    {
                        if(socket.isClosed())
                            stopListener();

                        socket.receive(packet);
                        update(packet);
                        packet.setLength(packet.getData().length);

                        lastMillis = System.currentTimeMillis();
                    }
                } catch(Exception v) {
                    v.printStackTrace();
                }
            }
        };

        try
        {
            if(socket != null && socket.isBound())
                socket.close();

            socket = new DatagramSocket(this.port);
            packet = new DatagramPacket(buffer, buffer.length);
            running = true;
            thread.start();
        }catch (Exception e)
        {
            stopListener();
            e.printStackTrace();
        }
    }

    public void stopListener()
    {
        if(running)
        {
            running = false;
            thread.stop();
            thread = null;
        }
    }

    public boolean isStopped()
    {
        return thread == null;
    }

    public boolean isReceivingData()
    {
        return System.currentTimeMillis() - lastMillis < 100;
    }

    public abstract void statKeeper();
}