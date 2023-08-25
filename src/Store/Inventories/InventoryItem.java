package Store.Inventories;

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
