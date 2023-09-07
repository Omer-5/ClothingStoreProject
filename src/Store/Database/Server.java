package Store.Database;

import java.io.*;
import java.net.*;
import java.util.*;

import Store.Employees.Employee;
import Store.Employees.EmployeeTitle;
import Store.Inventories.InventoryItem;

public class Server {
    private static final int PORT = 7000;
    private static Map<Employee, SocketData> allClients = new HashMap<Employee, SocketData>();
    private static Map<Employee, ChatSession> allChats = new HashMap<Employee, ChatSession>();

    public static void main(String[] args) {
        System.out.println("--> Server is running...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Employee emp = new Employee("ישראל ישראלי", "0528921319", 123456789, 212444, "חולון", "1111", EmployeeTitle.CASHIER);
                new ClientHandler(serverSocket.accept(), emp).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                synchronized (allClients) {
                    allClients.put(emp, socketData);
                }

                String message;
                while ((message = socketData.getInputStream().readLine()) != null) {
                    System.out.println("השרת החזיר: ההודעה התתקבלה בהצלחה");
                    broadcast(message, socketData.getOutputStream()); // Broadcast the client's message to all clients
                    //respondToClient(out, "הודעה חדשה: " + message); // Respond to the client
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
                synchronized (allClients) {
                    allClients.remove(emp);
                }
            }
        }

        private void broadcast(String message, PrintWriter sender) {
            synchronized (allClients) {
                for (Map.Entry<Employee, SocketData> entry : allClients.entrySet()) {
                    SocketData socketData = entry.getValue();
                    if (socketData.getOutputStream() != sender) {
                        socketData.getOutputStream().println(message);
                    }
                }
            }
        }

        private void respondToClient(PrintWriter clientWriter, String response) {
            clientWriter.println(response);
        }
    }
}
