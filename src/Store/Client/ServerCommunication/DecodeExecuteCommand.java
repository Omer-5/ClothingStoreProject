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
                    response = DecodeExecuteCommandInventory.execute(command);
                    break;
                case PURCHASE_HISTORY:
                    response = DecodeExecuteCommandPurchaseHistory.execute(command);
                    break;
            }
        } catch (SQLException e)
        {
            response = Format.encodeException("קיימת שגיאה מול מסד הנתונים, אנא נסה שוב מאוחר יותר");
            System.out.println(response+ "\n"+ e);
        }
        System.out.println("Server response: "+response);

        if(response == null || response.length() == 0 || response == "")
            response = Format.encodeException("ריק");
        return response;
     }
}
