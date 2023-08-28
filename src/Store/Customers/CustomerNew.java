package Store.Customers;

import java.io.*;

public class CustomerNew extends Customer implements Serializable {
    private static final long serialVersionUID = 3L;
    public CustomerNew(String fullName, String phoneNumber, int id) {
        super(fullName, phoneNumber, id); 
        this.setDiscountPercentage("5");
    }
    
    // 5% discount for new customers
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice * 0.95;
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
        int idtest = 4;
        Customer customer = new CustomerNew("John Doe", "12345" , idtest);
        customer.serializeToFile("customer.obj");
        Customer deserializedCustomer = Customer.deserializeFromFile("customer.obj");
        System.out.println(deserializedCustomer);
    }
    */
}
