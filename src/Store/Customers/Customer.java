package Store.Customers;

import Store.Person;

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

    public abstract double applyDiscount(double originalPrice);

    public String getType() {
        if (this instanceof CustomerNew) return "New";
        if (this instanceof CustomerRegular) return "Regular";
        return "VIP";
    }
    private String generateSerializationString() {
        switch (getType()) {
            case "New":
                return this.getClass().getSimpleName() + "-" + this.toString();
            case "Regular":
                return this.getClass().getSimpleName() + "-" + this.toString();
            case "VIP":
                return this.getClass().getSimpleName() + "-" + this.toString();
            default:
                return "Unknown-" + this.toString();
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
            String[] parts = serializedString.split("-");
            String className = parts[0];
            String data = parts[1];

            switch (className) {
                case "CustomerNew":
                    // logic to create a CustomerNew object from data and return
                case "CustomerRegular":
                    // logic to create a CustomerRegular object from data and return
                case "CustomerVIP":
                    // logic to create a CustomerVIP object from data and return
                default:
                    throw new IllegalArgumentException("Unknown class type: " + className);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


}
