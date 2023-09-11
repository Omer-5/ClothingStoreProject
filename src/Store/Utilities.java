package Store;

import javax.swing.*;

import Store.Client.ServerCommunication.EncodeCommandChat;
import Store.Client.ServerCommunication.Format;
import Store.Database.SocketData;
import Store.Employees.Employee;

import java.awt.*;
import java.io.IOException;

/**
 * A utility class that provides various helper methods for the application.
 */
public class Utilities {

    /**
     * Represents the client socket data for communication.
     */
    private static SocketData client;

    /**
     * Flag indicating whether the user is in the chat panel.
     */
    private static boolean isInChatPanel = false;

    /**
     * Checks if the given string is numeric.
     *
     * @param str The string to check.
     * @return true if the string is numeric, false otherwise.
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch( NumberFormatException e) {
            return false;
        }
    }

    public static boolean isPositiveInt(String str) {
        return (Integer.parseInt(str) >= 0);
    }

    public static boolean isPositiveDouble(String str) {
        return (Double.parseDouble(str) >= 0.0);
    }

    /**
     * Checks if the given string is a valid double value.
     *
     * @param str The string to check.
     * @return true if the string is a valid double, false otherwise.
     */
    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch( NumberFormatException e) {
            return false;
        }
    }

    /**
     * Sets the client socket data for communication.
     *
     * @param socketData The SocketData instance to set.
     */
    public static void setClientSocketData(SocketData socketData) {
        client = socketData;
    }

    /**
     * Retrieves the client socket data.
     *
     * @return The current SocketData instance.
     */
    public static SocketData getClientSocketData() {
        return client;
    }

    /**
     * Checks if the user is currently in the chat panel.
     *
     * @return true if in the chat panel, false otherwise.
     */
    public static boolean isInChatPanel() {
        return isInChatPanel;
    }

    /**
     * Sets the user's status in the chat panel.
     *
     * @param emp The employee instance.
     * @param status The status to set (true if in chat panel, false otherwise).
     */
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

    /**
     * Displays a message box with the specified text.
     *
     * @param text The message to display.
     */
    public static void MessageBox(String text) {
        Font font = new Font("Calibri", Font.PLAIN, 16);
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setFont(font);
        JOptionPane.showMessageDialog(null,
                label,
                "הודעה חדשה",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static String SendReceive(String command)
    {
        String response = Format.encodeException("!שגיאת תקשורת מול השרת");
        Utilities.getClientSocketData().getOutputStream().println(command);
        try {
            response = Utilities.getClientSocketData().getInputStream().readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return response;
        }
        return response;
    }
}

