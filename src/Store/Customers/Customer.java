package Store.Customers;

import Store.Person;
import Store.Client.ServerCommunication.Format;

import java.io.*;

public abstract class Customer extends Person implements Serializable {
    private static final long serialVersionUID = 2L;
    private String discountPercentage;

    public Customer(String fullName, String phoneNumber, int id) {
        super(fullName, phoneNumber, id);
    }

    public String getDiscountPercentage() {
        return this.discountPercentage;
    }

    public void setDiscountPercentage(String percentage) {
        this.discountPercentage = percentage;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return getFullName() + Format.fieldSeparator + getPhoneNumber() + Format.fieldSeparator + getId();
    }

    public abstract double applyDiscount(double originalPrice);

    public String getType() {
        if (this instanceof CustomerNew) return "New";
        if (this instanceof CustomerRegular) return "Regular";
        return "VIP";
    }

    // Serialization
    public String serializeToString() {
        switch (this.getType()) {
            case "New":
                return this.getClass().getSimpleName() + Format.fieldSeparator + this.toString();
            case "Regular":
                return this.getClass().getSimpleName() + Format.fieldSeparator + this.toString();
            case "VIP":
                return this.getClass().getSimpleName() + Format.fieldSeparator + this.toString();
            default:
                return "Unknown" + Format.fieldSeparator + this.toString();
        }
    }

    // Deserialization
    public static Customer deserializeFromString(String objString) {
        String[] parts = objString.split(Format.fieldSeparator);
        String className = parts[0];
        String data = parts[1];
        String[] dataParts = data.split(Format.fieldSeparator);
        System.out.println(dataParts);

        switch (className) {
            case "CustomerNew":
                return new CustomerNew(parts[1], parts[2], Integer.parseInt(parts[3]));

            case "CustomerRegular":
                return new CustomerRegular(parts[1], parts[2], Integer.parseInt(parts[3]));

            case "CustomerVIP":
                return new CustomerVIP(parts[1], parts[2], Integer.parseInt(parts[3]));

            default:
                throw new IllegalArgumentException("Unknown class type: " + className);
        }

    }
}
