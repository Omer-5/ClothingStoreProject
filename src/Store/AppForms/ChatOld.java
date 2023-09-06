package Store.AppForms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatOld {
    private JFrame frame;
    private JTextArea chatHistory;
    private JTextField messageField;
    private JButton sendButton;

    public ChatOld() {
        frame = new JFrame("Chat with Employee");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        chatHistory = new JTextArea();
        chatHistory.setEditable(false);
        chatHistory.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        JScrollPane scrollPane = new JScrollPane(chatHistory);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        messageField = new JTextField();
        messageField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });
        sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        bottomPanel.add(sendButton, BorderLayout.WEST);
        bottomPanel.add(messageField, BorderLayout.CENTER);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void sendMessage() {
        String message = messageField.getText();
        chatHistory.append("You: " + message + "\n");
        messageField.setText("");

        // Echo back for simplicity
        chatHistory.append("Employee: " + message + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChatOld();
            }
        });
    }
}
