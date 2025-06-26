package view;

import javax.swing.*;
import java.awt.*;

public class CallDoctorPanel extends JPanel {
    JButton backButton;
    JPanel contentPanel, topPanel, callOptionsPanel;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public CallDoctorPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());
        setBackground(new Color(200, 220, 245)); // nền xanh nhạt ngoài cùng

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // === Nút quay lại ===
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);
        topPanel.setMaximumSize(new Dimension(1000, 50));
        backButton = new JButton();
        backButton.setText("");
        backButton.setIcon(new ImageIcon("src/image/back.png"));
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "doctorSelection");
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
        JLabel titleLabel = new JLabel("Gọi bác sĩ");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(10));

        // === Khung chứa các nút gọi ===
        callOptionsPanel = new RoundedPanel(20, Color.WHITE, new Color(0, 0, 0, 0));
        callOptionsPanel.setLayout(new BoxLayout(callOptionsPanel, BoxLayout.Y_AXIS));
        callOptionsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        callOptionsPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        callOptionsPanel.setMaximumSize(new Dimension(320, 220));

        callOptionsPanel.add(wrapWithPadding(createCallOption("src/image/phone.png", "Cuộc gọi điện thoại", new Color(0, 132, 255))));
        callOptionsPanel.add(Box.createVerticalStrut(10));
        callOptionsPanel.add(wrapWithPadding(createCallOption("src/image/video.png", "Cuộc gọi video", new Color(37, 145, 163))));
        callOptionsPanel.add(Box.createVerticalStrut(10));
        callOptionsPanel.add(wrapWithPadding(createCallOption("src/image/mail.png", "Nhắn tin", new Color(197, 173, 141))));

        contentPanel.add(callOptionsPanel);
        contentPanel.add(Box.createVerticalGlue());

        add(contentPanel, BorderLayout.CENTER);
        add(new BottomMenuPanel(), BorderLayout.SOUTH);
    }

    private JPanel createCallOption(String iconPath, String text, Color bgColor) {
        JPanel panel = new RoundedPanel(20, Color.WHITE, new Color(0, 132, 255));
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        panel.setPreferredSize(new Dimension(300, 70)); // tăng chiều cao lên
        panel.setMaximumSize(new Dimension(300, 70));   // vẫn cần
        panel.setMinimumSize(new Dimension(300, 70));
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Icon vùng bên trái
        JPanel iconPanel = new JPanel();
        iconPanel.setPreferredSize(new Dimension(65, 65));
        iconPanel.setBackground(bgColor);
        iconPanel.setLayout(new GridBagLayout());

        ImageIcon rawIcon = new ImageIcon(iconPath);
        Image img = rawIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(img));
        iconPanel.add(iconLabel);

        // Text
        JLabel label = new JLabel(text);
        label.setFont(new Font("Roboto", Font.BOLD, 16));
        label.setBorder(BorderFactory.createEmptyBorder(5, 15, 0, 0));

        panel.add(iconPanel, BorderLayout.WEST);
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    private JPanel wrapWithPadding(JPanel panel) {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        wrapper.add(panel, BorderLayout.CENTER);
        return wrapper;
    }

    // ==== Test thử giao diện ====
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Gọi bác sĩ");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 700);
//        frame.setLocationRelativeTo(null);
//        frame.setContentPane(new CallDoctorPanel());
//        frame.setVisible(true);
//    }
}
