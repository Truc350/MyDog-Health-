package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DoctorSelectionPanel extends JPanel {
    JPanel contentPanel, doctorListPanel, filterPanel, topPanel;
    JButton backButton, filterButton;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public DoctorSelectionPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout());
        setBackground(new Color(200, 220, 245));

        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(200, 220, 245));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20)); // padding trái phải
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //         ===== Nút quay lại =====
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);
        topPanel.setMaximumSize(new Dimension(1000, 50));
        backButton = new JButton();
        backButton.setText("");
        backButton.setIcon(new ImageIcon("src/image/back.png"));
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "dashboard");
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

        // Tiêu đề
        JLabel titleLabel = new JLabel("Chọn bác sĩ mong muốn");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(10));

        // Khung chứa danh sách bác sĩ
        doctorListPanel = new RoundedPanel(20, Color.WHITE, new Color(0, 0, 0, 0));
        doctorListPanel.setLayout(new BoxLayout(doctorListPanel, BoxLayout.Y_AXIS));
        doctorListPanel.setMaximumSize(new Dimension(300, Integer.MAX_VALUE));
        doctorListPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        doctorListPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Nút lọc (filter)
        filterPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        filterPanel.setOpaque(false);
        ImageIcon rawIcon = new ImageIcon("src/image/filter.png");
        Image scaledImg = rawIcon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImg);

        filterButton = new JButton();
        filterButton.setIcon(resizedIcon);

        filterButton.setFocusPainted(false);
        filterButton.setContentAreaFilled(false);
        filterButton.setForeground(new Color(90, 150, 255));
        filterButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        filterButton.setPreferredSize(new Dimension(34, 34));
        filterButton.setFont(new Font("Roboto", Font.BOLD, 16));
        filterButton.setMaximumSize(new Dimension(34, 34));
        filterButton.setBorder(new RoundedBorder(36)); // hình tròn
        filterPanel.add(filterButton);
        doctorListPanel.add(filterPanel);
        doctorListPanel.add(Box.createVerticalStrut(10)); // thêm khoảng cách nhỏ dưới filter

        // Thêm bác sĩ
        JPanel d1 = createDoctorItem("src/image/doctor1.png", "Bác sĩ Trần Văn A", "Khoa da liễu");
        doctorListPanel.add(d1);
        doctorListPanel.add(Box.createVerticalStrut(10));
        JPanel d2 = createDoctorItem("src/image/doctor2.png", "Bác sĩ Trần Văn B", "Khoa tiêu hóa");
        doctorListPanel.add(d2);
        doctorListPanel.add(Box.createVerticalStrut(10));
        JPanel d3 = createDoctorItem("src/image/doctor3.png", "Bác sĩ Trần Văn C", "Khoa di truyền");
        doctorListPanel.add(d3);

        doctorListPanel.add(Box.createVerticalGlue()); // đẩy nội dung lên trên
        doctorListPanel.add(Box.createVerticalGlue()); // đẩy nội dung lên trên
        doctorListPanel.add(Box.createVerticalGlue()); // đẩy nội dung lên trên
        doctorListPanel.add(Box.createVerticalGlue()); // đẩy nội dung lên trên
        doctorListPanel.add(Box.createVerticalGlue()); // đẩy nội dung lên trên
        doctorListPanel.add(Box.createVerticalGlue()); // đẩy nội dung lên trên
        doctorListPanel.add(Box.createVerticalGlue()); // đẩy nội dung lên trên

        contentPanel.add(doctorListPanel);

        add(contentPanel, BorderLayout.CENTER);
        add(new BottomMenuPanel(), BorderLayout.SOUTH);

    }

    private JPanel createDoctorItem(String imgPath, String name, String department) {
        JPanel panel = new RoundedPanel(20, Color.WHITE, new Color(13, 153, 255));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 15, 10));
        panel.setMaximumSize(new Dimension(300, 80));
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel avatar = new JLabel(new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(42, 42, Image.SCALE_SMOOTH)));
        avatar.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 10));
        panel.add(avatar);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        nameLabel.setForeground(new Color(0, 96, 207));
        JLabel deptLabel = new JLabel(department);
        deptLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        deptLabel.setForeground(new Color(0, 96, 207));

        textPanel.add(nameLabel);
        textPanel.add(deptLabel);
        panel.add(textPanel);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cardLayout.show(mainPanel, "callDoctor");
            }
        });

        return panel;
    }

    // ==== Main để test ====
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Chọn bác sĩ");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 700);
//        frame.setLocationRelativeTo(null);
//        frame.setContentPane(new DoctorSelectionPanel());
//        frame.setVisible(true);
//    }
}
