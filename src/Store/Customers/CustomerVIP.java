package Store.Customers;
import java.io.*;
public class CustomerVIP extends Customer implements Serializable {
    public CustomerVIP(String fullName, String phoneNumber, int id) {
        super(fullName, phoneNumber, id); 
        this.setDiscountPercentage("15");
    }
    
    // 15% discount for VIP customers
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice * 0.85;
    }
    public void serializeToFile(String filename, String DBCommand) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            String serializedData = "CustomerVIP-" + DBCommand + "-" + getFullName() + "-" + getPhoneNumber() + "-" + getId() + "-" + getDiscountPercentage();
            oos.writeUTF(serializedData);
            System.out.println("Serialization successful! Serialized data: " + serializedData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CustomerVIP deserializeFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            String serializedData = ois.readUTF();
            String[] parts = serializedData.split("-");
            if (!parts[0].equals("CustomerVIP")) {
                throw new IOException("Data does not represent a CustomerVIP object");
            }
            System.out.println("Deserialization successful! Extracted data: FullName: " + parts[2] + ", PhoneNumber: " + parts[3] + ", ID: " + parts[4] + ", DiscountPercentage: " + parts[5]);
            CustomerVIP customer = new CustomerVIP(parts[2], parts[3], Integer.parseInt(parts[4]));
            customer.setDiscountPercentage(parts[5]);
            return customer;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        CustomerVIP customer = new CustomerVIP("Robert Smith", "12345678", 6);
        String filename = "customerVIP.ser";

        // Serialize
        customer.serializeToFile(filename, "Insert");

        // Deserialize
        CustomerVIP deserializedCustomer = CustomerVIP.deserializeFromFile(filename);
        System.out.println("Deserialized customer: " + deserializedCustomer.getFullName() + ", Discount: " + deserializedCustomer.getDiscountPercentage());
    }

}
