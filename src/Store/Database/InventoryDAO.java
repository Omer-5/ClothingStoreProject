package Store.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.Connection;

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

    public InventoryItem getItemByID(int productID) {
        // Implementation of the getObject method should be in GeneralDAO
        try {
            return getObject("Inventory", "ProductID=" + productID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private ArrayList<InventoryItem> resToInventoryCollection(ResultSet res) {
        ArrayList<InventoryItem> resArray = new ArrayList<>();

        try {
            while(res.next()) {
                String branch = res.getString("branch");
                int productID = res.getInt("productID");
                String name = res.getString("name");
                String category = res.getString("category");
                int quantity = res.getInt("quantity");
                double price = res.getDouble("price");

                InventoryItem temp = new InventoryItem(branch, productID, name, category, quantity, price);
                resArray.add(temp);
            }
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println(e);
        }
        return resArray;
    }

    private InventoryItem getObject(String tableName, String condition) throws SQLException {
        // Construct the SQL query
        String query = "SELECT * FROM " + tableName + " WHERE " + condition;
        Connection conn = getConnection(); // Get the connection from the parent class

        // Execute the query and get the result set
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Check if a result was returned
            if (rs.next()) {
                // Convert the result set to an InventoryItem object
                return convertToInventoryItem(rs);
            } else {
                // If no result was returned, return null or handle the error as needed
                return null;
            }
        }
    }
    private String queryForInsert(InventoryItem item) {
        String query = String.format("VALUES (N'%s', %d, '%s', '%s', %d, %.2f)",
                item.getBranch(),
                item.getProductID(),
                item.getName(),
                item.getCategory(),
                item.getQuantity(),
                item.getPrice());

        return query;
    }

    private String queryForUpdate(InventoryItem item) {
        String query = String.format("SET Branch=N'%s', ProductID=%d, Name='%s', Category='%s', Quantity=%d, Price=%.2f WHERE ProductID=%d",
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
    private InventoryItem convertToInventoryItem(ResultSet rs) throws SQLException {
        String branch = rs.getString("Branch");
        int productID = rs.getInt("ProductID");
        String name = rs.getString("Name");
        String category = rs.getString("Category");
        int quantity = rs.getInt("Quantity");
        double price = rs.getDouble("Price");

        return new InventoryItem(branch, productID, name, category, quantity, price);
    }


}
