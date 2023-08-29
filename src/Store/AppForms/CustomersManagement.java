package Store.AppForms;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

import Store.Utilities;
import Store.Customers.Customer;
import Store.Database.CustomerDAO;

public class CustomersManagement extends JPanel {
    // Variables declaration - do not modify   
    private javax.swing.JPanel jPanel1;              
    private javax.swing.JTable customersTable;
    private javax.swing.JScrollPane jScrollPane3;
    private ArrayList<Customer> customers;
    private Map<Integer, Customer> customersMap;

    public CustomersManagement() {
        initComponents();
        LoadCustomers();
    }
              
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        customersTable = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setMinimumSize(new java.awt.Dimension(1044, 670));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1044, 670));

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        customersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "מחק", "ערוך", "סוג", "תעודת זהות", "טלפון", "שם מלא"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(customersTable);
        if (customersTable.getColumnModel().getColumnCount() > 0) {
            customersTable.getColumnModel().getColumn(0).setResizable(false);
            customersTable.getColumnModel().getColumn(1).setResizable(false);
            customersTable.getColumnModel().getColumn(2).setResizable(false);
            customersTable.getColumnModel().getColumn(3).setResizable(false);
            customersTable.getColumnModel().getColumn(4).setResizable(false);
            customersTable.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 947, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>                        


    private void LoadCustomers() {
        CustomerDAO customerDAO = new CustomerDAO(); //TODO: DAO Needs to be on the Server-Side! 
        customers = customerDAO.getCustomers();
        customersMap = new HashMap<Integer, Customer>();

        for(int i=0; i < customers.size(); i++) {
            Customer temp = customers.get(i);
            addRowWithButtonsToCustomersTable(temp.getFullName(), temp.getPhoneNumber(), temp.getId(), temp.getType());

            customersMap.put(temp.getId(), temp);
        }

        CenterTablesCells();
    }
    private void ClearTablesCells() {
        DefaultTableModel dmCustomerTable = (DefaultTableModel)customersTable.getModel();
        dmCustomerTable.getDataVector().removeAllElements();
        dmCustomerTable.fireTableDataChanged(); 
    }

    private void CenterTablesCells() {
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int columnIndex = 0; columnIndex < customersTable.getColumnCount(); columnIndex++) {
            customersTable.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }

        for (int columnIndex = 0; columnIndex < customersTable.getColumnCount(); columnIndex++) {
            customersTable.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }

        customersTable.getColumn("ערוך").setCellRenderer(new ButtonRenderer("src/Store/images/icon-edit.png"));
        customersTable.getColumn("ערוך").setCellEditor(new ButtonEditor(new JCheckBox(), "src/Store/images/icon-edit.png", "edit"));

        customersTable.getColumn("מחק").setCellRenderer(new ButtonRenderer("src/Store/images/icon-remove.png"));
        customersTable.getColumn("מחק").setCellEditor(new ButtonEditor(new JCheckBox(), "src/Store/images/icon-remove.png", "remove"));
    }

    private void addRowWithButtonsToCustomersTable(String fullname, String phonenumber, int id, String type) { 
        Object[] rowData = { id, id, type, id, phonenumber, fullname};

        ((DefaultTableModel)customersTable.getModel()).addRow(rowData);
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        private String iconLocation;
        public ButtonRenderer(String iconLocation) {
            this.iconLocation = iconLocation;
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionForeground());
            } else {
                setForeground(table.getForeground());
                setBackground(table.getForeground());
            }

            setHorizontalAlignment(SwingConstants.CENTER);

            // Load and set the image icon
            ImageIcon icon = new ImageIcon(iconLocation);
            Image scaledImage = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(scaledImage));
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private int customerId;
        private boolean isPushed;
        private String buttonAction;
        private String iconLocation;
      
        public ButtonEditor(JCheckBox checkBox, String iconLocation, String buttonAction) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });

            this.buttonAction = buttonAction;
            this.iconLocation = iconLocation;
        }
      
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionForeground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getForeground());
            }

            ImageIcon icon = new ImageIcon(iconLocation);
            Image scaledImage = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledImage));
            
            customerId = (value == null || value == "") ? -1 : Integer.parseInt(value.toString());
            isPushed = true;

            return button;
        }
      
        public Object getCellEditorValue() {
            if (isPushed) {
                if(buttonAction.equals("edit")) {
                    DefaultTableModel dmCustomersTable = (DefaultTableModel)customersTable.getModel();
                    int row = customersTable.getSelectedRow();
                    int id = (int)dmCustomersTable.getValueAt(row, 3);

                    CustomerDAO customerDAO = new CustomerDAO(); //TODO: Add Server-Side Action to get Customer Info here
                    Customer customer = customerDAO.getCustomerByID(id);
                    CustomerAddOrUpdate customerAddOrUpdate = new CustomerAddOrUpdate(customer);
                    
                    JDialog dialog = new JDialog();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setModal(true); // Block interaction with other windows

                    dialog.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            // Action to perform when child dialog is closed
                            ClearTablesCells();
                            LoadCustomers();
                        }
                    });
                    
                    customerAddOrUpdate.setDialog(dialog);

                    dialog.add(customerAddOrUpdate);
                    
                    dialog.pack();
                    dialog.setLocationRelativeTo(null);
                    dialog.setVisible(true);
                    
                }  
                else if(buttonAction.equals("remove")) {
                    DefaultTableModel dmCustomersTable = (DefaultTableModel)customersTable.getModel();
                    int row = customersTable.getSelectedRow();
                    int id = (int)dmCustomersTable.getValueAt(row, 3);

                    CustomerDAO customerDAO = new CustomerDAO(); //TODO: Add Server-Side Action to get Customer Info here
                    customerDAO.deleteCustomer(id);

                    dmCustomersTable.removeRow(row);
                }    
            }
            
            isPushed = false;
            return customerId;
        }
      
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
      
        protected void fireEditingStopped() {
            try {
                super.fireEditingStopped();
            }
            catch( Exception e ) {

            }
        }
    } 
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("CustomersManagement"); // Create a JFrame
            CustomersManagement customersManagement = new CustomersManagement(); // Create your CashRegister JPanel

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(customersManagement); // Add your CashRegister panel to the frame's content pane
            frame.pack(); // Pack the frame to fit its contents
            frame.setVisible(true); // Make the frame visible
        });
    }
}
