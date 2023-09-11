package Store.Customers;

/**
 * Represents a VIP customer with specific discount attributes.
 * VIP customers receive a 15% discount on purchases.
 */
public class CustomerVIP extends Customer {

    /**
     * Constructs a VIP customer with the specified details and sets the discount percentage to 15%.
     *
     * @param fullName     The full name of the VIP customer.
     * @param phoneNumber  The phone number of the VIP customer.
     * @param id           The unique identification number of the VIP customer.
     */
    public CustomerVIP(String fullName, String phoneNumber, int id) {
        super(fullName, phoneNumber, id);
        this.setDiscountPercentage("15");
    }

    /**
     * Applies a 15% discount to the original price and returns the discounted price.
     *
     * @param originalPrice  The original price before discount.
     * @return The discounted price after applying the 15% discount.
     */
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice * 0.85;
    }
}
