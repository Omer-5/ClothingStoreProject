package Store.AppForms;

import javax.swing.*;

import Store.Database.SocketData;
import Store.Employees.Employee;
import Store.Employees.EmployeeTitle;
import Store.Utilities;
import Store.Client.ServerCommunication.EncodeCommandEmployee;
import Store.Client.ServerCommunication.Format;
import Store.Database.Admin;
import Store.Exceptions.EmployeeException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;

public class Login extends JFrame {
    private JTextField employeeIdField;
    private JPasswordField passwordField;
    private String command, response;

    public Login() {
        initClient();
        initComponents();
    }

    public void initComponents() {
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

            if(id.equals("") || password.equals("")) 
                Utilities.MessageBox("חלק מהשדות לא הוכנסו");
            else {
                if( !Utilities.isNumeric(id) ) {
                    Utilities.MessageBox("תעודת הזהות חייבת להכיל רק ספרות");
                } else {
                    command = EncodeCommandEmployee.Login(id, password);
                    response = Utilities.SendReceive(command);
                    System.out.println("login response: "+ response);
                    switch(Format.getType(response)) {
                        case EXCEPTION:
                            Utilities.MessageBox(Format.getFirstParam(response));
                            break;
                        default: // Successful login, getting back the user
                            setVisible(false);
                            Employee emp = Employee.deserializeFromString(response);
                            StoreApp mainAppForm = new StoreApp(emp);
                            mainAppForm.setVisible(true); 
                    }
                }
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
                    {
                        JFrame frame = new JFrame("ניהול חנות בגדים - ממשק אדמין"); // Create a JFrame
                        EmployeesManagement employeesManagement = new EmployeesManagement();

                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.getContentPane().add(employeesManagement); // Add your CashRegister panel to the frame's content pane
                        frame.pack(); // Pack the frame to fit its contents
                        frame.setVisible(true); // Make the frame visible
                        frame.setResizable(false);
                        frame.setLocationRelativeTo(null);
                        setVisible(false);
                        
                        employeesManagement.setVisible(true); 
                    }
                    else {
                        msg = EmployeeException.MsgId.WRONG_PASSWORD;
                        Utilities.MessageBox("הסיסמה שהכנסה שגויה");
                    }
                }
            }
        });

        add(panel);
    }
    
    private void initClient() {
        try {
            Utilities.setClientSocketData(new SocketData(new Socket("localhost", 7000))); // Replace with your server address and port
            System.out.println(Utilities.getClientSocketData());
        } catch (IOException e) {
            System.out.println("Cannot connect to the Server! Please check that ther Server is Running!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login loginForm = new Login();
            loginForm.setVisible(true);
        });
    }
}
