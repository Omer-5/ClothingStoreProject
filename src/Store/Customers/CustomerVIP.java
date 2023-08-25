package Store.Customers;

public class CustomerVIP extends Customer {
    public CustomerVIP(String fullName, String phoneNumber, int id) {
        super(fullName, phoneNumber, id); 
        this.setDiscountPercentage("15");
    }
    
    // 15% discount for VIP customers
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice * 0.85;
    }
}
