package Store.Customers;

public class CustomerVIP extends Customer {
    public CustomerVIP(String fullName, String phoneNumber, int id, double discount) {
        super(fullName, phoneNumber, id, 0.15); // 15% discount for VIP customers
    }

    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice * (1 - getDiscount());
    }
}
