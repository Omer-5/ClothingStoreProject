package Store.Client.ServerCommunication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Store.Customers.Customer;
import Store.Database.ChatSession;
import Store.Employees.Employee;
import Store.Inventories.InventoryItem;
import Store.PurchaseHistory.PurchasedItem;

// [TYPE] | [METHOD_NAME] | [PARAMETERS]
public class Format {
    //DON'T USE `%` format
    public static String typeSeparator = "@@@";
    public static String methodSeparator = "###";
    public static String paramsSeparator = "&&&";
    public static String objectSeparator = "~~~";
    public static String fieldSeparator = "!!!";

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static DateTimeFormatter formatter_get = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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
            case "SUCCESS":
                result = ClassType.SUCCESS;
                break;
            case "EMPTY":
                result = ClassType.EMPTY;
                break;
            case "LOGGER":
                result = ClassType.LOGGER;
                break;
            default:
                result = ClassType.OBJECT;
                break;
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

    public static String encodeException(String message)
    {
        return encode(ClassType.EXCEPTION, "", message);
    }

    public static String encodeEmpty(String optionalString)
    {
        return encode(ClassType.EMPTY, "", "");
    }

    public static String encodeSuccessMessage(){
        return encode(ClassType.SUCCESS, "", "הפעולה בוצעה בהצלחה!");
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

    public static LocalDateTime stringToDate(String str)
    {
        return LocalDateTime.parse(str, formatter);
    }

    public static String dateToString(LocalDateTime date)
    {
        return formatter.format(date);

    }

    public static LocalDateTime stringToDateDB(String str)
    {
        return LocalDateTime.parse(str, formatter_get);
    }

    public static String encodeCustomers(List<Customer> arr)
    {
        StringBuilder result = new StringBuilder();
        for(Customer customer: arr){
            result.append(customer.serializeToString());
            result.append(objectSeparator);
        }
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
        for(Employee Employee: arr){
            result.append(Employee.serializeToString());
            result.append(objectSeparator);
        }
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
        for(InventoryItem InventoryItem: arr){
            result.append(InventoryItem.serializeToString());
            result.append(objectSeparator);
        }
        return result.toString();
    } 

    public static List<PurchasedItem> decodePurchasedItems(String str)
    {
       String[] objects = str.split(objectSeparator);
       List<PurchasedItem> arr = new ArrayList<>();
    
       for(String objectString: objects){
           arr.add(PurchasedItem.deserializeFromString(objectString));
       }
       return arr;
    } 

   public static String encodePurchasedItems(List<PurchasedItem> arr)
    {
        StringBuilder result = new StringBuilder();
        for(PurchasedItem PurchasedItem: arr){
            result.append(PurchasedItem.serializeToString());
            result.append(objectSeparator);
        }
        return result.toString();
    } 

    public static Set<String> decodeAvailableBranches(String str) {
        String[] objects = str.split(objectSeparator);
        Set<String> branches = new HashSet<>();
        
        for(String objectString: objects){
           branches.add(objectString);
       }
       return branches;
    }

    public static String encodeAvailableBranches(Set<String> branches) {
        StringBuilder result = new StringBuilder();

        for (String branch : branches) {
            result.append(branch);
            result.append(objectSeparator);
        }
        return result.toString();
    }

    public static List<Object[]> decodeAvailableChats(String str) {
        String[] objects = str.split(objectSeparator);
        List<Object[]> tableLines = new ArrayList<>();
        
        for(String objectString: objects){
           String[] fields = objectString.split(fieldSeparator);
           Object[] object = {fields[0], fields[1], fields[2], fields[3] };

           tableLines.add(object);
       }
       return tableLines;
    }

    public static String encodeAvailableChats(Set<ChatSession> chats) {
        StringBuilder result = new StringBuilder();

        for (ChatSession chat : chats) {
            result.append(chat.getSessionID());
            result.append(fieldSeparator);
            result.append(chat.getReceiverEmployee().getBranch());
            result.append(fieldSeparator);
            result.append(chat.getReceiverEmployee().getFullName());
            result.append(fieldSeparator);
            result.append(chat.getCreatorEmployee().getFullName());
            result.append(objectSeparator);
        }
        return result.toString();
    }
}
