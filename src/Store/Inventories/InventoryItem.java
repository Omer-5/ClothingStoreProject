package Store.Inventories;

import Store.Client.ServerCommunication.Format;
import Store.Employees.Employee;
import Store.Employees.EmployeeTitle;

/**
 * Represents an item in the inventory with attributes like branch, product ID, name, category, quantity, and price.
 */
public class InventoryItem {
    /**
     * The branch where the item is located.
     */
    private String branch;

    /**
     * The unique product ID of the item.
     */
    private int productID;

    /**
     * The name of the item.
     */
    private String name;

    /**
     * The category to which the item belongs.
     */
    private String category;

    /**
     * The quantity of the item in stock.
     */
    private int quantity;

    /**
     * The price of the item.
     */
    private double price;

    /**
     * Constructs an inventory item with the specified details.
     *
     * @param branch    The branch where the item is located.
     * @param productID The unique product ID of the item.
     * @param name      The name of the item.
     * @param category  The category to which the item belongs.
     * @param quantity  The quantity of the item in stock.
     * @param price     The price of the item.
     */
    public InventoryItem(String branch, int productID, String name, String category, int quantity, double price) {
        this.branch = branch;
        this.productID = productID;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Constructs an inventory item with the specified details without a product ID.
     *
     * @param branch   The branch where the item is located.
     * @param name     The name of the item.
     * @param category The category to which the item belongs.
     * @param quantity The quantity of the item in stock.
     * @param price    The price of the item.
     */
    public InventoryItem(String branch, String name, String category, int quantity, double price) {
        this.branch = branch;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }
    /**
     * Retrieves the branch of this inventory item.
     *
     * @return The branch where the item is located.
     */
    public String getBranch() {
        return branch;
    }

    /**
     * Retrieves the product ID of this inventory item.
     *
     * @return The unique product ID of the item.
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Retrieves the name of this inventory item.
     *
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the category of this inventory item.
     *
     * @return The category to which the item belongs.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Retrieves the quantity of this inventory item in stock.
     *
     * @return The quantity of the item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Retrieves the price of this inventory item.
     *
     * @return The price of the item.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the branch for this inventory item.
     *
     * @param branch The branch to set.
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * Sets the product ID for this inventory item.
     *
     * @param productID The product ID to set.
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * Sets the name for this inventory item.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the category for this inventory item.
     *
     * @param category The category to set.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Sets the quantity for this inventory item.
     *
     * @param quantity The quantity to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Sets the price for this inventory item.
     *
     * @param price The price to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Provides a string representation of this inventory item.
     *
     * @return A string representation of the item.
     */
    public String toString() {
        return getBranch() + Format.fieldSeparator
                + getProductID() + Format.fieldSeparator
                + getName()+ Format.fieldSeparator
                + getCategory()+ Format.fieldSeparator
                + getQuantity()+ Format.fieldSeparator
                + getPrice();
    }

    /**
     * Serializes this inventory item to its string representation.
     *
     * @return A serialized string of the item.
     */
    public String serializeToString() {
        return this.toString();
    }

    /**
     * Creates an InventoryItem instance from its serialized string representation.
     *
     * @param serializedString The serialized string of the inventory item.
     * @return An instance of InventoryItem.
     */
    public static InventoryItem deserializeFromString(String serializedString) {
        String[] fields = serializedString.split(Format.fieldSeparator);
        String branch = fields[0];
        int productID = Integer.parseInt(fields[1]);
        String name = fields[2];
        String category = fields[3];
        int quantity = Integer.parseInt(fields[4]);
        double price = Double.parseDouble(fields[5]);
        return new InventoryItem(branch, productID, name, category, quantity, price);
    }

    // there is a main from GPT that checks the functions
    public static void main(String[] args) {
        // Create a new InventoryItem instance
        InventoryItem item = new InventoryItem("Store1", 101, "Laptop", "Electronics", 5, 999.99);

        // Serialize the item to string
        String serializedString = item.serializeToString();
        System.out.println("Serialized InventoryItem: " + serializedString);

        // Deserialize the string back to an InventoryItem
        InventoryItem deserializedItem = InventoryItem.deserializeFromString(serializedString);
        System.out.println("Deserialized InventoryItem: " + deserializedItem.toString());

        // Check if the original and deserialized items are equal
        System.out.println("Are the original and deserialized items equal? " + item.toString().equals(deserializedItem.toString()));
    }
}



/*
CREATE TABLE Inventory (
    branch nvarchar(255) NOT NULL,
    productID int IDENTITY(1,1) NOT NULL,
	name nvarchar(255) NOT NULL,
    category nvarchar(255) NOT NULL,
    quantity int NOT NULL,
    price float NOT NULL,
    PRIMARY KEY (productID)
);
 */
