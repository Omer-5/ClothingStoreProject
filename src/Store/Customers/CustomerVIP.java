package Store.Customers;

import java.io.*;

public class CustomerVIP extends Customer implements Serializable {
    private static final long serialVersionUID = 5L;

    public CustomerVIP(String fullName, String phoneNumber, int id) {
        super(fullName, phoneNumber, id);
        this.setDiscountPercentage("15");
    }

    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice * 0.85;
    }

    @Override
    public String getType() {
        return "VIP";
    }

    @Override
    public String toString() {
        return super.getFullName() + "-" + super.getPhoneNumber() + "-" + super.getId();
    }

    public static void main(String[] args) {
        // Testing serialization and deserialization for the CustomerVIP class
        int idtest = 6;
        Customer customer = new CustomerVIP("Alice Cooper", "54321", idtest);
        customer.serializeToFile("customerVIP.obj");

        Customer deserializedCustomer = Customer.deserializeFromFile("customerVIP.obj");
        if (deserializedCustomer instanceof CustomerVIP) {
            System.out.println("Deserialization successful for CustomerVIP!");
            System.out.println(deserializedCustomer);
        } else {
            System.out.println("Deserialization failed for CustomerVIP.");
        }
    }
}
