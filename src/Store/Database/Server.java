package Store.Database;

import java.io.*;
import java.net.*;
import java.util.*;

import Store.Client.ServerCommunication.Format;
import Store.AppForms.Chats;
import Store.Client.ServerCommunication.ClassType;
import Store.Client.ServerCommunication.DecodeExecuteCommand;
import Store.Employees.Employee;
import Store.Employees.EmployeeTitle;
import Store.Inventories.InventoryItem;

public class Server {
    private static final int PORT = 7000;
    private static Map<Employee, SocketData> connections = new HashMap<Employee, SocketData>();
    private static Map<Employee, ChatSession> chattingEmployees = new HashMap<Employee, ChatSession>();

    public static void main(String[] args) {
        System.out.println("--> Server is running...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                //TODO: Getting Employee When Client-Login Here
                Employee emp = new Employee("ישראל ישראלי", "0528921319", 123456789, 212444, "חולון", "1111", EmployeeTitle.CASHIER);
                new ClientHandler(serverSocket.accept(), emp).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<Employee, SocketData> getConnections() {
        return connections;
    }

    public static Map<Employee, ChatSession> getChattingEmployees() {
        return chattingEmployees;
    }
    public static void validateChatSession(ChatSession chat) {
        int chattingCount = 0;

        for (Map.Entry<Employee, ChatSession> entry : chattingEmployees.entrySet()) {
            int tempSessionID = entry.getValue().getSessionID();
            int sessionID = chat.getSessionID();

            if(tempSessionID == sessionID)
                chattingCount++;
        }

        if(chattingCount < 2) { // Close chat because not enough employees for chat
            for (Map.Entry<Employee, ChatSession> entry : chattingEmployees.entrySet()) {
                int tempSessionID = entry.getValue().getSessionID();
                int sessionID = chat.getSessionID();

                if(tempSessionID == sessionID) {
                    Employee emp = entry.getKey();
                    chattingEmployees.remove(emp); 
                }
            }
        }        
    }

    public static ChatSession getChatSessionByEmployee(Employee emp) {
        return chattingEmployees.get(emp);
    }

    private static class ClientHandler extends Thread {
        private SocketData socketData;
        private Employee emp;

        public ClientHandler(Socket socket, Employee emp) {
            this.socketData = new SocketData(socket);
            this.emp = emp;
        }

        public void run() {
            try {
                synchronized (connections) {
                    connections.put(emp, socketData);
                }
    
                String inputString;
                while ((inputString = socketData.getInputStream().readLine()) != null) {
                    //TODO: Separate Chat, DAO & other Server functions here
                    System.out.println(inputString);
                    String res = DecodeExecuteCommand.decode_and_execute(inputString);
                    socketData.getOutputStream().println(res);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socketData.getOutputStream().close();
                    socketData.getSocket().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                synchronized (connections) {
                    connections.remove(emp);
                }
            }
        }
    }
}
