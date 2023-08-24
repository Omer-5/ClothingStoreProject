package Store.Inventories;

public class InventoryItem {
    String branch;
    int productID;
    String name;
    int quantity;
    double price;
}

/*
CREATE TABLE Inventory (
    branch nvarchar(255) NOT NULL,
    productID int IDENTITY(1,1) NOT NULL,
	name nvarchar(255) NOT NULL,
    quantity int NOT NULL,
    price float NOT NULL,
    PRIMARY KEY (productID)
);
 */
