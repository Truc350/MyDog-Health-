package view;

import model.DiagnosisResult;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MedicalResultPanel extends JPanel {
    JPanel topPanel, contentPanel, diagnosisBox, diagnosisLine, adviceBox, datePanel, symptomPanel, detailPanel;
    JLabel dateLabel, symptomTitle, diagnosis, diagnosisText, adviceTitle, adviceContent, followUpTitle, followUpDate;
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
            cardLayout.show(mainPanel, "dogInfor"); // Quay lại DogInforPanel
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
        diagnosisText = new JLabel("");
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

        adviceTitle = createBoldLabel("Tư vấn bác sĩ:", 15);
        ImageIcon iconAdvise = new ImageIcon("src\\image\\advise.png");
        Image image4 = iconAdvise.getImage();
        Image newImage4 = image4.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon icon4 = new ImageIcon(newImage4);
        adviceTitle.setIcon(icon4);

        adviceContent = new JLabel("");
        adviceContent.setFont(new Font("Roboto", Font.PLAIN, 15));
        followUpTitle = createBoldLabel("Gợi ý tái khám:", 15);
        ImageIcon iconFollow = new ImageIcon("src\\image\\goiy.png");
        Image image5 = iconFollow.getImage();
        Image newImage5 = image5.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon icon5 = new ImageIcon(newImage5);
        followUpTitle.setIcon(icon5);
        followUpDate = createPlainLabel("");

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

    public void updateMedicalResult(List<DiagnosisResult> results, String mainSymptom, String otherSymptoms, String imagePath) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        dateLabel.setText("Ngày " + now.format(formatter));

        // Bao bọc detailPanel trong JScrollPane
        detailPanel.removeAll();
        JScrollPane scrollPane = new JScrollPane(detailPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(300, 100)); // Kích thước cố định, có thể cuộn

        String[] symptoms = (mainSymptom + ", " + otherSymptoms).split(",\\s*");
        for (String symptom : symptoms) {
            if (!symptom.trim().isEmpty()) {
                detailPanel.add(createPlainLabel("+ " + symptom.trim()));
                detailPanel.add(Box.createVerticalStrut(2));
            }
        }
        detailPanel.revalidate();
        detailPanel.repaint();

        if (results != null && !results.isEmpty() && results.get(0).getDiseaseName() != null && !results.get(0).getDiseaseName().equals("Không xác định")) {
            DiagnosisResult topResult = results.stream()
                    .max((r1, r2) -> Double.compare(r1.getProbability(), r2.getProbability()))
                    .orElse(null);
            if (topResult != null) {
                diagnosisText.setText(topResult.getDiseaseName());
                adviceContent.setText("<html><div style='width:250px;'>" + topResult.getStatusNote() + "</div></html>");
                followUpDate.setText("Ngày " + now.plusDays(3).format(formatter));
            }
        } else {
            diagnosisText.setText("Không xác định");
            adviceContent.setText("<html><div style='width:250px;'>Lỗi kết nối với API AI. Vui lòng thử lại sau hoặc liên hệ bác sĩ.</div></html>");
            followUpDate.setText("Không xác định");
        }

        if (imagePath != null && !imagePath.isEmpty()) {
            ImageIcon originalIcon = new ImageIcon(imagePath);
            Image scaledImage = originalIcon.getImage().getScaledInstance(250, 200, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(scaledImage);
            for (Component comp : contentPanel.getComponents()) {
                if (comp instanceof JLabel && ((JLabel) comp).getIcon() != null) {
                    ((JLabel) comp).setIcon(resizedIcon);
                    break;
                }
            }
        }

        // Thêm scrollPane vào diagnosisBox thay vì detailPanel trực tiếp
        diagnosisBox.remove(detailPanel);
        diagnosisBox.add(scrollPane);
        revalidate();
        repaint();
    }
    private void _extracted() {
        detailPanel.add(Box.createVerticalStrut(2));
    }

}