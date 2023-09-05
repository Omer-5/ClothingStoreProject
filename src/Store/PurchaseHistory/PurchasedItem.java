package Store.PurchaseHistory;

import Store.Inventories.InventoryItem;

public class PurchasedItem {
    private int purchaseID;
    private int productID;

    public PurchasedItem(int purchaseID, int productID) {
        this.purchaseID = purchaseID;
        this.productID = productID;
    }

    public int getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(int purchaseID) {
        this.purchaseID = purchaseID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
}

/*
CREATE TABLE PurchaseHistory(
    purchaseID int IDENTITY(1,1) NOT NULL,
    productID int NOT NULL,
    PRIMARY KEY (purchaseID)
);
 */
