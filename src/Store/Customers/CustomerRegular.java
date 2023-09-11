package Store.Customers;

/**
 * Represents a regular customer with specific discount attributes.
 * Regular customers receive a 10% discount on purchases.
 */
public class CustomerRegular extends Customer {


    /**
     * Constructs a regular customer with the specified details and sets the discount percentage to 10%.
     *
     * @param fullName     The full name of the regular customer.
     * @param phoneNumber  The phone number of the regular customer.
     * @param id           The unique identification number of the regular customer.
     */
    public CustomerRegular(String fullName, String phoneNumber, int id) {
        super(fullName, phoneNumber, id);
        this.setDiscountPercentage("10");
    }

    /**
     * Applies a 10% discount to the original price and returns the discounted price.
     *
     * @param originalPrice  The original price before discount.
     * @return The discounted price after applying the 10% discount.
     */
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice * 0.9;
    }
}
