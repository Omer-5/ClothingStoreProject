package Store.Server.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import Store.Customers.Customer;
import Store.Database.PropertiesHandler;
import Store.Employees.Employee;
import Store.PurchaseHistory.Purchase;

/**
 * A utility class responsible for logging various activities within the application.
 */
public class Logger {

    /**
     * The basic path for storing logs.
     */
    private static final String basicPath = "src/Store/Server/Logger/";

    /**
     * The complete path to the log file.
     */
    private static final String LOG_FILE_PATH = basicPath + "log.txt";

    /**
     * Configuration to decide whether to save chat conversations.
     */
    private static String saveChatConversation;

    /**
     * Handler for properties configuration.
     */
    private static PropertiesHandler propertiesHandler;

    /**
     * Initializes the logger with configurations.
     */
    public static void initLogger() {
        propertiesHandler = new PropertiesHandler(basicPath + "Preferences.properties");
        saveChatConversation = propertiesHandler.getProperty("SAVE_CHAT_CONVERSATIONS");
        System.out.println("Logger is live");
    }

    /**
     * Logs the registration of an employee.
     *
     * @param emp The registered employee.
     */
    public static void registerEmployee(Employee emp) {
        log("Employee Registered: " + emp.getId());
    }

    /**
     * Logs the registration of a customer.
     *
     * @param customer The registered customer.
     */
    public static void registerCustomer(Customer customer) {
        log("Customer Registered: " + customer.getId());
    }

    /**
     * Logs a purchase event.
     *
     * @param purchase The purchase instance.
     */
    public static void logPurchase(Purchase purchase) {
        log("Customer " + purchase.getCustomerID()+ " purchased " + purchase.getPurchaseID());
    }

     //TODO: handle chat
    /**
     * Handles the saving of chat conversations.
     *
     * @param fileName The file name for storing the conversation.
     * @param conversation The conversation content.
     */
    public static void saveChat(String fileName, String conversation) {
        if (saveChatConversation == "Yes") {
            // Save the entire chat to separate file

        }
        // String logMessage = "[" + LocalDateTime.now() + "] " + sender + " to " + receiver + ": " + message;
        // log(logMessage);
    }

    /**
     * Logs the initiation of a chat.
     */
    public static void logChatStarted() {
        log("Chat started ");
    }

    /**
     * Turns off the saving of chat conversations.
     */
    public static void turnOffSavingChat() {
        saveChatConversation = "No";
        propertiesHandler.setProperty(saveChatConversation, "No");
    }

    /**
     * Turns on the saving of chat conversations.
     */
    public static void turnOnSavingChat() {
        saveChatConversation = "No";
        propertiesHandler.setProperty(saveChatConversation, "No");
    }

    /**
     * Retrieves the current configuration for saving chat conversations.
     *
     * @return The status of saving chat conversations.
     */
    public static String getSavingChat() {
        return saveChatConversation;
    }

    /**
     * Logs a given message with a timestamp to the log file.
     *
     * @param message The message to be logged.
     */
    private static void log(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            writer.write("[" + LocalDateTime.now() + "] " + message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
