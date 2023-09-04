package Store.Customers;

import java.io.*;

public class CustomerNew extends Customer implements Serializable {
    private static final long serialVersionUID = 2L;

    public CustomerNew(String fullName, String phoneNumber, int id) {
        super(fullName, phoneNumber, id);
        this.setDiscountPercentage("5");
    }

    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice * 0.95;
    }

    @Override
    public String getType() {
        return "New";
    }

    @Override
    public String toString() {
        return super.getFullName() + "-" + super.getPhoneNumber() + "-" + super.getId();
    }

    public static void main(String[] args) {
        // Testing serialization and deserialization for the CustomerNew class
        int idtest = 4;
        Customer customer = new CustomerNew("John Doe", "12345", idtest);
        customer.serializeToFile("customerNew.obj");

        Customer deserializedCustomer = Customer.deserializeFromFile("customerNew.obj");
        if (deserializedCustomer instanceof CustomerNew) {
            System.out.println("Deserialization successful for CustomerNew!");
            System.out.println(deserializedCustomer);
        } else {
            System.out.println("Deserialization failed for CustomerNew.");
        }
    }
}