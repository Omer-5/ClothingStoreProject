package Store;
import java.io.*;
public class Person implements Serializable {
    protected String fullName;
    protected String phoneNumber;
    protected int id;

    public Person(String fullName, String phoneNumber, int id) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    public Person() {

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

    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", id=" + id +
                '}';
    }

}
