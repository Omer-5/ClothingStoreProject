package Store.Customers;

import Store.Person;

import java.io.*;

public abstract class Customer extends Person implements Serializable {
    private static final long serialVersionUID = 2L;
    private String discountPercentage;
    public static String fieldSeparator = "**";

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
        return getFullName() + fieldSeparator + getPhoneNumber() + fieldSeparator + getId();
    }

    public abstract double applyDiscount(double originalPrice);

    public String getType() {
        if (this instanceof CustomerNew) return "New";
        if (this instanceof CustomerRegular) return "Regular";
        return "VIP";
    }

    private String generateSerializationString() {
        switch (getType()) {
            case "New":
                return this.getClass().getSimpleName() + fieldSeparator + this.toString();
            case "Regular":
                return this.getClass().getSimpleName() + fieldSeparator + this.toString();
            case "VIP":
                return this.getClass().getSimpleName() + fieldSeparator + this.toString();
            default:
                return "Unknown" + fieldSeparator + this.toString();
        }
    }

    // Serialization
    public void serializeToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(generateSerializationString());
            System.out.println("Serialization successful!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Deserialization
    public static Customer deserializeFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            String serializedString = (String) ois.readObject();
            String[] parts = serializedString.split(fieldSeparator);
            String className = parts[0];
            String data = parts[1];

            switch (className) {
                case "CustomerNew":
                    // Assuming that the data format is "fullName**phoneNumber**id"
                    String[] partsNew = data.split(fieldSeparator);
                    return new CustomerNew(partsNew[0], partsNew[1], Integer.parseInt(partsNew[2]));

                case "CustomerRegular":
                    String[] partsRegular = data.split(fieldSeparator);
                    return new CustomerRegular(partsRegular[0], partsRegular[1], Integer.parseInt(partsRegular[2]));

                case "CustomerVIP":
                    String[] partsVIP = data.split(fieldSeparator);
                    return new CustomerVIP(partsVIP[0], partsVIP[1], Integer.parseInt(partsVIP[2]));

                default:
                    throw new IllegalArgumentException("Unknown class type: " + className);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
