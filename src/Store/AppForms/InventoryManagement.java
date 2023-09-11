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
import Store.Client.ServerCommunication.ClassType;
import Store.Client.ServerCommunication.EncodeCommandInventory;
import Store.Client.ServerCommunication.Format;
import Store.Inventories.InventoryItem;

public class InventoryManagement extends JPanel{
    private javax.swing.JPanel addProductPanel;
    private javax.swing.JButton addProductPanel_SubmitButton;
    private javax.swing.JLabel addProductPanel_mainLabel;
    private javax.swing.JLabel addProductPanel_productNameLabel;
    private javax.swing.JTextField addProductPanel_productNameTextField;
    private javax.swing.JLabel addProductPanel_quantityLabel;
    private javax.swing.JTextField addProductPanel_quantityTextField;
    private javax.swing.JLabel addProductPanel_unitPriceLabel;
    private javax.swing.JTextField addProductPanel_unitPriceTextField;
    private javax.swing.JTable InventoryTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox<String> addProductPanel_categoryComboBox;
    private javax.swing.JLabel addProductPanel_categoryLabel;

    private List<InventoryItem> items;
    private Map<String, InventoryItem> itemsMap;
    private String branch, command, response;

    public InventoryManagement(String branch) {
        initComponents();

        this.branch = branch;
        LoadInventory();
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        InventoryTable = new javax.swing.JTable();
        addProductPanel = new javax.swing.JPanel();
        addProductPanel_productNameLabel = new javax.swing.JLabel();
        addProductPanel_productNameTextField = new javax.swing.JTextField();
        addProductPanel_unitPriceLabel = new javax.swing.JLabel();
        addProductPanel_unitPriceTextField = new javax.swing.JTextField();
        addProductPanel_quantityLabel = new javax.swing.JLabel();
        addProductPanel_quantityTextField = new javax.swing.JTextField();
        addProductPanel_mainLabel = new javax.swing.JLabel();
        addProductPanel_categoryLabel = new javax.swing.JLabel();
        addProductPanel_categoryComboBox = new javax.swing.JComboBox<>();
        addProductPanel_SubmitButton = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setMinimumSize(new java.awt.Dimension(1044, 670));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1044, 670));

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        InventoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "מחק", "ערוך", "כמות במלאי", "מחיר ליחידה", "קטגוריה", "שם המוצר"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
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
        jScrollPane3.setViewportView(InventoryTable);
        if (InventoryTable.getColumnModel().getColumnCount() > 0) {
            InventoryTable.getColumnModel().getColumn(0).setResizable(false);
            InventoryTable.getColumnModel().getColumn(1).setResizable(false);
            InventoryTable.getColumnModel().getColumn(2).setResizable(false);
            InventoryTable.getColumnModel().getColumn(3).setResizable(false);
            InventoryTable.getColumnModel().getColumn(4).setResizable(false);
            InventoryTable.getColumnModel().getColumn(5).setResizable(false);
        }

        addProductPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        addProductPanel_productNameLabel.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        addProductPanel_productNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        addProductPanel_productNameLabel.setText("שם המוצר");

        addProductPanel_productNameTextField.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        addProductPanel_productNameTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        addProductPanel_unitPriceLabel.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        addProductPanel_unitPriceLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        addProductPanel_unitPriceLabel.setText("מחיר ליחידה");

        addProductPanel_unitPriceTextField.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        addProductPanel_unitPriceTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        addProductPanel_quantityLabel.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        addProductPanel_quantityLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        addProductPanel_quantityLabel.setText("כמות");

        addProductPanel_quantityTextField.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        addProductPanel_quantityTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        addProductPanel_mainLabel.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        addProductPanel_mainLabel.setText("הוספת מוצר חדש");

        addProductPanel_categoryLabel.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        addProductPanel_categoryLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        addProductPanel_categoryLabel.setText("קטגוריה");

        addProductPanel_categoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "הלבשה", "הנעלה", "אקססוריז" }));

        addProductPanel_SubmitButton.setBackground(new java.awt.Color(102, 153, 0));
        addProductPanel_SubmitButton.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        addProductPanel_SubmitButton.setText("הוסף מוצר למלאי");
        addProductPanel_SubmitButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        addProductPanel_SubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductPanel_SubmitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addProductPanelLayout = new javax.swing.GroupLayout(addProductPanel);
        addProductPanel.setLayout(addProductPanelLayout);
        addProductPanelLayout.setHorizontalGroup(
            addProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addProductPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(addProductPanel_SubmitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(addProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(addProductPanelLayout.createSequentialGroup()
                        .addComponent(addProductPanel_categoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addProductPanel_categoryLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addProductPanel_quantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addProductPanel_quantityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addProductPanel_unitPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addProductPanel_unitPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addProductPanel_productNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addProductPanel_productNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addProductPanel_mainLabel))
                .addGap(17, 17, 17))
        );
        addProductPanelLayout.setVerticalGroup(
            addProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addProductPanelLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(addProductPanel_mainLabel)
                .addGap(18, 18, 18)
                .addGroup(addProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addProductPanel_productNameLabel)
                    .addComponent(addProductPanel_productNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addProductPanel_quantityLabel)
                    .addComponent(addProductPanel_quantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addProductPanel_unitPriceLabel)
                    .addComponent(addProductPanel_unitPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addProductPanel_categoryLabel)
                    .addComponent(addProductPanel_categoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addProductPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addProductPanel_SubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 953, Short.MAX_VALUE)
                    .addComponent(addProductPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(addProductPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
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

    private void addProductPanel_SubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                             
        String name = addProductPanel_productNameTextField.getText();    
        String price = addProductPanel_unitPriceTextField.getText();
        String quantity = addProductPanel_quantityTextField.getText();
        String category = addProductPanel_categoryComboBox.getSelectedItem().toString();

        if( name.equals("") || price.equals("") || quantity.equals("") )
        {
            Utilities.MessageBox("וודא שכל השדות אינם ריקים");
            return;
        }
        else if(!Utilities.isNumeric(quantity) || !Utilities.isPositiveInt(quantity)) {
            Utilities.MessageBox("כמות חייבת להיות מספר חיובי");
            return;
        }
        else if(!Utilities.isDouble(price) || !Utilities.isPositiveDouble(price)) {
            Utilities.MessageBox("מחיר חייב להיות מספר חיובי");
            return;
        }

        InventoryItem temp = new InventoryItem(this.branch, name, category, Integer.parseInt(quantity), Double.parseDouble(price));
        
        command = EncodeCommandInventory.createNewItem(temp);
        response = Utilities.SendReceive(command);
        Utilities.MessageBox(Format.getFirstParam(response)); 
        
        LoadInventory();
    }  
    
    public void LoadInventory() {
        ClearTablesCells();
        command = EncodeCommandInventory.getInventoryItemsByBranch(branch);
        response = Utilities.SendReceive(command);
        switch (Format.getType(response)) {
            case EXCEPTION:
                Utilities.MessageBox(Format.getFirstParam(response));
                break;
            default:
                System.out.println("Response: " + response);
                items = Format.decodeInventoryItems(response);
                itemsMap = new HashMap<String, InventoryItem>();
        
                for(int i=0; i < items.size(); i++) {
                    InventoryItem temp = items.get(i);
                    addRowWithButtonsToCustomersTable(temp.getName(), temp.getCategory(), temp.getPrice(), temp.getQuantity(), temp.getProductID());
                    itemsMap.put(temp.getName(), temp);
                }
                CenterTablesCells();
                break;
        }
    }

    private void ClearTablesCells() {
        DefaultTableModel dmCustomerTable = (DefaultTableModel)InventoryTable.getModel();
        dmCustomerTable.getDataVector().removeAllElements();
        dmCustomerTable.fireTableDataChanged(); 
    }

    private void CenterTablesCells() {
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int columnIndex = 0; columnIndex < InventoryTable.getColumnCount(); columnIndex++) {
            InventoryTable.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }

        for (int columnIndex = 0; columnIndex < InventoryTable.getColumnCount(); columnIndex++) {
            InventoryTable.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }

        InventoryTable.getColumn("ערוך").setCellRenderer(new ButtonRenderer("src/Store/images/icon-edit.png"));
        InventoryTable.getColumn("ערוך").setCellEditor(new ButtonEditor(new JCheckBox(), "src/Store/images/icon-edit.png", "edit"));

        InventoryTable.getColumn("מחק").setCellRenderer(new ButtonRenderer("src/Store/images/icon-remove.png"));
        InventoryTable.getColumn("מחק").setCellEditor(new ButtonEditor(new JCheckBox(), "src/Store/images/icon-remove.png", "remove"));
    }

    private void addRowWithButtonsToCustomersTable(String name, String category, double price, int quantity, int productID ) { 
        Object[] rowData = { productID, productID, quantity, price, category, name};

        ((DefaultTableModel)InventoryTable.getModel()).addRow(rowData);
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
                    DefaultTableModel dmInventoryTable = (DefaultTableModel)InventoryTable.getModel();
                    int row = InventoryTable.getSelectedRow();
                    String name  = dmInventoryTable.getValueAt(row, 5).toString();
                    
                    InventoryItem item = itemsMap.get(name);
                    InventoryProductUpdate inventoryProductUpdate = new InventoryProductUpdate(item, branch);
                    
                    JDialog dialog = new JDialog();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setModal(true); // Block interaction with other windows

                    dialog.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            // Action to perform when child dialog is closed
                            ClearTablesCells();
                            LoadInventory();
                        }
                    });
                    
                    inventoryProductUpdate.setDialog(dialog);

                    dialog.add(inventoryProductUpdate);
                    
                    dialog.pack();
                    dialog.setLocationRelativeTo(null);
                    dialog.setVisible(true);
                    
                }  
                else if(buttonAction.equals("remove")) {
                    DefaultTableModel dmInventoryTable = (DefaultTableModel)InventoryTable.getModel();
                    int row = InventoryTable.getSelectedRow();
                    int id = itemsMap.get(dmInventoryTable.getValueAt(row, 5)).getProductID();
                    command = EncodeCommandInventory.deleteItem(id);
                    response = Utilities.SendReceive(command);
                    if(Format.getType(response) == ClassType.SUCCESS) 
                            dmInventoryTable.removeRow(row);
                    Utilities.MessageBox(Format.getFirstParam(response));
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
            JFrame frame = new JFrame("InventoryManagement"); // Create a JFrame
            InventoryManagement inventoryManagement = new InventoryManagement("חולון"); // Create your CashRegister JPanel

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(inventoryManagement); // Add your CashRegister panel to the frame's content pane
            frame.pack(); // Pack the frame to fit its contents
            frame.setVisible(true); // Make the frame visible
        });
    }
    
}
