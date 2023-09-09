package Store.Server.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import Store.Customers.Customer;
import Store.Database.PropertiesHandler;
import Store.Employees.Employee;
import Store.PurchaseHistory.Purchase;

public class Logger {
    private static final String basicPath = "src/Store/Server/Logger/";
    private static final String LOG_FILE_PATH = basicPath + "log.txt";
    private static String saveChatConversation;
    private static PropertiesHandler propertiesHandler;
    
    public static void initLogger()
    {
        propertiesHandler = new PropertiesHandler(basicPath + "Preferences.properties"); 
        saveChatConversation = propertiesHandler.getProperty("SAVE_CHAT_CONVERSATIONS");
        System.out.println("Logger is live");
    }

    public static void registerEmployee(Employee emp) {
        log("Employee Registered: " + emp.getId());
    }

    public static void registerCustomer(Customer customer) {
        log("Customer Registered: " + customer.getId());
    }

    public static void logPurchase(Purchase purchase) {
        log("Customer " + purchase.getCustomerID()+ " purchased " + purchase.getPurchaseID());
    }

    public static void saveChat(String fileName, String conversation) {
        if (saveChatConversation == "Yes") {
            // Save the entire chat to separate file
        }

        // String logMessage = "[" + LocalDateTime.now() + "] " + sender + " to " + receiver + ": " + message;
        // log(logMessage);
    }

    public static void turnOffSavingChat()
    {
        saveChatConversation = "No";
        propertiesHandler.setProperty(saveChatConversation, "No");
    }

    public static void turnOnSavingChat()
    {
        saveChatConversation = "No";
        propertiesHandler.setProperty(saveChatConversation, "No");
    }

    public static String getSavingChat()
    {
        return saveChatConversation;
    }

    private static void log(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            writer.write("[" + LocalDateTime.now() + "] " + message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
