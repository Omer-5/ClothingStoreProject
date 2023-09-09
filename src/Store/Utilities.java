package Store;

import javax.swing.*;

import Store.Client.ServerCommunication.EncodeCommandChat;
import Store.Database.SocketData;
import Store.Employees.Employee;

import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class Utilities {
    private static SocketData client;
    private static boolean isInChatPanel = false;

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

    public static boolean isInChatPanel() {
        return isInChatPanel;
    }

    public static void setInChatPanel(Employee emp, boolean status) {
        isInChatPanel = status;
        
        String command = "";

        if(status)
            command = EncodeCommandChat.clientOnline(emp);
        else 
            command = EncodeCommandChat.clientOffline(emp);

        client.getOutputStream().println(command);

        try {
            String res = client.getInputStream().readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
