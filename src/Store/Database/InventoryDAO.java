package Store.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Store.Inventories.InventoryItem;

public class InventoryDAO extends GeneralDAO {

    public void createNewItem(InventoryItem item) {
        // Implementation of the insertObject method should be in GeneralDAO
        insertObject("Inventory", queryForInsert(item));
    }

    public void updateItem(InventoryItem item) {
        // Implementation of the updateObject method should be in GeneralDAO
        updateObject("Inventory", queryForUpdate(item));
    }

    public void deleteItem(int productID)  {
        // Implementation of the deleteObject method should be in GeneralDAO
        deleteObject("Inventory", "ProductID=" + productID);
    }

    public InventoryItem getItemByProductID(int productID) throws SQLException {
        // Implementation of the getObject method should be in GeneralDAO
        ResultSet  res = getObject("Inventory", "*", "productID = "+ productID);
        
        List<InventoryItem> collection = resToCollection(res);
        if(collection.isEmpty())
            return null;

        return collection.get(0);
    }

    private String queryForInsert(InventoryItem item) {
        String query = String.format("VALUES (N'%s', N'%s', N'%s', %d, %.2f)",
                item.getBranch(),
                item.getName(),
                item.getCategory(),
                item.getQuantity(),
                item.getPrice());

        return query;
    }

    private String queryForUpdate(InventoryItem item) {
        String query = String.format("SET Branch=N'%s', Name=N'%s', Category=N'%s', Quantity=%d, Price=%.2f WHERE ProductID=%d",
                item.getBranch(),
                item.getName(),
                item.getCategory(),
                item.getQuantity(),
                item.getPrice(),
                item.getProductID());

        return query;
    }

    // Additional helper method to convert ResultSet to InventoryItem objects
    public List<InventoryItem> getInventoryItemsByBranch(String branch) throws SQLException {
        ResultSet  res = getObject("Inventory", "*", "Branch = N'"+ branch+"'");
        return resToCollection(res);
    }

    private List<InventoryItem> resToCollection(ResultSet res) throws SQLException {
        List<InventoryItem> resArray = new ArrayList<>();
        while(res.next())
        {
            String branch = res.getString("Branch");
            int productID = Integer.parseInt(res.getString("ProductID"));
            String name = res.getString("Name");
            String category = res.getString("Category");
            int quantity = Integer.parseInt(res.getString("Quantity"));
            double price = Double.parseDouble(res.getString("Price"));

            InventoryItem temp = new InventoryItem(branch, productID, name, category, quantity, price);
            resArray.add(temp);
        }
        return resArray;
    }
}
