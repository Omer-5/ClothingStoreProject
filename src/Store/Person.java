package Store;
import Store.Client.ServerCommunication.Format;

import java.io.*;
public abstract class Person implements Serializable {
    protected static final String fieldSeparator = "**";
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
    public String serializeToString() {
        return this.getClass().getSimpleName() + fieldSeparator +
                fullName + fieldSeparator +
                phoneNumber + fieldSeparator +
                id;
    }
    public static Person deserializeFromString(String serializedString) {
        String[] fields = serializedString.split(fieldSeparator);

        String className = fields[0];
        String fullName = fields[1];
        String phoneNumber = fields[2];
        int id = Integer.parseInt(fields[3]);

        switch (className) {
            case "Person":
                return new Person(fullName, phoneNumber, id) {};
            default:
                throw new IllegalArgumentException("Unknown class type: " + className);
        }
    }
    @Override
    public String toString() {
        return getFullName() + Format.fieldSeparator + getPhoneNumber() + Format.fieldSeparator + getId();
    }


}
