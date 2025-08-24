package view;

import model.CareAdvice;
import model.DiagnosisResult;
import model.Symptom;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MedicalResultPanel extends JPanel {
    JPanel topPanel, contentPanel, diagnosisBox, diagnosisLine, adviceBox, datePanel, symptomPanel, detailPanel;
    JLabel dateLabel, symptomTitle, diagnosis, adviceTitle, followUpTitle, followUpDate;
    JTextArea adviceContent, diagnosisText;
    JScrollPane adviceScrollPane;
    JButton backButton;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MedicalResultPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BorderLayout());
        setBackground(new Color(226, 235, 245));

        // === Main content panel ===
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // === Back button ===
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);
        topPanel.setMaximumSize(new Dimension(1000, 50));
        backButton = new JButton();
        backButton.setText("");
        backButton.setIcon(new ImageIcon("src/image/back.png"));
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "aiAnalysisResults"); // Quay lại DogInforPanel
        });

        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorder(BorderFactory.createLineBorder(new Color(90, 150, 255), 2));
        backButton.setForeground(new Color(90, 150, 255));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setPreferredSize(new Dimension(34, 34));
        backButton.setFont(new Font("Roboto", Font.BOLD, 16));
        backButton.setMaximumSize(new Dimension(36, 36));
        backButton.setBorder(new RoundedBorder(36));
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

        dateLabel = createBoldLabel("", 15);
        ImageIcon iconCa = new ImageIcon("src\\image\\calendar.png");
        Image image1 = iconCa.getImage();
        Image newImage1 = image1.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(newImage1);
        dateLabel.setIcon(icon1);

        datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.setOpaque(false);
        datePanel.add(dateLabel);
        diagnosisBox.add(Box.createVerticalStrut(1));
        diagnosisBox.add(datePanel);

        symptomTitle = createBoldLabel("Triệu chứng", 15);
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
        diagnosisBox.add(detailPanel);

        diagnosis = createBoldLabel("Chẩn đoán: ", 15);
        ImageIcon iconDio = new ImageIcon("src\\image\\diagnosis.png");
        Image image3 = iconDio.getImage();
        Image newImage3 = image3.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon icon3 = new ImageIcon(newImage3);
        diagnosis.setIcon(icon3);
        diagnosis.setAlignmentX(Component.CENTER_ALIGNMENT);

        diagnosisText = new JTextArea();
        diagnosisText.setFont(new Font("Roboto", Font.PLAIN, 15));
        diagnosisText.setLineWrap(true);
        diagnosisText.setWrapStyleWord(true);
        diagnosisText.setEditable(false);
        diagnosisText.setOpaque(false);
        diagnosisText.setBorder(null);
        diagnosisText.setAlignmentX(Component.LEFT_ALIGNMENT);

        diagnosisLine = new JPanel();
        diagnosisLine.setLayout(new BoxLayout(diagnosisLine, BoxLayout.Y_AXIS));
        diagnosisLine.setOpaque(false);
        diagnosisLine.add(diagnosis);
        diagnosisLine.add(diagnosisText);
        diagnosisBox.add(Box.createVerticalStrut(5));
        diagnosisBox.add(diagnosisLine);

        // === Advice box ===
        adviceBox = new RoundedPanel(20, Color.WHITE, new Color(13, 153, 255));
        adviceBox.setLayout(new BoxLayout(adviceBox, BoxLayout.Y_AXIS));
        adviceBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        adviceBox.setBackground(Color.WHITE);
        adviceBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        adviceTitle = createBoldLabel("Khuyến nghị:", 15);
        adviceTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        ImageIcon iconAdvise = new ImageIcon("src\\image\\advise.png");
        Image image4 = iconAdvise.getImage();
        Image newImage4 = image4.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon icon4 = new ImageIcon(newImage4);
        adviceTitle.setIcon(icon4);

        adviceContent = new JTextArea();
        adviceContent.setLineWrap(true);
        adviceContent.setWrapStyleWord(true);
        adviceContent.setEditable(false);
        adviceContent.setOpaque(false);
        adviceContent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        adviceContent.setFont(new Font("Roboto", Font.PLAIN, 15));

        // Bọc trong JScrollPane
        adviceScrollPane = new JScrollPane(adviceContent);
        adviceScrollPane.setPreferredSize(new Dimension(500, 200));
        adviceScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

// Thêm vào layout thay vì thêm trực tiếp adviceContent
        detailPanel.add(adviceScrollPane);

        JPanel followUpPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        followUpPanel.setOpaque(false);
        followUpTitle = createBoldLabel("Gợi ý tái khám:", 15);
        followUpTitle.setLayout(new FlowLayout(FlowLayout.LEFT));
        ImageIcon iconFollow = new ImageIcon("src\\image\\goiy.png");
        Image image5 = iconFollow.getImage();
        Image newImage5 = image5.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon icon5 = new ImageIcon(newImage5);
        followUpTitle.setIcon(icon5);
        followUpDate = createPlainLabel("");

        followUpPanel.add(followUpTitle);
        followUpPanel.add(followUpDate);

        adviceBox.add(adviceTitle);
        adviceBox.add(adviceScrollPane);

        // === Add to content panel ===
        contentPanel.add(diagnosisBox);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(adviceBox);

        // === Add to main panel ===
        add(contentPanel, BorderLayout.CENTER);
        BottomMenuPanel bottomMenuPanel = new BottomMenuPanel();
        bottomMenuPanel.setNavigationHandler(cardLayout, mainPanel);
        add(bottomMenuPanel, BorderLayout.SOUTH);
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

    public void updateMedicalResult(List<DiagnosisResult> results, String mainSymptom) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        dateLabel.setText("Ngày " + now.format(formatter));

        // === Triệu chứng ===
        detailPanel.removeAll();
        diagnosisText.setText("");
        adviceContent.setText("");


        if (results == null || results.isEmpty()) {
            diagnosisText.setText("Không có dữ liệu chẩn đoán.");
            detailPanel.revalidate();
            detailPanel.repaint();
            return;
        }

        // === Hiển thị triệu chứng chính ===
        if (mainSymptom != null && !mainSymptom.isEmpty()) {
            symptomTitle.setText("Triệu chứng: " + mainSymptom);
        } else {
            symptomTitle.setText("Triệu chứng chính: Không xác định");
        }

        // Duyệt toàn bộ kết quả
        for (DiagnosisResult result : results) {

            // Chẩn đoán
            if (result.getAiAnalysis() != null && !result.getAiAnalysis().isEmpty()) {
//                diagnosisText.setText(result.getAiAnalysis());
//            } else {
//                diagnosisText.setText("Không xác định");
                diagnosisText.append(result.getAiAnalysis());
            }

            //2. Gợi ý chăm sóc chi tiết
            if (result.getCareAdvices() != null && !result.getCareAdvices().isEmpty()) {
                for (CareAdvice advice : result.getCareAdvices()) {
                    StringBuilder adviceBuilder = new StringBuilder();
                    if (advice.getDangerSigns() != null && !advice.getDangerSigns().isEmpty()) {
                        adviceBuilder.append("• ").append(advice.getDangerSigns()).append("\n");
                    }
//                    if (advice.getAdvice() != null && !advice.getAdvice().isEmpty()) {
//                        adviceBuilder.append("• ").append(advice.getAdvice()).append("\n");
//                    }
                    if (advice.getExtraNotes() != null && !advice.getExtraNotes().isEmpty()) {
                        adviceBuilder.append("Lưu ý thêm: ").append(advice.getExtraNotes()).append("\n");
                    }
                    adviceContent.append(adviceBuilder.toString() + "\n");
                }
            }
        }

        detailPanel.revalidate();
        detailPanel.repaint();
    }

    private void _extracted() {
        detailPanel.add(Box.createVerticalStrut(2));
    }

}