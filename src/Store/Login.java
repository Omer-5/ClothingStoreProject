package Store;

import javax.swing.*;

import Store.Database.EmployeeDAO;
import Store.Database.Admin;
import Store.Exceptions.EmployeeException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {
    private JTextField employeeIdField;
    private JPasswordField passwordField;

    public Login() {

        setTitle("ניהול חנות בגדים - התחברות");
        setSize(300, 440);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Font font = new Font("Arial", Font.PLAIN, 15);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 10, 5, 10);

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

        JLabel manageLink = new JLabel("כניסה לממשק ניהול");
        manageLink.setFont(font);
        manageLink.setForeground(Color.BLUE.darker());
        manageLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        panel.add(manageLink, constraints);

        // Password Field Actions - Pressing 'Enter'
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButton.doClick();
            }
        });

        // Login Button Actions - Validate Credentials and Redirecting to Main Store Form
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = employeeIdField.getText();

                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                EmployeeException.MsgId msg;
                EmployeeDAO dao = new EmployeeDAO();

                //TODO: Add Server Login Request Here (Socket etc..)
                if(id.equals("") || password.equals(""))
                    msg = EmployeeException.MsgId.MISSING_INFO;
                else {
                    msg = dao.Login(id, password);
                }

                if(msg == EmployeeException.MsgId.SUCCESS) {
                    setVisible(false);
                    StoreApp mainAppForm = new StoreApp(dao.getEmployeeByID(Integer.parseInt(id)));
                    mainAppForm.setVisible(true);
                }
                else {
                    JLabel label = new JLabel(EmployeeException.Msg[msg.ordinal()], JLabel.CENTER);
                    label.setFont(font);
                    JOptionPane.showMessageDialog(Login.this,
                        label,
                        "הודעה חדשה",
                        JOptionPane.INFORMATION_MESSAGE);
                }
                
                
            }
        });

        // Manage Hyperlink Actions - Validate Credentials and Redirecting to Admin Form
        manageLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPasswordField managePasswordField = new JPasswordField(12);
                JLabel managePasswordLabel = new JLabel("הזן סיסמת ניהול:");
                Font font = new Font("Arial", Font.PLAIN, 14);

                managePasswordField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                managePasswordLabel.setFont(font);

                JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                panel.add(managePasswordField);
                panel.add(managePasswordLabel);
                
                managePasswordField.requestFocusInWindow();

                Object[] message = {panel};

                int option = JOptionPane.showConfirmDialog(null, message, "ממשק ניהול", JOptionPane.OK_CANCEL_OPTION);

                if (option == JOptionPane.OK_OPTION) {
                    char[] passwordChars = managePasswordField.getPassword();
                    String password = new String(passwordChars);
                    EmployeeException.MsgId msg;
                    Admin admin = new Admin();

                    if(admin.checkCred(password))
                        msg = EmployeeException.MsgId.ADMIN;
                    else
                        msg = EmployeeException.MsgId.WRONG_PASSWORD;


                    //TODO: Redirecting To Admin Form if Password Currect 
                    JLabel label = new JLabel(EmployeeException.Msg[msg.ordinal()], JLabel.CENTER);
                    label.setFont(font);
                    JOptionPane.showMessageDialog(Login.this,
                            label,
                            "הודעה חדשה",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
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
