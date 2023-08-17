package Store.Customers;

import Store.Database.CustomerDAO;
import Store.Person;

public abstract class Customer extends Person {
    private double discount;

    public Customer(String fullName, String phoneNumber, int id, double discount) {
        super(fullName, phoneNumber, id);
        this.discount = discount;
    }

    public abstract double applyDiscount(double originalPrice);

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getType() {
        if (this instanceof CustomerNew) return "New";
        if (this instanceof CustomerRegular) return "Regular";

        return "VIP";

    }
}

