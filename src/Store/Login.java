package Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JTextField employeeIdField;
    private JPasswordField passwordField;

    public Login() {
        setTitle("מערכת התחברות לעובדים");
        setSize(300, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Font font = new Font("Arial", Font.PLAIN, 15);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 10, 5, 10);

        // Add image label
        ImageIcon imageIcon = new ImageIcon("src\\Store\\Images\\StoreImage.png"); // Replace with your image path
        JLabel imageLabel = new JLabel(imageIcon);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(imageLabel, constraints);

        JLabel employeeIdLabel = new JLabel("מס' עובד / שם מלא:");
        employeeIdLabel.setFont(font);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(employeeIdLabel, constraints);

        employeeIdField = new JTextField(15);
        employeeIdField.setFont(font);
        employeeIdField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(employeeIdField, constraints);

        JLabel passwordLabel = new JLabel("סיסמה:");
        passwordLabel.setFont(font);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(passwordLabel, constraints);

        passwordField = new JPasswordField(15);
        passwordField.setFont(font);
        passwordField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        panel.add(passwordField, constraints);

        JButton loginButton = new JButton("התחברות");
        loginButton.setFont(font);
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        panel.add(loginButton, constraints);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeId = employeeIdField.getText();
                char[] password = passwordField.getPassword();
                // Add your login logic here
                // For demonstration, you can display a message
                JOptionPane.showMessageDialog(Login.this,
                        "Login button clicked.\nEmployee ID: " + employeeId,
                        "Login Result",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        add(panel);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login loginForm = new Login();
            loginForm.setVisible(true);
        });
    }
}
