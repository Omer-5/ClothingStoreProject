package Store.Database;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Store.Employees.Employee;
import Store.Employees.EmployeeTitle;
import Store.Exceptions.EmployeeException;
import Store.Server.Logger.Logger;
import Store.Utilities;
import Store.Client.ServerCommunication.Format;

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

    public Employee getEmployeeByID(int id) throws SQLException
    {
        ResultSet  res = getObject("Employees", "*", "ID = "+ id);
        
        List<Employee> collection = resToCollection(res);
        if(collection.isEmpty())
            return null;

        return collection.get(0);
    }

    public List<Employee> getEmployees() throws SQLException{
        ResultSet res = getObject("Employees", "*", "");
        return resToCollection(res);
    }
    
    public List<Employee> getEmployeesByBranch(String branch) throws SQLException
    {
        ResultSet  res = getObject("Employees", "*", "branch = N'"+ branch+"'");
        return resToCollection(res);
    }

    private List<Employee> resToCollection(ResultSet res)
    {
        List<Employee> resArray = new ArrayList<>();

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

    public String Login(String username, String password) throws SQLException
    {
            String response;
            Employee emp = getEmployeeByID(Integer.parseInt(username));
            if(emp == null)
                response = Format.encodeException("לא קיים משתמש כזה במערכת");
            else if(!emp.getPassword().equals(password)) 
                response = Format.encodeException("הסיסמה שהכנסת שגויה");
            else if(Server.getSocketDataByEmployee(emp) != null)
                response = Format.encodeException("המשתמש כבר מחובר למערכת");
            else
                response = emp.serializeToString();
            return response;
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