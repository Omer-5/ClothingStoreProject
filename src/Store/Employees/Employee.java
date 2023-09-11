package Store.Employees;

import Store.Client.ServerCommunication.Format;
import Store.Person;

/**
 * Represents an employee of the store with attributes like bank account, branch, employee number, password, and title.
 */
public class Employee extends Person {
   /**
    * The bank account number of the employee.
    */
   int bankAccount;

   /**
    * The branch where the employee works.
    */
   String branch;

   /**
    * The unique employee number.
    */
   int employeeNumber;

   /**
    * The password used by the employee for authentication.
    */
   String password;

   /**
    * The title or position held by the employee.
    */
   EmployeeTitle title;

   /**
    * Constructs an employee with the specified details without an employee number.
    *
    * @param fullName     The full name of the employee.
    * @param phoneNumber  The phone number of the employee.
    * @param id           The unique identification number of the employee.
    * @param bankAccount  The bank account number of the employee.
    * @param branch       The branch where the employee works.
    * @param password     The password used by the employee.
    * @param title        The title held by the employee.
    */
   public Employee(String fullName, String phoneNumber, int id, int bankAccount, String branch, String password, EmployeeTitle title) {
      super(fullName, phoneNumber, id);
      this.bankAccount = bankAccount;
      this.branch = branch;
      this.password = password;
      this.title = title;
   }

   /**
    * Constructs an employee with the specified details including an employee number.
    *
    * @param fullName       The full name of the employee.
    * @param phoneNumber    The phone number of the employee.
    * @param id             The unique identification number of the employee.
    * @param bankAccount    The bank account number of the employee.
    * @param branch         The branch where the employee works.
    * @param employeeNumber The unique employee number.
    * @param password       The password used by the employee.
    * @param title          The title held by the employee.
    */
   public Employee(String fullName, String phoneNumber, int id, int bankAccount, String branch, int employeeNumber, String password, EmployeeTitle title) {
      super(fullName, phoneNumber, id);
      this.bankAccount = bankAccount;
      this.branch = branch;
      this.employeeNumber = employeeNumber;
      this.password = password;
      this.title = title;
   }

   /**
    * Returns the bank account number of the employee.
    *
    * @return The bank account number.
    */
   public int getBankAccount() {
      return bankAccount;
   }

   /**
    * Sets the bank account number for the employee.
    *
    * @param bankAccount The bank account number to be set.
    */
   public void setBankAccount(int bankAccount) {
      this.bankAccount = bankAccount;
   }

   /**
    * Returns the branch where the employee works.
    *
    * @return The branch name.
    */
   public String getBranch() {
      return branch;
   }

   /**
    * Sets the branch for the employee.
    *
    * @param branch The branch name to be set.
    */
   public void setBranch(String branch) {
      this.branch = branch;
   }

   /**
    * Returns the password of the employee.
    *
    * @return The password.
    */
   public String getPassword() {
      return password;
   }

   /**
    * Sets the password for the employee.
    *
    * @param password The password to be set.
    */
   public void setPassword(String password) {
      this.password = password;
   }

   /**
    * Returns the title or position held by the employee.
    *
    * @return The employee title.
    */
   public EmployeeTitle getTitle() {
      return title;
   }

   /**
    * Sets the title or position for the employee.
    *
    * @param title The title or position to be set.
    */
   public void setTitle(EmployeeTitle title) {
      this.title = title;
   }

   /**
    * Returns a string representation of the employee, combining super class's string with employee-specific details.
    *
    * @return The string representation of the employee.
    */
   @Override
   public String toString() {
      return super.toString() + Format.fieldSeparator +
              bankAccount + Format.fieldSeparator +
              branch + Format.fieldSeparator +
              employeeNumber + Format.fieldSeparator +
              password + Format.fieldSeparator +
              title.toString();
   }

   /**
    * Serializes the employee object to a string representation.
    *
    * @return The serialized string representation of the employee.
    */
   public String serializeToString() {
      return this.toString();
   }

   /**
    * Deserializes a string representation of an employee back to an Employee object.
    *
    * @param serializedString The serialized string representation of the employee.
    * @return The deserialized Employee object.
    */
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
