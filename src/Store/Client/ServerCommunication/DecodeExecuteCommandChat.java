package Store.Client.ServerCommunication;

import Store.Database.ChatSession;
import Store.Database.Server;
import Store.Database.SocketData;
import Store.Employees.Employee;

public class DecodeExecuteCommandChat {
    //TODO: return type??
    public static String execute(String command)
    {
        String response = "";
        switch (Format.getMethod(command)) {
            case "sendMessage":
                Employee emp = Employee.deserializeFromString(Format.getFirstParam(command));
                SocketData socketData = Server.getSocketDataByEmployee(emp);
                String message = Format.getSecondParam(command);
                ChatSession chat = Server.getChatHandler().getChatSessionBySocketData(socketData);
                chat.broadcast(message, socketData.getOutputStream());
                break;
            case "getAvailableBranches":
                response = Format.encodeAvailableBranches(Server.getChatHandler().getAvailableBranches());
                break;
            case "waitingForEmployeeToAccept":      // Employee #1 wanting to chat with other branch  --> look for available employee to accept
                break;

            case "createNewChat":                   // Employee #1 and #2 starting a new chat between them
                break;

            default:
                break;
        }
        
        return response;
    }
}
