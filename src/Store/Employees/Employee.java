package Store.Employees;
import java.io.*;

import Store.Client.ServerCommunication.Format;
import Store.Person;


public class Employee extends Person {
   public static final String fieldSeparator = "**";
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

   public Employee() {
      super();
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
      return super.toString() + fieldSeparator +
              bankAccount + fieldSeparator +
              branch + fieldSeparator +
              employeeNumber + fieldSeparator +
              password + fieldSeparator +
              title.toString();
   }

   private String generateSerializationString() {
      return this.getClass().getSimpleName() + fieldSeparator + this.toString();
   }

   @Override
   public String serializeToString() {
      String baseSerialization = super.serializeToString();
      return baseSerialization + fieldSeparator + bankAccount + fieldSeparator + branch
              + fieldSeparator + employeeNumber + fieldSeparator + password
              + fieldSeparator + title.toString();
   }
   public static Employee deserializeFromString(String serializedString) {
      String[] fields = serializedString.split(fieldSeparator);
      // Extracting common fields (inherited from Person)
      String className = fields[0];
      String fullName = fields[1];
      String phoneNumber = fields[2];
      int id = Integer.parseInt(fields[3]);

      // Extracting Employee-specific fields
      int bankAccount = Integer.parseInt(fields[4]);
      String branch = fields[5];
      int employeeNumber = Integer.parseInt(fields[6]);
      String password = fields[7];
      EmployeeTitle title = EmployeeTitle.valueOf(fields[8]);

      if (!Objects.equals(className, "Employee")) {
         throw new IllegalArgumentException("Expected Employee class but found: " + className);
      }

      Employee emp = new Employee();
      emp.setFullName(fullName);
      emp.setPhoneNumber(phoneNumber);
      emp.setId(id);
      emp.bankAccount = bankAccount;
      emp.branch = branch;
      emp.employeeNumber = employeeNumber;
      emp.password = password;
      emp.title = title;
      return emp;
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
