package Store.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList; 
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import Store.Inventories.InventoryItem;
import Store.PurchaseHistory.*;

public class PurchaseHistoryDAO extends GeneralDAO {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public void createNewPurchase(Order order) {
        // Implementation of the insertObject method should be in GeneralDAO
        insertObject("PurchaseHistory", queryForInsert(order));  
        int orderID = -1;
        ResultSet rs = getObject("PurchaseHistory", "TOP 1 *", "CustomerID=" + order.getCustomerID() + " ORDER BY date DESC");
        try {
            rs.next();
            orderID = Integer.parseInt(rs.getString("PurchaseID"));
        } catch(SQLException e) {
            System.out.println(e);
        }

        ArrayList<InventoryItem> items = order.getItems();
        for( int i=0; i < items.size(); i++) {
            PurchasedItem item = new PurchasedItem(orderID, items.get(i));
            insertObject("[PurchaseHistoryItems]", queryForInsertItems(item));
        }
    }

    private String queryForInsert(Order order) {
        String query = String.format("VALUES (%d, CONVERT(datetime, '%s', 103), N'%s')",
                order.getCustomerID(),
                order.getDate().format(formatter),
                order.getBranch());

        return query;
    }

    private String queryForInsertItems(PurchasedItem item) {
        String query = String.format("VALUES (%d, %d)",
                item.getPurchaseID(),
                item.getItem().getProductID());

        return query;
    }

    // Additional helper method to convert ResultSet to InventoryItem objects
    /*public ArrayList<PurchasedItem> getPurchasedItemsByBranchAndDays(String branch, int days) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
        LocalDateTime minimumDate = LocalDateTime.now().minus(days, ChronoUnit.DAYS);  

        ResultSet  res = getObject("PurchaseHistory", "*", "Branch = N'" + branch+ "' AND (date >= DATE_SUB(CURDATE(), INTERVAL 30 DAY) AND purchase_date <= CURDATE()))");
        return resToCollection(res);
    }

    private ArrayList<PurchasedItem> resToCollection(ResultSet res)
    {
        ArrayList<PurchasedItem> resArray = new ArrayList<>();

        try {
            while(res.next())
            {
                int purchaseID = Integer.parseInt(res.getString("PurchaseID"));
                int customerID = Integer.parseInt(res.getString("CustomerID"));

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime date = LocalDateTime.parse(res.getString("Date"), formatter);

                String branch = res.getString("Branch");

                Order temp = new InventoryItem(branch, productID, name, category, quantity, price);
                resArray.add(temp);
            }
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println(e);
        }
        return resArray;
    }*/
}

