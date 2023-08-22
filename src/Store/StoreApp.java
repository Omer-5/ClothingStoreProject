package Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreApp extends JFrame {
    private JPanel navigationPanel;
    private JPanel contentPanel;

    public StoreApp() {
        setTitle("ממש ניהול חנות, סניף: ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setResizable(false);

        // Create the navigation panel
        navigationPanel = new JPanel();
        navigationPanel.setBackground(Color.LIGHT_GRAY);
        navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.Y_AXIS));
        navigationPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add margin

        ImageIcon imageIcon = new ImageIcon("src\\Store\\Images\\StoreImage.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg); 

        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        imageLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        navigationPanel.add(imageLabel);
        navigationPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        createNavigationButton("images/icon-cash-register.png", "קופה");
        createNavigationButton("images/icon-chat.png", "צ'אט");
        createNavigationButton("images/icon-customers.png", "ניהול לקוחות");
        createNavigationButton("images/icon-inventory.png", "ניהול מלאי סניף");
        createNavigationButton("images/icon-reports.png", "דוחות וסטטיסטיקות"); //TODO: Permissions for MANAGER
        

        // Set right-to-left component orientation
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        // Create the content panel
        contentPanel = new JPanel(new BorderLayout());

        // Add panels to the main frame
        add(navigationPanel, BorderLayout.EAST);
        add(contentPanel, BorderLayout.CENTER);

        // Center align the frame on the screen
        setLocationRelativeTo(null);

        CashRegister cashRegister = new CashRegister(); // Create the CashRegister panel
        cashRegister.setPreferredSize(contentPanel.getSize());
        contentPanel.add(cashRegister); // Add the CashRegister panel to the content panel's center
    }

    private void createNavigationButton(String iconPath, String label) {
        CustomButton button = new CustomButton(iconPath, label);
        button.setFont(new Font("Calibri", Font.PLAIN, 15));
        button.setAlignmentX(Component.RIGHT_ALIGNMENT); // Align to the right of the panel
        button.setMaximumSize(new Dimension(200, 40)); // Set button size
        button.setHorizontalAlignment(SwingConstants.RIGHT); // Align text to the left
        button.addActionListener(new NavigationButtonListener(label));
        navigationPanel.add(button);
        navigationPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing between buttons
    }

    private class CustomButton extends JButton {
        private String iconPath;

        public CustomButton(String iconPath, String text) {
            super();
            this.iconPath = iconPath;
            setText(text);
            setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0)); // Align text to the left

            // Set preferred size for the button
            setPreferredSize(new Dimension(200, 40));

            // Load and set the image icon
            ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
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

            if (panelName.equals("קופה")) {
                CashRegister cashRegister = new CashRegister(); // Create the CashRegister panel
                cashRegister.setPreferredSize(contentPanel.getSize());
                contentPanel.add(cashRegister); // Add the CashRegister panel to the content panel's center
            } else {
                JLabel label = new JLabel(panelName + " Content", SwingConstants.CENTER);
                label.setFont(new Font("Arial", Font.PLAIN, 24)); // Set font size
                contentPanel.add(label, BorderLayout.CENTER); // Add a label for other panels
            }

            // Refresh the content panel
            contentPanel.revalidate();
            contentPanel.repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StoreApp form = new StoreApp();
            form.setVisible(true);
        });
    }
}
