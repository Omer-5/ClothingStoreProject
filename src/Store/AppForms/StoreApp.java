package Store.AppForms;
import Store.Utilities;
import Store.Employees.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreApp extends JFrame {
    private JPanel navigationPanel;
    private JPanel contentPanel;
    private Employee emp;
    private CashRegister cashRegister;
    private CustomersManagement customersManagement;
    private InventoryManagement inventoryManagement;
    private Chats chats;
    private BranchReport branchReport;

    public StoreApp(Employee emp) {
        this.emp = emp;

        initComponents();

        cashRegister = new CashRegister(emp); // Create the CashRegister panel
        customersManagement = new CustomersManagement(); // Create the Customer Management panel
        inventoryManagement = new InventoryManagement(emp.getBranch()); // Create the Inventory Management panel
        chats = new Chats(emp); // Create the Chats panel
        branchReport = new BranchReport(emp.getBranch()); // Create the Branch Report panel

        cashRegister.setPreferredSize(contentPanel.getSize());
        contentPanel.add(cashRegister); // Add the CashRegister panel to the content panel's center
    }

    public void initComponents() {
        setTitle("ניהול חנות בגדים - מסך ראשי [מחובר עם: " + emp.getFullName() + "] | [סניף: " + emp.getBranch() + "]");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setResizable(false);

        navigationPanel = new JPanel();
        navigationPanel.setBackground(new Color(41, 74, 92));
        navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.Y_AXIS));
        navigationPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

        ImageIcon imageIcon = new ImageIcon("src/Store/Images/StoreImage.png"); 
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);   
        imageIcon = new ImageIcon(newimg); 
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        imageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        imageLabel.setBorder(new EmptyBorder(0,0,0,22));

        navigationPanel.add(imageLabel);
        navigationPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        JLabel userInfo = new JLabel();
        createNavigationButton("src/Store/images/icon-cash-register.png", "קופה");
        createNavigationButton("src/Store/images/icon-chat.png", "צ'אט");
        createNavigationButton("src/Store/images/icon-customers.png", "ניהול לקוחות");
        createNavigationButton("src/Store/images/icon-inventory.png", "ניהול מלאי סניף");

        if(emp.getTitle() == EmployeeTitle.MANAGER)
            createNavigationButton("src/Store/images/icon-reports.png", "דוחות וסטטיסטיקות"); 
        

        // Set right-to-left component orientation
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        // Create the content panel
        contentPanel = new JPanel(new BorderLayout());

        // Add panels to the main frame
        add(navigationPanel, BorderLayout.EAST);
        add(contentPanel, BorderLayout.CENTER);

        // Center align the frame on the screen
        setLocationRelativeTo(null);
    }

    private void createNavigationButton(String iconPath, String label) {
        CustomButton button = new CustomButton(iconPath, label);
        button.setFont(new Font("Calibri", Font.PLAIN, 15));
        button.setAlignmentX(Component.RIGHT_ALIGNMENT); // Align to the right of the panel
        button.setMaximumSize(new Dimension(200, 40)); // Set button size
        button.setHorizontalAlignment(SwingConstants.CENTER); // Align text to the left
        button.addActionListener(new NavigationButtonListener(label));
        button.setFocusPainted(false);
        button.setOpaque(false);
        navigationPanel.add(button);
        navigationPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing between buttons
    }

    private class CustomButton extends JButton {
        private String iconPath;

        public CustomButton(String iconPath, String text) {
            super();

            if (isSelected()) {
                setBorder(BorderFactory.createEmptyBorder());
            } else {
                setBorder(BorderFactory.createLoweredBevelBorder());
            }

            this.iconPath = iconPath;
            setText(text);
            setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0)); // Align text to the left

            // Set preferred size for the button
            setPreferredSize(new Dimension(200, 40));
            setHorizontalAlignment(SwingConstants.CENTER);

            // Load and set the image icon
            ImageIcon icon = new ImageIcon(iconPath);
            Image scaledImage = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            setIconTextGap(10); // Add gap between icon and text
            setIcon(new ImageIcon(scaledImage));
        }
    }

    private class NavigationButtonListener implements ActionListener {
        private String panelName;

        public NavigationButtonListener(String panelName) {
            this.panelName = panelName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Clear the content panel
            contentPanel.removeAll();
            contentPanel.repaint();

            if (panelName.equals("צ'אט")) {
                Utilities.setInChatPanel(emp, true);
                chats.getListenToServerThread().resumeThread();
            }
            else {
                Utilities.setInChatPanel(emp, false);
                chats.getListenToServerThread().pauseThread();
            }

            if (panelName.equals("קופה")) {
                cashRegister.setPreferredSize(contentPanel.getSize());
                cashRegister.ClearCustomerInfo();
                cashRegister.ClearTablesCells();
                contentPanel.add(cashRegister); // Add the CashRegister panel to the content panel's center
            } else if (panelName.equals("ניהול לקוחות")) { 
                customersManagement.setPreferredSize(contentPanel.getSize());
                customersManagement.LoadCustomers();
                contentPanel.add(customersManagement); // Add the CashRegister panel to the content panel's center
            } else if (panelName.equals("ניהול מלאי סניף")) { 
                inventoryManagement.setPreferredSize(contentPanel.getSize());
                inventoryManagement.LoadInventory();
                contentPanel.add(inventoryManagement); // Add the CashRegister panel to the content panel's center
            } else if (panelName.equals("דוחות וסטטיסטיקות")) { 
                branchReport.setPreferredSize(contentPanel.getSize());
                branchReport.loadBranchInfo();
                contentPanel.add(branchReport); // Add the CashRegister panel to the content panel's center
            }else if (panelName.equals("צ'אט")) { 
                chats.LoadAvailableBranches();
                chats.LoadAvailableChats();
                chats.setPreferredSize(contentPanel.getSize());
                contentPanel.add(chats); // Add the CashRegister panel to the content panel's center
            }
            
            contentPanel.revalidate();
            contentPanel.repaint();
        }
    }
}
