package Store.PurchaseHistory;

import Store.Inventories.InventoryItem;

public class PurchasedItem {
    private int purchaseID;
    private InventoryItem item;

    public PurchasedItem(int purchaseID, InventoryItem item) {
        this.purchaseID = purchaseID;
        this.item = item;
    }

    public int getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(int purchaseID) {
        this.purchaseID = purchaseID;
    }

    public InventoryItem getItem() {
        return item;
    }

    public void setItem(InventoryItem item) {
        this.item = item;
    }
}

/*
CREATE TABLE PurchaseHistory(
    purchaseID int IDENTITY(1,1) NOT NULL,
    productID int NOT NULL,
    PRIMARY KEY (purchaseID)
);
 */
