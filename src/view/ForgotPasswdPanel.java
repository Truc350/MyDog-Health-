package view;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import java.awt.*;

public class ForgotPasswdPanel extends JPanel {

    private JTextField txtEmail;
    private JButton btnFind;
    private JLabel lblResult;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public ForgotPasswdPanel() {
//    public ForgotPasswdPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setLayout(null);
        setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("🔐 Quên mật khẩu", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Roboto", Font.BOLD, 20));
        lblTitle.setBounds(80, 50, 240, 40);
        add(lblTitle);

        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Roboto", Font.PLAIN, 14));
        txtEmail.setBorder(BorderFactory.createTitledBorder("Nhập email"));
        txtEmail.setBounds(60, 110, 280, 50);
        add(txtEmail);

        btnFind = new JButton("Lấy lại mật khẩu");
        btnFind.setFont(new Font("Roboto", Font.BOLD, 14));
        btnFind.setBackground(new Color(70, 150, 236));
        btnFind.setForeground(Color.WHITE);
        btnFind.setFocusPainted(false);
        btnFind.setBounds(120, 180, 160, 40);
        add(btnFind);

        lblResult = new JLabel("", SwingConstants.CENTER);
        lblResult.setFont(new Font("Roboto", Font.ITALIC, 13));
        lblResult.setBounds(40, 240, 320, 30);
        add(lblResult);
//
//            btnFind.addActionListener(e -> {
//                String email = txtEmail.getText().trim();
//                if (email.isEmpty()) {
//                    lblResult.setForeground(Color.RED);
//                    lblResult.setText("Vui lòng nhập email.");
//                    return;
//                }
//
//                UserDAO dao = new UserDAO();
//                User user = dao.findByEmail(email);
//                if (user != null) {
//                    lblResult.setForeground(new Color(0, 128, 0));
//                    lblResult.setText("🔑 Mật khẩu của bạn: " + user.getPassword());
//                } else {
//                    lblResult.setForeground(Color.RED);
//                    lblResult.setText("❌ Không tìm thấy email trong hệ thống.");
//                }
//            });
    }


}


