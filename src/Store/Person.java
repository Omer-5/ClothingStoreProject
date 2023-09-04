package Store;
import java.io.*;
public class Person implements Serializable{

    String fullName;
    String phoneNumber;
    int id;

    public Person(String fullName, String phoneNumber, int id) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        // This method is overridden for custom serialization
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        // This method is overridden for custom deserialization
    }

    public void serializeToFile(String filename, String DBCommand) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            String serializedData = "Person-" + DBCommand + "-" + fullName + "-" + phoneNumber + "-" + id;
            oos.writeUTF(serializedData);
            System.out.println("Serialization successful! Serialized data: " + serializedData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Person deserializeFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            String serializedData = ois.readUTF();
            String[] parts = serializedData.split("-");
            if (!parts[0].equals("Person")) {
                throw new IOException("Data does not represent a Person object");
            }
            System.out.println("Deserialization successful! Extracted data: FullName: " + parts[2] + ", PhoneNumber: " + parts[3] + ", ID: " + parts[4]);
            return new Person(parts[2], parts[3], Integer.parseInt(parts[4]));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Person person = new Person("John Doe", "12345", 1);
        String filename = "person.ser";

        // Serialize
        person.serializeToFile(filename, "Insert");

        // Deserialize
        Person deserializedPerson = Person.deserializeFromFile(filename);
        System.out.println("Deserialized person: " + deserializedPerson.getFullName());
    }
}
