package Store.Client.ServerCommunication;

import java.sql.SQLException;

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
                case LOGGER:
                    response = DecodeExecuteCommandLogger.execute(command);
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

        if(response == null || response.length() == 0 || response == "")
            response = Format.encodeEmpty("");
        return response;
     }
}
