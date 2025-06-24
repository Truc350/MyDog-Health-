package view;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class SettingPanel extends JPanel {
    public SettingPanel() {
        setLayout(null);
        setBackground(new Color(214, 229, 250)); // Light blue
        setPreferredSize(new Dimension(320, 650));

        // Header

        JButton btnBack = new JButton();
        btnBack.setText("");
        btnBack.setIcon(new ImageIcon("src/image/back.png"));

        btnBack.setFocusPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorder(BorderFactory.createLineBorder(new Color(90, 150, 255), 2));
        btnBack.setForeground(new Color(90, 150, 255));
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.setPreferredSize(new Dimension(36, 36));
        btnBack.setBorder(new RoundedBorder(36)); // hình tròn

        btnBack.setBounds(15, 10, 30, 30); // ✅ Thêm dòng này để nó hiện
        add(btnBack);


        JLabel title = new JLabel("Tài khoản người dùng");
        title.setFont(new Font("Roboto", Font.BOLD, 20));
        title.setBounds(60, 15, 250, 25);
        add(title);

        // User info panel (smaller)
        JPanel userInfo = createCardPanel();
        userInfo.setBounds(20, 50, 270, 50);
        userInfo.setLayout(null);

        JLabel name = new JLabel("Nguyễn Anh Tú");
        name.setBounds(10, 3, 200, 20);
        name.setFont(new Font("Roboto", Font.BOLD, 14));
        userInfo.add(name);

        JLabel email = new JLabel("antus@gmail.com");
        email.setBounds(10, 22, 200, 20);
        email.setFont(new Font("Roboto", Font.PLAIN, 12));
        userInfo.add(email);

        JButton editBtn = new JButton();
        editBtn.setIcon(new ImageIcon("src/image/edit.png"));
        editBtn.setBounds(235, 13, 24, 24);
        styleRoundIconButton(editBtn);
        userInfo.add(editBtn);
        add(userInfo);

        // Notifications
        JLabel notifyLabel = new JLabel("Thông báo");
        notifyLabel.setFont(new Font("Roboto", Font.BOLD, 15));
        notifyLabel.setBounds(20, 115, 100, 20);
        add(notifyLabel);

        JPanel notifyPanel = createCardPanel();
        notifyPanel.setLayout(new GridLayout(2, 1));
        notifyPanel.setBounds(20, 140, 270, 70);
        notifyPanel.add(createToggleRow("Cảnh báo lịch khám sắp tới"));
        notifyPanel.add(createToggleRow("Nhận thông báo từ bác sĩ"));
        add(notifyPanel);

        // Security
        JLabel securityLabel = new JLabel("Bảo mật");
        securityLabel.setFont(new Font("Arial", Font.BOLD, 15));
        securityLabel.setBounds(20, 230, 100, 20);
        add(securityLabel);

        JPanel securityPanel = createCardPanel();
        securityPanel.setLayout(new GridLayout(3, 1, 0, 8));
        securityPanel.setBounds(20, 255, 270, 110);

        securityPanel.add(createPasswordRow("Đổi mật khẩu"));
        securityPanel.add(createButton("Đăng xuất"));
        securityPanel.add(createButton("Xóa tài khoản"));
        add(securityPanel);
    }

    private JPanel createCardPanel() {
        JPanel panel = new RoundedPanel(
                16, // bo góc 16px
                Color.WHITE, // nền trắng
                new Color(200, 200, 200) // viền xám
        );
        panel.setLayout(new GridLayout(0, 1)); // hoặc GridLayout(2,1) tùy mục đích
        panel.setBorder(new EmptyBorder(8, 10, 8, 10)); // padding
        return panel;
    }


    private JPanel createToggleRow(String labelText) {
        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(false);
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Roboto", Font.BOLD, 13));

        JToggleButton toggle = new JToggleButton();
        toggle.setSelected(true);
        toggle.setPreferredSize(new Dimension(50, 25));
        toggle.setUI(new ToggleButtonUI());

        row.add(label, BorderLayout.WEST);
        row.add(toggle, BorderLayout.EAST);
        return row;
    }

    private JPanel createPasswordRow(String text) {
        // Custom JPanel để bo góc
        JPanel row = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Màu nền và viền
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);

                g2.setColor(new Color(180, 180, 180)); // viền
                g2.setStroke(new BasicStroke(1));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);

                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            public boolean isOpaque() {
                return false; // Cho phép nền bo tròn hiển thị đúng
            }
        };

        row.setPreferredSize(new Dimension(270, 36));
        row.setLayout(new BorderLayout());
        row.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 4)); // padding trong

        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        row.add(label, BorderLayout.CENTER);

        JButton icon = new JButton();
        icon.setIcon(new ImageIcon("src/image/edit.png")); // Thay icon tùy đường dẫn
        icon.setPreferredSize(new Dimension(30, 30));
        styleRoundIconButton(icon);
        row.add(icon, BorderLayout.EAST);

        return row;
    }


    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false); // Tắt mặc định để vẽ tùy chỉnh
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setForeground(Color.WHITE);

        // Tùy chỉnh giao diện bằng UI delegate
        button.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int width = c.getWidth();
                int height = c.getHeight();

                // Màu xanh bo tròn
                g2.setColor(new Color(70, 150, 236)); // #007AFF
                g2.fillRoundRect(0, 0, width, height, 20, 20); // Bo góc

                super.paint(g2, c);
                g2.dispose();
            }
        });

        button.setPreferredSize(new Dimension(250, 36));
        return button;
    }


    private void styleRoundIconButton(JButton button) {
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(Color.DARK_GRAY);
        button.setFont(new Font("Arial", Font.PLAIN, 12));
    }

    private void styleIconButton(JButton button) {
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(Color.DARK_GRAY);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
    }

    // Custom Toggle UI
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

//   // test
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("User Account");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setContentPane(new SettingPanel());
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
}
