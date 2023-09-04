package Store.AppForms;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Store.Utilities;
import Store.Inventories.InventoryItem;
import Store.Database.InventoryDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class InventoryProductUpdate extends JPanel{
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton mainPanel_SubmitButton;
    private javax.swing.JComboBox<String> mainPanel_categoryComboBox;
    private javax.swing.JLabel mainPanel_categoryLabel;
    private javax.swing.JLabel mainPanel_nameLabel;
    private javax.swing.JTextField mainPanel_nameTextField;
    private javax.swing.JLabel mainPanel_priceLabel;
    private javax.swing.JTextField mainPanel_priceTextField;
    private javax.swing.JLabel mainPanel_productIDDataLabel;
    private javax.swing.JLabel mainPanel_productIDLabel;
    private javax.swing.JLabel mainPanel_quantityLabel;
    private javax.swing.JTextField mainPanel_quantityTextField;

    private JDialog dialog;
    private InventoryItem item;
    private String branch;
    private boolean exitSuccessfully = false;

    public InventoryProductUpdate(InventoryItem item, String branch) {
        initComponents();

        this.item = item;
        this.branch = branch;

        mainPanel_productIDDataLabel.setText(Integer.toString(item.getProductID()));
        mainPanel_nameTextField.setText(item.getName());
        mainPanel_priceTextField.setText(Double.toString(item.getPrice()));
        mainPanel_quantityTextField.setText(Integer.toString(item.getQuantity()));
        mainPanel_categoryComboBox.setSelectedItem(item.getCategory());
    }

    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        mainPanel_productIDLabel = new javax.swing.JLabel();
        mainPanel_productIDDataLabel = new javax.swing.JLabel();
        mainPanel_nameLabel = new javax.swing.JLabel();
        mainPanel_nameTextField = new javax.swing.JTextField();
        mainPanel_priceLabel = new javax.swing.JLabel();
        mainPanel_priceTextField = new javax.swing.JTextField();
        mainPanel_quantityLabel = new javax.swing.JLabel();
        mainPanel_quantityTextField = new javax.swing.JTextField();
        mainPanel_categoryLabel = new javax.swing.JLabel();
        mainPanel_categoryComboBox = new javax.swing.JComboBox<>();
        mainPanel_SubmitButton = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setMinimumSize(new java.awt.Dimension(1, 1));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(401, 338));

        mainPanel.setBackground(new java.awt.Color(250, 250, 250));
        mainPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        mainPanel_productIDLabel.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        mainPanel_productIDLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mainPanel_productIDLabel.setText("מס' סידורי");

        mainPanel_productIDDataLabel.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        mainPanel_productIDDataLabel.setForeground(new java.awt.Color(0, 102, 255));
        mainPanel_productIDDataLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mainPanel_productIDDataLabel.setText("מס' סידורי");

        mainPanel_nameLabel.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        mainPanel_nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mainPanel_nameLabel.setText("שם המוצר");

        mainPanel_nameTextField.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        mainPanel_nameTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        mainPanel_priceLabel.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        mainPanel_priceLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mainPanel_priceLabel.setText("מחיר ליחידה");

        mainPanel_priceTextField.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        mainPanel_priceTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        mainPanel_quantityLabel.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        mainPanel_quantityLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mainPanel_quantityLabel.setText("כמות במלאי");

        mainPanel_quantityTextField.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        mainPanel_quantityTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        mainPanel_categoryLabel.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        mainPanel_categoryLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mainPanel_categoryLabel.setText("קטגוריה");

        mainPanel_categoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "הלבשה", "הנעלה", "אקססוריז" }));

        mainPanel_SubmitButton.setBackground(new java.awt.Color(206, 148, 89));
        mainPanel_SubmitButton.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        mainPanel_SubmitButton.setText("שמור שינויים");
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap(31, Short.MAX_VALUE)
                        .addComponent(mainPanel_SubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mainPanel_nameTextField)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addGap(71, 71, 71)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(mainPanel_quantityTextField)
                                            .addComponent(mainPanel_categoryComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(mainPanel_productIDDataLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mainPanel_priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(mainPanel_productIDLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mainPanel_priceLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mainPanel_nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(mainPanel_quantityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(mainPanel_categoryLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainPanel_productIDLabel)
                    .addComponent(mainPanel_productIDDataLabel))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mainPanel_nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mainPanel_nameLabel))
                        .addGap(9, 9, 9)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mainPanel_priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mainPanel_priceLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mainPanel_quantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(mainPanel_quantityLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanel_categoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mainPanel_categoryLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
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
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }                  
    
    private void mainPanel_SubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {  
        InventoryDAO inventoryDAO = new InventoryDAO();   
        String name = mainPanel_nameTextField.getText();
        String price = mainPanel_priceTextField.getText();
        String quantity = mainPanel_quantityTextField.getText();
        String category = mainPanel_categoryComboBox.getSelectedItem().toString();

        if( name.equals("") || price.equals("") || quantity.equals("") )
        {
            Utilities.MessageBox("וודא שכל השדות אינם ריקים");
            return;
        }
        else if(!Utilities.isNumeric(quantity)) {
            Utilities.MessageBox("כמות חייבת להיות מספר");
            return;
        }
        else if(!Utilities.isDouble(price)) {
            Utilities.MessageBox("מחיר חייב להיות מספר");
            return;
        }

        InventoryItem temp = new InventoryItem(this.branch, item.getProductID(), name, category, Integer.parseInt(quantity), Double.parseDouble(price));
        //System.out.println(this.branch + ", " +  item.getProductID() + ", " +  name + ", " +  category + ", " +  Integer.parseInt(quantity) + ", " +  Double.parseDouble(price));
        inventoryDAO.updateItem(temp);

        Utilities.MessageBox("המוצר עודכן בהצלחה!");

        exitSuccessfully = true;
        dialog.dispose();
    }                                                      

    public boolean IsExitedSuccessfully() {
        return exitSuccessfully;
    }

    public void setDialog(JDialog dialog) {
        this.dialog = dialog;
    }                                                    

}
