package Store.Customers;

/**
 * Represents a new customer with specific discount attributes.
 * New customers receive a 5% discount on purchases.
 */
public class CustomerNew extends Customer {

    /**
     * Constructs a new customer with the specified details and sets the discount percentage to 5%.
     *
     * @param fullName     The full name of the new customer.
     * @param phoneNumber  The phone number of the new customer.
     * @param id           The unique identification number of the new customer.
     */
    public CustomerNew(String fullName, String phoneNumber, int id) {
        super(fullName, phoneNumber, id);
        this.setDiscountPercentage("5");
    }

    /**
     * Applies a 5% discount to the original price and returns the discounted price.
     *
     * @param originalPrice  The original price before discount.
     * @return The discounted price after applying the 5% discount.
     */
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice * 0.95;
    }

    // public static void main(String[] args) {
    //     int idtest = 4;
    //     // Customer customer = new CustomerNew("John Doe", "12345", idtest);
    //     // customer.serializeToFile("customerNew.obj");
    //     // Customer deserializedCustomer = Customer.deserializeFromFile("customerNew.obj");
    //     // System.out.println(deserializedCustomer);
    // }
}
