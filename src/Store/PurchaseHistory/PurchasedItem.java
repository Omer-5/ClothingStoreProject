package Store.PurchaseHistory;

import Store.Inventories.InventoryItem;

/**
 * Represents an item that has been purchased, detailing the purchase ID and the product ID.
 */
public class PurchasedItem {

    /**
     * Unique identifier for the purchase.
     */
    private int purchaseID;

    /**
     * Identifier of the purchased product.
     */
    private int productID;

    /**
     * Creates a new purchased item instance with the given purchase ID and product ID.
     *
     * @param purchaseID Unique identifier for the purchase.
     * @param productID Identifier of the purchased product.
     */
    public PurchasedItem(int purchaseID, int productID) {
        this.purchaseID = purchaseID;
        this.productID = productID;
    }

    /**
     * Retrieves the purchase ID associated with the purchased item.
     *
     * @return The unique identifier for the purchase.
     */
    public int getPurchaseID() {
        return purchaseID;
    }

    /**
     * Sets the purchase ID for this purchased item.
     *
     * @param purchaseID The unique identifier to be set for the purchase.
     */
    public void setPurchaseID(int purchaseID) {
        this.purchaseID = purchaseID;
    }

    /**
     * Retrieves the product ID of the purchased item.
     *
     * @return The identifier of the purchased product.
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Sets the product ID for this purchased item.
     *
     * @param productID The identifier to be set for the purchased product.
     */
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
