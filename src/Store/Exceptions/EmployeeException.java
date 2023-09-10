package Store.Exceptions;

public class EmployeeException {

    public enum MsgId {
        SUCCESS, NO_USER, WRONG_PASSWORD, ADMIN, MISSING_INFO, ONLY_DIGITS, ALREADY_LOGGED_IN; 
    }
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

