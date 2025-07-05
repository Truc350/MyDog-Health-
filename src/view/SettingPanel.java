package view;

import dao.UserDAO;
import model.AppSession;
import model.Setting;
import model.User;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class SettingPanel extends JPanel {
    private BottomMenuPanel bottomMenuPanel;
    private Setting settingModel;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    public SettingPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.settingModel = AppSession.currentUser.getSetting();

        setLayout(new BorderLayout());
        setBackground(new Color(214, 229, 250));
        setPreferredSize(new Dimension(400, 700));

        JPanel contentPanel = new JPanel(null);
        contentPanel.setBackground(new Color(214, 229, 250));

        // Header cố định
        JButton btnBack = new JButton(new ImageIcon("src/image/back.png"));
        btnBack.setFocusPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);
        btnBack.setBounds(20, 15, 36, 36);
        contentPanel.add(btnBack);

        JLabel title = new JLabel("Tài khoản người dùng");
        title.setFont(new Font("Roboto", Font.BOLD, 20));
        title.setBounds(70, 20, 300, 30);
        contentPanel.add(title);

        // User Info
        JPanel userInfo = createCardPanel();
        userInfo.setLayout(null);
        userInfo.setBounds(20, 70, 360, 65);

        JLabel name = new JLabel("Nguyễn Anh Tú");
        name.setFont(new Font("Roboto", Font.BOLD, 14));
        name.setBounds(10, 5, 250, 20);
        userInfo.add(name);

        JLabel email = new JLabel("antus@gmail.com");
        email.setFont(new Font("Roboto", Font.PLAIN, 12));
        email.setBounds(10, 30, 250, 20);
        userInfo.add(email);

        JButton editBtn = new JButton(new ImageIcon("src/image/edit.png"));
        editBtn.setBounds(320, 18, 24, 24);
        styleRoundIconButton(editBtn);
        editBtn.addActionListener(e -> editUserEmail(email));
        userInfo.add(editBtn);
        contentPanel.add(userInfo);

        // Thông báo
        JLabel notifyLabel = new JLabel("Thông báo");
        notifyLabel.setFont(new Font("Roboto", Font.BOLD, 15));
        notifyLabel.setBounds(20, 145, 200, 25);
        contentPanel.add(notifyLabel);

        JPanel notifyPanel = createCardPanel();
        notifyPanel.setLayout(new GridLayout(2, 1, 0, 5));
        notifyPanel.setBounds(20, 175, 360, 70);
        notifyPanel.add(createToggleRow("Cảnh báo lịch khám sắp tới", settingModel.isAppointmentReminder(),
                () -> settingModel.toggleAppointmentReminder()));
        notifyPanel.add(createToggleRow("Nhận thông báo từ bác sĩ", settingModel.isDoctorNotification(),
                () -> settingModel.toggleDoctorNotification()));
        contentPanel.add(notifyPanel);

        // Bảo mật
        JLabel securityLabel = new JLabel("Bảo mật");
        securityLabel.setFont(new Font("Roboto", Font.BOLD, 15));
        securityLabel.setBounds(20, 260, 100, 25);
        contentPanel.add(securityLabel);

        JPanel securityPanel = createCardPanel();
        securityPanel.setLayout(new GridLayout(3, 1, 0, 10));
        securityPanel.setBounds(20, 290, 360, 140);
        securityPanel.add(createPasswordRow("Đổi mật khẩu"));
        JButton logoutBtn = createButton("Đăng xuất");
        logoutBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc muốn đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                AppSession.logoutCurrentUser();
                cardLayout.show(mainPanel, "login");
            }
        });

        securityPanel.add(logoutBtn);

        JButton deleteBtn = createButton("Xóa tài khoản");
        deleteBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc muốn xóa tài khoản?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                User currentUser = AppSession.currentUser;
                if (currentUser != null && currentUser.deleteAccount()) {
                    AppSession.logoutCurrentUser();
                    JOptionPane.showMessageDialog(this, "Tài khoản đã bị xóa.");
                    cardLayout.show(mainPanel, "login");
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa tài khoản thất bại!");
                }
            }
        });

        securityPanel.add(deleteBtn);
        contentPanel.add(securityPanel);

        add(contentPanel, BorderLayout.CENTER);

        // Footer
        bottomMenuPanel = new BottomMenuPanel();
        bottomMenuPanel.setNavigationHandler(cardLayout, mainPanel);
//        bottomMenuPanel.setPreferredSize(new Dimension(400, 60));
        add(bottomMenuPanel, BorderLayout.SOUTH);
    }


    private void editUserEmail(JLabel emailLabel) {
        String currentEmail = AppSession.currentUser.getEmail();
        String newEmail = JOptionPane.showInputDialog(this, "Nhập email mới:", currentEmail);

        if (newEmail != null && !newEmail.trim().isEmpty() && !newEmail.equals(currentEmail)) {
            boolean updated = new UserDAO().updateEmail(AppSession.currentUser.getUserId(), newEmail);
            if (updated) {
                AppSession.currentUser.setEmail(newEmail);
                emailLabel.setText(newEmail);
                JOptionPane.showMessageDialog(this, "✔️ Đã cập nhật email vào hệ thống.");
            } else {
                JOptionPane.showMessageDialog(this, "❌ Cập nhật email thất bại!");
            }
        }
    }

    private JPanel createCardPanel() {
        JPanel panel = new RoundedPanel(16, Color.WHITE, new Color(200, 200, 200));
        panel.setLayout(new GridLayout(0, 1));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        return panel;
    }

    private JPanel createToggleRow(String labelText,boolean initialValue, Runnable onToggle) {
        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(false);
        row.setBorder(new EmptyBorder(0, 0, 0, 10));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Roboto", Font.PLAIN, 13));

        JToggleButton toggle = new JToggleButton();
        toggle.setSelected(initialValue);
        toggle.setPreferredSize(new Dimension(50, 25));
        toggle.setUI(new ToggleButtonUI());

        // Khi người dùng nhấn nút → chạy hành động
        toggle.addActionListener(e -> {
            onToggle.run();
        });

        row.add(label, BorderLayout.WEST);
        row.add(toggle, BorderLayout.EAST);
        return row;
    }

    private JPanel createPasswordRow(String text) {
        JPanel row = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                g2.setColor(new Color(180, 180, 180));
                g2.setStroke(new BasicStroke(1));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            public boolean isOpaque() {
                return false;
            }
        };

        row.setPreferredSize(new Dimension(360, 36));
        row.setLayout(new BorderLayout());
        row.setBorder(new EmptyBorder(4, 10, 4, 4));

        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        row.add(label, BorderLayout.CENTER);

        JButton icon = new JButton(new ImageIcon("src/image/edit.png"));
        icon.setPreferredSize(new Dimension(30, 30));
        styleRoundIconButton(icon);
        // trong createPasswordRow
        icon.addActionListener(e -> {
            cardLayout.show(mainPanel, "changePassword");
        });

        row.add(icon, BorderLayout.EAST);

        return row;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setFont(new Font("Roboto", Font.BOLD, 13));
        button.setForeground(Color.WHITE);

        button.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int width = c.getWidth();
                int height = c.getHeight();
                g2.setColor(new Color(70, 150, 236));
                g2.fillRoundRect(0, 0, width, height, 20, 20);
                super.paint(g2, c);
                g2.dispose();
            }
        });

        button.setPreferredSize(new Dimension(320, 40));
        return button;
    }

    private void styleRoundIconButton(JButton button) {
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }

    static class ToggleButtonUI extends javax.swing.plaf.basic.BasicToggleButtonUI {
        @Override
        public void paint(Graphics g, JComponent c) {
            AbstractButton b = (AbstractButton) c;
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = c.getWidth();
            int height = c.getHeight();
            g2.setColor(b.isSelected() ? new Color(0, 122, 255) : Color.LIGHT_GRAY);
            g2.fillRoundRect(0, 0, width, height, height, height);
            g2.setColor(Color.WHITE);
            int knobSize = height - 4;
            int knobX = b.isSelected() ? width - knobSize - 2 : 2;
            g2.fillOval(knobX, 2, knobSize, knobSize);
            g2.dispose();
        }

        @Override
        public Dimension getPreferredSize(JComponent c) {
            return new Dimension(50, 25);
        }
    }



}




