package Store.Customers;
import java.io.Serializable;
public class CustomerRegular extends Customer implements Serializable{
    private static final long serialVersionUID = 3L;
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
