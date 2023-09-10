package Store.Exceptions;

/**
 * Represents custom exception messages for employee-related operations.
 */
public class EmployeeException {

    /**
     * Enumeration of different message identifiers for exceptions.
     */
    public enum MsgId {
        SUCCESS,
        NO_USER,
        WRONG_PASSWORD,
        ADMIN,
        MISSING_INFO,
        ONLY_DIGITS,
        ALREADY_LOGGED_IN;
    }

    /**
     * Array of exception messages corresponding to each message identifier.
     */
    public static String[] Msg = {
        "התחברות בהצלחה",
        "לא קיים משתמש כזה במערכת",
        "הסיסמה שהכנסת שגויה",
        "ממשק אדמין",
        "חלק מהשדות לא הוכנסו",
        "תעודת הזהות חייבת להכיל רק ספרות",
        "המשתמש כבר מחובר למערכת"
    };
}
