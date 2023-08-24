package Store.Inventories;

public class InventoryItem {
    private String branch;
    private int productID;
    private String name;
    private String category;
    private int quantity;
    private double price;
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
