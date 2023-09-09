package Store.AppForms;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;

import Store.Utilities;
import Store.Client.ServerCommunication.EncodeCommandChat;
import Store.Client.ServerCommunication.Format;
import Store.Database.InventoryDAO;
import Store.Database.SocketData;
import Store.Employees.Employee;
import Store.Employees.EmployeeTitle;
import Store.Inventories.InventoryItem;

public class Chats extends JPanel {
    
    private javax.swing.JPanel chatsPanel;
    private javax.swing.JTable chatsPanel_AvailableBranchesTable;
    private javax.swing.JButton chatsPanel_reloadAvailableBranchesTableButton;
    private javax.swing.JSeparator chatsPanel__HeaderSeparator;
    private javax.swing.JLabel chatsPanel_mainLabel;
    private javax.swing.JLabel chatsPanel_subMainLabel;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextPane liveChatPanel_historyTextPane;
    private javax.swing.JPanel liveChatPanel;
    private javax.swing.JTextField liveChatPanel_messageTextField;
    private javax.swing.JButton liveChatPanel_sendMessageButton;
    private javax.swing.JPanel manageChatsPanel;
    private javax.swing.JTable manageChatsPanel_AvailableChatsTable;
    private javax.swing.JSeparator manageChatsPanel__HeaderSeparator1;
    private javax.swing.JLabel manageChatsPanel_mainLabel;
    private javax.swing.JButton manageChatsPanel_reloadAvailableChatsTableButton;

    private Employee emp;

    // Chat Styles
    private StyledDocument doc;
    private Style regularStyle;
    private Style otherClientStyle;
    private Style boldStyle;

    public Chats(Employee emp) {
        initComponents();
        initComponentsSettings();
        setLiveChat(false);

        this.emp = emp;
    }

    private void initComponents() {

        chatsPanel = new javax.swing.JPanel();
        chatsPanel_mainLabel = new javax.swing.JLabel();
        chatsPanel_subMainLabel = new javax.swing.JLabel();
        chatsPanel__HeaderSeparator = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        chatsPanel_AvailableBranchesTable = new javax.swing.JTable();
        chatsPanel_reloadAvailableBranchesTableButton = new javax.swing.JButton();
        manageChatsPanel = new javax.swing.JPanel();
        manageChatsPanel_mainLabel = new javax.swing.JLabel();
        manageChatsPanel__HeaderSeparator1 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        manageChatsPanel_AvailableChatsTable = new javax.swing.JTable();
        manageChatsPanel_reloadAvailableChatsTableButton = new javax.swing.JButton();
        liveChatPanel = new javax.swing.JPanel();
        liveChatPanel_messageTextField = new javax.swing.JTextField();
        liveChatPanel_sendMessageButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        liveChatPanel_historyTextPane = new javax.swing.JTextPane();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setMinimumSize(new java.awt.Dimension(1044, 670));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1044, 670));

        chatsPanel.setBackground(new java.awt.Color(204, 204, 204));
        chatsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        chatsPanel_mainLabel.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        chatsPanel_mainLabel.setText("סניפים זמינים");

        chatsPanel_subMainLabel.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        chatsPanel_subMainLabel.setText("* סניף נחשב פעיל אם לפחות עובד אחד מחובר");

        chatsPanel__HeaderSeparator.setForeground(new java.awt.Color(0, 0, 0));

        chatsPanel_AvailableBranchesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "התחל שיחה", "שם סניף"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(chatsPanel_AvailableBranchesTable);
        if (chatsPanel_AvailableBranchesTable.getColumnModel().getColumnCount() > 0) {
            chatsPanel_AvailableBranchesTable.getColumnModel().getColumn(0).setResizable(false);
            chatsPanel_AvailableBranchesTable.getColumnModel().getColumn(1).setResizable(false);
        }

        chatsPanel_reloadAvailableBranchesTableButton.setBackground(new java.awt.Color(51, 153, 0));
        chatsPanel_reloadAvailableBranchesTableButton.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        chatsPanel_reloadAvailableBranchesTableButton.setText("רענן");
        chatsPanel_reloadAvailableBranchesTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chatsPanel_reloadAvailableBranchesTableButtonActionPerformed(evt);
            }
        });

        manageChatsPanel.setBackground(new java.awt.Color(204, 204, 204));

        manageChatsPanel_mainLabel.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        manageChatsPanel_mainLabel.setText("שיחות פעילות");

        manageChatsPanel__HeaderSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        manageChatsPanel_AvailableChatsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "הצטרף", "סניף יעד", "נמען", "שם העובד"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(manageChatsPanel_AvailableChatsTable);
        if (manageChatsPanel_AvailableChatsTable.getColumnModel().getColumnCount() > 0) {
            manageChatsPanel_AvailableChatsTable.getColumnModel().getColumn(0).setResizable(false);
            manageChatsPanel_AvailableChatsTable.getColumnModel().getColumn(1).setResizable(false);
            manageChatsPanel_AvailableChatsTable.getColumnModel().getColumn(2).setResizable(false);
            manageChatsPanel_AvailableChatsTable.getColumnModel().getColumn(3).setResizable(false);
        }

        manageChatsPanel_reloadAvailableChatsTableButton.setBackground(new java.awt.Color(51, 153, 0));
        manageChatsPanel_reloadAvailableChatsTableButton.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        manageChatsPanel_reloadAvailableChatsTableButton.setText("רענן");
        manageChatsPanel_reloadAvailableChatsTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageChatsPanel_reloadAvailableChatsTableButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout manageChatsPanelLayout = new javax.swing.GroupLayout(manageChatsPanel);
        manageChatsPanel.setLayout(manageChatsPanelLayout);
        manageChatsPanelLayout.setHorizontalGroup(
            manageChatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manageChatsPanelLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(manageChatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(manageChatsPanel__HeaderSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(manageChatsPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(manageChatsPanel_mainLabel)
                        .addGap(51, 51, 51)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(manageChatsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(manageChatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(manageChatsPanel_reloadAvailableChatsTableButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        manageChatsPanelLayout.setVerticalGroup(
            manageChatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manageChatsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(manageChatsPanel_mainLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manageChatsPanel__HeaderSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manageChatsPanel_reloadAvailableChatsTableButton)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout chatsPanelLayout = new javax.swing.GroupLayout(chatsPanel);
        chatsPanel.setLayout(chatsPanelLayout);
        chatsPanelLayout.setHorizontalGroup(
            chatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chatsPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(chatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(chatsPanelLayout.createSequentialGroup()
                                .addGroup(chatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chatsPanel_subMainLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(chatsPanel__HeaderSeparator, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(72, 72, 72))
                            .addGroup(chatsPanelLayout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(chatsPanel_mainLabel)
                                .addGap(20, 20, 20))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chatsPanelLayout.createSequentialGroup()
                        .addGroup(chatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chatsPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(manageChatsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chatsPanel_reloadAvailableBranchesTableButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        chatsPanelLayout.setVerticalGroup(
            chatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatsPanelLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(chatsPanel_mainLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chatsPanel_subMainLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chatsPanel__HeaderSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chatsPanel_reloadAvailableBranchesTableButton)
                .addGap(35, 35, 35)
                .addComponent(manageChatsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        liveChatPanel.setPreferredSize(new java.awt.Dimension(575, 800));

        liveChatPanel_messageTextField.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        liveChatPanel_messageTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        liveChatPanel_messageTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                liveChatPanel_messageTextFieldActionPerformed(evt);
            }
        });

        liveChatPanel_sendMessageButton.setBackground(new java.awt.Color(51, 153, 0));
        liveChatPanel_sendMessageButton.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        liveChatPanel_sendMessageButton.setText("שלח");
        liveChatPanel_sendMessageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                liveChatPanel_sendMessageButtonActionPerformed(evt);
            }
        });

        liveChatPanel_historyTextPane.setEditable(false);
        jScrollPane2.setViewportView(liveChatPanel_historyTextPane);

        javax.swing.GroupLayout liveChatPanelLayout = new javax.swing.GroupLayout(liveChatPanel);
        liveChatPanel.setLayout(liveChatPanelLayout);
        liveChatPanelLayout.setHorizontalGroup(
            liveChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(liveChatPanelLayout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(liveChatPanel_sendMessageButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(liveChatPanel_messageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(liveChatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        liveChatPanelLayout.setVerticalGroup(
            liveChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, liveChatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(liveChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(liveChatPanel_messageTextField)
                    .addComponent(liveChatPanel_sendMessageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(liveChatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chatsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(liveChatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chatsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void initComponentsSettings() {
        liveChatPanel_historyTextPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        // Create a StyledDocument
        doc = liveChatPanel_historyTextPane.getStyledDocument();

        // Create styles
        regularStyle = doc.addStyle("Regular", null);
        StyleConstants.setFontFamily(regularStyle, "Calibri");
        StyleConstants.setFontSize(regularStyle, 18);

        boldStyle = doc.addStyle("Bold", regularStyle);
        StyleConstants.setBold(boldStyle, true);

        otherClientStyle = doc.addStyle("Bold", boldStyle);
        StyleConstants.setForeground(otherClientStyle, Color.blue);

    }
    
    private void setLiveChat(boolean status) {
        if(status) {
            liveChatPanel_messageTextField.setEnabled(true);
            liveChatPanel_sendMessageButton.setEnabled(true);
        }
        else {
            liveChatPanel_messageTextField.setEnabled(false);
        liveChatPanel_sendMessageButton.setEnabled(false);
        }
    }
    
    public void LoadAvailableBranches() {
        ClearTablesCells();

        String command = EncodeCommandChat.getAvailableBranches(emp.getBranch());
        Set<String> branches;

        Utilities.getClientSocketData().getOutputStream().println(command);
        try {
            String res = Utilities.getClientSocketData().getInputStream().readLine();
            if( res != "" ) {
                branches = Format.decodeAvailableBranches(res);
            
            for(String branch : branches)
                addRowWithButtonsToAvailableBranchesTable(branch);
            }   
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            branches = new HashSet<>();
        }

        CenterTablesCells();
    }
    

    private void liveChatPanel_sendMessageButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String message = liveChatPanel_messageTextField.getText();
        String senderName = emp.getFullName();

        appendMessage(senderName, message, true);
        sendMessage(senderName, message);

        liveChatPanel_messageTextField.setText("");
    }

    private void liveChatPanel_messageTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        liveChatPanel_sendMessageButton.doClick();
    }

    private void manageChatsPanel_reloadAvailableChatsTableButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void chatsPanel_reloadAvailableBranchesTableButtonActionPerformed(java.awt.event.ActionEvent evt) {
        LoadAvailableBranches();
    }

    private void appendMessage(String senderName, String message, boolean isCurrClient) {
        // Format the message as HTML with sender's name in bold
        try {
            if(!isCurrClient) 
                doc.insertString(doc.getLength(), senderName + ": ", otherClientStyle);  
            else 
                doc.insertString(doc.getLength(), senderName + ": ", boldStyle);

            doc.insertString(doc.getLength(), message + "\n", regularStyle);
             
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    private void sendMessage(String senderName, String message) {
        if (!message.isEmpty()) {
            Utilities.getClientSocketData().getOutputStream().println(senderName + ": " + message);
        }
    }

    public void listen(Employee emp, SocketData socketData) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String message;
                    while ((message = socketData.getInputStream().readLine()) != null) {
                        //String[] messageSplit = message.split(": ");
                        //appendMessage(messageSplit[0], messageSplit[1], false);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void ClearTablesCells() {
        DefaultTableModel dmCustomerTable = (DefaultTableModel)chatsPanel_AvailableBranchesTable.getModel();
        dmCustomerTable.getDataVector().removeAllElements();
        dmCustomerTable.fireTableDataChanged(); 
    }

    private void CenterTablesCells() {
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int columnIndex = 0; columnIndex < chatsPanel_AvailableBranchesTable.getColumnCount(); columnIndex++) {
            chatsPanel_AvailableBranchesTable.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }

        for (int columnIndex = 0; columnIndex < manageChatsPanel_AvailableChatsTable.getColumnCount(); columnIndex++) {
            manageChatsPanel_AvailableChatsTable.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }

        chatsPanel_AvailableBranchesTable.getColumn("התחל שיחה").setCellRenderer(new ButtonRenderer("src/Store/images/icon-start-chatting.png"));
        chatsPanel_AvailableBranchesTable.getColumn("התחל שיחה").setCellEditor(new ButtonEditor(new JCheckBox(), "src/Store/images/icon-start-chatting"));

        manageChatsPanel_AvailableChatsTable.getColumn("הצטרף").setCellRenderer(new ButtonRenderer("src/Store/images/icon-start-chatting"));
        manageChatsPanel_AvailableChatsTable.getColumn("הצטרף").setCellEditor(new ButtonEditor(new JCheckBox(), "src/Store/images/icon-start-chatting"));
    }


    private void addRowWithButtonsToAvailableBranchesTable(String branch) { 
        Object[] rowData = { branch, branch };

        ((DefaultTableModel)chatsPanel_AvailableBranchesTable.getModel()).addRow(rowData);
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
        private String branch;
        private boolean isPushed;
        private String iconLocation;
      
        public ButtonEditor(JCheckBox checkBox, String iconLocation) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });

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
            
            branch = (value == null || value == "") ? "" : value.toString();
            isPushed = true;

            return button;
        }
      
        public Object getCellEditorValue() {
            if (isPushed) {
                
            }
            
            isPushed = false;
            return branch;
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
            JFrame frame = new JFrame("Cash Register"); // Create a JFrame
            Employee emp = new Employee("ישראל ישראלי", "0528921319", 123456789, 212444, "חולון", "1111", EmployeeTitle.CASHIER);
            Chats chatPanel = new Chats(emp); // Create your CashRegister JPanel

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(chatPanel); // Add your CashRegister panel to the frame's content pane
            frame.pack(); // Pack the frame to fit its contents
            frame.setVisible(true); // Make the frame visible
        });
    }
}
