package Store.Customers;

public class CustomerRegular extends Customer {
    public CustomerRegular(String fullName, String phoneNumber, int id, double discount) {
        super(fullName, phoneNumber, id, 0.10); // 10% discount for regular customers
    }

    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice * (1 - getDiscount());
    }
}
