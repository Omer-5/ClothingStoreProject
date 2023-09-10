package Store.PurchaseHistory;

import java.time.LocalDateTime;

import Store.Client.ServerCommunication.Format;
import Store.Employees.Employee;
import Store.Employees.EmployeeTitle;
import Store.Inventories.InventoryItem;

/**
 * Represents an item that has been purchased, detailing the purchase ID and the product ID.
 */
public class PurchasedItem {


    private int purchaseID;
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

    /**
    * Returns a string representation of the purchased items.
    *
    * @return The string representation of the purchase item.
    */

    @Override
    public String toString() {
        return  purchaseID + Format.fieldSeparator + productID;
    }

    /**
        * Serializes the purchased item object to a string representation.
        *
        * @return The serialized string representation of the purchased item.
        */
    public String serializeToString() {
        return this.toString();
    }

    /**
        * Deserializes a string representation of an purchased item back to an PurchasedItem object.
        *
        * @param serializedString The serialized string representation of the purchased item.
        * @return The deserialized PurchasedItem object.
        */
    public static PurchasedItem deserializeFromString(String serializedString) {
        String[] fields = serializedString.split(Format.fieldSeparator);
        int purchaseID = Integer.parseInt(fields[0]);
        int productID = Integer.parseInt(fields[1]);

        return new PurchasedItem(purchaseID, productID);
    }
}


/*
CREATE TABLE PurchaseHistory(
    purchaseID int IDENTITY(1,1) NOT NULL,
    productID int NOT NULL,
    PRIMARY KEY (purchaseID)
);
 */
