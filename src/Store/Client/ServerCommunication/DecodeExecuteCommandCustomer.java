package Store.Client.ServerCommunication;

import Store.Customers.Customer;
import Store.Database.CustomerDAO;

public class DecodeExecuteCommandCustomer {
    //TODO: return type??
    public static void execute(String command)
    {
        CustomerDAO DAO = new CustomerDAO();
        switch (Format.getMethod(command)) {
            case "createNewCustomer":
            // static String createNewCustomer(Customer customer, String customerType) {
                Customer temp = Customer.deserializeFromString(Format.getFirstParam(command));
                String customerType = Format.getSecondParam(command);
                DAO.createNewCustomer(temp, customerType);
                break;
        
            default:
                break;
        }
        
    }
}
