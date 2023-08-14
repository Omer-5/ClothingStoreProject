package Store.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Statement;

public class GeneralDAO <T>{
    private ArrayList<T> resArrayList;
    private static Connection connection;

    public GeneralDAO()
    {
        connection = AzureSqlConnection.getConnection(); 
    }

    private static ResultSet executeQuery(String query)
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

    public static ResultSet getObject(String tableName, String params, String condition)
    {
        String conditionString = "";
        if (condition != "") conditionString = "WHERE " + condition;
        String query = String.format("SELECT %s FROM %s %s", params, tableName, conditionString);
        ResultSet res = executeQuery(query);
        
        return res;
    }

    public ResultSet insertObject(String tableName, String params, String values)
    {
        
        ResultSet res = null;
        
        return res;
    }
}
