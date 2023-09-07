package Store.Employees;
import java.io.*;
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
   public void serializeToFile(String filename) {
      try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
         oos.writeObject(generateSerializationString());
         System.out.println("Serialization successful!");
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   // Deserialization
   public static Employee deserializeFromFile(String filename) {
      try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
         String serializedString = (String) ois.readObject();
         String[] parts = serializedString.split(fieldSeparator);
         String className = parts[0];
         String data = parts[1];
         String[] fields = data.split(fieldSeparator);

         switch (className) {
            case "Employee":
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
            default:
               throw new IllegalArgumentException("Unknown class type: " + className);
         }
      } catch (IOException | ClassNotFoundException e) {
         e.printStackTrace();
         return null;
      }
   }

   public static void main(String[] args) {
      Employee emp = new Employee("John Doe", "123456789", 1, 12345678, "XYZ Branch", 1001, "password123", EmployeeTitle.MANAGER);

      // Serialize to file
      emp.serializeToFile("employee.obj");

      // Deserialize from file
      Employee deserializedEmp = Employee.deserializeFromFile("employee.obj");

      // Print deserialized data
      System.out.println(deserializedEmp);
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
