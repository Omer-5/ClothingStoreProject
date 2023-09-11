package Store.Client.ServerCommunication;

import java.util.Set;

import Store.Database.ChatSession;
import Store.Database.Server;
import Store.Database.SocketData;
import Store.Employees.Employee;

public class DecodeExecuteCommandChat {
    public static String execute(String command)
    {
        Employee emp;
        SocketData socketData;
        String branch;
        ChatSession chat;
        String response = Format.encodeSuccessMessage();

        switch (Format.getMethod(command)) {
            case "clientOnline":
                emp = Employee.deserializeFromString(Format.getFirstParam(command));
                socketData = Server.getSocketDataByEmployee(emp);
                Server.ChatHandler.getAvailableEmployees().put(socketData, emp);
                break;
            case "clientOffline":
                emp = Employee.deserializeFromString(Format.getFirstParam(command));
                socketData = Server.getSocketDataByEmployee(emp);
                Server.ChatHandler.getAvailableEmployees().remove(socketData);
                break;
            case "sendMessage":
                emp = Employee.deserializeFromString(Format.getFirstParam(command));
                socketData = Server.getSocketDataByEmployee(emp);
                String message = Format.getSecondParam(command);
                chat = Server.ChatHandler.getChatSessionBySocketData(socketData);
                chat.broadcast(emp, message, socketData.getOutputStream());
                break;
            case "getAvailableBranches":
                branch = Format.getFirstParam(command);
                Set<String> branches = Server.ChatHandler.getAvailableBranches(branch);

                if( branches.size() == 0 )
                    response = Format.encodeEmpty("");
                else
                    response = Format.encodeAvailableBranches(branches);    
                
                break;
            case "getAvailableChats":
                branch = Format.getFirstParam(command);
                Set<ChatSession> availableChats = Server.ChatHandler.getAvailableChats(branch);

                if( availableChats.size() == 0 )
                    response = Format.encodeEmpty("");
                else
                    response = Format.encodeAvailableChats(availableChats);           
                break;
            case "requestChatWithBranch":                   // Employee #1 and #2 starting a new chat between them
                emp = Employee.deserializeFromString(Format.getFirstParam(command));
                branch = Format.getSecondParam(command);

                if(Server.ChatHandler.isAvailablesEmployeesForChat(branch)) {      
                    SocketData otherEmpSocketData = Server.ChatHandler.getFirstAvailableEmployeByBranch(branch);

                    if(otherEmpSocketData != null) {
                        chat = new ChatSession(emp, Server.getEmployeeBySocketData(otherEmpSocketData));
                        chat.addListener(otherEmpSocketData, Server.getEmployeeBySocketData(otherEmpSocketData));
                        chat.addListener(Server.getSocketDataByEmployee(emp), emp);

                        Server.ChatHandler.getChattingEmployees().put(otherEmpSocketData, chat);
                        Server.ChatHandler.getChattingEmployees().put(Server.getSocketDataByEmployee(emp), chat);

                        Server.ChatHandler.getAvailableEmployees().remove(otherEmpSocketData);
                        Server.ChatHandler.getAvailableEmployees().remove(Server.getSocketDataByEmployee(emp));

                        otherEmpSocketData.getOutputStream().println("CHAT@@@setCurrentChat###" + chat.getSessionID());
                        response = "CHAT@@@setCurrentChat###" + chat.getSessionID();
                    }
                } else {
                    Server.ChatHandler.addEmployeToWaitingList(emp, branch);
                    response = "CHAT@@@waitingList###" + branch;
                }
                break;
            case "leaveChat":
                emp = Employee.deserializeFromString(Format.getFirstParam(command));
                socketData = Server.getSocketDataByEmployee(emp);
                chat = Server.ChatHandler.getChatSessionBySocketData(socketData);
                chat.removeListener(socketData);
                response = "CHAT@@@abortCurrentChat###";
                break;
            case "joinChatSession":
                int sessionID = Integer.parseInt(Format.getSecondParam(command));
                emp = Employee.deserializeFromString(Format.getFirstParam(command));
                socketData = Server.getSocketDataByEmployee(emp);
                chat = Server.getChatHandler().getChatSessionByID(sessionID);

                chat.addListener(socketData, emp);
                Server.ChatHandler.getChattingEmployees().put(socketData, chat);

                response = "CHAT@@@setCurrentChat###" + chat.getSessionID();
                break;
            default:
                break;
        }
        
        return response;
    }
}
