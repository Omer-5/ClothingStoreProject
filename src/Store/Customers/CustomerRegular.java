package Store.Customers;
import java.io.*;
public class CustomerRegular extends Customer implements Serializable{
    public CustomerRegular(String fullName, String phoneNumber, int id) {
        super(fullName, phoneNumber, id);
        this.setDiscountPercentage("10");
    }
    
    // 10% discount for regular customers
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice * 0.9;
    }

    public void serializeToFile(String filename, String DBCommand) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            String serializedData = "CustomerRegular-" + DBCommand + "-" + getFullName() + "-" + getPhoneNumber() + "-" + getId() + "-" + getDiscountPercentage();
            oos.writeUTF(serializedData);
            System.out.println("Serialization successful! Serialized data: " + serializedData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CustomerRegular deserializeFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            String serializedData = ois.readUTF();
            String[] parts = serializedData.split("-");
            if (!parts[0].equals("CustomerRegular")) {
                throw new IOException("Data does not represent a CustomerRegular object");
            }
            System.out.println("Deserialization successful! Extracted data: FullName: " + parts[2] + ", PhoneNumber: " + parts[3] + ", ID: " + parts[4] + ", DiscountPercentage: " + parts[5]);
            CustomerRegular customer = new CustomerRegular(parts[2], parts[3], Integer.parseInt(parts[4]));
            customer.setDiscountPercentage(parts[5]);
            return customer;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        CustomerRegular customer = new CustomerRegular("Jane Doe", "54321", 5);
        String filename = "customerRegular.ser";

        // Serialize
        customer.serializeToFile(filename, "Insert");

        // Deserialize
        CustomerRegular deserializedCustomer = CustomerRegular.deserializeFromFile(filename);
        System.out.println("Deserialized customer: " + deserializedCustomer.getFullName() + ", Discount: " + deserializedCustomer.getDiscountPercentage());
    }
}
