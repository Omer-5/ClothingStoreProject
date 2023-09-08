package Store.Inventories;

import Store.Client.ServerCommunication.Format;
import Store.Employees.Employee;
import Store.Employees.EmployeeTitle;

public class InventoryItem {
    private String branch;
    private int productID;
    private String name;
    private String category;
    private int quantity;
    private double price;

    public InventoryItem(String branch, int productID, String name, String category, int quantity, double price) {
        this.branch = branch;
        this.productID = productID;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public InventoryItem(String branch, String name, String category, int quantity, double price) {
        this.branch = branch;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public String getBranch() {
        return branch;
    }

    public int getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String toString() {
        return getBranch() + Format.fieldSeparator
                + getProductID() + Format.fieldSeparator
                + getName()+ Format.fieldSeparator
                + getCategory()+ Format.fieldSeparator
                + getQuantity()+ Format.fieldSeparator
                + getPrice();
    }
    public String serializeToString()
    {
        return this.toString();
    }

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
