package Store.Client.ServerCommunication;

import Store.Employees.Employee;

public class EncodeCommandChat {
    public static String clientOnline(Employee emp) {
        String res = Format.encode(ClassType.CHAT, "clientOnline", emp.serializeToString());
        return res;
    }
    public static String clientOffline(Employee emp) {
        String res = Format.encode(ClassType.CHAT, "clientOffline", emp.serializeToString());
        return res;
    }
    public static String sendMessage(Employee emp, String message) {
        String res = Format.encode(ClassType.CHAT, "sendMessage", emp.serializeToString(), message);
        return res;
    }
    public static String getAvailableBranches(String branch) {
        String res = Format.encode(ClassType.CHAT, "getAvailableBranches", branch);
        return res;
    }
}
