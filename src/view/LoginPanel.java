package view;

import controller.PetController;
import dao.UserDAO;
import model.AppSession;
import model.User;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPanel extends JPanel {
    private JLabel lblTitle, lblEmail, lblPassword, lblForgot, lblRegister;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private DashboardPanel dashboardPanel;

    public LoginPanel(CardLayout cardLayout, JPanel mainPanel,DashboardPanel dashboardPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.dashboardPanel = dashboardPanel;

        setLayout(null);
        setBackground(Color.WHITE);

        Color primaryColor = new Color(70, 150, 236);
        Font fontLabel = new Font("Roboto", Font.PLAIN, 16);
        Font fontTitle = new Font("Roboto", Font.BOLD, 30);

        // Title
        lblTitle = new JLabel("Đăng nhập", SwingConstants.CENTER);
        lblTitle.setFont(fontTitle);
        lblTitle.setForeground(primaryColor);
        lblTitle.setBounds(100, 80, 200, 40);

        // Email
        JLabel lblEmailLabel = new JLabel("Email:");
        lblEmailLabel.setFont(fontLabel);
        lblEmailLabel.setBounds(60, 150, 100, 30);

        txtEmail = new JTextField();
        txtEmail.setFont(fontLabel);
        txtEmail.setBounds(60, 180, 280, 40);
        txtEmail.setBorder(new CompoundBorder(
                new LineBorder(primaryColor, 2, true),
                new EmptyBorder(5, 10, 5, 10)
        ));

        // Password
        JLabel lblPasswordLabel = new JLabel("Mật khẩu:");
        lblPasswordLabel.setFont(fontLabel);
        lblPasswordLabel.setBounds(60, 230, 100, 30);

        txtPassword = new JPasswordField();
        txtPassword.setFont(fontLabel);
        txtPassword.setBounds(60, 260, 280, 40);
        txtPassword.setBorder(new CompoundBorder(
                new LineBorder(primaryColor, 2, true),
                new EmptyBorder(5, 10, 5, 10)
        ));

        // Forgot Password
        lblForgot = new JLabel("Quên mật khẩu?");
        lblForgot.setFont(new Font("Roboto", Font.ITALIC, 16));
        lblForgot.setForeground(primaryColor);
        lblForgot.setBounds(210, 310, 150, 30);
        lblForgot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblForgot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleForgotPassword();
            }
        });

        // Login button
        btnLogin = new JButton("Đăng nhập");
        btnLogin.setFont(new Font("Roboto", Font.BOLD, 16));
        btnLogin.setBackground(primaryColor);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBounds(120, 360, 160, 45);
        btnLogin.setBorder(new RoundBorder(20));
        btnLogin.addActionListener(e -> handleLogin());

        // Register link
        JPanel panelBottom = new JPanel();
        panelBottom.setBounds(60, 430, 280, 30);
        panelBottom.setBackground(Color.WHITE);
        panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        JLabel lblNoAccount = new JLabel("Nếu bạn chưa có tài khoản?");
        lblNoAccount.setFont(new Font("Roboto", Font.ITALIC, 16));
        lblNoAccount.setForeground(new Color(80, 80, 80));

        lblRegister = new JLabel("<html><u>Đăng ký</u></html>");
        lblRegister.setFont(new Font("Roboto", Font.BOLD, 16));
        lblRegister.setForeground(primaryColor);
        lblRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(mainPanel, "register");
            }
        });

        panelBottom.add(lblNoAccount);
        panelBottom.add(lblRegister);

        // Add components
        add(lblTitle);
        add(lblEmailLabel);
        add(txtEmail);
        add(lblPasswordLabel);
        add(txtPassword);
        add(lblForgot);
        add(btnLogin);
        add(panelBottom);
    }

    private void handleForgotPassword() {
        String email = JOptionPane.showInputDialog(this, "Nhập email đã đăng ký:", "Quên mật khẩu", JOptionPane.QUESTION_MESSAGE);
        if (email == null || email.trim().isEmpty()) return;

        UserDAO dao = new UserDAO();
        User user = dao.findByEmail(email.trim());

        if (user != null) {
            String subject = "Khôi phục mật khẩu - MyDog Health+";
            String body = "Xin chào " + user.getName() + ",\n\n"
                    + "Bạn đã yêu cầu khôi phục mật khẩu.\n"
                    + "Mật khẩu hiện tại của bạn là: " + user.getPassword() + "\n\n"
                    + "Vui lòng đăng nhập và đổi mật khẩu nếu cần.\n"
                    + "Cảm ơn bạn.";

            boolean sent = util.EmailSender.send(user.getEmail(), subject, body);
            if (sent) {
                JOptionPane.showMessageDialog(this, "✅ Mật khẩu đã được gửi đến email của bạn.");
            } else {
                JOptionPane.showMessageDialog(this, "❌ Gửi email thất bại. Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy tài khoản với email này.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleLogin() {
        String email = txtEmail.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        UserDAO dao = new UserDAO();
        User user = dao.login(email, password);

        if (user != null) {
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công!\nXin chào, " + user.getName());
            AppSession.currentUser = user;
            dashboardPanel.updateUserInfo(); // 👈 cập nhật tên người dùng
            cardLayout.show(mainPanel, "dashboard");


        }
        else {
            JOptionPane.showMessageDialog(this, "Sai email hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Border bo góc
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
