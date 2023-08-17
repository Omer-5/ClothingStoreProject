package Store.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Store.Customers.Customer;
import Store.Customers.CustomerNew;
import Store.Customers.CustomerRegular;
import Store.Customers.CustomerVIP;

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
                double discount = res.getDouble("Discount");
                String type = res.getString("Type");

                Customer customer = createCustomerFromResultSet(fullName, phoneNumber, id, discount, type);
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

    private Customer createCustomerFromResultSet(String fullName, String phoneNumber, int id, double discount, String type) {
        switch (type) {
            case "CustomerNew":
                return new CustomerNew(fullName, phoneNumber, id, discount); // Ensure this constructor exists
            case "CustomerRegular":
                return new CustomerRegular(fullName, phoneNumber, id, discount); // Ensure this constructor exists
            case "CustomerVIP":
                return new CustomerVIP(fullName, phoneNumber, id, discount); // Ensure this constructor exists
            default:
                return null;
        }
    }
    private String queryForInsert(Customer customer) {
        String query = String.format("VALUES (N'%s', '%s', %d, %.2f, '%s')",
                customer.getFullName(),
                customer.getPhoneNumber(),
                customer.getId(),
                customer.getDiscount(),
                customer.getType());

        return query;
    }

    private String queryForUpdate(Customer customer) {
        String query = String.format("SET FullName=N'%s', PhoneNumber='%s', Discount=%.2f, Type='%s' WHERE ID=%d",
                customer.getFullName(),
                customer.getPhoneNumber(),
                customer.getDiscount(),
                customer.getType(),
                customer.getId());

        return query;
    }
}
