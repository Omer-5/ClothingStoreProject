package Store.PurchaseHistory;

import java.text.Normalizer.Form;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Store.Client.ServerCommunication.Format;
import Store.Inventories.InventoryItem;

/**
 * Represents a purchase made by a customer, detailing the items bought,
 * the date of purchase, the branch where the purchase was made, and more.
 */
public class Purchase {

    private int purchaseID;
    private int customerID;
    private LocalDateTime date;
    private String branch;

    private List<InventoryItem> purchasedItems;

    static DateTimeFormatter formatter_get = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Creates a new purchase instance with the given customer ID, date, branch, and purchased items.
     *
     * @param customerID The ID of the customer.
     * @param date Date and time of the purchase.
     * @param branch Branch where the purchase was made.
     * @param purchasedItems List of items that were purchased.
     */
    public Purchase(int customerID, LocalDateTime date, String branch, List<InventoryItem> purchasedItems) {
        this.customerID = customerID;
        this.date = date;
        this.branch = branch;
        this.purchasedItems = purchasedItems;
    }

    /**
     * Creates a new purchase instance with the given purchase ID, customer ID, date, and branch.
     *
     * @param purchaseID Unique identifier for the purchase.
     * @param customerID The ID of the customer.
     * @param date Date and time of the purchase.
     * @param branch Branch where the purchase was made.
     */
    public Purchase(int purchaseID, int customerID, LocalDateTime date, String branch) {
        this.purchaseID = purchaseID;
        this.customerID = customerID;
        this.date = date;
        this.branch = branch;
    }

    /**
     * Retrieves the purchase ID.
     *
     * @return The unique identifier for the purchase.
     */
    public int getPurchaseID() {
        return this.purchaseID;
    }

    /**
     * Retrieves the customer ID associated with the purchase.
     *
     * @return The ID of the customer.
     */
    public int getCustomerID() {
        return this.customerID;
    }

    /**
     * Retrieves the date and time of the purchase.
     *
     * @return The LocalDateTime of the purchase.
     */
    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * Retrieves the branch where the purchase was made.
     *
     * @return The name of the branch.
     */
    public String getBranch() {
        return this.branch;
    }

    /**
     * Sets the list of purchased items for this purchase.
     *
     * @param items The list of items to be set.
     */
    public void setPurchasedItems(ArrayList<InventoryItem> items) {
        this.purchasedItems = items;
    }

    /**
     * Retrieves the list of items included in the purchase.
     *
     * @return The list of purchased items.
     */
    public List<InventoryItem> getItems() {
        return this.purchasedItems;
    }

    /**
    * Returns a string representation of the a purchase.
    *
    * @return The string representation of the purchase.
    */
    @Override
    public String toString() {
        return  purchaseID + Format.fieldSeparator +
                customerID + Format.fieldSeparator + 
                date + Format.fieldSeparator + 
                branch;
    }

    // Type 1: createNewPurchase, Type 2: Import Purchases List<> for Reports
    public String toString(int type) {
        String response = customerID + Format.fieldSeparator + date + Format.fieldSeparator + branch;

        switch(type) {
            case 1:
                return response + Format.fieldSeparator + Format.encodeInventoryItems(purchasedItems);
            case 2:
                return purchaseID + Format.fieldSeparator + response;
        }

        return null;
    }

    /**
        * Serializes the purchase object to a string representation.
        *
        * @return The serialized string representation of the purchase.
        */
    public String serializeToString(int type) {
        return this.toString(type);
    }

    /**
        * Deserializes a string representation of an purchased item back to an PurchasedItem object.
        *
        * @param serializedString The serialized string representation of the purchased item.
        * @return The deserialized PurchasedItem object.
        */
    public static Purchase deserializeFromString(String serializedString, int type) {
        String[] fields = serializedString.split(Format.fieldSeparator);
        
        int customerID;
        LocalDateTime date;
        String branch;

        switch(type) { 
            case 1:
                customerID = Integer.parseInt(fields[0]);
                date = LocalDateTime.parse(fields[1], formatter_get);
                branch = fields[2];
                List<InventoryItem> purchasedItems = Format.decodeInventoryItems(fields[3]);

                return new Purchase(customerID, date, branch, purchasedItems);
            case 2: 
                int purchaseID = Integer.parseInt(fields[0]);
                customerID = Integer.parseInt(fields[1]);
                date = LocalDateTime.parse(fields[2], formatter_get);
                branch = fields[3];

                return new Purchase(purchaseID, customerID, date, branch);
        }

        return null;
    }
}

