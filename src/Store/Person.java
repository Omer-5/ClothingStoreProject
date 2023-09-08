package Store;
import Store.Client.ServerCommunication.Format;

import java.io.*;
/**
 * Represents a generic person with essential attributes.
 * This class serves as a base class for more specific person types in the store system.
 */
public abstract class Person implements Serializable {
    /**
     * The full name of the person.
     */
    private String fullName;

    /**
     * The phone number of the person.
     */
    private String phoneNumber;

    /**
     * The unique identification number of the person.
     */
    private int id;

    /**
     * Constructs a new person with the specified details.
     *
     * @param fullName     The full name of the person.
     * @param phoneNumber  The phone number of the person.
     * @param id           The unique identification number of the person.
     */
    public Person(String fullName, String phoneNumber, int id) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    /**
     * Returns the full name of the person.
     *
     * @return The full name of the person.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name of the person.
     *
     * @param fullName  The new full name to be set.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Returns the phone number of the person.
     *
     * @return The phone number of the person.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the person.
     *
     * @param phoneNumber  The new phone number to be set.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the unique identification number of the person.
     *
     * @return The identification number of the person.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the identification number of the person.
     *
     * @param id  The new identification number to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns a string representation of the person, including the full name, phone number, and ID.
     *
     * @return A string representation of the person.
     */
    @Override
    public String toString() {
        return getFullName() + Format.fieldSeparator + getPhoneNumber() + Format.fieldSeparator + getId();
    }
}
