package view;

import model.AppSession;
import model.User;

import javax.swing.*;
import java.awt.*;

public class ChangePasswordPanel extends JPanel {
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;
    private JButton saveButton;
    private JButton backButton;

    public ChangePasswordPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(null);
        setBackground(new Color(214, 229, 250));

        JLabel titleLabel = new JLabel("Đổi mật khẩu");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        titleLabel.setBounds(120, 30, 200, 30);
        add(titleLabel);

        backButton = new JButton("← Quay lại");
        backButton.setBounds(20, 20, 100, 30);
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "setting"));
        add(backButton);

        oldPasswordField = createPasswordField("Mật khẩu hiện tại", 100);
        newPasswordField = createPasswordField("Mật khẩu mới", 160);
        confirmPasswordField = createPasswordField("Xác nhận lại mật khẩu", 220);

        saveButton = new JButton("Lưu thay đổi");
        saveButton.setBounds(100, 280, 200, 40);
        saveButton.setBackground(new Color(70, 150, 236));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        add(saveButton);

        saveButton.addActionListener(e -> {
            String oldPass = new String(oldPasswordField.getPassword());
            String newPass = new String(newPasswordField.getPassword());
            String confirm = new String(confirmPasswordField.getPassword());

            if (!newPass.equals(confirm)) {
                JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không khớp!");
                return;
            }

            User currentUser = AppSession.currentUser;
            if (currentUser != null && currentUser.changePassword(oldPass, newPass)) {
                JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công!");
                cardLayout.show(mainPanel, "setting");
            } else {
                JOptionPane.showMessageDialog(this, "Mật khẩu cũ không đúng hoặc lỗi hệ thống.");
            }
        });
    }

    private JPasswordField createPasswordField(String label, int y) {
        JLabel lbl = new JLabel(label);
        lbl.setBounds(50, y, 300, 20);
        add(lbl);

        JPasswordField field = new JPasswordField();
        field.setBounds(50, y + 25, 300, 30);
        add(field);
        return field;
    }
}
