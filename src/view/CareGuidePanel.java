package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CareGuidePanel extends JPanel {
    JPanel contentPanel, infoPanel, topPanel, skinPanel, wormPanel;
    JButton backButton, aiButton;
    JTextArea description;
    private CardLayout cardLayout;
    private JPanel mainPanel;



    public CareGuidePanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BorderLayout());
        setBackground(new Color(200, 220, 245)); // nền xanh nhạt nhẹ

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20)); // padding trái phải

        Font titleFont = new Font("Roboto", Font.BOLD, 20);
        Font bodyFont = new Font("Roboto", Font.PLAIN, 15);
        Font italicFont = new Font("Roboto", Font.ITALIC, 16);
        Font boldFont = new Font("Roboto", Font.BOLD, 16);

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

        // ===== Tiêu đề =====
        JLabel titleLabel = new JLabel("Hướng dẫn chăm sóc");
        titleLabel.setFont(titleFont);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(10));

        // ===== Mô tả =====
        infoPanel = new JPanel();
        infoPanel.setMaximumSize(new Dimension(360, 100));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(230, 243, 255)); // khung xanh nhạt
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        description = new JTextArea("Dựa trên các triệu chứng và phân tích AI, dưới đây là những cách chăm sóc phù hợp cho thú cưng của bạn:");
        description.setFont(italicFont);
        description.setWrapStyleWord(true);
        description.setLineWrap(true);
        description.setOpaque(false);
        description.setEditable(false);
        description.setFocusable(false);
//        description.setAlignmentX(Component.CENTER_ALIGNMENT);
//        description.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        infoPanel.add(description);
        contentPanel.add(infoPanel);
        contentPanel.add(Box.createVerticalStrut(10));


        // ===== Viêm da dị ứng =====
        skinPanel = createDiseasePanel("Viêm da dị ứng", "src/image/skin.png", new String[]{
                "Vệ sinh vùng da bằng nước muối sinh lý 2 lần/ngày.",
                "Không để thú cưng liếm hoặc cào vào vùng bị ngứa.",
                "Có thể dùng thuốc bôi dị ứng do bác sĩ kê đơn trước đó."
        }, boldFont, bodyFont);
        contentPanel.add(skinPanel);
        contentPanel.add(Box.createVerticalStrut(12));

        // ===== Nhiễm giun =====
        wormPanel = createDiseasePanel("Nhiễm giun", "src/image/worm.png", new String[]{
                "Kiểm tra lại sổ tiêm/ngừa giun gần nhất.",
                "Cho uống thuốc tẩy giun đúng liều (có thể tham khảo bác sĩ thú y).",
                "Vệ sinh chỗ nằm, thức ăn, nước uống thường xuyên."
        }, boldFont, bodyFont);
        contentPanel.add(wormPanel);
        contentPanel.add(Box.createVerticalStrut(20));

        // ===== Nút hỏi thêm AI =====
        aiButton = customButton("Hỏi thêm AI");
        aiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cardLayout.show(mainPanel, "chatBoxAI");
            }
        });
        aiButton.setFont(new Font("Roboto", Font.BOLD, 16));
        aiButton.setBackground(new Color(70, 150, 236));
        aiButton.setForeground(Color.WHITE);
        aiButton.setFocusPainted(false);
        aiButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        aiButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        aiButton.setPreferredSize(new Dimension(200, 40));
        aiButton.setMaximumSize(new Dimension(200, 40));
        aiButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

        ImageIcon iconAsk = new ImageIcon("src\\image\\question.png");
        Image image = iconAsk.getImage();
        Image newImage = image.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newImage);
        aiButton.setIcon(icon);
        aiButton.setMargin(new Insets(2, 6, 2, 6));
        contentPanel.add(aiButton);
        contentPanel.add(Box.createVerticalStrut(15));


        add(contentPanel, BorderLayout.CENTER);
        add(new BottomMenuPanel(), BorderLayout.SOUTH);
    }

    private JPanel createDiseasePanel(String title, String iconPath, String[] items, Font titleFont, Font bodyFont) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panel.setMaximumSize(new Dimension(360, Integer.MAX_VALUE));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Tiêu đề có icon
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(titleFont);
        titleLabel.setIcon(new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
        titleLabel.setIconTextGap(8);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(5));

        for (String s : items) {
            JTextArea textArea = new JTextArea("• " + s);
            textArea.setFont(bodyFont);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setOpaque(false);
            textArea.setEditable(false);
            textArea.setFocusable(false);
            textArea.setAlignmentX(Component.LEFT_ALIGNMENT);
            textArea.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
            panel.add(textArea);

//            label.setFont(bodyFont);
//            label.setAlignmentX(Component.LEFT_ALIGNMENT);
//            label.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0)); // thêm khoảng cách giữa các dòng
            panel.add(textArea);
        }
        return panel;
    }

    private CustomButton customButton(String text) {
        CustomButton button = new CustomButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(250, 300));
        button.setBackgroundColor(new Color(70, 150, 236));
        button.setTextColor(Color.WHITE);
        button.setBorderRadius(20);
        return button;
    }

    // For testing
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Care Guide");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 700);
//        frame.setLocationRelativeTo(null);
//        frame.add(new CareGuidePanel());
//        frame.setVisible(true);
//    }
}
