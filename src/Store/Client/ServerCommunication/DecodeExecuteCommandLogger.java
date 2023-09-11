package Store.Client.ServerCommunication;

import java.sql.SQLException;
import Store.Server.Logger.Logger;

public class DecodeExecuteCommandLogger {
    public static String execute(String command) throws SQLException
    {
        String response = Format.encodeSuccessMessage();

        switch(Format.getMethod(command)) {
            case "turnOffSavingChat":
            // public static void turnOffSavingChat() {
                Logger.turnOffSavingChat();
                break;
            case "turnOnSavingChat":
            // public static void turnOnSavingChat() {
                Logger.turnOnSavingChat();
                break;
        }
        return response;
    } 
}
