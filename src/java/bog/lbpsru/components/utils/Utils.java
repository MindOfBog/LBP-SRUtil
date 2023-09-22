package bog.lbpsru.components.utils;

import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.util.tinyfd.TinyFileDialogs;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static org.lwjgl.system.MemoryStack.stackPush;

/**
 * @author Bog
 */
public class Utils {

    public static int getIntFromBuffer(byte[] buffer, int index)
    {
        return (buffer[index * 4] & 0xFF) << 24 | (buffer[index * 4 + 1] & 0xFF) << 16 | (buffer[index * 4 + 2] & 0xFF) << 8 | (buffer[index * 4 + 3] & 0xFF) << 0;
    }

    public static void putIntIntoBuffer(int value, byte[] buffer, int index) {
        buffer[index * 4] = (byte) (value >> 24);
        buffer[index * 4 + 1] = (byte) (value >> 16);
        buffer[index * 4 + 2] = (byte) (value >> 8);
        buffer[index * 4 + 3] = (byte) value;
    }

    public static List<String> getFilenames(String directoryName) throws URISyntaxException, UnsupportedEncodingException, IOException {
        List<String> filenames = new ArrayList<>();

        URL url = Thread.currentThread().getContextClassLoader().getResource(directoryName);
        if (url != null) {
            if (url.getProtocol().equals("file")) {
                File file = Paths.get(url.toURI()).toFile();
                if (file != null) {
                    File[] files = file.listFiles();
                    if (files != null) {
                        for (File filename : files) {
                            filenames.add(filename.toString());
                        }
                    }
                }
            } else if (url.getProtocol().equals("jar")) {
                String dirname = directoryName + "/";
                String path = url.getPath();
                String jarPath = path.substring(5, path.indexOf("!"));
                try (JarFile jar = new JarFile(URLDecoder.decode(jarPath, StandardCharsets.UTF_8.name()))) {
                    Enumeration<JarEntry> entries = jar.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry entry = entries.nextElement();
                        String name = entry.getName();
                        if (name.startsWith(dirname) && !dirname.equals(name)) {
                            URL resource = Thread.currentThread().getContextClassLoader().getResource(name);
                            filenames.add(resource.toString());
                        }
                    }
                }
            }
        }
        return filenames;
    }

    public static int countChar(String str, char c)
    {
        int count = 0;

        for(int i=0; i < str.length(); i++)
        {    if(str.charAt(i) == c)
            count++;
        }

        return count;
    }

    public static boolean validateIP(String ip, JPanel form)
    {
        if(Utils.countChar(ip, '.') == 3)
        {
            try
            {
                String[] args = ip.split("\\.");
                boolean passed = true;
                for(String arg : args)
                {
                    int b = Integer.parseInt(arg);
                    if(b < 0)
                    {
                        passed = false;
                        JOptionPane.showMessageDialog(form, "Component in IP can't be lesser than 0.\n\"" + b + "\"", "ERROR!", JOptionPane.ERROR_MESSAGE);
                    }
                    else if(b > 255)
                    {
                        passed = false;
                        JOptionPane.showMessageDialog(form, "Component in IP can't be greater than 255.\n\"" + b + "\"", "ERROR!", JOptionPane.ERROR_MESSAGE);
                    }
                }
                return passed;
            }catch (Exception ex)
            {
                if(ex instanceof NumberFormatException)
                    JOptionPane.showMessageDialog(form, "Invalid component in IP.\n" + ex.toString().split(":")[2].trim(), "ERROR!", JOptionPane.ERROR_MESSAGE);
                else
                    ex.printStackTrace();
            }
        }
        else
        {
            if(Utils.countChar(ip, '.') < 3)
                JOptionPane.showMessageDialog(form, "Not enough components in IP.", "ERROR!", JOptionPane.ERROR_MESSAGE);
            else if(Utils.countChar(ip, '.') > 3)
                JOptionPane.showMessageDialog(form, "Too many components in IP.", "ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static int validatePort(String port, JPanel form)
    {

        if(!port.contains("."))
        {
            try
            {
                int u16 = Integer.parseInt(port);
                boolean passed = true;

                if(u16 < 0)
                {
                    passed = false;
                    JOptionPane.showMessageDialog(form, "Port can't be lesser than 0.\n\"" + u16 + "\"", "ERROR!", JOptionPane.ERROR_MESSAGE);
                }
                else if(u16 > 65535)
                {
                    passed = false;
                    JOptionPane.showMessageDialog(form, "Port can't be greater than 65535.\n\"" + u16 + "\"", "ERROR!", JOptionPane.ERROR_MESSAGE);
                }

                if(passed)
                    return u16;
            }catch (Exception ex)
            {
                if(ex instanceof NumberFormatException)
                    JOptionPane.showMessageDialog(form, "Invalid component in port.\n" + ex.toString().split(":")[2].trim(), "ERROR!", JOptionPane.ERROR_MESSAGE);
                else
                    ex.printStackTrace();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(form, "Invalid component in port.\n\"" + port + "\"", "ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }

    public static int parseInt(String i)
    {
        try
        {
            return Integer.parseInt(i);
        }catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public static File openFile(String extension) {
        try (MemoryStack stack = stackPush()) {
            PointerBuffer pattern = null;
            pattern = stack.mallocPointer(1);
            pattern.put(stack.UTF8("*." + extension));
            pattern.flip();

            return new File(TinyFileDialogs.tinyfd_openFileDialog(
                    "Open File",
                    Paths.get(System.getProperty("user.home"), "Documents", "").toAbsolutePath().toString(),
                    pattern,
                    null,
                    false));
        }
    }

    public static JFileChooser fileChooser = new JFileChooser();
    private static boolean setupFilter(String extension) {
        fileChooser.resetChoosableFileFilters();
        fileChooser.setSelectedFile(new File(""));
        fileChooser.setCurrentDirectory(new File(Paths.get(System.getProperty("user.home"), "Documents", "").toAbsolutePath().toString()));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*." + extension, extension));
        fileChooser.setAcceptAllFileFilterUsed(true);
        return true;
    }

    public static File openFileLegacy(String extension) {
        int returnValue = JFileChooser.CANCEL_OPTION;
        setupFilter(extension);
        returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION)
            return fileChooser.getSelectedFile();
        return null;
    }
}
