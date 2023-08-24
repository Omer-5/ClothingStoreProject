package Store.PurchaseHistory;

public class PurchaseHistory {
    private int purchaseID;
    private String branch;
    private int productID;
    private String name;
    private String category;
    private int quantity;
    private double price;

    public PurchaseHistory(int purchaseID, String branch, int productID, String name, String category, int quantity, double price) {
        this.purchaseID = purchaseID;
        this.branch = branch;
        this.productID = productID;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public int getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(int purchaseID) {
        this.purchaseID = purchaseID;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

/*
CREATE TABLE PurchaseHistory (
    purchaseID int IDENTITY(1,1) NOT NULL,
    branch nvarchar(255) NOT NULL,
    productID int IDENTITY(1,1) NOT NULL,
	name nvarchar(255) NOT NULL,
    category nvarchar(255) NOT NULL,
    quantity int NOT NULL,
    price float NOT NULL,
    PRIMARY KEY (productID)
);
 */
