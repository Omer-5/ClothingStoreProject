package Store.Customers;

import Store.Person;
import Store.Client.ServerCommunication.Format;

/**
 * Represents a generic customer with associated discount attributes.
 * This class serves as a base class for various types of customers in the store system.
 */
public abstract class Customer extends Person {

    /**
     * The discount percentage applicable for the customer.
     */
    private String discountPercentage;

    /**
     * Constructs a new customer with the specified details.
     *
     * @param fullName     The full name of the customer.
     * @param phoneNumber  The phone number of the customer.
     * @param id           The unique identification number of the customer.
     */
    public Customer(String fullName, String phoneNumber, int id) {
        super(fullName, phoneNumber, id);
    }

    /**
     * Returns the discount percentage of the customer.
     *
     * @return The discount percentage.
     */
    public String getDiscountPercentage() {
        return this.discountPercentage;
    }

    /**
     * Sets the discount percentage for the customer.
     *
     * @param percentage  The discount percentage to be set.
     */
    public void setDiscountPercentage(String percentage) {
        this.discountPercentage = percentage;
    }

    /**
     * Applies the customer's discount to the original price and returns the discounted price.
     *
     * @param originalPrice  The original price before discount.
     * @return The discounted price after applying the customer's discount.
     */
    public abstract double applyDiscount(double originalPrice);

    /**
     * Determines and returns the type of the customer.
     * The type can be "New", "Regular", or "VIP".
     *
     * @return The type of the customer.
     */
    public String getType() {
        if (this instanceof CustomerNew) return "New";
        if (this instanceof CustomerRegular) return "Regular";
        return "VIP";
    }

    /**
     * Serializes the customer object to a string representation.
     *
     * @return The serialized string representation of the customer.
     */
    public String serializeToString() {
        switch (this.getType()) {
            case "New":
                return this.getClass().getSimpleName() + Format.fieldSeparator + this.toString();
            case "Regular":
                return this.getClass().getSimpleName() + Format.fieldSeparator + this.toString();
            case "VIP":
                return this.getClass().getSimpleName() + Format.fieldSeparator + this.toString();
            default:
                return "Unknown" + Format.fieldSeparator + this.toString();
        }
    }

    /**
     * Deserializes a string representation of a customer back to a Customer object.
     *
     * @param objString  The serialized string representation of the customer.
     * @return The deserialized Customer object.
     * @throws IllegalArgumentException if the class type in the string is unknown.
     */
    public static Customer deserializeFromString(String objString) {
        String[] parts = objString.split(Format.fieldSeparator);
        String className = parts[0];

        switch (className) {
            case "CustomerNew":
                return new CustomerNew(parts[1], parts[2], Integer.parseInt(parts[3]));

            case "CustomerRegular":
                return new CustomerRegular(parts[1], parts[2], Integer.parseInt(parts[3]));

            case "CustomerVIP":
                return new CustomerVIP(parts[1], parts[2], Integer.parseInt(parts[3]));

            default:
                throw new IllegalArgumentException("Unknown class type: " + className);
        }
    }
}

/*
 CREATE TABLE Customers (
    FullName nvarchar(255) NOT NULL,
	PhoneNumber nvarchar(255) NOT NULL,
    ID int NOT NULL,
    Type nvarchar(255) NOT NULL,
    PRIMARY KEY (ID)
);
 */
