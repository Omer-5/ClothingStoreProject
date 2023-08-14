package Store;

import java.sql.Connection;

import Store.Database.AzureSqlConnection;
import Store.Database.EmployeeDAO;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        EmployeeDAO emp = new EmployeeDAO();
        emp.test4();
        // Connection connection = AzureSqlConnection.getConnection();
        // AzureSqlConnection.closeConnection();
    }
}