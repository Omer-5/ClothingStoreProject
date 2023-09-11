package Store.AppForms;

import javax.swing.*;
import java.awt.*;

import Store.Utilities;
import Store.Client.ServerCommunication.EncodeCommandEmployee;
import Store.Client.ServerCommunication.Format;
import Store.Employees.Employee;
import Store.Employees.EmployeeTitle;

public class EmployeeAddOrUpdate extends JPanel {
    // Variables declaration - do not modify                     
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel mainPanel_BankAcountLabel;
    private javax.swing.JTextField mainPanel_BankAcountTextField;
    private javax.swing.JLabel mainPanel_BranchLabel;
    private javax.swing.JTextField mainPanel_BranchTextField;
    private javax.swing.JComboBox<String> mainPanel_EmployeeTypeComboBox;
    private javax.swing.JLabel mainPanel_EmployeeTypeLabel;
    private javax.swing.JLabel mainPanel_FullnameLabel;
    private javax.swing.JTextField mainPanel_FullnameTextField;
    private javax.swing.JLabel mainPanel_IDLabel;
    private javax.swing.JTextField mainPanel_IDTextField;
    private javax.swing.JLabel mainPanel_PasswordLabel;
    private javax.swing.JTextField mainPanel_PasswordTextField;
    private javax.swing.JLabel mainPanel_PhoneLabel;
    private javax.swing.JTextField mainPanel_PhoneTextField;
    private javax.swing.JButton mainPanel_SubmitButton;

    private JDialog dialog;
    private String actionType;
    private boolean exitSuccessfully = false;
    private String command, response;

    // Contractor for New Customer Form
    public EmployeeAddOrUpdate() {
        initComponents();

        actionType = "add";
    }

    // Contractor for Edit Customer Form
    public EmployeeAddOrUpdate(Employee emp) {
        initComponents();

        mainPanel_IDTextField.setText(Integer.toString(emp.getId()));
        mainPanel_IDTextField.setEnabled(false);

        mainPanel_FullnameTextField.setText(emp.getFullName());
        mainPanel_PhoneTextField.setText(emp.getPhoneNumber());
        mainPanel_BankAcountTextField.setText(Integer.toString(emp.getBankAccount()));
        mainPanel_BranchTextField.setText(emp.getBranch());
        mainPanel_PasswordTextField.setText(emp.getPassword());
        mainPanel_EmployeeTypeComboBox.setSelectedItem(emp.getTitle().toString());
        
        mainPanel_SubmitButton.setBackground(new Color(206,148,89));
        mainPanel_SubmitButton.setText("שמור שינויים");

        actionType = "edit";
    }
                        
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        mainPanel_IDLabel = new javax.swing.JLabel();
        mainPanel_IDTextField = new javax.swing.JTextField();
        mainPanel_FullnameLabel = new javax.swing.JLabel();
        mainPanel_FullnameTextField = new javax.swing.JTextField();
        mainPanel_PhoneLabel = new javax.swing.JLabel();
        mainPanel_PhoneTextField = new javax.swing.JTextField();
        mainPanel_BankAcountLabel = new javax.swing.JLabel();
        mainPanel_BankAcountTextField = new javax.swing.JTextField();
        mainPanel_BranchLabel = new javax.swing.JLabel();
        mainPanel_BranchTextField = new javax.swing.JTextField();
        mainPanel_PasswordLabel = new javax.swing.JLabel();
        mainPanel_PasswordTextField = new javax.swing.JTextField();
        mainPanel_EmployeeTypeLabel = new javax.swing.JLabel();
        mainPanel_EmployeeTypeComboBox = new javax.swing.JComboBox<>();
        mainPanel_SubmitButton = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setMinimumSize(new java.awt.Dimension(1, 1));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(325, 425));

        mainPanel.setBackground(new java.awt.Color(250, 250, 250));
        mainPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mainPanel.setPreferredSize(new java.awt.Dimension(290, 378));

        mainPanel_IDLabel.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        mainPanel_IDLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mainPanel_IDLabel.setText("תעודת זהות");

        mainPanel_IDTextField.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        mainPanel_IDTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        mainPanel_FullnameLabel.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        mainPanel_FullnameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mainPanel_FullnameLabel.setText("שם מלא");

        mainPanel_FullnameTextField.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        mainPanel_FullnameTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        mainPanel_PhoneLabel.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        mainPanel_PhoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mainPanel_PhoneLabel.setText("טלפון");

        mainPanel_PhoneTextField.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        mainPanel_PhoneTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        mainPanel_BankAcountLabel.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        mainPanel_BankAcountLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mainPanel_BankAcountLabel.setText("חשבון בנק");

        mainPanel_BankAcountTextField.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        mainPanel_BankAcountTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        mainPanel_BranchLabel.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        mainPanel_BranchLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mainPanel_BranchLabel.setText("סניף");

        mainPanel_BranchTextField.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        mainPanel_BranchTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        mainPanel_PasswordLabel.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        mainPanel_PasswordLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mainPanel_PasswordLabel.setText("סיסמה");

        mainPanel_PasswordTextField.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        mainPanel_PasswordTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        mainPanel_EmployeeTypeLabel.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        mainPanel_EmployeeTypeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mainPanel_EmployeeTypeLabel.setText("סוג עובד");

        mainPanel_EmployeeTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELLER", "CASHIER", "MANAGER" }));

        mainPanel_SubmitButton.setBackground(new java.awt.Color(102, 153, 0));
        mainPanel_SubmitButton.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        mainPanel_SubmitButton.setText("הוסף לקוח חדש");
        mainPanel_SubmitButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mainPanel_SubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainPanel_SubmitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mainPanel_SubmitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(mainPanel_PasswordTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mainPanel_BranchTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mainPanel_BankAcountTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mainPanel_PhoneTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mainPanel_FullnameTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mainPanel_IDTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mainPanel_EmployeeTypeComboBox, 0, 132, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(mainPanel_IDLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mainPanel_PhoneLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mainPanel_FullnameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mainPanel_EmployeeTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(mainPanel_BankAcountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mainPanel_BranchLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(mainPanel_PasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainPanel_IDLabel)
                    .addComponent(mainPanel_IDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainPanel_FullnameLabel)
                    .addComponent(mainPanel_FullnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainPanel_PhoneLabel)
                    .addComponent(mainPanel_PhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainPanel_BankAcountLabel)
                    .addComponent(mainPanel_BankAcountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainPanel_BranchLabel)
                    .addComponent(mainPanel_BranchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainPanel_PasswordLabel)
                    .addComponent(mainPanel_PasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanel_EmployeeTypeLabel)
                    .addComponent(mainPanel_EmployeeTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(mainPanel_SubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void mainPanel_SubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {  
        int id = Integer.parseInt(mainPanel_IDTextField.getText());

        String fullname = mainPanel_FullnameTextField.getText();
        String phoneNumber = mainPanel_PhoneTextField.getText();
        String bankAccount = mainPanel_BankAcountTextField.getText();
        String branch = mainPanel_BranchTextField.getText();
        String password = mainPanel_PasswordTextField.getText();
        int employeeType = mainPanel_EmployeeTypeComboBox.getSelectedIndex();

        if( fullname.equals("") || phoneNumber.equals("") || bankAccount.equals("") || branch.equals("") || password.equals(""))
        {
            Utilities.MessageBox("וודא שכל השדות אינם ריקים");
            return;
        }
        else if(!Utilities.isNumeric(phoneNumber)) {
            Utilities.MessageBox("מספר הטלפון חייב להכיל רק מספרים");
            return;
        }
        else if(!Utilities.isNumeric(bankAccount)) {
            Utilities.MessageBox("חשבון הבנק חייב להכיל רק מספרים");
            return;
        }

        Employee emp = new Employee(fullname, phoneNumber, id, Integer.parseInt(bankAccount), branch, password, EmployeeTitle.values()[employeeType]);

        if(actionType.equals("add")) {
            command = EncodeCommandEmployee.getEmployeeByID(id);
            response = Utilities.SendReceive(command);

            switch (Format.getType(response)) {
                case EXCEPTION:
                    Utilities.MessageBox(Format.getFirstParam(response));
                    break;
                case EMPTY:
                    command = EncodeCommandEmployee.createNewEmployee(emp);
                    response = Utilities.SendReceive(command);
                    switch (Format.getType(response)) {
                        case EXCEPTION:
                            Utilities.MessageBox(Format.getFirstParam(response));
                            break;
                        case SUCCESS:
                            Utilities.MessageBox(Format.getFirstParam(response));
                            exitSuccessfully = true;
                            dialog.dispose();
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    Utilities.MessageBox("תעודת הזהות כבר קיימת במערכת");
                    break;
            }
        }
        else if (actionType.equals("edit")) {
            command = EncodeCommandEmployee.updateEmployee(emp);
            response = Utilities.SendReceive(command);
            Utilities.MessageBox(Format.getFirstParam(response));

            exitSuccessfully = true;
            dialog.dispose();
        }
    }                                                      

    public boolean IsExitedSuccessfully() {
        return exitSuccessfully;
    }

    public void setDialog(JDialog dialog) {
        this.dialog = dialog;
    }
}

