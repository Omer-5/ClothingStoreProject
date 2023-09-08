package Store.Database;

import java.io.PrintWriter;
import java.util.*;

import Store.Employees.Employee;
import Store.Database.SocketData;

public class ChatSession extends Thread {
    private static int sessionCounter = 0;
    private int sessionID;
    private Map<Employee, SocketData> allListeners;

    public ChatSession() {
        this.allListeners = new HashMap<Employee, SocketData>();
        sessionID = sessionCounter++;
    }

    public int getSessionID() {
        return this.sessionID;
    }

    public SocketData getSocketDataByEmployee(Employee emp) {
        return allListeners.get(emp);
    }

    public void addListener(Employee emp, SocketData socketData) {
        allListeners.put(emp, socketData);
    }

    public void removeListener(Employee emp) {
        allListeners.remove(emp);
    }

    public void broadcast(String message, PrintWriter sender) {
        synchronized (allListeners) {
            for (Map.Entry<Employee, SocketData> entry : allListeners.entrySet()) {
                SocketData socketData = entry.getValue();
                if (socketData.getOutputStream() != sender) {
                    socketData.getOutputStream().println(message);
                }
            }
        }
    }

    public void respondToClient(PrintWriter clientWriter, String response) {
        clientWriter.println(response);
    }
}
