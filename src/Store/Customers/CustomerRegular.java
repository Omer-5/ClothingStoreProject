package Store.Customers;

import java.io.*;

public class CustomerRegular extends Customer implements Serializable {
    private static final long serialVersionUID = 4L;

    public CustomerRegular(String fullName, String phoneNumber, int id) {
        super(fullName, phoneNumber, id);
        this.setDiscountPercentage("10");
    }

    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice * 0.9;
    }

    @Override
    public String getType() {
        return "Regular";
    }

    @Override
    public String toString() {
        return super.getFullName() + "-" + super.getPhoneNumber() + "-" + super.getId();
    }

    public static void main(String[] args) {
        // Testing serialization and deserialization for the CustomerRegular class
        int idtest = 5;
        Customer customer = new CustomerRegular("Jane Smith", "67890", idtest);
        customer.serializeToFile("customerRegular.obj");

        Customer deserializedCustomer = Customer.deserializeFromFile("customerRegular.obj");
        if (deserializedCustomer instanceof CustomerRegular) {
            System.out.println("Deserialization successful for CustomerRegular!");
            System.out.println(deserializedCustomer);
        } else {
            System.out.println("Deserialization failed for CustomerRegular.");
        }
    }
}
