package Store.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// import Secrets;

public class AzureSqlConnection {
    // Azure SQL Database connection details
    PropertiesReader reader = new PropertiesReader("src/Store/Database/Secrets.properties");
    private final String SERVER_NAME = reader.getProperty("SERVER_NAME");
    private final String DATABASE_NAME = reader.getProperty("DATABASE_NAME");
    private final String USERNAME = reader.getProperty("USERNAME");
    private final String PASSWORD = reader.getProperty("PASSWORD");
    // private static final String CONNECTION_URL = "jdbc:sqlserver://" + SERVER_NAME + ":1433;database=" + DATABASE_NAME;
    private final String CONNECTION_URL = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s@sql-hit-omer;password=%s;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30",SERVER_NAME,DATABASE_NAME,USERNAME, PASSWORD);

    public AzureSqlConnection()
    {
        System.out.println(SERVER_NAME);
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD)) {
            System.out.println("Connected to Azure SQL Database successfully!");

            // Perform database operations here if needed

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