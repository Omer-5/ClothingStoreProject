package Store.Client.ServerCommunication;

import java.text.Normalizer.Form;
import java.util.ArrayList;

import Store.Customers.Customer;
import Store.Customers.CustomerNew;
import Store.Customers.CustomerRegular;
import Store.Customers.CustomerVIP;
import Store.Employees.Employee;

public class EncodeCommandChat {
    public static String sendMessage(Employee emp, String message) {
        String res = Format.encode(ClassType.CHAT, "sendMessage", emp.serializeToString(), message);
        return res;
    }
    public static String getAvailableBranches() {
        String res = Format.encode(ClassType.CHAT, "getAvailableBranches");
        return res;
    }
}
