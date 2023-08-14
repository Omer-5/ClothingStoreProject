package Store.Database;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

import Store.Employees.Employee;
import Store.Employees.EmployeeTitle;

import java.sql.SQLException;

public class EmployeeDAO extends GeneralDAO<Employee>{

    public void createNewEmployee(Employee emp) 
    {
        insertObject("Employees", queryForInsert(emp));
    }

    public void updateEmployee(Employee emp)
    {
        updateObject("Employees", queryForUpdate(emp));
    }

    public void deleteEmployee(int id)
    {
        deleteObject("Employees", "ID=" + id);
    }

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

    public void test2()
    {
        Employee emp = new Employee("Daniel", "0528921319", 318595246, 123456, "חולון", "1111", EmployeeTitle.MANAGER);
        createNewEmployee(emp);
    }

    public void test3()
    {
        Employee emp = new Employee("Daniel", "0528921319", 318595246, 123456, "חולון", "1111", EmployeeTitle.MANAGER);
        emp.setFullName("Omer");
        emp.setPassword("4444");

        updateEmployee(emp);
    }

    public void test4() 
    {
        int id = 318595246;
        deleteEmployee(318595246);
    }
    
    private String queryForInsert(Employee emp)
    {
        String query = String.format("Values (N'%s', '%s', %d, %d, N'%s', '%s', %d)",
                            emp.getFullName(),
                            emp.getPhoneNumber(),
                            emp.getId(),
                            emp.getBankAccount(),
                            emp.getBranch(),
                            emp.getPassword(),
                            emp.getTitle().ordinal());

        return query;
    }

    private String queryForUpdate(Employee emp)
    {
        String query = String.format("SET FullName=N'%s', PhoneNumber='%s', BankAccount=%d, Branch=N'%s', Password='%s', EmployeeTitle=%d WHERE ID=%d",
                            emp.getFullName(),
                            emp.getPhoneNumber(),
                            emp.getBankAccount(),
                            emp.getBranch(),
                            emp.getPassword(),
                            emp.getTitle().ordinal(),
                            emp.getId());
        
        return query;
    }
}