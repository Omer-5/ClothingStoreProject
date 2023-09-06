package Store.Customers;

public class CustomerNew extends Customer {
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

    // public static void main(String[] args) {
    //     int idtest = 4;
    //     // Customer customer = new CustomerNew("John Doe", "12345", idtest);
    //     // customer.serializeToFile("customerNew.obj");
    //     // Customer deserializedCustomer = Customer.deserializeFromFile("customerNew.obj");
    //     // System.out.println(deserializedCustomer);
    // }
}
