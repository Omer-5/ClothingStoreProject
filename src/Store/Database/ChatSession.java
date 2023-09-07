package Store.Database;

import java.util.ArrayList;
import java.util.*;

import Store.Employees.Employee;
import Store.Database.SocketData;

public class ChatSession extends Thread {
    private int sessionID;
    private Map<Employee, SocketData> allListeners;

    public ChatSession(int sessionID) {
        this.sessionID = sessionID;
        this.allListeners = new HashMap<Employee, SocketData>();
    }

    @Override
    public void run() {
        while(true) {
            
        }
    }
}
