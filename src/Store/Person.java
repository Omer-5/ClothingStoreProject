package Store;
import Store.Client.ServerCommunication.Format;

import java.io.*;
public abstract class Person implements Serializable {
    private String fullName;
    private String phoneNumber;
    private int id;

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

    @Override
    public String toString() {
        return getFullName() + Format.fieldSeparator + getPhoneNumber() + Format.fieldSeparator + getId();
    }
}
