package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;

import static javax.swing.text.StyleConstants.setBackground;

public class RegisterPanel extends JPanel {
    // ===== Biến thành phần =====
    private JLabel lblTitle, lblName, lblEmail, lblPassword;
    private JTextField txtName, txtEmail, txtPassword;
    private JButton btnRegister;

    public RegisterPanel() {
        setBackground(Color.WHITE);
        setBorder(new LineBorder(new Color(100, 160, 255), 3));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        // Tiêu đề
        lblTitle = new JLabel("Đăng ký");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(50, 130, 255));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setBorder(new EmptyBorder(30, 0, 20, 0));

        // Trường nhập
        lblName = createLabel("Họ và tên");
        txtName = createTextField();

        lblEmail = createLabel("Email");
        txtEmail = createTextField();

        lblPassword = createLabel("Mật khẩu");
        txtPassword = createTextField();

        // Nút đăng ký
//        btnRegister = new JButton("Đăng ký");
//        btnRegister.setFont(new Font("Arial", Font.BOLD, 14));
//        btnRegister.setForeground(Color.WHITE);
//        btnRegister.setBackground(new Color(90, 150, 255));
//        btnRegister.setBorder(new RoundedBorder(15));
//        btnRegister.setFocusPainted(false);
//        btnRegister.setMaximumSize(new Dimension(150, 40));
//        btnRegister.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnRegister = new JButton("Đăng ký");
        btnRegister.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setBackground(new Color(90, 150, 255));
        btnRegister.setBorder(new RoundedBorder(15));
        btnRegister.setFocusPainted(false);

        btnRegister.setMaximumSize(new Dimension(150, 40));
        btnRegister.setPreferredSize(new Dimension(150, 40));
        btnRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegister.setHorizontalAlignment(SwingConstants.CENTER);
        btnRegister.setVerticalAlignment(SwingConstants.CENTER);
    }

    private void layoutComponents() {
        add(lblTitle);
        add(createInputGroup(lblName, txtName));
        add(createInputGroup(lblEmail, txtEmail));
        add(createInputGroup(lblPassword, txtPassword));
        add(Box.createVerticalStrut(20));
        add(btnRegister);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(new Color(90, 150, 255));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JTextField createTextField() {
        JTextField tf = new JTextField();
        tf.setPreferredSize(new Dimension(200, 35));
        tf.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        tf.setBorder(new RoundedBorder(10));
        tf.setFont(new Font("Arial", Font.PLAIN, 14));
        return tf;
    }

    private JPanel createInputGroup(JLabel label, JTextField field) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(10, 40, 0, 40));
        panel.add(label);
        panel.add(Box.createVerticalStrut(5));
        panel.add(field);
        return panel;
    }

    // ==== Border bo góc ====
    class RoundedBorder extends LineBorder {
        private final int radius;

        public RoundedBorder(int radius) {
            super(new Color(100, 160, 255));
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(lineColor);
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    // ==== Demo sử dụng JPanel này ====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Demo Register Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 700);
            frame.setLocationRelativeTo(null);
            frame.add(new RegisterPanel()); // thêm panel vào frame
            frame.setVisible(true);
        });
    }
}
