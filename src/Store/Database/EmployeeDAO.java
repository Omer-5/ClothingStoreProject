package Store.Database;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

import Store.Employees.Employee;

import java.sql.SQLException;

public class EmployeeDAO extends GeneralDAO<Employee>{

    public void test()
    {
        ResultSet  res = getObject("Employees", "*", "FullName = 'Yossi'");
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
