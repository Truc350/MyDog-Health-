package view;

import dao.DiagnosisDAO;
import model.CareAdvice;
import model.DiagnosisResult;
import model.Symptom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;

public class AIAnalysisResultsPanel extends JPanel {
    JButton backButton;
    CustomButton guideButton;
    JLabel status2, status1, fullResultLabel, titleLabel;
    JPanel resultPanel, infoPanel, contentPanel, topPanel;
    JTextArea infoLabel;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private CareGuidePanel careGuidePanel;
    private List<DiagnosisResult> lastResults;


    public AIAnalysisResultsPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BorderLayout()); // để thêm menu dưới cùng
        setBackground(new Color(200, 220, 245)); // nền xanh tổng thể

        // ===== Nội dung chính =====
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(200, 220, 245));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20)); // padding trái phải
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Font titleFont = new Font("Roboto", Font.BOLD, 20);
        Font normalFont = new Font("Roboto", Font.PLAIN, 16);

//         ===== Nút quay lại =====
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setMaximumSize(new Dimension(1000, 50));
        topPanel.setOpaque(false);
        backButton = new JButton();
        backButton.setText("");
        backButton.setIcon(new ImageIcon("src/image/back.png"));
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "dogInfor");
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


        // ===== Tiêu đề =====
        titleLabel = new JLabel("AI phân tích triệu chứng", JLabel.LEFT);
        titleLabel.setFont(titleFont);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0)); // Khoảng cách với nút
        contentPanel.add(titleLabel);

        contentPanel.add(Box.createVerticalStrut(10));

        // ===== Khung mô tả (xanh nhạt nhạt) =====
        infoPanel = new JPanel();
        infoPanel.setMaximumSize(new Dimension(320, 50));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(230, 243, 255)); // khung xanh nhạt
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        infoLabel = new JTextArea("AI đã đọc dữ liệu đầu vào và phân tích kết quả như sau:");
        infoLabel.setFont(new Font("Roboto", Font.ITALIC, 16));
        infoLabel.setWrapStyleWord(true);
        infoLabel.setLineWrap(true);
        infoLabel.setOpaque(false);
        infoLabel.setEditable(false);
        infoLabel.setFocusable(false);
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(infoLabel);
        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(infoPanel);


        // ===== Bảng kết quả =====
        resultPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        resultPanel.setBackground(Color.WHITE);

        resultPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(13, 153, 255), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        resultPanel.setMaximumSize(new Dimension(320, 150));
        resultPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(resultPanel);
        contentPanel.add(Box.createVerticalStrut(15));


        // ===== Tình trạng =====
// Tạo icon cho dòng status 1
        ImageIcon iconTinhTrang = new ImageIcon("src\\image\\circle.png");
        Image newImage = iconTinhTrang.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon1 = new ImageIcon(newImage);

        status1 = new JLabel("Tình trạng nhẹ - Có thể chăm sóc tại nhà", JLabel.LEFT);
        status1.setIcon(resizedIcon1);
        status1.setFont(normalFont);
        status1.setAlignmentX(Component.LEFT_ALIGNMENT); // để chữ bám trái trong panel


// Tạo icon cho dòng status 2
        ImageIcon iconCanhBao = new ImageIcon("src\\image\\warning.png");
        Image newImage2 = iconCanhBao.getImage().getScaledInstance(19, 19, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(newImage2);

        status2 = new JLabel("Khuyên gọi bác sĩ", JLabel.LEFT);
        status2.setIcon(resizedIcon2);
        status2.setFont(normalFont);
        status2.setAlignmentX(Component.LEFT_ALIGNMENT); // để chữ bám trái trong panel

// Gộp cả hai dòng vào 1 panel chính
        JPanel fullStatusPanel = new JPanel();
        fullStatusPanel.setLayout(new BoxLayout(fullStatusPanel, BoxLayout.Y_AXIS));
        fullStatusPanel.setOpaque(false);
        fullStatusPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        fullStatusPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fullStatusPanel.setMaximumSize(new Dimension(320, Integer.valueOf(200)));

        fullStatusPanel.add(Box.createVerticalStrut(10));
        fullStatusPanel.add(status1);
        fullStatusPanel.add(Box.createVerticalStrut(5));
        fullStatusPanel.add(status2);
        fullStatusPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(fullStatusPanel);
        contentPanel.add(Box.createVerticalStrut(15));


        // ===== Link xem kết quả =====
        fullResultLabel = new JLabel("<html><u>Xem toàn bộ kết quả</u></html>", SwingConstants.CENTER);
        fullResultLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                // 🔹 Lấy instance MedicalResultPanel từ mainPanel
                for (Component comp : mainPanel.getComponents()) {
                    if (comp instanceof MedicalResultPanel) {
                        MedicalResultPanel medicalPanel = (MedicalResultPanel) comp;

                        String mainSymptom = "";
                        if (!lastResults.isEmpty() && !lastResults.get(0).getSymptoms().isEmpty()) {
                            // ✅ Ghép tất cả triệu chứng chính thành 1 chuỗi (VD: "Nôn, Tiêu chảy")
                            mainSymptom = lastResults.get(0).getSymptoms()
                                    .stream()
                                    .map(Symptom::getName)
                                    .collect(Collectors.joining(", "));
                        }
                        // (bạn có thể set lastResults trong updateResults())
                        medicalPanel.updateMedicalResult(
                                lastResults, // List<DiagnosisResult>
                                mainSymptom
                        );
                    }
                }

                cardLayout.show(mainPanel, "medicalResult");
            }
        });


        fullResultLabel.setFont(new Font("Roboto", Font.ITALIC, 18));
        fullResultLabel.setForeground(Color.WHITE);
        fullResultLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        fullResultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fullResultLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        contentPanel.add(fullResultLabel);
        contentPanel.add(Box.createVerticalStrut(25)); // cách link và nút hướng dẫn 20px

        // ===== Nút xem hướng dẫn =====
        guideButton = customButton("Xem hướng dẫn chăm sóc");
        guideButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (careGuidePanel != null && lastResults != null) {
                    java.util.List<CareAdvice> adviceList = new java.util.ArrayList<>();

                    // Lấy tất cả CareAdvice từ kết quả AI
                    for (DiagnosisResult result : lastResults) {
                        if (result.getCareAdvices() != null) {
                            adviceList.addAll(result.getCareAdvices());
                        }
                    }

                    // Gửi sang CareGuidePanel
                    careGuidePanel.showCareAdviceList(adviceList);
                }

                cardLayout.show(mainPanel, "careGuide");
            }
        });

        ImageIcon iconHuongDan = new ImageIcon("src\\image\\instruct.png");
        Image image7 = iconHuongDan.getImage();
        Image newImage7 = image7.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        ImageIcon icon7 = new ImageIcon(newImage7);
        guideButton.setIcon(icon7);
        guideButton.setMargin(new Insets(2, 6, 2, 6));
        guideButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(guideButton);

        contentPanel.add(Box.createVerticalGlue());

        // ===== Thêm content và menu vào chính panel =====
        BottomMenuPanel bottomMenuPanel = new BottomMenuPanel();
        add(contentPanel, BorderLayout.CENTER);
        bottomMenuPanel.setNavigationHandler(cardLayout, mainPanel);
        add(bottomMenuPanel, BorderLayout.SOUTH);

    }

    private CustomButton customButton(String text) {
        CustomButton button = new CustomButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(250, 200));
        button.setBackgroundColor(Color.WHITE);
        button.setTextColor(new Color(70, 150, 236));
        button.setBorderRadius(20);
        button.setDrawBorder(false);
        return button;
    }

    public void setCareGuidePanel(CareGuidePanel panel) {
        this.careGuidePanel = panel;
    }

    public void setAnalysisResult(String resultText) {
        infoLabel.setText(resultText);
    }


    private void showAnalysisResults(List<DiagnosisResult> results) {
        resultPanel.removeAll();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        for (DiagnosisResult r : results) {
            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.setBorder(BorderFactory.createTitledBorder("Kết quả:"));

            // Triệu chứng
            String symptomText = r.getSymptoms().stream()
                    .map(Symptom::getName)
                    .distinct()
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("Không rõ triệu chứng");

            // Lời khuyên (gộp theo bệnh, bỏ trùng)
            String advicesText = r.getCareAdvices().stream()
                    .map(c -> {
                        // Nếu summary đã chứa tên bệnh → chỉ lấy summary
                        if (c.getSummary() != null && c.getSummary().startsWith(c.getDiseaseName())) {
                            return "- " + c.getSummary();
                        } else {
                            return "- " + c.getDiseaseName() + ": " + c.getSummary();
                        }
                    })
                    .distinct()
                    .reduce((a, b) -> a + "\n" + b)
                    .orElse("Chưa có");

            JTextArea txt = new JTextArea();
            txt.setEditable(false);
            txt.setLineWrap(true);
            txt.setWrapStyleWord(true);
            txt.setFont(new Font("Roboto", Font.PLAIN, 14));

            txt.setText("Triệu chứng: " + symptomText + "\n\n"
                    + "Phân tích AI: " + r.getAiAnalysis() + "\n\n"
                    + "Lời khuyên:\n" + advicesText);

            JScrollPane scroll = new JScrollPane(txt);
            scroll.setPreferredSize(new Dimension(0, 200));
            itemPanel.add(scroll, BorderLayout.CENTER);

            resultPanel.add(itemPanel);
        }
        resultPanel.revalidate();
        resultPanel.repaint();
    }


    /**
     * Cập nhật kết quả phân tích AI lên giao diện.
     * - Hiển thị danh sách bệnh & xác suất
     * - Cập nhật label mô tả tình trạng
     */
    public void updateResults(List<DiagnosisResult> results) {
        this.lastResults = results;
        if (results == null || results.isEmpty()) {
            setAnalysisResult("⚠️ Không có kết quả chẩn đoán từ AI.");
            resultPanel.removeAll();
            resultPanel.add(new JLabel("Không có dữ liệu"));
            resultPanel.revalidate();
            resultPanel.repaint();
            return;
        }

        // 👉 Hiển thị danh sách kết quả chi tiết
        showAnalysisResults(results);

        // 👉 Mô tả tổng quát ở trên cùng
//        setAnalysisResult("AI đã phân tích và phát hiện " + results.size() + " chẩn đoán sơ bộ.");
        setAnalysisResult("AI đã đọc dữ liệu đầu vào và phân tích kết quả như sau:");

        // 👉 Tạo tình trạng tổng quát dựa trên danh sách CareAdvice
        DiagnosisResult first = results.get(0); // lấy kết quả đầu tiên làm tiêu biểu
        if (first.getCareAdvices() != null && !first.getCareAdvices().isEmpty()) {
            CareAdvice mainAdvice = first.getCareAdvices().get(0); // lấy lời khuyên chính
            // status1 hiển thị tình trạng từ dangerSigns
            if (mainAdvice.getDangerSigns() != null && !mainAdvice.getDangerSigns().isEmpty()) {
                status1.setText("Tình trạng: " + mainAdvice.getDangerSigns());
            } else {
                status1.setText("⚠️ Chưa có đánh giá tình trạng.");
            }

            // status2 hiển thị lời khuyên từ advice
            if (mainAdvice.getAdvice() != null && !mainAdvice.getAdvice().isEmpty()) {
                status2.setText("Khuyến nghị: " + mainAdvice.getAdvice());
            } else {
                status2.setText("Vui lòng theo dõi thêm triệu chứng.");
            }

        } else {
            status1.setText("⚠️ CChưa có đánh giá tình trạng.");
            status2.setText("Vui lòng theo dõi thêm triệu chứng.");
        }

        resultPanel.revalidate();
        resultPanel.repaint();
    }

}
