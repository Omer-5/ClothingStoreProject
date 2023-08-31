package Store;

import javax.swing.*;
import java.awt.*;

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

    public static boolean isDouble(String str)
    {
        double doubleValue;
        try {
            doubleValue = Double.parseDouble(str);
            return true;
        } catch( NumberFormatException e) {
            return false;
        }
    }

    public static void MessageBox(String text)
    {
        Font font = new Font("Calibri", Font.PLAIN, 16);
        JLabel label = new JLabel(text, JLabel.CENTER);
                    label.setFont(font);
                    JOptionPane.showMessageDialog(null,
                        label,
                        "הודעה חדשה",
                        JOptionPane.INFORMATION_MESSAGE);
    }
}
