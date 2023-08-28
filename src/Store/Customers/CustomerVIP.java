package Store.Customers;
import java.io.*;
public class CustomerVIP extends Customer implements Serializable {
    private static final long serialVersionUID = 5L;
    public CustomerVIP(String fullName, String phoneNumber, int id) {
        super(fullName, phoneNumber, id); 
        this.setDiscountPercentage("15");
    }
    
    // 15% discount for VIP customers
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice * 0.85;
    }
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        // add any additional processing if needed
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        // add any additional processing if needed
    }

    //TEST serialization and deserialization
    /*
    public static void main(String[] args) {
        int idtest = 6;
        Customer customer = new CustomerVIP("Alice Johnson", "112233", idtest);
        customer.serializeToFile("customerVIP.obj");
        Customer deserializedCustomer = Customer.deserializeFromFile("customerVIP.obj");
        System.out.println(deserializedCustomer);
    }
    */
}
