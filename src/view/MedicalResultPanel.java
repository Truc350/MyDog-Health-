package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MedicalResultPanel extends JPanel {
    JPanel topPanel, contentPanel, diagnosisBox, diagnosisLine, adviceBox, datePanel, symptomPanel, detailPanel;
    JButton backButton;
    private CardLayout cardLayout;
    private JPanel mainPanel;



    public MedicalResultPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BorderLayout());
        setBackground(new Color(226, 235, 245)); // Light blue-gray background

        // === Main content panel ===
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //         ===== Nút quay lại =====
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);
        topPanel.setMaximumSize(new Dimension(1000, 50));
        backButton = new JButton();
        backButton.setText("");
        backButton.setIcon(new ImageIcon("src/image/back.png"));
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "aiAnalysisResults");
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

        // === Diagnosis box ===
        diagnosisBox = new RoundedPanel(20, Color.WHITE, new Color(13, 153, 255));
        diagnosisBox.setLayout(new BoxLayout(diagnosisBox, BoxLayout.Y_AXIS));
        diagnosisBox.setBackground(Color.WHITE);
        diagnosisBox.setMaximumSize(new Dimension(1000, 200));
        diagnosisBox.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        diagnosisBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel dateLabel = createBoldLabel("Ngày 20 tháng 6, 2025", 15);
        ImageIcon iconCa = new ImageIcon("src\\image\\calendar.png");
        Image image1 = iconCa.getImage();
        Image newImage1 = image1.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(newImage1);
        dateLabel.setIcon(icon1);

        datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.setOpaque(false);
        datePanel.add(dateLabel);
        diagnosisBox.add(Box.createVerticalStrut(1)); // thêm khoảng cách nhỏ
        diagnosisBox.add(datePanel);

        JLabel symptomTitle = createBoldLabel("Triệu chứng", 15);
        ImageIcon iconSym = new ImageIcon("src\\image\\stethoscope.png");
        Image image2 = iconSym.getImage();
        Image newImage2 = image2.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(newImage2);
        symptomTitle.setIcon(icon2);

        symptomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        symptomPanel.setOpaque(false);
        symptomPanel.add(symptomTitle);
        diagnosisBox.add(Box.createVerticalStrut(1));
        diagnosisBox.add(symptomPanel);

        detailPanel = new JPanel();
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
        detailPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailPanel.setOpaque(false);

        detailPanel.add(createPlainLabel("+ Sốt"));
        detailPanel.add(Box.createVerticalStrut(2)); // khoảng cách nhỏ
        detailPanel.add(createPlainLabel("+ Nôn"));
        detailPanel.add(Box.createVerticalStrut(2)); // khoảng cách nhỏ
        detailPanel.add(createPlainLabel("+ Tiêu chảy"));
        diagnosisBox.add(detailPanel);

        JLabel diagnosis = createBoldLabel("Chẩn đoán: ", 15);
        ImageIcon iconDio = new ImageIcon("src\\image\\diagnosis.png");
        Image image3 = iconDio.getImage();
        Image newImage3 = image3.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon icon3 = new ImageIcon(newImage3);
        diagnosis.setIcon(icon3);
        diagnosis.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel diagnosisText = new JLabel("Viêm đường ruột");
        diagnosisText.setFont(new Font("Roboto", Font.PLAIN, 15));

        diagnosisLine = new JPanel(new FlowLayout(FlowLayout.LEFT));
        diagnosisLine.setOpaque(false);
        diagnosisLine.add(diagnosis);
        diagnosisLine.add(diagnosisText);

        diagnosisBox.add(Box.createVerticalStrut(5));
        diagnosisBox.add(diagnosisLine);

        // === Dog image ===
        ImageIcon originalIcon = new ImageIcon("src\\image\\dog1.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(250, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        // === Advice box ===
        adviceBox = new RoundedPanel(20, Color.WHITE, new Color(13, 153, 255));
        adviceBox.setLayout(new BoxLayout(adviceBox, BoxLayout.Y_AXIS));
        adviceBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        adviceBox.setBackground(Color.WHITE);
        adviceBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel adviceTitle = createBoldLabel("Tư vấn bác sĩ:", 15);
        ImageIcon iconAdvise = new ImageIcon("src\\image\\advise.png");
        Image image4 = iconAdvise.getImage();
        Image newImage4 = image4.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon icon4 = new ImageIcon(newImage4);
        adviceTitle.setIcon(icon4);

        JLabel adviceContent = new JLabel("<html><div style='width:250px;'>Nên uống nhiều nước, hạn chế chạy nhảy, ăn thực phẩm mềm</div></html>");
        adviceContent.setFont(new Font("Roboto", Font.PLAIN, 15));
        JLabel followUpTitle = createBoldLabel("Gợi ý tái khám:", 15);
        ImageIcon iconFollow = new ImageIcon("src\\image\\goiy.png");
        Image image5 = iconFollow.getImage();
        Image newImage5 = image5.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon icon5 = new ImageIcon(newImage5);
        followUpTitle.setIcon(icon5);
        JLabel followUpDate = createPlainLabel("Ngày 23 tháng 6, 2025");

        adviceBox.add(adviceTitle);
        adviceBox.add(adviceContent);
        adviceBox.add(Box.createVerticalStrut(10));
        adviceBox.add(followUpTitle);
        adviceBox.add(followUpDate);

        // === Add to content panel ===
        contentPanel.add(diagnosisBox);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(imageLabel);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(adviceBox);

        // === Add to main panel ===
        add(contentPanel, BorderLayout.CENTER);
        add(new BottomMenuPanel(), BorderLayout.SOUTH);
    }

    private JLabel createBoldLabel(String text, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Roboto", Font.BOLD, fontSize));
        return label;
    }

    private JLabel createPlainLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Roboto", Font.PLAIN, 15));
        return label;
    }


//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Kết quả khám");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 700);
//        frame.setLocationRelativeTo(null);
//        frame.setContentPane(new view.MedicalResultPanel());
//        frame.setVisible(true);
//    }
}
