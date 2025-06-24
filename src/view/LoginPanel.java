package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    public JLabel lblTitle, lblEmail, lblPassword, lblForgot, lblNoAccount, lblRegister;
    public JTextField txtEmail;
    public JPasswordField txtPassword;
    public JButton btnLogin;
    public JPanel panelBottom;
    public LoginPanel() {
        setLayout(null);
        setBackground(Color.WHITE);

        Color primaryColor = new Color(70, 150, 236);
        Font fontLabel = new Font("Arial", Font.PLAIN, 16);
        Font fontTitle = new Font("Arial", Font.BOLD, 26);

        // Title
        lblTitle = new JLabel("Đăng nhập", SwingConstants.CENTER);
        lblTitle.setFont(fontTitle);
        lblTitle.setForeground(primaryColor);
        lblTitle.setBounds(100, 80, 200, 40);

        // Email
        lblEmail = new JLabel("Email:");
        lblEmail.setFont(fontLabel);
        lblEmail.setBounds(60, 150, 100, 30);

        txtEmail = new JTextField();
        txtEmail.setFont(fontLabel);
        txtEmail.setBounds(60, 180, 280, 40);
        txtEmail.setBorder(new CompoundBorder(
                new LineBorder(primaryColor, 2, true),
                new EmptyBorder(5, 10, 5, 10)
        ));

        // Password
        lblPassword = new JLabel("Mật khẩu:");
        lblPassword.setFont(fontLabel);
        lblPassword.setBounds(60, 230, 100, 30);

        txtPassword = new JPasswordField();
        txtPassword.setFont(fontLabel);
        txtPassword.setBounds(60, 260, 280, 40);
        txtPassword.setBorder(new CompoundBorder(
                new LineBorder(primaryColor, 2, true),
                new EmptyBorder(5, 10, 5, 10)
        ));

        // Forgot Password
        lblForgot = new JLabel("Quên mật khẩu?");
        lblForgot.setFont(new Font("Arial", Font.ITALIC, 14));
        lblForgot.setForeground(primaryColor);
        lblForgot.setBounds(210, 310, 150, 30);

        // Login button
        btnLogin = new JButton("Đăng nhập");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.setBackground(primaryColor);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBounds(120, 360, 160, 45);
        btnLogin.setBorder((Border) new RoundBorder(20)); // custom border

        // Bottom panel for register text
        panelBottom = new JPanel();
        panelBottom.setBounds(60, 430, 280, 30);
        panelBottom.setBackground(Color.WHITE);
        panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        JLabel lblNoAccount = new JLabel("Nếu bạn chưa có tài khoản ?");
        lblNoAccount.setFont(new Font("Arial", Font.ITALIC, 14));
        lblNoAccount.setForeground(new Color(80, 80, 80));

        lblRegister = new JLabel("Đăng ký");
        lblRegister.setFont(new Font("Arial", Font.BOLD, 14));
        lblRegister.setForeground(primaryColor);
        panelBottom.add(lblNoAccount);
        panelBottom.add(lblRegister);

        // Add components
        add(lblTitle);
        add(lblEmail);
        add(txtEmail);
        add(lblPassword);
        add(txtPassword);
        add(lblForgot);
        add(btnLogin);
        add(panelBottom);
    }

    // Custom bo tròn border cho nút
    class RoundBorder extends AbstractBorder {
        private int radius;

        public RoundBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(c.getForeground());
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(10, 20, 10, 20);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(10, 20, 10, 20);
            return insets;
        }
    }
}
