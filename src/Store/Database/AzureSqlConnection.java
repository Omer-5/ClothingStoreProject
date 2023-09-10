package Store.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
// import Secrets;
import java.sql.Statement;

public class AzureSqlConnection {
    // Azure SQL Database connection details
    private static PropertiesHandler propertiesHandler = new PropertiesHandler("src/Store/Database/Secrets.properties"); //TODO: Change path after restructure
    private static String SERVER_NAME = propertiesHandler.getProperty("SERVER_NAME");
    private static String DATABASE_NAME = propertiesHandler.getProperty("DATABASE_NAME");
    private static String USERNAME = propertiesHandler.getProperty("USERNAME");
    private static String PASSWORD = propertiesHandler.getProperty("PASSWORD");
    private static String CONNECTION_URL = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s@sql-hit-daniel;password=%s;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30",SERVER_NAME,DATABASE_NAME,USERNAME, PASSWORD);
    private static Connection connection;

    private AzureSqlConnection(){};

    public static Connection getConnection() {
        try{
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
                System.out.println("Connected to Azure SQL Database successfully!");
            }
            System.out.println(connection);
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return connection;
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



    // public static void main(String[] args) {
    //     try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD)) {
    //         System.out.println("Connected to Azure SQL Database successfully!");

    //         // Perform database operations here if needed

    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }
}