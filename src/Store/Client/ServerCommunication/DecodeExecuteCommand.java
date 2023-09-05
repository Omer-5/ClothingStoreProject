package Store.Client.ServerCommunication;

import Store.Client.ServerCommunication.Format;
import Store.Client.ServerCommunication.DecodeExecuteCommandCustomer;

public class DecodeExecuteCommand {
    // TODO return type??
    public static void decode_and_execute(String command)
    {
        switch (Format.getType(command)) {
            case CUSTOMER:
                DecodeExecuteCommandCustomer.execute(command);              
            break;
            
            default:
            break;
        }
     }
}
