package Store.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.Connection;

import Store.Employees.Employee;
import Store.Employees.EmployeeTitle;
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

    public void deleteItem(int productID) {
        // Implementation of the deleteObject method should be in GeneralDAO
        deleteObject("Inventory", "ProductID=" + productID);
    }

    public InventoryItem getItemByProductID(int productID) {
        // Implementation of the getObject method should be in GeneralDAO
        ResultSet  res = getObject("Inventory", "*", "productID = "+ productID);
        
        ArrayList<InventoryItem> collection = resToCollection(res);
        if(collection.isEmpty())
            return null;

        return collection.get(0);
    }

    private String queryForInsert(InventoryItem item) {
        String query = String.format("VALUES (N'%s', %d, N'%s', N'%s', %d, %.2f)",
                item.getBranch(),
                item.getProductID(),
                item.getName(),
                item.getCategory(),
                item.getQuantity(),
                item.getPrice());

        return query;
    }

    private String queryForUpdate(InventoryItem item) {
        String query = String.format("SET Branch=N'%s', ProductID=%d, Name=N'%s', Category=N'%s', Quantity=%d, Price=%.2f WHERE ProductID=%d",
                item.getBranch(),
                item.getProductID(),
                item.getName(),
                item.getCategory(),
                item.getQuantity(),
                item.getPrice(),
                item.getProductID());

        return query;
    }


    // Additional helper method to convert ResultSet to InventoryItem objects
    public ArrayList<InventoryItem> getInventoryItemsByBranch(String branch) throws SQLException {
        ResultSet  res = getObject("Inventory", "*", "Branch = N'"+ branch+"'");
        return resToCollection(res);
    }

    private ArrayList<InventoryItem> resToCollection(ResultSet res)
    {
        ArrayList<InventoryItem> resArray = new ArrayList<>();

        try {
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
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println(e);
        }
        return resArray;
    }
}
