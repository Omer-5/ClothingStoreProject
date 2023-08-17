package Store.Database;

import java.sql.ResultSet;
import java.util.ArrayList;

import Store.Employees.Employee;
import Store.Employees.EmployeeTitle;

import java.sql.SQLException;

public class EmployeeDAO extends GeneralDAO{

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

    public Employee getEmployeeByID(int id)
    {
        ResultSet  res = getObject("Employees", "*", "ID = "+ id);
        
        return resToCollection(res).get(0);
    }

    public ArrayList<Employee> getEmployeesByBranch(String branch)
    {
        ResultSet  res = getObject("Employees", "*", "branch = N'"+ branch+"'");
        return resToCollection(res);
    }

    private ArrayList<Employee> resToCollection(ResultSet res)
    {
        ArrayList<Employee> resArray = new ArrayList<>();

        try {
            while(res.next())
            {
                String fullName = res.getString("fullName");
                String phoneNumber = res.getString("phoneNumber");
                int id = Integer.parseInt(res.getString("id"));
                int bankAccount = Integer.parseInt(res.getString("BankAccount"));
                String branch = res.getString("branch");
                int employeeNumber = Integer.parseInt(res.getString("employeeNumber")); 
                String password = res.getString("password");
                EmployeeTitle title = EmployeeTitle.values()[Integer.parseInt(res.getString("title"))];
                Employee temp = new Employee(fullName, phoneNumber, id, bankAccount, branch, employeeNumber, password, title);
                resArray.add(temp);
            }
        } catch (SQLException e) {
            // TODO: handle exception
        }
        return resArray;
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