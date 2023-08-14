package Store.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
// import Secrets;
import java.sql.Statement;

public class AzureSqlConnection {
    // Azure SQL Database connection details
    private static PropertiesReader reader = new PropertiesReader("src/Store/Database/Secrets.properties");
    private static String SERVER_NAME = reader.getProperty("SERVER_NAME");
    private static String DATABASE_NAME = reader.getProperty("DATABASE_NAME");
    private static String USERNAME = reader.getProperty("USERNAME");
    private static String PASSWORD = reader.getProperty("PASSWORD");
    private static String CONNECTION_URL = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s@sql-hit-omer;password=%s;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30",SERVER_NAME,DATABASE_NAME,USERNAME, PASSWORD);
    private static Connection connection;

    private AzureSqlConnection(){};

    public static void Connect() {
        try{
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
                System.out.println("Connected to Azure SQL Database successfully!");
            }
        }
        catch (SQLException e)
        {
            System.out.println("");
        }
    }

    public static void closeConnection(){
        try {
            if (connection != null || connection.isClosed()) { 
                connection.close();
                System.out.println("Connection to Azure SQL Database closed successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        Connect();
        String conditionString = "";
        if (condition != "") conditionString = "WHERE " + condition;
        String query = String.format("SELECT %s FROM %s %s", params, tableName, conditionString);
        ResultSet res = executeQuery(query);
        
        return res;
    }

    public ResultSet insertObject(String tableName, String params, String condition)
    {
        
        ResultSet res = null;
        
        return res;
    }

    // public static void main(String[] args) {
    //     try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD)) {
    //         System.out.println("Connected to Azure SQL Database successfully!");

    //         // Perform database operations here if needed

    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }
}