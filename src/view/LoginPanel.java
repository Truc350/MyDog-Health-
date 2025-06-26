package view;

import dao.UserDAO;
import model.AppSession;
import model.User;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPanel extends JPanel {
    public JLabel lblTitle, lblEmail, lblPassword, lblForgot, lblNoAccount, lblRegister;
    public JTextField txtEmail;
    public JPasswordField txtPassword;
    public JButton btnLogin;
    public JPanel panelBottom;
    private  CardLayout cardLayout;
    private  JPanel mainPanel;
    public LoginPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
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
        lblForgot.setFont(new Font("Roboto", Font.ITALIC, 16));
        lblForgot.setForeground(primaryColor);
        lblForgot.setBounds(210, 310, 150, 30);

        // Login button
        btnLogin = customButton("Đăng nhập");
        btnLogin.setFont(new Font("Roboto", Font.BOLD, 16));
        btnLogin.setBounds(60, 360, 280, 45);
        btnLogin.addActionListener(e -> handleLogin());

        // Bottom panel for register text
        panelBottom = new JPanel();
        panelBottom.setBounds(60, 430, 280, 30);
        panelBottom.setBackground(Color.WHITE);
        panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        JLabel lblNoAccount = new JLabel("Nếu bạn chưa có tài khoản ?");
        lblNoAccount.setFont(new Font("Roboto", Font.ITALIC, 16));
        lblNoAccount.setForeground(new Color(80, 80, 80));

        lblRegister = new JLabel("<html><u>Đăng ký</u></html>");
        lblRegister.setFont(new Font("Roboto", Font.BOLD, 16));
        lblRegister.setForeground(primaryColor);
        lblRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(mainPanel, "register");// Chuyển sang RegisterPanel
            }
        });




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

    public void handleLogin() {
        String email = txtEmail.getText().trim();
        String password =  new String(txtPassword.getPassword()).trim();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!!!");
            return;
        }
        UserDAO dao = new UserDAO();
        User user = dao.login(email, password);
        if (user != null) {
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công! \nXin chào, "+user.getName());
            AppSession.currentUser = user;
            cardLayout.show(mainPanel, "dashboard");
        }else {
            JOptionPane.showMessageDialog(this, "Sai email hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }

    private CustomButton customButton(String text) {
        CustomButton button = new CustomButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(1000, 250));
        button.setBackgroundColor(new Color(70, 150, 236));
        button.setTextColor(Color.WHITE);
        button.setBorderRadius(10);
        return button;
    }

}
