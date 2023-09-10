package Store.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.*;
import java.time.format.DateTimeFormatter;

import Store.Inventories.InventoryItem;
import Store.PurchaseHistory.*;
import Store.Server.Logger.Logger;

public class PurchaseHistoryDAO extends GeneralDAO {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    DateTimeFormatter formatter_get = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void createNewPurchase(Purchase purchase) {
        // Implementation of the insertObject method should be in GeneralDAO
        insertObject("PurchaseHistory", queryForInsert(purchase));  
        int orderID = -1;
        ResultSet rs = getObject("PurchaseHistory", "TOP 1 *", "CustomerID=" + purchase.getCustomerID() + " ORDER BY date DESC");
        try {
            rs.next();
            orderID = Integer.parseInt(rs.getString("PurchaseID"));
        } catch(SQLException e) {
            System.out.println(e);
        }

        List<InventoryItem> items = purchase.getItems();
        for( int i=0; i < items.size(); i++) {
            PurchasedItem item = new PurchasedItem(orderID, items.get(i).getProductID());
            insertObject("[PurchaseHistoryItems]", queryForInsertItems(item));
        }
        Logger.logPurchase(purchase);
    }

    private String queryForInsert(Purchase purchase) {
        String query = String.format("VALUES (%d, CONVERT(datetime, '%s', 103), N'%s')",
                purchase.getCustomerID(),
                formatter.format(purchase.getDate()),
                purchase.getBranch());
        return query;
    }

    private String queryForInsertItems(PurchasedItem item) {
        String query = String.format("VALUES (%d, %d)",
                item.getPurchaseID(),
                item.getProductID());

        return query;
    }

    // Additional helper method to convert ResultSet to InventoryItem objects
    public ArrayList<PurchasedItem> getItemsFromOrdersByBranchAndDays(String branch, int days) {
        ResultSet res;
        ArrayList<Purchase> orders;
        ArrayList<PurchasedItem> purchasedItems = new ArrayList<PurchasedItem>();
        
        if( days == 0 )
            res = getObject("PurchaseHistory", "*", "Branch = N'" + branch + "' AND cast(date as Date) = cast(getdate() as Date)"); 
        else
            res = getObject("PurchaseHistory", "*", "Branch = N'" + branch + "' AND ( cast(date as DATE) >= DATEADD(day, -" + days + ", GETDATE()) AND ( cast(date as DATE) <= GETDATE()))");
        
        orders = resToCollection(res);

        for(int i=0; i < orders.size(); i++) {
            ArrayList<PurchasedItem> temp = getItemsByPurchaseID(orders.get(i).getPurchaseID());
            for(int j=0; j < temp.size(); j++) 
                purchasedItems.add(temp.get(j));
        }

        return purchasedItems;
    }

    private ArrayList<Purchase> resToCollection(ResultSet res)
    {
        ArrayList<Purchase> resArray = new ArrayList<>();

        try {
            while(res.next())
            {
                int purchaseID = Integer.parseInt(res.getString("PurchaseID"));
                int customerID = Integer.parseInt(res.getString("CustomerID"));

                LocalDateTime date = LocalDateTime.parse(res.getString("Date"), formatter_get);

                String branch = res.getString("Branch");

                Purchase temp = new Purchase(purchaseID, customerID, date, branch); 
                resArray.add(temp);
            }
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println(e);
        }
        return resArray;
    }

    private ArrayList<PurchasedItem> getItemsByPurchaseID(int purchaseID) {
        ResultSet res = getObject("PurchaseHistoryItems", "*", "PurchaseID=" + purchaseID); 
        return resToItemsCollection(res);
    }

    private ArrayList<PurchasedItem> resToItemsCollection(ResultSet res)
    {
        ArrayList<PurchasedItem> resArray = new ArrayList<>();

        try {
            while(res.next())
            {
                int purchaseID = Integer.parseInt(res.getString("PurchaseID"));
                int productID = Integer.parseInt(res.getString("ProductID"));

                PurchasedItem temp = new PurchasedItem(purchaseID, productID); 
                resArray.add(temp);
            }
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println(e);
        }
        return resArray;
    }
}

