package bog.lbpsru.gui;

import bog.lbpsru.Main;
import bog.lbpsru.components.structs.Skin;
import bog.lbpsru.components.SkinRenderer;
import bog.lbpsru.components.Server;
import bog.lbpsru.components.structs.tas.Replay;
import bog.lbpsru.components.utils.Patch;
import bog.lbpsru.components.utils.Utils;
import bog.lbpsru.server.SRUtilListener;
import bog.lbpsru.server.SRUtilServer;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Bog
 */
public class LBPSRUtil {
    public JPanel mainForm;
    private JTabbedPane tabbedPane1;
    private JTextField UDPListenerPort;
    private JButton setListenerPort;
    private JButton startUDPListener;
    private JButton stopUDPListener;
    private JLabel listenerPortLabel;
    private JLabel listenerRunning;
    public JComboBox<String> player1Combo;
    public JComboBox<String> player2Combo;
    public JComboBox<String> player3Combo;
    public JComboBox<String> player4Combo;
    private JButton startPlayer1;
    private JButton startPlayer2;
    private JButton startPlayer3;
    private JButton startPlayer4;
    private JButton refreshSkinsButton;
    private JLabel playerCount;
    private JLabel listenerActivity;
    private JButton patchELFFileButton;
    private JCheckBox legacyFileDialogueCheckBox;
    private JLabel serverIPLabel;
    private JLabel serverPortLabel;
    private JLabel serverRunning;
    private JTextField UDPServerIP;
    private JTextField UDPServerPort;
    private JButton setServerIP;
    private JButton setServerPort;
    private JButton startUDPServer;
    private JButton stopUDPServer;
    private JRadioButton a121RadioButton;
    private JRadioButton a130RadioButton;
    private JButton LOADREPLAYButton;
    private JLabel loadedReplayLabel;
    private JButton clearButton;
    public JButton playReplayButton;
    private JLabel currentFrame;
    private JPanel tasButtonsPanel;
    private JButton reloadButton;
    public static Replay loadedReplay;
    public static Path loadedReplayPath;
    public static boolean running;
    public static boolean endRun;
    public static boolean readyForInputPacket;


    public void init(SRUtilListener listener, SRUtilServer server)
    {
        ButtonGroup patches = new ButtonGroup();
        patches.add(a121RadioButton);
        patches.add(a130RadioButton);
        setListenerPort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String port = UDPListenerPort.getText();
                int validated = Utils.validatePort(port, mainForm);
                if(validated != -1)
                    listener.setPort(validated);
            }
        });
        startUDPListener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.startListener();
            }
        });
        stopUDPListener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.stopListener();
            }
        });
        setServerIP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ip = UDPServerIP.getText();
                boolean valid = Utils.validateIP(ip, mainForm);
                if(valid)
                    server.setIp(ip);
            }
        });
        startUDPServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.startServer();
            }
        });
        stopUDPServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.stopServer();
            }
        });
        refreshSkinsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String path = Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
                    if(path.startsWith("/"))
                        path = path.substring(1);
                    path = path.substring(0, path.lastIndexOf("/") + 1);

                    File controllerskins = new File(path + "controllerskins/");
                    Main.skins.clear();
                    player1Combo.removeAllItems();
                    player2Combo.removeAllItems();
                    player3Combo.removeAllItems();
                    player4Combo.removeAllItems();

                    File[] files = controllerskins.listFiles();

                    if(files == null)
                    {
                        JOptionPane.showMessageDialog(mainForm, "Controllerskins folder is missing.", "ERROR!", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    for(File file : Objects.requireNonNull(files))
                        Main.skins.add(SkinRenderer.readSkin(file.getPath(), file.getName()));

                    for(Skin skin : Main.skins)
                    {
                        player1Combo.addItem(skin.name);
                        player2Combo.addItem(skin.name);
                        player3Combo.addItem(skin.name);
                        player4Combo.addItem(skin.name);
                    }

                    Main.mainFrame.pack();
                } catch (Exception ex) {ex.printStackTrace();}
            }
        });
        startPlayer1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new SkinRenderer(Main.skins.get(player1Combo.getSelectedIndex()), Main.listener.Player1, "Player 1");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        startPlayer2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new SkinRenderer(Main.skins.get(player2Combo.getSelectedIndex()), Main.listener.Player2, "Player 2");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        startPlayer3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new SkinRenderer(Main.skins.get(player3Combo.getSelectedIndex()), Main.listener.Player3, "Player 3");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        startPlayer4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new SkinRenderer(Main.skins.get(player4Combo.getSelectedIndex()), Main.listener.Player4, "Player 4");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        patchELFFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    boolean a130 = a130RadioButton.isSelected();
                    boolean a121 = a121RadioButton.isSelected();

                    if(!a130 && !a121)
                    {
                        return;
                    }

                    File elf = legacyFileDialogueCheckBox.isSelected() ? Utils.openFileLegacy("elf") : Utils.openFile("elf");
                    byte[] outData = Files.readAllBytes(Path.of(elf.getPath()));

                    if(a130)
                    {
                        Patch.applyPatches(outData, Patch.patch130());
                        Files.write(Path.of(elf.getPath() + ".patched"), outData);
                        JOptionPane.showMessageDialog(mainForm, "Applied 1.30 patches. Patched file: " + elf.getPath() + ".patched", "Done!", JOptionPane.PLAIN_MESSAGE);
                    }
                    else if(a121)
                    {
                        Patch.applyPatches(outData, Patch.patch121());
                        Files.write(Path.of(elf.getPath() + ".patched"), outData);
                        JOptionPane.showMessageDialog(mainForm, "Applied 1.21 patches. Patched file: " + elf.getPath() + ".patched", "Done!", JOptionPane.PLAIN_MESSAGE);
                    }

                }catch (Exception ex){ex.printStackTrace();}
            }
        });

        LOADREPLAYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    File json = legacyFileDialogueCheckBox.isSelected() ? Utils.openFileLegacy("json") : Utils.openFile("json");
                    loadedReplay = Replay.fromJson(json, mainForm);
                    loadedReplayPath = json.toPath();
                    loadedReplayLabel.setText(json.getName().substring(0, json.getName().lastIndexOf(".")));
                    LOADREPLAYButton.setEnabled(false);
                    clearButton.setEnabled(true);
                    reloadButton.setEnabled(true);
                    playReplayButton.setEnabled(true);
                }catch (Exception ex){ex.printStackTrace();}
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadedReplay = null;
                loadedReplayPath = null;
                loadedReplayLabel.setText("No file loaded.");
                LOADREPLAYButton.setEnabled(true);
                clearButton.setEnabled(false);
                reloadButton.setEnabled(false);
                playReplayButton.setEnabled(false);
            }
        });

        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    loadedReplay = Replay.fromJson(new File(loadedReplayPath.toString()), mainForm);
                } catch (FileNotFoundException ex) {ex.printStackTrace();}
            }
        });

        playReplayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(running)
                {
                    running = false;
                    endRun = true;
                    readyForInputPacket = false;
                    playReplayButton.setText("Play");
                }
                else
                {
                    Main.currentSegment = 0;
                    Main.lastIndex = 0;

                    running = true;
                    readyForInputPacket = true;
                    playReplayButton.setText("Stop");
                }

            }
        });

        try {
            String path = Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            if(path.startsWith("/"))
                path = path.substring(1);
            path = path.substring(0, path.lastIndexOf("/") + 1);

            File controllerskins = new File(path + "controllerskins/");

            Main.skins = new ArrayList<>();

            if(controllerskins.listFiles() == null)
                System.err.println("Missing controller skins!");
            else
                for(File file : controllerskins.listFiles())
                    Main.skins.add(SkinRenderer.readSkin(file.getPath(), file.getName()));

            for(Skin skin : Main.skins)
            {
                player1Combo.addItem(skin.name);
                player2Combo.addItem(skin.name);
                player3Combo.addItem(skin.name);
                player4Combo.addItem(skin.name);
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    public void statKeeper(SRUtilListener listener, Server server)
    {
        listenerPortLabel.setText(Integer.toString(listener.getPort()));
        listenerRunning.setText(listener.isStopped() ? "Stopped" : "Running");
        listenerActivity.setText(listener.isStopped() ? "Inactive" : listener.isReceivingData() ? "Active" : "Waiting");

        playerCount.setText(Integer.toString(listener.playerCount));

        serverIPLabel.setText(server.getIp());
        serverPortLabel.setText(Integer.toString(server.getPort()));
        serverRunning.setText(server.isStopped() ? "Stopped" : "Running");
        Main.mainFrame.pack();
    }

    private void createUIComponents() {

    }
}
