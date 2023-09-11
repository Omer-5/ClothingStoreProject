package Store.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Store.Customers.Customer;
import Store.Customers.CustomerNew;
import Store.Customers.CustomerRegular;
import Store.Customers.CustomerVIP;
import Store.Server.Logger.Logger;

public class CustomerDAO extends GeneralDAO {
    public void createNewCustomer(Customer customer, String customerType) {
        insertObject("Customers", queryForInsert(customer, customerType));
        Logger.registerCustomer(customer);
    }

    public void updateCustomer(Customer customer, String customerType) {
        updateObject("Customers", queryForUpdate(customer, customerType));
    }

    public void deleteCustomer(int id) {
        deleteObject("Customers", "ID=" + id);
    }

    public Customer getCustomerByID(int id) throws SQLException{
        ResultSet res = getObject("Customers", "*", "ID = " + id);
        List<Customer> collection = resToCollection(res);
        if (collection.isEmpty())
            return null;
        return collection.get(0);
    }

    public List<Customer> getCustomersByType(String type) throws SQLException{
        ResultSet res = getObject("Customers", "*", "Type = '" + type + "'");
        return resToCollection(res);
    }

    public List<Customer> getCustomers() throws SQLException{
        ResultSet res = getObject("Customers", "*", "");
        return resToCollection(res);
    }

    private List<Customer> resToCollection(ResultSet res) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        while (res.next()) {
            String fullName = res.getString("FullName");
            String phoneNumber = res.getString("PhoneNumber");
            int id = res.getInt("ID");
            String type = res.getString("Type");

            Customer customer = createCustomerFromResultSet(fullName, phoneNumber, id, type);
            if (customer != null) {
                customers.add(customer);
            }
        }
        return customers;
    }

    private Customer createCustomerFromResultSet(String fullName, String phoneNumber, int id, String type) {
        switch (type) {
            case "New":
                return new CustomerNew(fullName, phoneNumber, id); 
            case "Regular":
                return new CustomerRegular(fullName, phoneNumber, id);
            case "VIP":
                return new CustomerVIP(fullName, phoneNumber, id); 
            default:
                return null;
        }
    }
    private String queryForInsert(Customer customer, String customerType) {
        String query = String.format("VALUES (N'%s', '%s', %d, '%s')",
                customer.getFullName(),
                customer.getPhoneNumber(),
                customer.getId(),
                customerType);

        return query;
    }

    private String queryForUpdate(Customer customer, String customerType) {
        String query = String.format("SET FullName=N'%s', PhoneNumber='%s', Type='%s' WHERE ID=%d",
                customer.getFullName(),
                customer.getPhoneNumber(),
                customerType,
                customer.getId());

        return query;
    }
}
