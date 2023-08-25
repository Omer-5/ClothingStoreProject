package Store.Customers;

public class CustomerRegular extends Customer {
    public CustomerRegular(String fullName, String phoneNumber, int id) {
        super(fullName, phoneNumber, id);
        this.setDiscountPercentage("10");
    }
    
    // 10% discount for regular customers
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice * 0.9;
    }
}
