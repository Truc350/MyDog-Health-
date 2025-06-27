package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ChatboxPanel extends JPanel {

    JButton backButton;
    JPanel contentPanel, topPanel, buttonPanel, inputPanel, topPnel;
    private CardLayout cardLayout;
    private JPanel mainPanel;
// =======
//     private JButton backButton, resultButton, sendBtn;
//     private JPanel mainPanel, topPanel, inputPanel, topPnel;
//     private List<String> answers = new ArrayList<>();
// >>>>>>> main


    public ChatboxPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout());
        setBackground(new Color(200, 220, 245));

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Nút quay lại
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);
        topPanel.setMaximumSize(new Dimension(1000, 50));

        backButton = new JButton();
        backButton.setIcon(new ImageIcon("src/image/back.png"));

        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "careGuide");
        });

// =======
// >>>>>>> main
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorder(BorderFactory.createLineBorder(new Color(90, 150, 255), 2));
        backButton.setForeground(new Color(90, 150, 255));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setPreferredSize(new Dimension(34, 34));
        backButton.setBorder(new RoundedBorder(36));
        topPanel.add(backButton);

        contentPanel.add(topPanel);
        contentPanel.add(Box.createVerticalStrut(4));
// =======

//         mainPanel.add(topPanel);
//         mainPanel.add(Box.createVerticalStrut(10));
// >>>>>>> main

        // Tiêu đề
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
// =======
//         // Các câu hỏi
//         mainPanel.add(createQuestionPanel("Thú cưng có bỏ ăn hoàn toàn không?"));
//         mainPanel.add(Box.createVerticalStrut(15));
//         mainPanel.add(createQuestionPanel("Bạn sờ có thấy sưng tấy không?"));
//         mainPanel.add(Box.createVerticalStrut(15));
//         mainPanel.add(createQuestionPanel("Thú cưng có sốt trên 39.5°C không?"));
//         mainPanel.add(Box.createVerticalStrut(15));

//         // Kết quả chẩn đoán
//         resultButton = new JButton("Xem chẩn đoán");
//         resultButton.setFont(new Font("Roboto", Font.BOLD, 14));
//         resultButton.setBackground(new Color(70, 150, 236));
//         resultButton.setForeground(Color.WHITE);
//         resultButton.setFocusPainted(false);
//         resultButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//         resultButton.setMaximumSize(new Dimension(200, 36));
//         resultButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//         resultButton.addActionListener(e -> showResult());

//         mainPanel.add(resultButton);
//         mainPanel.add(Box.createVerticalStrut(10));
// >>>>>>> main

        // Ô nhập tự do
        JTextField inputField = new JTextField();
        inputField.setFont(new Font("Roboto", Font.PLAIN, 15));
        inputField.setPreferredSize(new Dimension(220, 36));
        inputField.setMaximumSize(new Dimension(220, 36));
        inputField.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));

        sendBtn = new JButton("Gửi");
        sendBtn.setFont(new Font("Roboto", Font.PLAIN, 14));
        sendBtn.setBackground(Color.WHITE);
        sendBtn.setForeground(new Color(90, 150, 255));
        sendBtn.setFocusPainted(false);
        sendBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sendBtn.addActionListener(e -> {
            String text = inputField.getText().trim();
            if (!text.isEmpty()) {
                JOptionPane.showMessageDialog(this, "📩 Bạn đã hỏi: " + text);
                inputField.setText("");
            }
        });

        inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.add(inputField);
        inputPanel.add(sendBtn);

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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setOpaque(false);

        JButton yesButton = customButton("Có");
        JButton noButton = customButton("Không");

        // Xử lý sự kiện
        ActionListener answerHandler = e -> {
            String answer = ((JButton) e.getSource()).getText();
            answers.add(answer);
            yesButton.setEnabled(false);
            noButton.setEnabled(false);
            JOptionPane.showMessageDialog(this, "✅ Trả lời: " + answer);
        };

        yesButton.addActionListener(answerHandler);
        noButton.addActionListener(answerHandler);

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        panel.add(buttonPanel);
        return panel;
    }

    private JButton customButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Roboto", Font.PLAIN, 14));
        button.setFocusPainted(false);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createLineBorder(new Color(70, 150, 236), 1));
        return button;
    }

    private void showResult() {
        int countYes = (int) answers.stream().filter(ans -> ans.equals("Có")).count();
        String message;
        if (countYes >= 3) {
            message = "⚠️ Có dấu hiệu nguy hiểm. Vui lòng gặp bác sĩ!";
        } else if (countYes == 2) {
            message = "🔍 Nên theo dõi thêm và kiểm tra triệu chứng khác.";
        } else {
            message = "✅ Có vẻ thú cưng vẫn ổn.";
        }
        JOptionPane.showMessageDialog(this, message, "Kết quả chẩn đoán", JOptionPane.INFORMATION_MESSAGE);
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

//     // Test
//     public static void main(String[] args) {
//         JFrame frame = new JFrame("Chatbox AI");
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setSize(400, 700);
//         frame.setLocationRelativeTo(null);
//         frame.setContentPane(new ChatboxPanel());
//         frame.setVisible(true);
//     }
// >>>>>>> main
}
