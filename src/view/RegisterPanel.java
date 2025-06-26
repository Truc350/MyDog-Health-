package view;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RegisterPanel extends JPanel {
    private JButton btnBack, btnRegister;
    private JLabel lblTitle, lblName, lblEmail, lblPassword;
    private JTextField txtName, txtEmail;
    private JPasswordField txtPassword;
    private  CardLayout cardLayout;
    private JPanel mainPanel;

    public RegisterPanel(CardLayout cardLayout,JPanel mainPanel ) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        // Nút quay lại (icon ←)
//            ImageIcon iconBack = new ImageIcon("src/image/arrow_left.png");
        btnBack = new JButton();
        btnBack.setText("");
        btnBack.setIcon(new ImageIcon("src/image/back.png"));

        btnBack.setFocusPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorder(BorderFactory.createLineBorder(new Color(90, 150, 255), 2));
        btnBack.setForeground(new Color(90, 150, 255));
        btnBack.setMaximumSize(new Dimension(40, 40));
        btnBack.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.setPreferredSize(new Dimension(34, 34));
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
        btnBack.setMaximumSize(new Dimension(36, 36));
        btnBack.setBorder(new RoundedBorder(36)); // hình tròn



        // Tiêu đề
        lblTitle = new JLabel("Đăng ký");
        lblTitle.setFont(new Font("Roboto", Font.BOLD, 30));
        lblTitle.setForeground(new Color(70, 150, 236));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setBorder(new EmptyBorder(20, 0, 30, 0));

        // Các trường nhập
        lblName = createLabel("Họ và tên");
        txtName = createTextField();

        lblEmail = createLabel("Email");
        txtEmail = createTextField();

        lblPassword = createLabel("Mật khẩu");
        txtPassword = new JPasswordField();
        styleTextField(txtPassword);

        // Nút đăng ký
        btnRegister = new JButton("Đăng ký");
        btnRegister.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setBackground(new Color(70, 150, 236));
        btnRegister.setBorder(new RoundedBorder(20));
        btnRegister.setFocusPainted(false);
        btnRegister.setMaximumSize(new Dimension(200, 40));
        btnRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegister.addActionListener(e-> handleRegister());
    }

    public void handleRegister() {
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this,"Vui lòng nhập đầy đủ thông tin!!!");
            return;
        }
        User user = new User(name, email, password);
        UserDAO dao = new UserDAO();
        if (dao.register(user)){
            JOptionPane.showMessageDialog(this,"Đăng ký tài khoản thành công!");

            cardLayout.show(mainPanel,"login");
        }


    }

    private void layoutComponents() {
        // Giao diện dùng BorderLayout cho bố cục rõ ràng
        setLayout(new BorderLayout());

        // ===== TOP: Nút quay lại =====
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topBar.setOpaque(false);
        topBar.add(btnBack);
        add(topBar, BorderLayout.NORTH);

        // ===== CENTER: Tiêu đề và biểu mẫu =====
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(new EmptyBorder(0, 30, 0, 30)); // Padding 2 bên

        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(lblTitle);
        centerPanel.add(Box.createVerticalStrut(25));
        centerPanel.add(createInputGroup(lblName, txtName));
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(createInputGroup(lblEmail, txtEmail));
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(createInputGroup(lblPassword, txtPassword));
        centerPanel.add(Box.createVerticalStrut(35));
        centerPanel.add(btnRegister);
        centerPanel.add(Box.createVerticalGlue()); // đẩy form lên trên một chút

        add(centerPanel, BorderLayout.CENTER);
    }




    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        label.setForeground(new Color(70, 150, 236));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JTextField createTextField() {
        JTextField tf = new JTextField();
        styleTextField(tf);
        return tf;
    }

    private void styleTextField(JTextField tf) {
        tf.setPreferredSize(new Dimension(200, 35));
        tf.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        tf.setBorder(new RoundedBorder(15));
        tf.setFont(new Font("Arial", Font.PLAIN, 14));
        tf.setMargin(new Insets(8, 12, 8, 12));
    }


    private JPanel createInputGroup(JLabel label, JTextField field) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(10, 0, 0, 0));
        panel.add(label);
        panel.add(Box.createVerticalStrut(5));
        panel.add(field);
        return panel;
    }


}
