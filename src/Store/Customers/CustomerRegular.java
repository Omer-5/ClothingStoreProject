package Store.Customers;

import java.io.*;

public class CustomerRegular extends Customer implements Serializable {
    private static final long serialVersionUID = 4L;

    public CustomerRegular(String fullName, String phoneNumber, int id) {
        super(fullName, phoneNumber, id);
        this.setDiscountPercentage("10");
    }

    // 10% discount for regular customers
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice * 0.9;
    }

    @Override
    public String toString() {
        return "CustomerRegular" + fieldSeparator + getFullName() + fieldSeparator + getPhoneNumber() + fieldSeparator + getId();
    }

    public static void main(String[] args) {
        int idtest = 5;
        Customer customer = new CustomerRegular("Jane Doe", "67890", idtest);
        customer.serializeToFile("customerRegular.obj");
        Customer deserializedCustomer = Customer.deserializeFromFile("customerRegular.obj");
        System.out.println(deserializedCustomer);
    }
}
