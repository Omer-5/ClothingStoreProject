package Store;

import java.sql.Connection;

import javax.swing.SwingUtilities;

import Store.Database.AzureSqlConnection;
import Store.Database.EmployeeDAO;

public class Main {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login loginForm = new Login();
            loginForm.setVisible(true);
        });
    }
}