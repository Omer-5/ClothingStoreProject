package Store.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Store.Customers.Customer;
import Store.Customers.CustomerNew;
import Store.Customers.CustomerRegular;
import Store.Customers.CustomerVIP;

// TODO: untested!
public class CustomerDAO extends GeneralDAO {

    public void createNewCustomer(Customer customer) {
        // Implementation of the insertObject method should be in GeneralDAO
        insertObject("Customers", queryForInsert(customer));
    }

    public void updateCustomer(Customer customer) {
        // Implementation of the updateObject method should be in GeneralDAO
        updateObject("Customers", queryForUpdate(customer));
    }

    public void deleteCustomer(int id) {
        // Implementation of the deleteObject method should be in GeneralDAO
        deleteObject("Customers", "ID=" + id);
    }

    public Customer getCustomerByID(int id) {
        // Implementation of the getObject method should be in GeneralDAO
        ResultSet res = getObject("Customers", "*", "ID = " + id);
        ArrayList<Customer> collection = resToCollection(res);
        if (collection.isEmpty())
            return null;
        return collection.get(0);
    }

    public ArrayList<Customer> getCustomersByType(String type) {
        // Implementation of the getObject method should be in GeneralDAO
        ResultSet res = getObject("Customers", "*", "Type = '" + type + "'");
        return resToCollection(res);
    }

    // Assuming that you have queryForInsert and queryForUpdate methods
    // that handle Customer objects in a way similar to Employee objects

    private ArrayList<Customer> resToCollection(ResultSet res) {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
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
        } catch (SQLException e) {
            // Handle exception
            e.printStackTrace();
        }
        return customers;
    }

    //TODO: ENUM?
    private Customer createCustomerFromResultSet(String fullName, String phoneNumber, int id, String type) {
        switch (type) {
            case "New":
                return new CustomerNew(fullName, phoneNumber, id); // Ensure this constructor exists
            case "Regular":
                return new CustomerRegular(fullName, phoneNumber, id); // Ensure this constructor exists
            case "VIP":
                return new CustomerVIP(fullName, phoneNumber, id); // Ensure this constructor exists
            default:
                return null;
        }
    }
    private String queryForInsert(Customer customer) {
        String query = String.format("VALUES (N'%s', '%s', %d, '%s')",
                customer.getFullName(),
                customer.getPhoneNumber(),
                customer.getId(),
                customer.getType());

        return query;
    }

    private String queryForUpdate(Customer customer) {
        String query = String.format("SET FullName=N'%s', PhoneNumber='%s', Type='%s' WHERE ID=%d",
                customer.getFullName(),
                customer.getPhoneNumber(),
                customer.getType(),
                customer.getId());

        return query;
    }
}
