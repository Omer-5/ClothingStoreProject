package Store.Customers;
import java.io.Serializable;
public class CustomerVIP extends Customer implements Serializable {
    private static final long serialVersionUID = 4L;
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
