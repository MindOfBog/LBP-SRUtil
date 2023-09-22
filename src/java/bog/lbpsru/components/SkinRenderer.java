package bog.lbpsru.components;

import bog.lbpsru.components.utils.Button;
import bog.lbpsru.components.utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bog
 */
public class SkinRenderer {

    private Point initialClick;
    private BufferedImage image;
    private Thread thread;
    private boolean running = true;
    JFrame frame;

    public SkinRenderer(Skin skin, PlayerInputs player, String name) throws IOException {

        image = ImageIO.read(Paths.get(skin.path + "\\" + skin.image).toFile());

        frame = new JFrame(name + ": " + skin.name);

        frame.setSize(skin.base.spriteWidth, skin.base.spriteHeight);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                drawPart(g, skin.base);

                if(player.isButtonPressed(Button.cross))
                    drawPart(g, skin.cross);
                if(player.isButtonPressed(Button.square))
                    drawPart(g, skin.square);
                if(player.isButtonPressed(Button.triangle))
                    drawPart(g, skin.triangle);
                if(player.isButtonPressed(Button.circle))
                    drawPart(g, skin.circle);
                if(player.isButtonPressed(Button.l1))
                    drawPart(g, skin.l1);
                if(player.isButtonPressed(Button.l2))
                    drawPart(g, skin.l2);
                if(player.isButtonPressed(Button.r1))
                    drawPart(g, skin.r1);
                if(player.isButtonPressed(Button.r2))
                    drawPart(g, skin.r2);
                if(player.isButtonPressed(Button.up))
                    drawPart(g, skin.dpadUp);
                if(player.isButtonPressed(Button.left))
                    drawPart(g, skin.dpadLeft);
                if(player.isButtonPressed(Button.down))
                    drawPart(g, skin.dpadDown);
                if(player.isButtonPressed(Button.right))
                    drawPart(g, skin.dpadRight);
                if(player.isButtonPressed(Button.select))
                    drawPart(g, skin.select);
                if(player.isButtonPressed(Button.start))
                    drawPart(g, skin.start);

                if(player.isButtonPressed(Button.l3))
                    drawPart(g, skin.l3, (int) (skin.analogPitch * player.getLeftStick()[0]), (int) (skin.analogPitch * -player.getLeftStick()[1]));
                else
                    drawPart(g, skin.l3Press, (int) (skin.analogPitch * player.getLeftStick()[0]), (int) (skin.analogPitch * -player.getLeftStick()[1]));

                if(player.isButtonPressed(Button.r3))
                    drawPart(g, skin.r3, (int) (skin.analogPitch * player.getRightStick()[0]), (int) (skin.analogPitch * -player.getRightStick()[1]));
                else
                    drawPart(g, skin.r3Press, (int) (skin.analogPitch * player.getRightStick()[0]), (int) (skin.analogPitch * -player.getRightStick()[1]));
            }
        };

        panel.setOpaque(false);
        panel.setToolTipText(name);

        panel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                frame.getComponentAt(initialClick);
                frame.requestFocus();
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int thisX = frame.getLocation().x;
                int thisY = frame.getLocation().y;

                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                int x = thisX + xMoved;
                int y = thisY + yMoved;
                frame.setLocation(x, y);
            }
        });

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                initialClick = null;
            }
        });

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem minimizeItem = new JMenuItem("Minimize    ");
        JMenuItem closeItem = new JMenuItem("Close    ");
        minimizeItem.addActionListener(ev -> frame.setState(Frame.ICONIFIED));
        closeItem.addActionListener(ev -> stop());
        popupMenu.add(minimizeItem);
        popupMenu.add(closeItem);
        panel.setComponentPopupMenu(popupMenu);

        frame.add(panel);
        frame.setUndecorated(true);
        frame.setBackground(new Color(0, 0, 0, 0));

        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                stop();
            }
        });

        thread = new Thread() {
            public void run() {
                try
                {
                    while (running)
                    {
                        panel.repaint();
                    }
                } catch(Exception v) {
                    v.printStackTrace();
                }
            }
        };
        thread.start();
    }

    public void drawPart(Graphics g, Skin.TextureFormat texture)
    {
        drawPart(g, texture, 0, 0);
    }

    public void drawPart(Graphics g, Skin.TextureFormat texture, int xOffset, int yOffset)
    {
        g.drawImage(image,
                texture.drawX + xOffset, texture.drawY + yOffset,
                texture.drawX + xOffset + texture.spriteWidth,
                texture.drawY + yOffset + texture.spriteHeight,
                texture.spriteX, texture.spriteY,
                texture.spriteX + texture.spriteWidth,
                texture.spriteY + texture.spriteHeight,
                null);
    }

    public void stop()
    {
        running = false;
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        frame.dispose();
    }

    public static Skin readSkin(String path, String name) throws FileNotFoundException {
        FileReader fileReader = new FileReader(path + "\\skin.txt");
        BufferedReader skintxt = new BufferedReader(fileReader);
        List<String> lines = skintxt.lines().collect(Collectors.toList());

        Skin skin = new Skin(name, path);

        for(String l : lines)
        {
            String line = l;
            if(line.contains("#"))
                line = l.substring(0, l.lastIndexOf("#"));
            if(line.isEmpty())
                continue;

            String[] args = line.split(":");
            String[] values = args[1].split(",");

            String variable = args[0].toLowerCase();

            Skin.TextureFormat format = null;
            if(values.length > 2)
                format = new Skin.TextureFormat(Utils.parseInt(values[0].trim()),
                Utils.parseInt(values[1].trim()),
                Utils.parseInt(values[2].trim()),
                Utils.parseInt(values[3].trim()),
                Utils.parseInt(values[4].trim()),
                Utils.parseInt(values[5].trim()));

            switch (variable)
            {
                case "base":
                    skin.base = format;
                    break;
                case "r3":
                    skin.r3 = format;
                    break;
                case "r3press":
                    skin.r3Press = format;
                    break;
                case "l3":
                    skin.l3 = format;
                    break;
                case "l3press":
                    skin.l3Press = format;
                    break;
                case "analogpitch":
                    skin.analogPitch = Utils.parseInt(values[0].trim());
                    break;
                case "dpadleft":
                    skin.dpadLeft = format;
                    break;
                case "dpadright":
                    skin.dpadRight = format;
                    break;
                case "dpaddown":
                    skin.dpadDown = format;
                    break;
                case "dpadup":
                    skin.dpadUp = format;
                    break;
                case "cross":
                    skin.cross = format;
                    break;
                case "circle":
                    skin.circle = format;
                    break;
                case "triangle":
                    skin.triangle = format;
                    break;
                case "square":
                    skin.square = format;
                    break;
                case "select":
                    skin.select = format;
                    break;
                case "start":
                    skin.start = format;
                    break;
                case "r1":
                    skin.r1 = format;
                    break;
                case "l1":
                    skin.l1 = format;
                    break;
                case "l2":
                    skin.l2 = format;
                    break;
                case "r2":
                    skin.r2 = format;
                    break;
                case "imagename":
                    skin.image = values[0].trim();
                    break;
            }
        }

        return skin;
    }
}
