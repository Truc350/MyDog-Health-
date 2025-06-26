package view;

import javax.swing.*;
import java.awt.*;

public class ChatboxPanel extends JPanel {
    JButton backButton;
    JPanel contentPanel, topPanel, buttonPanel, inputPanel, topPnel;
    private CardLayout cardLayout;
    private JPanel mainPanel;


    public ChatboxPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout());
        setBackground(new Color(200, 220, 245)); // nền xanh nhạt

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //         ===== Nút quay lại =====
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);
        topPanel.setMaximumSize(new Dimension(1000, 50));
        backButton = new JButton();
        backButton.setText("");
        backButton.setIcon(new ImageIcon("src/image/back.png"));
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "careGuide");
        });

        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorder(BorderFactory.createLineBorder(new Color(90, 150, 255), 2));
        backButton.setForeground(new Color(90, 150, 255));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setPreferredSize(new Dimension(34, 34));
        backButton.setFont(new Font("Roboto", Font.BOLD, 16));
        backButton.setMaximumSize(new Dimension(36, 36));
        backButton.setBorder(new RoundedBorder(36)); // hình tròn
        topPanel.add(backButton);
        contentPanel.add(topPanel);
        contentPanel.add(Box.createVerticalStrut(4));

        // === Tiêu đề ===
        JLabel titleLabel = new JLabel("Chatbox AI");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(10));


        // === Các khung câu hỏi ===
        contentPanel.add(createQuestionPanel("Thú cưng có bỏ ăn hoàn toàn không ?"));
        contentPanel.add(Box.createVerticalStrut(15));
        contentPanel.add(createQuestionPanel("Bạn sờ có thấy sưng tấy không ?"));

        // === Ô nhập câu hỏi ===
        CustomTextField inputField = new CustomTextField(0);
        inputField.setPlaceholder("Nhập câu hỏi...");
        inputField.setTextColor(Color.BLACK);
        inputField.setPlaceholderColor(Color.LIGHT_GRAY);
        inputField.setFont(new Font("Roboto", Font.PLAIN, 15));
        inputField.setPreferredSize(new Dimension(300, 36));
        inputField.setMaximumSize(new Dimension(300, 36));
        inputField.setMargin(new Insets(5, 10, 5, 10));
        inputField.setBackground(Color.WHITE);

        inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        inputPanel.add(inputField);

        topPnel = new JPanel();
        topPnel.setBackground(new Color(200, 220, 245));
        topPnel.setLayout(new BorderLayout());
        topPnel.add(contentPanel, BorderLayout.CENTER);
        topPnel.add(inputPanel, BorderLayout.SOUTH);

        add(topPnel, BorderLayout.CENTER);
        add(new BottomMenuPanel(), BorderLayout.SOUTH);
    }

    private JPanel createQuestionPanel(String questionText) {
        JPanel panel = new RoundedPanel(20, Color.WHITE, new Color(13, 153, 255));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setMaximumSize(new Dimension(300, 100));

        JLabel question = new JLabel(questionText);
        question.setFont(new Font("Roboto", Font.PLAIN, 16));
        question.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(question);
        panel.add(Box.createVerticalStrut(10));

        // Nút có / không
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setOpaque(false);

        JButton yesButton = customButton("Có");
        JButton noButton = customButton("Không");


        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        panel.add(buttonPanel);

        return panel;
    }

    private CustomButton customButton(String text) {
        CustomButton button = new CustomButton(text);
        button.setMaximumSize(new Dimension(100, 50));
        button.setFont(new Font("Roboto", Font.PLAIN, 16));
        button.setDrawBorder(true);
        button.setBorderThickness(1);
//        button.setBorder(BorderFactory.createLineBorder(new Color(70, 150, 236), 2));
        button.setBackgroundColor(Color.WHITE);
        button.setFocusPainted(false);
        button.setTextColor(Color.BLACK);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorderRadius(10);
        return button;
    }


    // Test thử giao diện
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Chatbot AI");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 700);
//        frame.setLocationRelativeTo(null);
//        frame.setContentPane(new ChatboxPanel());
//        frame.setVisible(true);
//    }
}
