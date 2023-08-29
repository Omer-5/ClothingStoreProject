package Store.AppForms;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Store.Utilities;
import Store.Customers.*;
import Store.Database.CustomerDAO;

public class CustomerAddOrUpdate extends JPanel {
    // Variables declaration - do not modify                     
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel mainPanel_CustomerTypeLabel;
    private javax.swing.JTextField mainPanel_CustomerTypeTextField;
    private javax.swing.JLabel mainPanel_FullnameLabel;
    private javax.swing.JTextField mainPanel_FullnameTextField;
    private javax.swing.JComboBox<String> mainPanel_CustomerTypeComboBox;
    private javax.swing.JLabel mainPanel_IDDataLabel;
    private javax.swing.JLabel mainPanel_IDLabel;
    private javax.swing.JLabel mainPanel_PhoneLabel;
    private javax.swing.JTextField mainPanel_PhoneTextField;
    private javax.swing.JButton mainPanel_SubmitButton;

    private JDialog dialog;
    private String actionType;

    // Constractor for New Customer Form
    public CustomerAddOrUpdate(int id) {
        initComponents();

        mainPanel_IDDataLabel.setText(Integer.toString(id));
        actionType = "add";
    }

    // Constractor for Edit Customer Form
    public CustomerAddOrUpdate(Customer customer) {
        initComponents();

        mainPanel_IDDataLabel.setText(Integer.toString(customer.getId()));
        mainPanel_FullnameTextField.setText(customer.getFullName());
        mainPanel_PhoneTextField.setText(customer.getPhoneNumber());
        mainPanel_CustomerTypeComboBox.setSelectedItem(customer.getType());
        
        mainPanel_SubmitButton.setBackground(new Color(206,148,89));
        mainPanel_SubmitButton.setText("שמור שינויים");

        actionType = "edit";
    }
                        
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        mainPanel_PhoneLabel = new javax.swing.JLabel();
        mainPanel_IDLabel = new javax.swing.JLabel();
        mainPanel_FullnameLabel = new javax.swing.JLabel();
        mainPanel_CustomerTypeLabel = new javax.swing.JLabel();
        mainPanel_IDDataLabel = new javax.swing.JLabel();
        mainPanel_FullnameTextField = new javax.swing.JTextField();
        mainPanel_PhoneTextField = new javax.swing.JTextField();
        mainPanel_SubmitButton = new javax.swing.JButton();
        mainPanel_CustomerTypeComboBox = new javax.swing.JComboBox<>();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setMinimumSize(new java.awt.Dimension(1, 1));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(334, 298));

        mainPanel.setBackground(new java.awt.Color(250, 250, 250));
        mainPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        mainPanel_PhoneLabel.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        mainPanel_PhoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mainPanel_PhoneLabel.setText("טלפון");

        mainPanel_IDLabel.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        mainPanel_IDLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mainPanel_IDLabel.setText("תעודת זהות");

        mainPanel_FullnameLabel.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        mainPanel_FullnameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mainPanel_FullnameLabel.setText("שם מלא");

        mainPanel_CustomerTypeLabel.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        mainPanel_CustomerTypeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mainPanel_CustomerTypeLabel.setText("סוג לקוח");

        mainPanel_IDDataLabel.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        mainPanel_IDDataLabel.setForeground(new java.awt.Color(0, 102, 255));
        mainPanel_IDDataLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mainPanel_IDDataLabel.setText("תעודת זהות");

        mainPanel_FullnameTextField.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        mainPanel_FullnameTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        mainPanel_PhoneTextField.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        mainPanel_PhoneTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        mainPanel_SubmitButton.setBackground(new java.awt.Color(102, 153, 0));
        mainPanel_SubmitButton.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        mainPanel_SubmitButton.setText("הוסף לקוח חדש");
        mainPanel_SubmitButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mainPanel_SubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainPanel_SubmitButtonActionPerformed(evt);
            }
        });

        mainPanel_CustomerTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "New", "Regular", "VIP" }));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mainPanel_SubmitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(mainPanel_IDDataLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mainPanel_FullnameTextField)
                            .addComponent(mainPanel_PhoneTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(mainPanel_CustomerTypeComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, 108, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(mainPanel_IDLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mainPanel_PhoneLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mainPanel_FullnameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mainPanel_CustomerTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainPanel_IDLabel)
                    .addComponent(mainPanel_IDDataLabel))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainPanel_FullnameLabel)
                    .addComponent(mainPanel_FullnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainPanel_PhoneLabel)
                    .addComponent(mainPanel_PhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanel_CustomerTypeLabel)
                    .addComponent(mainPanel_CustomerTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(mainPanel_SubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
    }// </editor-fold>                        
 
    private void mainPanel_SubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {  
        CustomerDAO customerDAO = new CustomerDAO();   
        String fullname = mainPanel_FullnameTextField.getText();
        String phoneNumber = mainPanel_PhoneTextField.getText();
        int id = Integer.parseInt(mainPanel_IDDataLabel.getText());
        String customerType = mainPanel_CustomerTypeComboBox.getSelectedItem().toString();

        Customer customer = null;

        if( fullname.equals("") || phoneNumber.equals(""))
        {
            Utilities.MessageBox("וודא שכל השדות אינם ריקים");
            return;
        }
        else if(!Utilities.isNumeric(phoneNumber)) {
            Utilities.MessageBox("מספר הטלפון חייב להכיל רק מספרים");
            return;
        }

        if(customerType.equals("New"))  customer = new CustomerNew(fullname, phoneNumber, id);
        else if(customerType.equals("Regular"))  customer = new CustomerRegular(fullname, phoneNumber, id);
        else if(customerType.equals("VIP"))  customer = new CustomerVIP(fullname, phoneNumber, id);

        if(actionType.equals("add")) {
            customerDAO.createNewCustomer(customer, customerType);
            Utilities.MessageBox("הלקוח נוסף בהצלחה!");
        }
        else if (actionType.equals("edit")) {
            customerDAO.updateCustomer(customer, customerType);
            Utilities.MessageBox("השינויים נשמרו בהצלחה!");
        }

        dialog.dispose();
    }                                                      

    public void setDialog(JDialog dialog) {
        this.dialog = dialog;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("CustomerAddOrUpdate"); // Create a JFrame
            CustomerAddOrUpdate customerAddOrUpdate = new CustomerAddOrUpdate(123456789); // Create your CashRegister JPanel

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(customerAddOrUpdate); // Add your CashRegister panel to the frame's content pane
            frame.pack(); // Pack the frame to fit its contents
            frame.setVisible(true); // Make the frame visible
        });
    }          
}

