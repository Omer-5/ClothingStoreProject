package Store.PurchaseHistory;

import java.time.*;
import java.util.ArrayList;
import Store.Inventories.InventoryItem;

/**
 * Represents a purchase made by a customer, detailing the items bought,
 * the date of purchase, the branch where the purchase was made, and more.
 */
public class Purchase {

    /**
     * Unique identifier for the purchase.
     */
    private int purchaseID;

    /**
     * Identifier of the customer making the purchase.
     */
    private int customerID;

    /**
     * Date and time when the purchase was made.
     */
    private LocalDateTime date;

    /**
     * Branch where the purchase was made.
     */
    private String branch;

    /**
     * List of items included in the purchase.
     */
    private ArrayList<InventoryItem> purchasedItems;

    /**
     * Creates a new purchase instance with the given customer ID, date, and branch.
     *
     * @param customerID The ID of the customer.
     * @param date Date and time of the purchase.
     * @param branch Branch where the purchase was made.
     */
    public Purchase(int customerID, LocalDateTime date, String branch) {
        this.customerID = customerID;
        this.date = date;
        this.branch = branch;
    }

    /**
     * Creates a new purchase instance with the given customer ID, date, branch, and purchased items.
     *
     * @param customerID The ID of the customer.
     * @param date Date and time of the purchase.
     * @param branch Branch where the purchase was made.
     * @param purchasedItems List of items that were purchased.
     */
    public Purchase(int customerID, LocalDateTime date, String branch, ArrayList<InventoryItem> purchasedItems) {
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
    public ArrayList<InventoryItem> getItems() {
        return this.purchasedItems;
    }
}

