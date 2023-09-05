package Store.Client.ServerCommunication;

import java.util.ArrayList;

import Store.Customers.Customer;
import Store.Customers.CustomerNew;
import Store.Customers.CustomerRegular;
import Store.Customers.CustomerVIP;

public class EncodeCommandCustomer {
    public static String createNewCustomer(Customer customer, String customerType) {
        //TODO: change `generateSerializationString` name
        String res = Format.encode(ClassType.CUSTOMER, "createNewCustomer", customer.generateSerializationString(), customerType);
        // System.out.println(res);
        return res;
    }

    public void updateCustomer(Customer customer, String customerType) {
    }

    public void deleteCustomer(int id) {
    }

    // public Customer getCustomerByID(int id) {
    // }

    // public ArrayList<Customer> getCustomersByType(String type) {
    // }

    // public ArrayList<Customer> getCustomers() {
    // }
    
        // // For Testing
        // public static void main(String[] args) {
        //     int idtest = 4;
        //     Customer customer = new CustomerNew("John Doe", "12345", idtest);
        //     String command = EncodeCommandCustomer.createNewCustomer(customer, customer.getType());
        //     // System.out.println(command);
        //     DecodeExecuteCommand.decode_and_execute(command);     
        // }
}
