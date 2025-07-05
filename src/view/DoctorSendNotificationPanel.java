package view;

import controller.NotificationService;

import javax.swing.*;
import java.awt.*;

public class DoctorSendNotificationPanel extends JPanel{
    private JTextField titleField;
    private JTextArea messageArea;
    private JButton sendButton;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public DoctorSendNotificationPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BorderLayout());
        setBackground(new Color(220, 235, 250));

        JLabel header = new JLabel("Gửi thông báo đến người dùng", JLabel.CENTER);
        header.setFont(new Font("Roboto", Font.BOLD, 18));
        header.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(header, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));
        formPanel.setBackground(new Color(220, 235, 250));

        titleField = new JTextField();
        titleField.setFont(new Font("Roboto", Font.PLAIN, 14));
        titleField.setBorder(BorderFactory.createTitledBorder("Tiêu đề"));
        formPanel.add(titleField);
        formPanel.add(Box.createVerticalStrut(15));

        messageArea = new JTextArea(6, 20);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setFont(new Font("Roboto", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(messageArea);
        scroll.setBorder(BorderFactory.createTitledBorder("Nội dung"));
        formPanel.add(scroll);
        formPanel.add(Box.createVerticalStrut(20));

        sendButton = new JButton("Gửi thông báo");
        sendButton.setFont(new Font("Roboto", Font.BOLD, 14));
        sendButton.setBackground(new Color(0, 122, 255));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setPreferredSize(new Dimension(150, 36));
        sendButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sendButton.addActionListener(e -> sendNotification());
        formPanel.add(sendButton);

        add(formPanel, BorderLayout.CENTER);
    }

    private void sendNotification() {
        String title = titleField.getText().trim();
        String message = messageArea.getText().trim();

        if (title.isEmpty() || message.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ tiêu đề và nội dung!");
            return;
        }

        NotificationService.sendNotification(title, message);
        JOptionPane.showMessageDialog(this, "✔️ Đã gửi thông báo!");
        titleField.setText("");
        messageArea.setText("");
    }
}
