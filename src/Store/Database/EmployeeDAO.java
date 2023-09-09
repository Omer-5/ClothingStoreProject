package Store.Database;

import java.sql.ResultSet;
import java.util.ArrayList;

import Store.Employees.Employee;
import Store.Employees.EmployeeTitle;
import Store.Exceptions.EmployeeException;
import Store.Server.Logger.Logger;
import Store.Utilities;

import java.sql.SQLException;

public class EmployeeDAO extends GeneralDAO{

    public void createNewEmployee(Employee emp) 
    {
        insertObject("Employees", queryForInsert(emp));
        Logger.registerEmployee(emp);
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
        
        ArrayList<Employee> collection = resToCollection(res);
        if(collection.isEmpty())
            return null;

        return collection.get(0);
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
                String fullName = res.getString("FullName");
                String phoneNumber = res.getString("PhoneNumber");
                int id = res.getInt("Id");
                int bankAccount = res.getInt("BankAccount");
                String branch = res.getString("Branch");
                int employeeNumber = res.getInt("EmployeeNumber"); 
                String password = res.getString("Password");
                EmployeeTitle title = EmployeeTitle.values()[res.getInt("EmployeeTitle")];
                Employee temp = new Employee(fullName, phoneNumber, id, bankAccount, branch, employeeNumber, password, title);
                resArray.add(temp);
            }
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println(e);
        }
        return resArray;
    }

    public EmployeeException.MsgId Login(String username, String password)
    {
        if( !Utilities.isNumeric(username) )
            return EmployeeException.MsgId.ONLY_DIGITS;
        else
        {
            Employee emp = getEmployeeByID(Integer.parseInt(username));
            if(emp == null)
                return EmployeeException.MsgId.NO_USER;
            else if(!emp.getPassword().equals(password)) 
                return EmployeeException.MsgId.WRONG_PASSWORD;
            else
                return EmployeeException.MsgId.SUCCESS;
        }
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