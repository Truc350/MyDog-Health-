package view;

import model.CareAdvice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AIAnalysisResultsPanel extends JPanel {
    JButton backButton;
    CustomButton guideButton;
    JLabel status2, status1, fullResultLabel, titleLabel;
    JPanel resultPanel, infoPanel, contentPanel, topPanel;
    JTextArea infoLabel;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private CareGuidePanel careGuidePanel;


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

        String[][] results = {
                {"Viêm da dị ứng", "60%"},
                {"Nhiễm giun", "30%"},
                {"Rối loạn tiêu hóa", "60%"}
        };

        for (String[] row : results) {
            JLabel left = new JLabel(row[0]);
            JLabel right = new JLabel(row[1], SwingConstants.RIGHT);
            left.setFont(new Font("Roboto", Font.BOLD, 16));
            right.setFont(new Font("Roboto", Font.BOLD, 16));
            resultPanel.add(left);
            resultPanel.add(right);
        }

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
                if (careGuidePanel != null) {
                    java.util.List<CareAdvice> adviceList = new java.util.ArrayList<>();
                    adviceList.add(new CareAdvice(
                            "Viêm da dị ứng",
                            "Vệ sinh vùng da bằng nước muối sinh lý 2 lần/ngày.;Không để thú cưng liếm hoặc cào vào vùng bị ngứa.;Có thể dùng thuốc bôi dị ứng do bác sĩ kê đơn trước đó.",
                            "Vùng da đỏ, rỉ dịch, vật cào gãi nhiều",
                            "Theo dõi tiến triển trong 2-3 ngày, nếu không cải thiện nên đưa đến bác sĩ."
                    ));
                    adviceList.add(new CareAdvice(
                            "Nhiễm giun",
                            "Kiểm tra lại sổ tiêm/ngừa giun gần nhất.;Cho uống thuốc tẩy giun đúng liều (có thể tham khảo bác sĩ thú y).;Vệ sinh chỗ nằm, thức ăn, nước uống thường xuyên.",
                            "Bụng to bất thường, nôn, tiêu chảy",
                            "Lưu ý không dùng thuốc tẩy giun quá liều."
                    ));
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
        add(contentPanel, BorderLayout.CENTER);
        add(new BottomMenuPanel(), BorderLayout.SOUTH);

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
        infoLabel.setText("<html><body style='width: 300px;'>" + resultText.replace("\n", "<br>") + "</body></html>");
    }



    // For testing UI
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Test");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new BorderLayout());
//
//        AIAnalysisResultsPanel panel = new AIAnalysisResultsPanel();
//        frame.add(panel, BorderLayout.CENTER);
//
//        frame.setSize(400, 700); // phù hợp kích thước mobile
//        frame.setLocationRelativeTo(null); // căn giữa màn hình
//        frame.setVisible(true);
//    }
}
