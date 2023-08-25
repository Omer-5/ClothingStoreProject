package Store.Database;

import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;

public class GeneralDAO {
    private Connection connection;

    public GeneralDAO()
    {
        connection = AzureSqlConnection.getConnection(); 
    }

    private ResultSet executeQuery(String query)
    {
       try{
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            return res;
        }
        catch (SQLException e){
            //TODO: Handle
        }
        return null;
    }

    private void executeUpdate(String query) 
    {
       try{
            Statement statement = connection.createStatement();
            int rowsUpdated = statement.executeUpdate(query);
            connection.commit();
            //TODO: Throw Exception if rowsUpdated is 0 (nothing happend) 
        }
        catch (SQLException e){
            System.out.println(e);
            //TODO: Handle
        }
    }

    protected ResultSet getObject(String tableName, String params, String condition)
    {
        String conditionString = "";
        if (condition != "") conditionString = "WHERE " + condition;
        String query = String.format("SELECT %s FROM %s %s", params, tableName, conditionString);
        ResultSet res = executeQuery(query);
        
        return res;
    }

    protected void insertObject(String tableName, String queryParams)
    {
        String query = String.format("INSERT INTO %s %s", tableName, queryParams);
        System.out.println(query);
        executeUpdate(query);
    }

    protected void updateObject(String tableName, String queryParams)
    { 
        String query = String.format("UPDATE %s %s", tableName, queryParams);
        executeUpdate(query);
    }

    protected void deleteObject(String tableName, String queryParams)
    { 
        String query = String.format("DELETE FROM %s WHERE %s", tableName, queryParams);
        executeUpdate(query);
    }
    //NEW ORAM ADDED For InventoryDAO
    protected Connection getConnection() {
        return connection;
    }

}
