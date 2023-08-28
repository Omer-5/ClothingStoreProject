package Store.Customers;

import java.io.Serializable;
public class CustomerNew extends Customer implements Serializable {
    private static final long serialVersionUID = 2L;
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
