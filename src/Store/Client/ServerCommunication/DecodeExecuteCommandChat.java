package Store.Client.ServerCommunication;

import Store.Database.ChatSession;
import Store.Database.Server;
import Store.Employees.Employee;

public class DecodeExecuteCommandChat {
    //TODO: return type??
    public static void execute(String command)
    {
        switch (Format.getMethod(command)) {
            case "sendMessage":
                Employee temp = Employee.deserializeFromString(Format.getFirstParam(command));
                String message = Format.getSecondParam(command);
                ChatSession chat = Server.getChatSessionByEmployee(temp);
                chat.broadcast(message, chat.getSocketDataByEmployee(temp).getOutputStream());
                break;

            case "waitingForEmployeeToAccept":      // Employee #1 wanting to chat with other branch  --> look for available employee to accept
                break;

            case "createNewChat":                   // Employee #1 and #2 starting a new chat between them
                break;
                
            default:
                break;
        }
        
    }
}
