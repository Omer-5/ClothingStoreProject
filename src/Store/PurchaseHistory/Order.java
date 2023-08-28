package Store.PurchaseHistory;

import java.util.Date;

import Store.Inventories.InventoryItem;

import java.time.*;
import java.util.ArrayList;

public class Order {
    private int purchaseID;
    private int customerID;
    private LocalDateTime date; 
    private String branch;

    private ArrayList<InventoryItem> purchasedItems;

    public Order(int customerID, LocalDateTime date, String branch) {
        this.customerID = customerID;
        this.date = date;
        this.branch = branch;
    }

    public Order(int customerID, LocalDateTime date, String branch, ArrayList<InventoryItem> purchasedItems) {
        this.customerID = customerID;
        this.date = date;
        this.branch = branch;
        this.purchasedItems = purchasedItems;
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public String getBranch() {
        return this.branch;
    }

    public void setPurchasedItems( ArrayList<InventoryItem> items) {
        this.purchasedItems = purchasedItems;
    }

    public ArrayList<InventoryItem> getItems() {
        return this.purchasedItems;
    }
}

