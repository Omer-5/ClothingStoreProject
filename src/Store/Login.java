package Store;

import javax.swing.*;

import Store.Database.EmployeeDAO;
import Store.Exceptions.EmployeeException;

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

        JLabel employeeIdLabel = new JLabel("תעודת זהות:");
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
                String id = employeeIdField.getText();

                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                EmployeeException.MsgId msg;

                //TODO: Add Server Login Request Here (Socket etc..)
                if(id.equals("") || password.equals(""))
                    msg = EmployeeException.MsgId.MISSING_INFO;
                else
                {
                    EmployeeDAO dao = new EmployeeDAO();
                    msg = dao.Login(id, password);
                }

                JOptionPane.showMessageDialog(Login.this,
                        EmployeeException.Msg[msg.ordinal()],
                        "הודעה חדשה",
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
