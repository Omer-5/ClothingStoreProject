package Store;

public class Utilities {
    public static boolean isNumeric(String str)
    {
        int intValue;
        try {
            intValue = Integer.parseInt(str);
            return true;
        } catch( NumberFormatException e) {
            return false;
        }
    }
}
