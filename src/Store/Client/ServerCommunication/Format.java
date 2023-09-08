package Store.Client.ServerCommunication;

import java.util.ArrayList;
import java.util.List;

import Store.Customers.Customer;
import Store.Employees.Employee;
import Store.Inventories.InventoryItem;

// [TYPE] | [METHOD_NAME] | [PARAMETERS]
public class Format {
    //DON'T USE `%` format
    public static String typeSeparator = "@@@";
    public static String methodSeparator = "###";
    public static String paramsSeparator = "&&&";
    public static String objectSeparator = "~~~";
    public static String fieldSeparator = "!!!";

    public static String encode(ClassType type, String methodName){
        return type + typeSeparator + methodName + methodSeparator;
    }

    public static String encode(ClassType type, String methodName, String param){
        return encode(type, methodName) + param + paramsSeparator;
    }

    public static String encode(ClassType type, String methodName, String param1, String param2){
        return encode(type, methodName, param1) + param2 + paramsSeparator;
    }

    public static ClassType getType(String str)
    {
        String typeStr = str.split(typeSeparator)[0];
        ClassType result = null;
        switch (typeStr) {
            case "CHAT":
                result = ClassType.CHAT;
                break;
            case "CUSTOMER":
                result = ClassType.CUSTOMER;
                break;
            case "EMPLOYEE":
                result = ClassType.EMPLOYEE;
                break;
            case "EXCEPTION":
                result = ClassType.EXCEPTION;
                break;
            case "INVENTORY":
                result = ClassType.INVENTORY;
                break;
            case "PURCHASE_HISTORY":
                result = ClassType.PURCHASE_HISTORY;
                break;
            default:
                result = ClassType.OBJECT;
        }
        return result;
    }

    public static String getMethod(String str)
    {
        String temp = str.split(typeSeparator)[1];
        return temp.split(methodSeparator)[0];
    }

    public static String getFirstParam(String str)
    {
        String temp = str.split(paramsSeparator)[0];
        return temp.split(methodSeparator)[1];
    }

    public static String getSecondParam(String str)
    {
        String temp = str.split(paramsSeparator)[1];
        return temp.split(paramsSeparator)[0];
    }

    public static List<Customer> decodeCustomers(String str)
    {
        String[] objects = str.split(objectSeparator);
        List<Customer> arr = new ArrayList<>();

        for(String objectString: objects){
            arr.add(Customer.deserializeFromString(objectString));
        }
        return arr;
    } 

    public static String encodeCustomers(List<Customer> arr)
    {
        StringBuilder result = new StringBuilder();
        System.out.println(arr);
        for(Customer customer: arr){
            result.append(customer.serializeToString());
            result.append(objectSeparator);
        }
        System.out.println(result);
        return result.toString();
    } 

    public static List<Employee> decodeEmployees(String str)
    {
       String[] objects = str.split(objectSeparator);
       List<Employee> arr = new ArrayList<>();
    
       for(String objectString: objects){
           arr.add(Employee.deserializeFromString(objectString));
       }
       return arr;
    } 

   public static String encodeEmployees(List<Employee> arr)
    {
        StringBuilder result = new StringBuilder();
        System.out.println(arr);
        for(Employee Employee: arr){
            result.append(Employee.serializeToString());
            result.append(objectSeparator);
        }
        System.out.println(result);
        return result.toString();
    } 

    public static List<InventoryItem> decodeInventoryItems(String str)
    {
       String[] objects = str.split(objectSeparator);
       List<InventoryItem> arr = new ArrayList<>();
    
       for(String objectString: objects){
           arr.add(InventoryItem.deserializeFromString(objectString));
       }
       return arr;
    } 

   public static String encodeInventoryItems(List<InventoryItem> arr)
    {
        StringBuilder result = new StringBuilder();
        System.out.println(arr);
        for(InventoryItem InventoryItem: arr){
            result.append(InventoryItem.serializeToString());
            result.append(objectSeparator);
        }
        System.out.println(result);
        return result.toString();
    } 
}
