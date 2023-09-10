package Store.Client.ServerCommunication;

import Store.Client.ServerCommunication.Format;
import Store.Server.Logger.Logger;

import java.sql.SQLException;

import Store.Client.ServerCommunication.DecodeExecuteCommandCustomer;

public class DecodeExecuteCommand {
    public static String decode_and_execute(String command)
    {
        String response = "";
        try{
            switch (Format.getType(command)) {
                case CHAT:
                    response = DecodeExecuteCommandChat.execute(command);
                    break;
                case CUSTOMER:
                    response = DecodeExecuteCommandCustomer.execute(command);
                    break;
                case EMPLOYEE:
                    response = DecodeExecuteCommandEmployee.execute(command);
                    break;
                case INVENTORY:
                    break;
                case PURCHASE_HISTORY:
                    break;
                default:
                    break;
            }
        } catch (SQLException e)
        {
            response = Format.encodeException("קיימת שגיאה מול מסד הנתונים, אנא נסה שוב מאוחר יותר");
            System.out.println(response+ "\n"+ e);
        }
        return response;
     }
}
