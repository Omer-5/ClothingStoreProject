package Store.Inventories;

public class InventoryItem {
    int branchID;
    int productID;
    String name;
    int quantity;
    double price;
}

/*
CREATE TABLE Inventory (
    branchID int NOT NULL,
    productID int NOT NULL,
	name nvarchar(255) NOT NULL,
    quantity int NOT NULL,
    price float NOT NULL,
    PRIMARY KEY (productID)
);
 */
