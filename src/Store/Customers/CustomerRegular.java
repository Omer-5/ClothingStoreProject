package Store.Customers;
import java.io.*;
public class CustomerRegular extends Customer implements Serializable{
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
        int idtest = 5;
        Customer customer = new CustomerRegular("Jane Smith", "67890", idtest);
        customer.serializeToFile("customerRegular.obj");
        Customer deserializedCustomer = Customer.deserializeFromFile("customerRegular.obj");
        System.out.println(deserializedCustomer);
    }
    */
}
