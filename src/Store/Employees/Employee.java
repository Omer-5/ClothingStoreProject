package Store.Employees;
import java.io.*;

import Store.Client.ServerCommunication.Format;
import Store.Person;


public class Employee extends Person {
   int bankAccount;
   String branch;
   int employeeNumber; 
   String password;
   EmployeeTitle title;

   public Employee(String fullName, String phoneNumber, int id, int bankAccount, String branch, String password, EmployeeTitle title) {
      super(fullName, phoneNumber, id);
      this.bankAccount = bankAccount;
      this.branch = branch;
      this.password = password;
      this.title = title;
   }

   public Employee(String fullName, String phoneNumber, int id, int bankAccount, String branch, int employeeNumber, String password, EmployeeTitle title) {
      super(fullName, phoneNumber, id);
      this.bankAccount = bankAccount;
      this.branch = branch;
      this.employeeNumber = employeeNumber;
      this.password = password;
      this.title = title;
   }

   public int getBankAccount() {
      return bankAccount;
   }
   public void setBankAccount(int bankAccount) {
      this.bankAccount = bankAccount;
   }

   public String getBranch() {
      return branch;
   }
   public void setBranch(String branch) {
      this.branch = branch;
   }

   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {
      this.password = password;
   }

   public EmployeeTitle getTitle() {
      return title;
   }
   public void setTitle(EmployeeTitle title) {
      this.title = title;
   }

   @Override
   public String toString() {
      return super.toString() + Format.fieldSeparator +
              bankAccount + Format.fieldSeparator +
              branch + Format.fieldSeparator +
              employeeNumber + Format.fieldSeparator +
              password + Format.fieldSeparator +
              title.toString();
   }

   public String serializeToString()
   {
      return this.toString();
   }


   // TODO: check if working!
   public static Employee deserializeFromString(String serializedString) {
      String[] fields = serializedString.split(Format.fieldSeparator);
      String fullName = fields[0];
      String phoneNumber = fields[1];
      int id = Integer.parseInt(fields[2]);
      int bankAccount = Integer.parseInt(fields[3]);
      String branch = fields[4];
      int employeeNumber = Integer.parseInt(fields[5]);
      String password = fields[6];
      EmployeeTitle title = EmployeeTitle.valueOf(fields[7]);

      return new Employee(fullName, phoneNumber, id, bankAccount, branch, employeeNumber, password, title);
   }
// there is a main from GPT that checks the functions
   public static void main(String[] args) {
      // Assuming EmployeeTitle is an enum with a value "MANAGER" for demonstration purposes
      Employee employee = new Employee("John Doe", "123-456-7890", 101, 12345678, "Store1", 1001, "password123", EmployeeTitle.MANAGER);

      // Serialize the employee to string
      String serializedString = employee.serializeToString();
      System.out.println("Serialized Employee: " + serializedString);

      // Deserialize the string back to an Employee
      Employee deserializedEmployee = Employee.deserializeFromString(serializedString);
      System.out.println("Deserialized Employee: " + deserializedEmployee.toString());

      // Check if the original and deserialized employees are equal
      System.out.println("Are the original and deserialized employees equal? " + employee.toString().equals(deserializedEmployee.toString()));
   }
}

/*
 CREATE TABLE Employees (
    FullName nvarchar(255) NOT NULL,
	PhoneNumber nvarchar(255) NOT NULL,
    ID int NOT NULL,
    BankAccount int NOT NULL,
	Branch int NOT NULL,
	EmployeeNumber int IDENTITY(1,1) NOT NULL,
	Password nvarchar(255) NOT NULL,
	EmployeeTitle int NOT NULL,
    PRIMARY KEY (ID)
);
 */
