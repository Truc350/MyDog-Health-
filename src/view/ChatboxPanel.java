package view;

import model.OpenAIService;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ChatboxPanel extends JPanel {

    private JButton sendButton;
    private JTextField inputField;
    private JPanel messagePanel;
    private JScrollPane scrollPane;
    private JButton backButton;
    private OpenAIService openAIService = new OpenAIService();

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public ChatboxPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Header (Back + Tiêu đề)
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        backButton = new JButton(new ImageIcon("src/image/back.png"));
        styleIconButton(backButton);
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "careGuide"));
        headerPanel.add(backButton, BorderLayout.WEST);

        JLabel titleLabel = new JLabel("Chatbox AI Chẩn đoán", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Message Panel
        messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        messagePanel.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(messagePanel);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // mượt hơn
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0)); // ẩn hoàn toàn ngang

        // Input Panel
        inputField = new JTextField();
        inputField.setFont(new Font("Roboto", Font.PLAIN, 15));

        sendButton = new JButton("Gửi");
        sendButton.setFont(new Font("Roboto", Font.BOLD, 14));
        sendButton.setBackground(new Color(90, 150, 255));
        sendButton.setForeground(Color.WHITE);
        sendButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sendButton.setFocusPainted(false);
        sendButton.addActionListener(e -> handleSend());

        JPanel inputPanel = new JPanel(new BorderLayout(10, 0));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(Color.WHITE);
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        // Add to layout
        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }

    private void styleIconButton(JButton btn) {
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void handleSend() {
        String userInput = inputField.getText().trim();
        if (userInput.isEmpty()) return;

        addMessage("👤 Bạn: " + userInput, true);
        inputField.setText("");
        sendButton.setEnabled(false);
        addMessage("💬 Đang suy nghĩ...", false);

        new Thread(() -> {
            try {
                String response = openAIService.ask(userInput);
                SwingUtilities.invokeLater(() -> {
                    removeLastMessage();
                    addMessage("🤖 AI: " + response, false);
                    sendButton.setEnabled(true);
                });
            } catch (IOException e) {
                SwingUtilities.invokeLater(() -> {
                    removeLastMessage();
                    addMessage("❌ Lỗi: " + e.getMessage(), false);
                    sendButton.setEnabled(true);
                });
            }
        }).start();
    }

    private void addMessage(String text, boolean isUser) {
        JTextArea messageText = new JTextArea(text);
        messageText.setFont(new Font("Roboto", Font.PLAIN, 14));
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        messageText.setEditable(false);
        messageText.setOpaque(false);
        messageText.setBorder(null);

        JPanel wrapper = new JPanel(new FlowLayout(isUser ? FlowLayout.RIGHT : FlowLayout.LEFT));
        wrapper.setOpaque(false);
        wrapper.add(messageText);

        messagePanel.add(wrapper);
        messagePanel.add(Box.createVerticalStrut(4));
        messagePanel.revalidate();
        messagePanel.repaint();

        SwingUtilities.invokeLater(() ->
                scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum()));
    }

    private void removeLastMessage() {
        int count = messagePanel.getComponentCount();
        if (count >= 2) {
            messagePanel.remove(count - 1); // khoảng cách
            messagePanel.remove(count - 2); // panel chứa text
            messagePanel.revalidate();
            messagePanel.repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Chatbox AI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(420, 680);
            frame.setLocationRelativeTo(null);

            CardLayout layout = new CardLayout();
            JPanel mainPanel = new JPanel(layout);
            ChatboxPanel chatbox = new ChatboxPanel(layout, mainPanel);

            mainPanel.add(chatbox, "chat");
            frame.setContentPane(mainPanel);
            frame.setVisible(true);
        });
    }
}
