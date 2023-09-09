package Store.Client.ServerCommunication;

import Store.Database.ChatSession;
import Store.Database.Server;
import Store.Database.SocketData;
import Store.Employees.Employee;

public class DecodeExecuteCommandChat {
    public static String execute(String command)
    {
        Employee emp;
        SocketData socketData;
        String response = ""; // TODO: add response
        switch (Format.getMethod(command)) {
            case "clientOnline":
                emp = Employee.deserializeFromString(Format.getFirstParam(command));
                socketData = Server.getSocketDataByEmployee(emp);
                Server.ChatHandler.getAvailableEmployees().put(socketData, emp);
                break;
            case "clientOffline":
                emp = Employee.deserializeFromString(Format.getFirstParam(command));
                socketData = Server.getSocketDataByEmployee(emp);
                Server.ChatHandler.getAvailableEmployees().remove(emp);
                break;
            case "sendMessage":
                emp = Employee.deserializeFromString(Format.getFirstParam(command));
                socketData = Server.getSocketDataByEmployee(emp);
                String message = Format.getSecondParam(command);
                ChatSession chat = Server.getChatHandler().getChatSessionBySocketData(socketData);
                chat.broadcast(message, socketData.getOutputStream());
                break;
            case "getAvailableBranches":
                String branch = Format.getFirstParam(command);
                response = Format.encodeAvailableBranches(Server.getChatHandler().getAvailableBranches(branch));
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
