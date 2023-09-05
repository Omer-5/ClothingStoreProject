package Store.Client.ServerCommunication;

import Store.Client.ServerCommunication.Format;
import Store.Client.ServerCommunication.DecodeExecuteCommandCustomer;

public class DecodeExecuteCommand {
    public static boolean decode_and_execute(String command)
    {
        switch (Format.getType(command)) {
            case CUSTOMER:
                DecodeExecuteCommandCustomer.execute(command);              
            break;
            
            default:
            break;
        }
        return true;
    }
}
