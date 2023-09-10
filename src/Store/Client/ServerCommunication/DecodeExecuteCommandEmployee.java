package Store.Client.ServerCommunication;

import java.sql.SQLException;
import java.util.ArrayList;

import Store.Database.EmployeeDAO;
import Store.Database.Server;
import Store.Employees.Employee;
import Store.Exceptions.EmployeeException;

public class DecodeExecuteCommandEmployee {
    public static String execute(String command) throws SQLException {
        EmployeeDAO DAO = new EmployeeDAO();
        Employee emp;
        int id;
        String response = ""; //TODO: add response
        switch (Format.getMethod(command)) {
            //public static String createNewEmployee(Employee emp)
            case "createNewEmployee":
                emp = Employee.deserializeFromString(Format.getFirstParam(command));
                DAO.createNewEmployee(emp);
                break;
            //public static String updateEmployee(Employee emp)
            case "updateEmployee":
                emp = Employee.deserializeFromString(Format.getFirstParam(command));
                DAO.updateEmployee(emp);
                break;
            //public static String deleteEmployee(int id)
            case "deleteEmployee":
                id = Integer.parseInt(Format.getFirstParam(command));
                DAO.deleteEmployee(id);
                break;
            //public static String getEmployeeByID(int id)
            case "getEmployeeByID":
                id = Integer.parseInt(Format.getFirstParam(command));
                emp = DAO.getEmployeeByID(id);
                response = emp.serializeToString();
                break;
            //public static String getEmployeesByBranch(String branch)
            case "getEmployeesByBranch":
                String branch = Format.getFirstParam(command);
                ArrayList<Employee> empList = DAO.getEmployeesByBranch(branch);
                response = Format.encodeEmployees(empList);
                break;
            //public static String Login(String username, String password)
            case "Login":
                String username = Format.getFirstParam(command);
                String password = Format.getSecondParam(command);
                EmployeeException.MsgId msg = DAO.Login(username, password);
                response = msg.toString();
                break;
        }
        return response;
    }

}
