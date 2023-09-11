package Store.AppForms;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.util.List;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import Store.Utilities;
import Store.Client.ServerCommunication.EncodeCommandInventory;
import Store.Client.ServerCommunication.EncodeCommandPurchaseHistory;
import Store.Client.ServerCommunication.Format;
import Store.Database.PurchaseHistoryDAO;
import Store.Inventories.*;
import Store.PurchaseHistory.*;

public class BranchReport extends JPanel {
    
    private javax.swing.JPanel filterPanel;
    private javax.swing.JButton filterPanel_StartButton;
    private javax.swing.JSeparator filterPanel__HeaderSeparator;
    private javax.swing.JComboBox<String> filterPanel_categoryComboBox;
    private javax.swing.JLabel filterPanel_categoryLabel;
    private javax.swing.JLabel filterPanel_filterLabel;
    private javax.swing.JComboBox<String> filterPanel_productComboBox;
    private javax.swing.JLabel filterPanel_productLabel;
    private javax.swing.JLabel filterPanel_timeSelectionLabel;
    private javax.swing.JRadioButton filterPanel_timeSelection_LastMonthButton;
    private javax.swing.JRadioButton filterPanel_timeSelection_LastWeekButton;
    private javax.swing.JRadioButton filterPanel_timeSelection_TodayButton;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTable mainPanel_prodcutTable;
    private javax.swing.JPanel revenuePanel;
    private javax.swing.JLabel revenuePanel_ILSLabel;
    private javax.swing.JLabel revenuePanel_productAmountDataLabel;
    private javax.swing.JLabel revenuePanel_productAmountLabel;
    private javax.swing.JLabel revenuePanel_revenueDataLabel;
    private javax.swing.JLabel revenuePanel_revenueLabel;
    private javax.swing.JLabel revenuePanel_totalLabel;
    private javax.swing.ButtonGroup timeSelectionGroup;

    private String branch, command, response;
    private Map<Integer, InventoryItem> itemsMap;
    private boolean isLoaded = false;


    public BranchReport(String branch) {
        initComponents();
        this.branch = branch;
        this.loadBranchInfo();
        this.isLoaded = true;
    }

    public void loadBranchInfo()
    {
        LoadProducts();
        // LoadProductsComboBox();
        filterPanel_StartButton.doClick();
    }

    private void initComponents() {

        timeSelectionGroup = new javax.swing.ButtonGroup();
        mainPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        mainPanel_prodcutTable = new javax.swing.JTable();
        revenuePanel = new javax.swing.JPanel();
        revenuePanel_totalLabel = new javax.swing.JLabel();
        revenuePanel_revenueLabel = new javax.swing.JLabel();
        revenuePanel_revenueDataLabel = new javax.swing.JLabel();
        revenuePanel_ILSLabel = new javax.swing.JLabel();
        revenuePanel_productAmountLabel = new javax.swing.JLabel();
        revenuePanel_productAmountDataLabel = new javax.swing.JLabel();
        filterPanel = new javax.swing.JPanel();
        filterPanel_filterLabel = new javax.swing.JLabel();
        filterPanel__HeaderSeparator = new javax.swing.JSeparator();
        filterPanel_timeSelectionLabel = new javax.swing.JLabel();
        filterPanel_timeSelection_TodayButton = new javax.swing.JRadioButton();
        filterPanel_timeSelection_LastWeekButton = new javax.swing.JRadioButton();
        filterPanel_timeSelection_LastMonthButton = new javax.swing.JRadioButton();
        filterPanel_categoryLabel = new javax.swing.JLabel();
        filterPanel_categoryComboBox = new javax.swing.JComboBox<>();
        filterPanel_productLabel = new javax.swing.JLabel();
        filterPanel_productComboBox = new javax.swing.JComboBox<>();
        filterPanel_StartButton = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setMinimumSize(new java.awt.Dimension(1044, 670));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1044, 670));

        mainPanel.setBackground(new java.awt.Color(250, 250, 250));
        mainPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mainPanel.setPreferredSize(new java.awt.Dimension(997, 650));

        mainPanel_prodcutTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "כמות שנמכרה", "מחיר ליח'", "קטגוריה", "שם המוצר"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(mainPanel_prodcutTable);

        revenuePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        revenuePanel_totalLabel.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        revenuePanel_totalLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        revenuePanel_totalLabel.setText("סך הכל");

        revenuePanel_revenueLabel.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        revenuePanel_revenueLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        revenuePanel_revenueLabel.setText("הכנסות:");

        revenuePanel_revenueDataLabel.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        revenuePanel_revenueDataLabel.setForeground(new java.awt.Color(0, 102, 0));
        revenuePanel_revenueDataLabel.setText("0");

        revenuePanel_ILSLabel.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        revenuePanel_ILSLabel.setForeground(new java.awt.Color(0, 102, 0));
        revenuePanel_ILSLabel.setText("₪");

        revenuePanel_productAmountLabel.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        revenuePanel_productAmountLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        revenuePanel_productAmountLabel.setText("כמות מוצרים:");

        revenuePanel_productAmountDataLabel.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        revenuePanel_productAmountDataLabel.setForeground(new java.awt.Color(0, 102, 0));
        revenuePanel_productAmountDataLabel.setText("0");

        javax.swing.GroupLayout revenuePanelLayout = new javax.swing.GroupLayout(revenuePanel);
        revenuePanel.setLayout(revenuePanelLayout);
        revenuePanelLayout.setHorizontalGroup(
            revenuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, revenuePanelLayout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(revenuePanel_productAmountDataLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(revenuePanel_productAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(revenuePanel_ILSLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(revenuePanel_revenueDataLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(revenuePanel_revenueLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(revenuePanel_totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        revenuePanelLayout.setVerticalGroup(
            revenuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, revenuePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(revenuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(revenuePanel_productAmountDataLabel)
                    .addComponent(revenuePanel_ILSLabel)
                    .addComponent(revenuePanel_productAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(revenuePanel_revenueDataLabel)
                    .addComponent(revenuePanel_totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(revenuePanel_revenueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        filterPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        filterPanel_filterLabel.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        filterPanel_filterLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        filterPanel_filterLabel.setText("סינון לפי");

        filterPanel__HeaderSeparator.setForeground(new java.awt.Color(205, 205, 205));

        filterPanel_timeSelectionLabel.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        filterPanel_timeSelectionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        filterPanel_timeSelectionLabel.setText("טווח זמן");

        timeSelectionGroup.add(filterPanel_timeSelection_TodayButton);
        filterPanel_timeSelection_TodayButton.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        filterPanel_timeSelection_TodayButton.setSelected(true);
        filterPanel_timeSelection_TodayButton.setText("היום");
        filterPanel_timeSelection_TodayButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        filterPanel_timeSelection_TodayButton.setIconTextGap(7);

        timeSelectionGroup.add(filterPanel_timeSelection_LastWeekButton);
        filterPanel_timeSelection_LastWeekButton.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        filterPanel_timeSelection_LastWeekButton.setText("שבוע");
        filterPanel_timeSelection_LastWeekButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        filterPanel_timeSelection_LastWeekButton.setIconTextGap(7);

        timeSelectionGroup.add(filterPanel_timeSelection_LastMonthButton);
        filterPanel_timeSelection_LastMonthButton.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        filterPanel_timeSelection_LastMonthButton.setText("חודש");
        filterPanel_timeSelection_LastMonthButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        filterPanel_timeSelection_LastMonthButton.setIconTextGap(7);

        filterPanel_categoryLabel.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        filterPanel_categoryLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        filterPanel_categoryLabel.setText("מחלקה");

        filterPanel_categoryComboBox.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        filterPanel_categoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "הכל", "הלבשה", "הנעלה", "אקססוריז" }));
        filterPanel_categoryComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterPanel_categoryComboBoxActionPerformed(evt);
            }
        });

        filterPanel_productLabel.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        filterPanel_productLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        filterPanel_productLabel.setText("מוצר");

        filterPanel_productComboBox.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        filterPanel_productComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "הכל" }));
        filterPanel_productComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterPanel_productComboBoxActionPerformed(evt);
            }
        });

        filterPanel_StartButton.setBackground(new java.awt.Color(0, 147, 250));
        filterPanel_StartButton.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        filterPanel_StartButton.setText("בצע סינון");
        filterPanel_StartButton.setMargin(new java.awt.Insets(5, 14, 3, 14));
        filterPanel_StartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterPanel_StartButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout filterPanelLayout = new javax.swing.GroupLayout(filterPanel);
        filterPanel.setLayout(filterPanelLayout);
        filterPanelLayout.setHorizontalGroup(
            filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filterPanelLayout.createSequentialGroup()
                .addGroup(filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(filterPanelLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(filterPanel_filterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(filterPanelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(filterPanel__HeaderSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, filterPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filterPanel_timeSelectionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, filterPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(filterPanel_timeSelection_TodayButton)
                    .addComponent(filterPanel_timeSelection_LastMonthButton)
                    .addComponent(filterPanel_timeSelection_LastWeekButton))
                .addGap(34, 34, 34))
            .addGroup(filterPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(filterPanel_categoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, filterPanelLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, filterPanelLayout.createSequentialGroup()
                                    .addComponent(filterPanel_categoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(33, 33, 33))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, filterPanelLayout.createSequentialGroup()
                                    .addComponent(filterPanel_productLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(35, 35, 35))))
                        .addComponent(filterPanel_productComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(filterPanel_StartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        filterPanelLayout.setVerticalGroup(
            filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filterPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(filterPanel_filterLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterPanel__HeaderSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(filterPanel_timeSelectionLabel)
                .addGap(18, 18, 18)
                .addComponent(filterPanel_timeSelection_TodayButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(filterPanel_timeSelection_LastWeekButton)
                .addGap(12, 12, 12)
                .addComponent(filterPanel_timeSelection_LastMonthButton)
                .addGap(29, 29, 29)
                .addComponent(filterPanel_categoryLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(filterPanel_categoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(filterPanel_productLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterPanel_productComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(filterPanel_StartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(revenuePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addGap(18, 18, 18)
                .addComponent(filterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(filterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addGap(18, 18, 18)
                .addComponent(revenuePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
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
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void filterPanel_categoryComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        if(!isLoaded)
            return;

        String item = filterPanel_categoryComboBox.getSelectedItem().toString();

        if(item.equals("הכל")) 
            filterPanel_productComboBox.setEnabled(true);
        else {
            filterPanel_productComboBox.setEnabled(false);
            filterPanel_productComboBox.setSelectedItem("הכל");
        }
            
    }

    private void filterPanel_productComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        if(!isLoaded)
            return;

        String item = filterPanel_productComboBox.getSelectedItem().toString();

        if(item.equals("הכל"))
            filterPanel_categoryComboBox.setEnabled(true);
        else {
            filterPanel_categoryComboBox.setEnabled(false);
            filterPanel_categoryComboBox.setSelectedItem("הכל");
        }  
    }

    private void filterPanel_StartButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int days = 0;
        List<PurchasedItem> purchasedItems;

        for (Enumeration<AbstractButton> buttons = timeSelectionGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                if(button.getText().equals("היום"))         days = 0;
                else if(button.getText().equals("שבוע"))    days = 7;
                else if(button.getText().equals("חודש"))    days = 30;

                break;
            }
        }
        
        PurchaseHistoryDAO dao = new PurchaseHistoryDAO();
        try {
        purchasedItems = dao.getItemsFromOrdersByBranchAndDays(branch, days);
        // Set quantity for all items to 0
        for (Map.Entry<Integer, InventoryItem> entry : itemsMap.entrySet()) {
            Integer key = entry.getKey();
            InventoryItem value = entry.getValue();
            value.setQuantity(0);
            
            itemsMap.put(key, value);
        }
        
        for(PurchasedItem item: purchasedItems) {
            int quantity = itemsMap.get(item.getProductID()).getQuantity() + 1;
            InventoryItem temp = itemsMap.get(item.getProductID());
            temp.setQuantity(quantity);
    
        } 
    } catch (SQLException e)  
    { 
        System.out.println(e);
    }
        // command = EncodeCommandPurchaseHistory.getItemsFromOrdersByBranchAndDays(branch, days);
        // response = Utilities.SendReceive(command);
        // switch (Format.getType(response)) {
        //     case EXCEPTION:
        //         Utilities.MessageBox(Format.getFirstParam(response));
        //         break;
        //     default:
        //         purchasedItems = Format.decodePurchasedItems(Format.getFirstParam(response));

        //         // Set quantity for all items to 0
        //         for (Map.Entry<Integer, InventoryItem> entry : itemsMap.entrySet()) {
        //             Integer key = entry.getKey();
        //             InventoryItem value = entry.getValue();
        //             value.setQuantity(0);
                    
        //             itemsMap.put(key, value);
        //         }
                
        //         for(PurchasedItem item: purchasedItems) {
        //             int quantity = itemsMap.get(item.getProductID()).getQuantity() + 1;
        //             InventoryItem temp = itemsMap.get(item.getProductID());
        //             temp.setQuantity(quantity);
    
        //             itemsMap.put(item.getProductID(), temp);
        //         }
        //         LoadReport();
        //         break;
        // }
    }


    private void LoadProducts() {
        command = EncodeCommandInventory.getInventoryItemsByBranch(branch);
        response = Utilities.SendReceive(command);
        switch (Format.getType(response)) {
            case EXCEPTION:
                Utilities.MessageBox(Format.getFirstParam(response));
                break;
            default:
                List<InventoryItem> items = Format.decodeInventoryItems(response);
                itemsMap = new HashMap<Integer, InventoryItem>();
                
                for(int i=0; i < items.size(); i++) {
                    InventoryItem temp = items.get(i);
                    itemsMap.put(temp.getProductID(), temp);
                }
                break;
        }
        
    }

    public void LoadReport() {
        ClearTablesCells();

        int revenue = 0;
        int products = 0;
        revenuePanel_revenueDataLabel.setText("0");

        String selectedCategory = filterPanel_categoryComboBox.getSelectedItem().toString();
        String selectedProduct = filterPanel_productComboBox.getSelectedItem().toString();

        for (Map.Entry<Integer, InventoryItem> entry : itemsMap.entrySet()) {
            InventoryItem temp = entry.getValue();
            if(temp.getQuantity() > 0) {
                if( (!selectedCategory.equals("הכל") && temp.getCategory().equals(selectedCategory))
                    || (!selectedProduct.equals("הכל") && temp.getName().equals(selectedProduct))
                    || (selectedProduct.equals("הכל") && selectedCategory.equals("הכל"))) {
                        addRowToProductTable(temp.getName(), temp.getCategory(), temp.getPrice(), temp.getQuantity());
                        
                        revenue += temp.getPrice() * temp.getQuantity();
                        products += temp.getQuantity();
                    }
                    
            }    
        }

        revenuePanel_revenueDataLabel.setText(Integer.toString(revenue));
        revenuePanel_productAmountDataLabel.setText(Integer.toString(products));
        
        CenterTablesCells();
    }

    private void LoadProductsComboBox() {
        filterPanel_productComboBox.removeAllItems();
        filterPanel_productComboBox.addItem("הכל");

        for (Map.Entry<Integer, InventoryItem> entry : itemsMap.entrySet()) {
            filterPanel_productComboBox.addItem(entry.getValue().getName());
        }
    }

    private void addRowToProductTable(String name, String category, double price, int quantity) { 
        Object[] rowData = { quantity, price, category, name};

        ((DefaultTableModel)mainPanel_prodcutTable.getModel()).addRow(rowData);
    }

    private void ClearTablesCells() {
        DefaultTableModel dmCustomerTable = (DefaultTableModel)mainPanel_prodcutTable.getModel();
        dmCustomerTable.getDataVector().removeAllElements();
        dmCustomerTable.fireTableDataChanged(); 
    }

    private void CenterTablesCells() {
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int columnIndex = 0; columnIndex < mainPanel_prodcutTable.getColumnCount(); columnIndex++) {
            mainPanel_prodcutTable.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }

        for (int columnIndex = 0; columnIndex < mainPanel_prodcutTable.getColumnCount(); columnIndex++) {
            mainPanel_prodcutTable.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Branch Report"); // Create a JFrame
            BranchReport cashRegister = new BranchReport("חולון"); // Create your CashRegister JPanel

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(cashRegister); // Add your CashRegister panel to the frame's content pane
            frame.pack(); // Pack the frame to fit its contents
            frame.setVisible(true); // Make the frame visible
        });
    }
}
