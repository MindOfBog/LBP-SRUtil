package bog.lbpsru.components;

import bog.lbpsru.components.structs.PlayerInputs;
import bog.lbpsru.components.utils.Utils;
import bog.lbpsru.gui.LBPSRUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Bog
 */
public abstract class Server {

    private InetAddress ipaddress;
    private String ip;
    private int port;
    private Thread thread;
    private boolean running;
    private DatagramSocket socket;
    private byte[] buffer = new byte[1024];

    public Server(String ip, int port)
    {
        try {
            this.ip = ip;
            this.port = port;
            this.ipaddress = InetAddress.getByName(ip.equalsIgnoreCase("0.0.0.0") ? "127.0.0.1" : ip);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setIp(String ip) {
        try
        {
            this.ipaddress = InetAddress.getByName(ip.equalsIgnoreCase("0.0.0.0") ? "127.0.0.1" : ip);
            this.ip = ip;
        }
        catch (Exception e) {e.printStackTrace();}

        if(!isStopped())
        {
            stopServer();
            startServer();
        }
    }

    public String getIp()
    {
        return ip;
    }

    public void setPort(int port)
    {
        this.port = port;

        if(!isStopped())
        {
            stopServer();
            startServer();
        }
    }

    public int getPort()
    {
        return port;
    }

    public abstract boolean update(byte[] buffer);

    public void startServer()
    {
        thread = new Thread() {
            public void run() {
                try
                {
                    while (running)
                    {
                        if(socket.isClosed())
                            stopServer();

                        buffer = new byte[1024];
                        if(update(buffer))
                            socket.send(new DatagramPacket(buffer, buffer.length, ipaddress, port));
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

            socket = new DatagramSocket();

            running = true;
            thread.start();
        }catch (Exception e)
        {
            stopServer();
            e.printStackTrace();
        }
    }

    public void stopServer()
    {
        if(running)
        {
//            try
//            {
//                Utils.putIntIntoBuffer(0, buffer, 0);
//
//                socket.send(packet);
//            }catch (Exception e){e.printStackTrace();}

            running = false;
            socket.close();
            thread.stop();
            thread = null;
        }
    }

    public boolean isStopped()
    {
        return thread == null;
    }
}
