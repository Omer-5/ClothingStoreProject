package Store.Client.ServerCommunication;

import Store.Client.ServerCommunication.Format;
import Store.Client.ServerCommunication.DecodeExecuteCommandCustomer;

public class DecodeExecuteCommand {
    public static String decode_and_execute(String command)
    {
        String response = "";
        switch (Format.getType(command)) {
            case CHAT:
                break;
            case CUSTOMER:
                response = DecodeExecuteCommandCustomer.execute(command);
                break;
            case EMPLOYEE:
                break;
            case EXCEPTION:
                break;
            case INVENTORY:
                break;
            case PURCHASE_HISTORY:
                break;
            default:
                break;
        }
        return response;
     }
}
