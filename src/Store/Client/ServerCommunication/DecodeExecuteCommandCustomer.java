package Store.Client.ServerCommunication;

import java.sql.SQLException;

import Store.Customers.Customer;
import Store.Database.CustomerDAO;

public class DecodeExecuteCommandCustomer {
    public static String execute(String command) throws SQLException {
        CustomerDAO DAO = new CustomerDAO();
        String response = Format.encodeSuccessMessage();
        switch (Format.getMethod(command)) {
            case "createNewCustomer":
            // static String createNewCustomer(Customer customer, String customerType) {
                Customer temp = Customer.deserializeFromString(Format.getFirstParam(command));
                String customerType = Format.getSecondParam(command);
                DAO.createNewCustomer(temp, customerType);
                break;        
            case "getCustomerByID":
                // public Customer getCustomerByID(int id) {
                int id = Integer.parseInt(Format.getFirstParam(command));
                response = DAO.getCustomerByID(id).serializeToString();
                break;
            case "updateCustomer":
                // public void updateCustomer(Customer customer, String customerType) {
                Customer customer = Customer.deserializeFromString(Format.getFirstParam(command));
                DAO.updateCustomer(customer, Format.getSecondParam(command));
                break;
            case "deleteCustomer":
                // public void deleteCustomer(int id) {
                DAO.deleteCustomer(Integer.parseInt(Format.getFirstParam(command)));
                break;
            case "getCustomers":
                //     public ArrayList<Customer> getCustomers() {
                response = Format.encodeCustomers(DAO.getCustomers());
                break;
            case "getCustomersByType":
                // public ArrayList<Customer> getCustomersByType(String type) {
                response = Format.encodeCustomers(DAO.getCustomersByType(Format.getFirstParam(command)));
                break;
            }
        return response;
    }
}