package Store.Customers;

import java.io.*;

public class CustomerNew extends Customer implements Serializable {
    public CustomerNew(String fullName, String phoneNumber, int id) {
        super(fullName, phoneNumber, id); 
        this.setDiscountPercentage("5");
    }
    
    // 5% discount for new customers
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice * 0.95;
    }

    public void serializeToFile(String filename, String DBCommand) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            String serializedData = "CustomerNew-" + DBCommand + "-" + getFullName() + "-" + getPhoneNumber() + "-" + getId() + "-" + getDiscountPercentage();
            oos.writeUTF(serializedData);
            System.out.println("Serialization successful! Serialized data: " + serializedData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CustomerNew deserializeFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            String serializedData = ois.readUTF();
            String[] parts = serializedData.split("-");
            if (!parts[0].equals("CustomerNew")) {
                throw new IOException("Data does not represent a CustomerNew object");
            }
            System.out.println("Deserialization successful! Extracted data: FullName: " + parts[2] + ", PhoneNumber: " + parts[3] + ", ID: " + parts[4] + ", DiscountPercentage: " + parts[5]);
            CustomerNew customer = new CustomerNew(parts[2], parts[3], Integer.parseInt(parts[4]));
            customer.setDiscountPercentage(parts[5]);
            return customer;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        CustomerNew customer = new CustomerNew("John Doe", "12345", 4);
        String filename = "customerNew.ser";

        // Serialize
        customer.serializeToFile(filename, "Update");

        // Deserialize
        CustomerNew deserializedCustomer = CustomerNew.deserializeFromFile(filename);
        System.out.println("Deserialized customer: " + deserializedCustomer.getFullName() + ", Discount: " + deserializedCustomer.getDiscountPercentage());
    }

}
