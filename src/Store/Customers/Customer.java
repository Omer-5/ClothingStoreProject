package Store.Customers;

import Store.Person;

import java.io.*;
public abstract class Customer extends Person implements Serializable {
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

    public abstract double applyDiscount(double originalPrice);

    public String getType() {
        if (this instanceof CustomerNew) return "New";
        if (this instanceof CustomerRegular) return "Regular";

        return "VIP";
    }

    // Serialization
    public void serializeToFile(String filename, String DBCommand) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            String serializedData = "Customer-" + DBCommand + "-" + getFullName() + "-" + getPhoneNumber() + "-" + getId() + "-" + discountPercentage;
            oos.writeUTF(serializedData);
            System.out.println("Serialization successful! Serialized data: " + serializedData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Customer deserializeFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            String serializedData = ois.readUTF();
            String[] parts = serializedData.split("-");
            if (!parts[0].equals("Customer")) {
                throw new IOException("Data does not represent a Customer object");
            }
            System.out.println("Deserialization successful! Extracted data: FullName: " + parts[2] + ", PhoneNumber: " + parts[3] + ", ID: " + parts[4] + ", DiscountPercentage: " + parts[5]);
            // We are returning a basic Customer object here. In a real-world scenario, you'd probably create a specific customer type (New/Regular/VIP) based on some criteria.
            Customer customer = new Customer(parts[2], parts[3], Integer.parseInt(parts[4])) {
                @Override
                public double applyDiscount(double originalPrice) {
                    return originalPrice; // This should be replaced by the appropriate logic for each customer type.
                }
            };
            customer.setDiscountPercentage(parts[5]);
            return customer;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Customer customer = new Customer("Jane Smith", "67890", 2) {
            @Override
            public double applyDiscount(double originalPrice) {
                return originalPrice * 0.9;
            }
        };
        String filename = "customer.ser";

        // Serialize
        customer.serializeToFile(filename, "Update");

        // Deserialize
        Customer deserializedCustomer = Customer.deserializeFromFile(filename);
        System.out.println("Deserialized customer: " + deserializedCustomer.getFullName() + ", Discount: " + deserializedCustomer.getDiscountPercentage());
    }
}

/*
 CREATE TABLE Customers (
    FullName nvarchar(255) NOT NULL,
	PhoneNumber nvarchar(255) NOT NULL,
    ID int NOT NULL,
    Type nvarchar(255) NOT NULL,
    PRIMARY KEY (ID)
);
 */