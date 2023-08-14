package Store.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {

    public void test()
    {
        // Connection connection = AzureSqlConnection.getConnection();
        ResultSet  res = AzureSqlConnection.getObject("Employees", "*", "FullName = 'Yossi'");
        try{
            while(res.next())
            {
                System.out.println(res.getString("FullName"));
            }
        }
        catch (SQLException e)
        {

        }
    }
}
