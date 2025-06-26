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
    private JPanel loginPanel;

    public RegisterPanel(CardLayout cardLayout,JPanel mainPanel ) {
        this.cardLayout = cardLayout;
        loginPanel = new JPanel();
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
        btnBack.setFont(new Font("Roboto", Font.BOLD, 16));
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
        btnRegister = customButton("Đăng ký");
        btnRegister.setFont(new Font("Roboto", Font.BOLD, 16));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setBackground(new Color(70, 150, 236));
        btnRegister.setBorder(new RoundedBorder(20));
        btnRegister.setFocusPainted(false);
        btnRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegister.addActionListener(e-> handleRegister());
    }

    private void handleRegister() {
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

           cardLayout.show(loginPanel,"login");
        }


    }

    private void layoutComponents() {
        // Giao diện dùng BorderLayout cho bố cục rõ ràng
        setLayout(new BorderLayout());
        Font fontLabel = new Font("Roboto", Font.PLAIN, 16);
        // ===== TOP: Nút quay lại =====
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar.setMaximumSize(new Dimension(1000, 50));
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
        centerPanel.add(Box.createVerticalStrut(10));
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
        label.setForeground(new Color(70, 150, 236));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setFont(new Font("Roboto", Font.PLAIN, 16));
        label.setBorder(null);
        return label;
    }

    private JTextField createTextField() {
        JTextField tf = new JTextField();
        styleTextField(tf);
        return tf;
    }

    private void styleTextField(JTextField tf) {
        tf.setPreferredSize(new Dimension(320, 45));
        tf.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        tf.setBorder(new RoundedBorder(15));
        tf.setFont(new Font("Roboto", Font.PLAIN, 14));
        tf.setMargin(new Insets(0, 8, 0, 8));
    }


    private JPanel createInputGroup(JLabel label, JTextField field) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        // Label
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        labelPanel.setBackground(Color.WHITE);
        labelPanel.add(label);
        panel.add(labelPanel);
//        panel.add(Box.createVerticalStrut(4)); // giảm khoảng cách label và field

        // Field
//        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        panel.add(field);

        return panel;

    }

    private CustomButton customButton(String text) {
        CustomButton button = new CustomButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(180, 35));
        button.setBackgroundColor(new Color(70, 150, 236));
        button.setTextColor(Color.WHITE);
        button.setBorderRadius(20);
        return button;
    }

    private CustomTextField customTextField(int column){
        CustomTextField textField = new CustomTextField(column);
        textField.setTextColor(Color.BLACK);
        textField.setFont(new Font("Roboto", Font.PLAIN, 15));
        textField.setPreferredSize(new Dimension(300, 40));
        textField.setMaximumSize(new Dimension(300, 40));
        textField.setMinimumSize(new Dimension(300, 40));
        textField.setMargin(new Insets(5, 10, 5, 10));
        textField.setBorderColor(new Color(70, 150, 236));
        textField.setBackground(Color.WHITE);
        textField.setBorderRadius(10);
        textField.setBounds(60, 180, 280, 40);
        textField.setDrawBorder(true);
        return textField;
    }


}
