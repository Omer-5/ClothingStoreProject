package Store.Database;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

import Store.Employees.Employee;
import Store.Database.SocketData;

public class ChatSession {
    private static int sessionCounter = 0;
    private int sessionID;
    private Map<SocketData, Employee> allListeners;

    public ChatSession() {
        this.allListeners = new HashMap<SocketData, Employee>();
        sessionID = sessionCounter++;
    }

    public int getSessionID() {
        return this.sessionID;
    }

    public Employee getEmployeeBySocketData(SocketData socketData) {
        return allListeners.get(socketData);
    }

    public SocketData getSocketDataByEmployee(Employee emp) {
        for( Map.Entry<SocketData, Employee> entry: allListeners.entrySet()) {
            if(emp.getId() == entry.getValue().getId())
                return entry.getKey();
        }

        return null;
    }

    public void addListener(SocketData socketData, Employee emp) {
        allListeners.put(socketData, emp);
    }

    public void removeListener(SocketData socketData) {
        allListeners.remove(socketData);
    }

    public void broadcast(String message, PrintWriter sender) {
        synchronized (allListeners) {
            for (Map.Entry<SocketData, Employee> entry : allListeners.entrySet()) {
                SocketData socketData = entry.getKey();
                if (socketData.getOutputStream() != sender) {
                    socketData.getOutputStream().println(message);
                }
            }
        }
    }

    public void respondToClient(PrintWriter clientWriter, String response) {
        clientWriter.println(response);
    }

    public void listen(Employee emp, SocketData socketData) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String message;
                    while ((message = socketData.getInputStream().readLine()) != null && !message.equals("")) {
                        //String[] messageSplit = message.split(": ");
                        //appendMessage(messageSplit[0], messageSplit[1], false);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
