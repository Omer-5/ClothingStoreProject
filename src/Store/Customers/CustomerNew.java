package Store.Customers;

public class CustomerNew extends Customer {
    public CustomerNew(String fullName, String phoneNumber, int id) {
        super(fullName, phoneNumber, id); 
        this.setDiscountPercentage("5");
    }
    
    // 5% discount for new customers
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice * 0.95;
    }
}
