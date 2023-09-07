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

   // Serialization
   public String serializeToString() {
      return this.getClass().getSimpleName() + Format.fieldSeparator + this.toString();
   }

   // Deserialization
   public static Employee deserializeFromString(String filename) {
      try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
         String serializedString = (String) ois.readObject();
         String[] parts = serializedString.split(Format.fieldSeparator);
         String className = parts[0];
         String data = parts[1];
         String[] fields = data.split(Format.fieldSeparator);

         if ("Employee".equals(className)) {
            Employee emp = new Employee();
            // Assuming the Person class has setters for the fields
            emp.setFullName(fields[0]);
            emp.setPhoneNumber(fields[1]);
            emp.setId(Integer.parseInt(fields[2]));
            emp.bankAccount = Integer.parseInt(fields[3]);
            emp.branch = fields[4];
            emp.employeeNumber = Integer.parseInt(fields[5]);
            emp.password = fields[6];
            emp.title = EmployeeTitle.valueOf(fields[7]);
            return emp;
         } else {
            throw new IllegalArgumentException("Unknown class type: " + className);
         }
      } catch (IOException | ClassNotFoundException e) {
         e.printStackTrace();
         return null;
      }
   }


   public static void main(String[] args) {
      // Create an Employee object
      Employee emp = new Employee("John Doe", "123456789", 1001, 12345678, "Main", 1, "password123", EmployeeTitle.MANAGER);

      // Serialize the object to a string
      String serializedData = emp.serializeToString();

      // Save the serialized string to a file named "employee.txt"
      String filename = "employee.txt";
      try (FileWriter writer = new FileWriter(filename)) {
         writer.write(serializedData);
      } catch (IOException e) {
         e.printStackTrace();
      }

      // Read the serialized string from the file
      String readData = "";
      try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
         readData = reader.readLine();
      } catch (IOException e) {
         e.printStackTrace();
      }

      // Deserialize the object from the read string
      Employee deserializedEmp = Employee.deserializeFromString(readData);

      // Print out the deserialized object's data to verify
      System.out.println("Deserialized Employee Data:");
      System.out.println("Full Name: " + deserializedEmp.getFullName());
      System.out.println("Phone Number: " + deserializedEmp.getPhoneNumber());
      System.out.println("ID: " + deserializedEmp.getId());
      System.out.println("Bank Account: " + deserializedEmp.bankAccount);
      System.out.println("Branch: " + deserializedEmp.branch);
      System.out.println("Employee Number: " + deserializedEmp.employeeNumber);
      System.out.println("Password: " + deserializedEmp.password);
      System.out.println("Title: " + deserializedEmp.title);
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
