package Store.AppForms;

import java.awt.*;
import javax.swing.*;

import Store.Employees.Employee;
import Store.Employees.EmployeeTitle;

public class Chats extends JPanel {
    
    private javax.swing.JPanel chatsPanel;
    private javax.swing.JTable chatsPanel_AvailableBranchesTable;
    private javax.swing.JSeparator chatsPanel__HeaderSeparator;
    private javax.swing.JLabel chatsPanel_mainLabel;
    private javax.swing.JLabel chatsPanel_subMainLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel liveChatPanel;
    private javax.swing.JTextArea liveChatPanel_historyTextArea;
    private javax.swing.JTextField liveChatPanel_messageTextField;
    private javax.swing.JButton liveChatPanel_sendMessageButton;
    private javax.swing.JPanel manageChatsPanel;
    private javax.swing.JTable manageChatsPanel_AvailableChatsTable;
    private javax.swing.JSeparator manageChatsPanel__HeaderSeparator1;
    private javax.swing.JLabel manageChatsPanel_mainLabel;

    private Employee emp;

    public Chats(Employee emp) {
        initComponents();
        liveChatPanel_historyTextArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        this.emp = emp;
    }

    private void initComponents() {

        chatsPanel = new javax.swing.JPanel();
        chatsPanel_mainLabel = new javax.swing.JLabel();
        chatsPanel_subMainLabel = new javax.swing.JLabel();
        chatsPanel__HeaderSeparator = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        chatsPanel_AvailableBranchesTable = new javax.swing.JTable();
        manageChatsPanel = new javax.swing.JPanel();
        manageChatsPanel_mainLabel = new javax.swing.JLabel();
        manageChatsPanel__HeaderSeparator1 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        manageChatsPanel_AvailableChatsTable = new javax.swing.JTable();
        liveChatPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        liveChatPanel_historyTextArea = new javax.swing.JTextArea();
        liveChatPanel_messageTextField = new javax.swing.JTextField();
        liveChatPanel_sendMessageButton = new javax.swing.JButton();

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
                "פעולות", "שם סניף"
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

        javax.swing.GroupLayout manageChatsPanelLayout = new javax.swing.GroupLayout(manageChatsPanel);
        manageChatsPanel.setLayout(manageChatsPanelLayout);
        manageChatsPanelLayout.setHorizontalGroup(
            manageChatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, manageChatsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(manageChatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, manageChatsPanelLayout.createSequentialGroup()
                        .addComponent(manageChatsPanel_mainLabel)
                        .addGap(51, 51, 51))
                    .addComponent(manageChatsPanel__HeaderSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(88, 88, 88))
            .addGroup(manageChatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(manageChatsPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        manageChatsPanelLayout.setVerticalGroup(
            manageChatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manageChatsPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(manageChatsPanel_mainLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manageChatsPanel__HeaderSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(254, Short.MAX_VALUE))
            .addGroup(manageChatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, manageChatsPanelLayout.createSequentialGroup()
                    .addContainerGap(70, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout chatsPanelLayout = new javax.swing.GroupLayout(chatsPanel);
        chatsPanel.setLayout(chatsPanelLayout);
        chatsPanelLayout.setHorizontalGroup(
            chatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chatsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(manageChatsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chatsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(chatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chatsPanelLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(chatsPanel_mainLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(chatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(chatsPanel_subMainLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(chatsPanel__HeaderSeparator, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(97, 97, 97))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chatsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        chatsPanelLayout.setVerticalGroup(
            chatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatsPanelLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(chatsPanel_mainLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chatsPanel_subMainLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chatsPanel__HeaderSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(manageChatsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        liveChatPanel_historyTextArea.setEditable(false);
        liveChatPanel_historyTextArea.setColumns(20);
        liveChatPanel_historyTextArea.setRows(5);
        jScrollPane1.setViewportView(liveChatPanel_historyTextArea);

        liveChatPanel_messageTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        liveChatPanel_sendMessageButton.setBackground(new java.awt.Color(51, 153, 0));
        liveChatPanel_sendMessageButton.setText("שלח");
        liveChatPanel_sendMessageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                liveChatPanel_sendMessageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout liveChatPanelLayout = new javax.swing.GroupLayout(liveChatPanel);
        liveChatPanel.setLayout(liveChatPanelLayout);
        liveChatPanelLayout.setHorizontalGroup(
            liveChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(liveChatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(liveChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(liveChatPanelLayout.createSequentialGroup()
                        .addComponent(liveChatPanel_sendMessageButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(liveChatPanel_messageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        liveChatPanelLayout.setVerticalGroup(
            liveChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(liveChatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(liveChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(liveChatPanelLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(liveChatPanel_messageTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                    .addGroup(liveChatPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(liveChatPanel_sendMessageButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(liveChatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chatsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void liveChatPanel_sendMessageButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String message = liveChatPanel_messageTextField.getText();
        liveChatPanel_historyTextArea.append(emp.getFullName() + ": " + message + "\n");
        liveChatPanel_messageTextField.setText("");
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
