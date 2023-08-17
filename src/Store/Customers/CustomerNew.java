package Store.Customers;

public class CustomerNew extends Customer {
    public CustomerNew(String fullName, String phoneNumber, int id, double discount) {
        super(fullName, phoneNumber, id, 0.05); // 5% discount for new customers
    }

    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice * (1 - getDiscount());
    }
}
