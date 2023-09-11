package Store.AppForms;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

import Store.Utilities;
import Store.Client.ServerCommunication.EncodeCommandEmployee;
import Store.Client.ServerCommunication.EncodeCommandLogger;
import Store.Client.ServerCommunication.Format;
import Store.Employees.Employee;
import Store.Employees.EmployeeTitle;

public class EmployeesManagement extends JPanel {
    // Variables declaration - do not modify   
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton mainPanel_createNewEmployeeButton;
    private javax.swing.JTable mainPanel_employeesTable;
    private javax.swing.JRadioButton mainPanel_notSavingButton;
    private javax.swing.JRadioButton mainPanel_savingButton;
    private javax.swing.JLabel mainPanel_savingChatLabel;
    private javax.swing.JButton mainPanel_showLogsButton;
    private javax.swing.ButtonGroup savingChats;

    private List<Employee> employees;
    private Map<Integer, Employee> employeesMap;
    private String command, response;

    public EmployeesManagement() {
        initComponents();
        LoadEmployees();
        LoadSavingStatus();
    }
              
    private void initComponents() {

        savingChats = new javax.swing.ButtonGroup();
        mainPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        mainPanel_employeesTable = new javax.swing.JTable();
        mainPanel_createNewEmployeeButton = new javax.swing.JButton();
        mainPanel_showLogsButton = new javax.swing.JButton();
        mainPanel_savingButton = new javax.swing.JRadioButton();
        mainPanel_notSavingButton = new javax.swing.JRadioButton();
        mainPanel_savingChatLabel = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setMinimumSize(new java.awt.Dimension(1044, 670));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1044, 670));

        mainPanel.setBackground(new java.awt.Color(250, 250, 250));
        mainPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        mainPanel_employeesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "מחק", "ערוך", "תפקיד", "סיסמה", "סניף", "חשבון בנק", "תעודת זהות", "טלפון", "שם מלא"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(mainPanel_employeesTable);
        if (mainPanel_employeesTable.getColumnModel().getColumnCount() > 0) {
            mainPanel_employeesTable.getColumnModel().getColumn(0).setResizable(false);
            mainPanel_employeesTable.getColumnModel().getColumn(1).setResizable(false);
            mainPanel_employeesTable.getColumnModel().getColumn(2).setResizable(false);
            mainPanel_employeesTable.getColumnModel().getColumn(3).setResizable(false);
            mainPanel_employeesTable.getColumnModel().getColumn(4).setResizable(false);
            mainPanel_employeesTable.getColumnModel().getColumn(5).setResizable(false);
            mainPanel_employeesTable.getColumnModel().getColumn(6).setResizable(false);
            mainPanel_employeesTable.getColumnModel().getColumn(7).setResizable(false);
            mainPanel_employeesTable.getColumnModel().getColumn(8).setResizable(false);
        }

        mainPanel_createNewEmployeeButton.setBackground(new java.awt.Color(102, 153, 0));
        mainPanel_createNewEmployeeButton.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        mainPanel_createNewEmployeeButton.setText("יצירת עובד חדש");
        mainPanel_createNewEmployeeButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mainPanel_createNewEmployeeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainPanel_createNewEmployeeButtonActionPerformed(evt);
            }
        });

        mainPanel_showLogsButton.setBackground(new java.awt.Color(204, 153, 0));
        mainPanel_showLogsButton.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        mainPanel_showLogsButton.setText("ניהול לוגים");
        mainPanel_showLogsButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mainPanel_showLogsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainPanel_showLogsButtonActionPerformed(evt);
            }
        });

        savingChats.add(mainPanel_savingButton);
        mainPanel_savingButton.setText("עם שמירה");
        mainPanel_savingButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        mainPanel_savingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainPanel_savingButtonActionPerformed(evt);
            }
        });

        savingChats.add(mainPanel_notSavingButton);
        mainPanel_notSavingButton.setText("ללא שמירה");
        mainPanel_notSavingButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        mainPanel_notSavingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainPanel_notSavingButtonActionPerformed(evt);
            }
        });

        mainPanel_savingChatLabel.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        mainPanel_savingChatLabel.setText("מנגנון שמירת שיחות");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap(24, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 947, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(mainPanel_notSavingButton)
                        .addGap(18, 18, 18)
                        .addComponent(mainPanel_savingButton)
                        .addGap(32, 32, 32)
                        .addComponent(mainPanel_savingChatLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mainPanel_showLogsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(mainPanel_createNewEmployeeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainPanel_showLogsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mainPanel_createNewEmployeeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mainPanel_savingChatLabel)
                    .addComponent(mainPanel_savingButton)
                    .addComponent(mainPanel_notSavingButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void mainPanel_showLogsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        
    }

    private void mainPanel_createNewEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        EmployeeAddOrUpdate employeeAddOrUpdate = new EmployeeAddOrUpdate();
        
        JDialog dialog = new JDialog();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setModal(true); // Block interaction with other windows

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // Action to perform when child dialog is closed
                ClearTablesCells();
                LoadEmployees();
            }
        });
        employeeAddOrUpdate.setDialog(dialog);
        dialog.add(employeeAddOrUpdate);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void mainPanel_notSavingButtonActionPerformed(java.awt.event.ActionEvent evt) {
        command = EncodeCommandLogger.turnOffSavingChat();
        response = Utilities.SendReceive(command);

        switch (Format.getType(response)) {
            case EXCEPTION:
                Utilities.MessageBox(Format.getFirstParam(response));
                break;
            default:
                break;
        }
    }

    private void mainPanel_savingButtonActionPerformed(java.awt.event.ActionEvent evt) {
        command = EncodeCommandLogger.turnOnSavingChat();
        response = Utilities.SendReceive(command);

        switch (Format.getType(response)) {
            case EXCEPTION:
                Utilities.MessageBox(Format.getFirstParam(response));
                break;
            default:
                break;
        }
    }

    public void LoadEmployees() {
        ClearTablesCells();
        command = EncodeCommandEmployee.getEmployees();
        response = Utilities.SendReceive(command);

        switch (Format.getType(response)) {
            case EXCEPTION:
                Utilities.MessageBox(Format.getFirstParam(response));
                break;
            case EMPTY:
                break;
            default:
                employees = Format.decodeEmployees(response);
                employeesMap = new HashMap<Integer, Employee>();
        
                for(int i=0; i < employees.size(); i++) {
                    Employee temp = employees.get(i);
                    addRowWithButtonsToEmployeesTable(temp.getFullName(), temp.getPhoneNumber(), temp.getId(), temp.getBankAccount(), temp.getBranch(), temp.getPassword(), temp.getTitle());
        
                    employeesMap.put(temp.getId(), temp);
                }
                CenterTablesCells();
            break;
        }
        
        // EmployeeDAO DAO = new EmployeeDAO();
        // try {
        //     employees = DAO.getEmployees();
        // } catch (SQLException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        // employeesMap = new HashMap<Integer, Employee>();
        
        // for(int i=0; i < employees.size(); i++) {
        //     Employee temp = employees.get(i);
        //     addRowWithButtonsToEmployeesTable(temp.getFullName(), temp.getPhoneNumber(), temp.getId(), temp.getBankAccount(), temp.getBranch(), temp.getPassword(), temp.getTitle());

        //     employeesMap.put(temp.getId(), temp);
        // }
        // CenterTablesCells();
    }

    public void LoadSavingStatus() {
        command = EncodeCommandLogger.getSavingChatStatus();
        response = Utilities.SendReceive(command);

        switch (Format.getType(response)) {
            case EXCEPTION:
                Utilities.MessageBox(Format.getFirstParam(response));
                break;
            default:
                String status = Format.getFirstParam(response);
                switch(status) {
                    case "Yes":
                        mainPanel_savingButton.setSelected(true);
                        break;
                    case "No":
                        Utilities.MessageBox("Status");
                        mainPanel_notSavingButton.setSelected(true);
                        break;
                }
                break;
        }
        
    }
    private void ClearTablesCells() {
        DefaultTableModel dnmEmployeeTable = (DefaultTableModel)mainPanel_employeesTable.getModel();
        dnmEmployeeTable.getDataVector().removeAllElements();
        dnmEmployeeTable.fireTableDataChanged(); 
    }

    private void CenterTablesCells() {
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int columnIndex = 0; columnIndex < mainPanel_employeesTable.getColumnCount(); columnIndex++) {
            mainPanel_employeesTable.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }

        mainPanel_employeesTable.getColumn("ערוך").setCellRenderer(new ButtonRenderer("src/Store/images/icon-edit.png"));
        mainPanel_employeesTable.getColumn("ערוך").setCellEditor(new ButtonEditor(new JCheckBox(), "src/Store/images/icon-edit.png", "edit"));

        mainPanel_employeesTable.getColumn("מחק").setCellRenderer(new ButtonRenderer("src/Store/images/icon-remove.png"));
        mainPanel_employeesTable.getColumn("מחק").setCellEditor(new ButtonEditor(new JCheckBox(), "src/Store/images/icon-remove.png", "remove"));
    }

    private void addRowWithButtonsToEmployeesTable(String fullname, String phonenumber, int id, int bankAccount, String branch, String password, EmployeeTitle title) { 
        Object[] rowData = { id, id, title.toString(), password, branch, bankAccount, id, phonenumber, fullname};

        ((DefaultTableModel)mainPanel_employeesTable.getModel()).addRow(rowData);
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
        private int id;
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
            
            id = (value == null || value == "") ? -1 : Integer.parseInt(value.toString());
            isPushed = true;

            return button;
        }
      
        public Object getCellEditorValue() {
            if (isPushed) {
                if(buttonAction.equals("edit")) {
                    DefaultTableModel dnmEmployeeTable = (DefaultTableModel)mainPanel_employeesTable.getModel();
                    int row = mainPanel_employeesTable.getSelectedRow();
                    int id = (int)dnmEmployeeTable.getValueAt(row, 6);
                    
                    String command = EncodeCommandEmployee.getEmployeeByID(id);
                    String response = Utilities.SendReceive(command);
                    switch (Format.getType(response)) { 
                        case EXCEPTION:
                            Utilities.MessageBox(Format.getFirstParam(response));
                            break;
                        default:
                            Employee emp = Employee.deserializeFromString(response);
                            EmployeeAddOrUpdate employeeAddOrUpdate = new EmployeeAddOrUpdate(emp);
                            
                            JDialog dialog = new JDialog();
                            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                            dialog.setModal(true); // Block interaction with other windows
        
                            dialog.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosed(WindowEvent e) {
                                    // Action to perform when child dialog is closed
                                    ClearTablesCells();
                                    LoadEmployees();
                                }
                            });
                            employeeAddOrUpdate.setDialog(dialog);
                            dialog.add(employeeAddOrUpdate);
                            dialog.pack();
                            dialog.setLocationRelativeTo(null);
                            dialog.setVisible(true);
                            break;
                    }
                }  
                else if(buttonAction.equals("remove")) {
                    DefaultTableModel dnmEmployeeTable = (DefaultTableModel)mainPanel_employeesTable.getModel();
                    int row = mainPanel_employeesTable.getSelectedRow();
                    int id = (int)dnmEmployeeTable.getValueAt(row, 6);
                    command = EncodeCommandEmployee.deleteEmployee(id);
                    response = Utilities.SendReceive(command);
                    switch (Format.getType(response)) {
                        case EXCEPTION:
                            break;
                        case SUCCESS:
                            dnmEmployeeTable.removeRow(row);
                            break;
                        default:
                            break;
                        }
                    Utilities.MessageBox(Format.getFirstParam(response));
                }    
            }
            isPushed = false;
            return id;
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
            JFrame frame = new JFrame("EmployeesManagement"); // Create a JFrame
            EmployeesManagement employeesManagement = new EmployeesManagement(); // Create your CashRegister JPanel

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(employeesManagement); // Add your CashRegister panel to the frame's content pane
            frame.pack(); // Pack the frame to fit its contents
            frame.setVisible(true); // Make the frame visible
        });
    }
}
