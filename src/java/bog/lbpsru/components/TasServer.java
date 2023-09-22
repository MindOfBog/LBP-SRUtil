package bog.lbpsru.components;

import bog.lbpsru.components.utils.Button;
import bog.lbpsru.components.utils.Utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Bog
 */
public class TasServer {

    private InetAddress ipaddress;
    private String ip;
    private int port;
    private Thread thread;
    private boolean running;
    private DatagramSocket socket;
    private DatagramPacket packet;
    byte[] buffer = new byte[1024];

    public boolean fakeInputsActive = false;

    public PlayerInputs Player1 = new PlayerInputs();
    public boolean player1Active = false;

    public PlayerInputs Player2 = new PlayerInputs();
    public boolean player2Active = false;

    public PlayerInputs Player3 = new PlayerInputs();
    public boolean player3Active = false;

    public PlayerInputs Player4 = new PlayerInputs();
    public boolean player4Active = false;


    public TasServer(String ip, int port)
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

    private void update() throws IOException
    {

        Utils.putIntIntoBuffer(fakeInputsActive ? 1 : 0, buffer, 0);

        Utils.putIntIntoBuffer(player1Active ? 1 : 0, buffer, 1);
        Player1.putPlayerInputsIntoBuffer(buffer, 2);

        Utils.putIntIntoBuffer(player2Active ? 1 : 0, buffer, 1 + 6);
        Player2.putPlayerInputsIntoBuffer(buffer, 2 + 6);

        Utils.putIntIntoBuffer(player3Active ? 1 : 0, buffer, 1 + 6 * 2);
        Player3.putPlayerInputsIntoBuffer(buffer, 2 + 6 * 2);

        Utils.putIntIntoBuffer(player4Active ? 1 : 0, buffer, 1 + 6 * 3);
        Player4.putPlayerInputsIntoBuffer(buffer, 2 + 6 * 3);

        socket.send(packet);
    }

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

                        update();
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
            packet = new DatagramPacket(buffer, buffer.length, this.ipaddress, this.port);

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
            try
            {
                Utils.putIntIntoBuffer(0, buffer, 0);

                socket.send(packet);
            }catch (Exception e){e.printStackTrace();}

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
