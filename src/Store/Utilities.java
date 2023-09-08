package Store;

import javax.swing.*;

import Store.Database.SocketData;

import java.awt.*;
import java.net.Socket;

public class Utilities {
    private static SocketData client;

    public static boolean isNumeric(String str)
    {
        int intValue;
        try {
            intValue = Integer.parseInt(str);
            return true;
        } catch( NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String str)
    {
        double doubleValue;
        try {
            doubleValue = Double.parseDouble(str);
            return true;
        } catch( NumberFormatException e) {
            return false;
        }
    }

    public static void setClientSocketData(SocketData socketData) {
        client = socketData;
    }

    public static SocketData getClientSocketData() {
        return client;
    }

    public static void MessageBox(String text)
    {
        Font font = new Font("Calibri", Font.PLAIN, 16);
        JLabel label = new JLabel(text, JLabel.CENTER);
                    label.setFont(font);
                    JOptionPane.showMessageDialog(null,
                        label,
                        "הודעה חדשה",
                        JOptionPane.INFORMATION_MESSAGE);
    }
}
