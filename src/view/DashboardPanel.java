package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Year;
import javax.imageio.ImageIO;

public class DashboardPanel extends JPanel {

    private JButton btnCheckSymptoms, btnCallDoctor, btnAddPet;

    public DashboardPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(200, 220, 245)); // Light blue

        // ===== TOP - Avatar + Name =====
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);

        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));

        JLabel avatarLabel = new JLabel(getRoundedAvatar("src/image/avatar.jpg", 60));
        avatarLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
//        ImageIcon avatar = getRoundedAvatar("src/image/avatar.jpg", 60);
//        System.out.println(avatar.getIconWidth());


        JLabel nameLabel = new JLabel("Nguyễn Anh Tú");
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        topPanel.add(avatarLabel);
        topPanel.add(nameLabel);
        add(topPanel, BorderLayout.NORTH);

        // ===== CENTER - Card =====
        JPanel centerWrapper = new JPanel();
        centerWrapper.setOpaque(false);
        centerWrapper.setLayout(new BoxLayout(centerWrapper, BoxLayout.Y_AXIS));

        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        cardPanel.setMaximumSize(new Dimension(300, 400));
        cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        cardPanel.setOpaque(true);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 0, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel petTitle = new JLabel("Thú cưng đã thêm");
        petTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        petTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel pet1 = createPetItem("src/image/dog1.jpg", "Miu");
        JPanel pet2 = createPetItem("src/image/dog2.jpg", "Đốm");

        btnCheckSymptoms = createButton("⚕", "Kiểm tra triệu chứng");

        btnCallDoctor = createButton("📞", "Gọi bác sĩ");
        btnAddPet = createButton("➕", "Thêm thú cưng");

        cardPanel.add(petTitle);
        cardPanel.add(Box.createVerticalStrut(10));
        cardPanel.add(pet1);
        cardPanel.add(Box.createVerticalStrut(10));
        cardPanel.add(pet2);
        cardPanel.add(Box.createVerticalStrut(15));
        cardPanel.add(btnCheckSymptoms);
        cardPanel.add(Box.createVerticalStrut(10));
        cardPanel.add(btnCallDoctor);
        cardPanel.add(Box.createVerticalStrut(10));
        cardPanel.add(btnAddPet);

        centerWrapper.add(Box.createVerticalStrut(10)); // khoảng cách nhỏ phía trên
        centerWrapper.add(cardPanel);
        centerWrapper.add(Box.createVerticalGlue());    // vẫn giữ ở dưới để đẩy lên


        add(centerWrapper, BorderLayout.CENTER);

        // ===== BOTTOM NAVIGATION =====
        JPanel navBar = new JPanel(new GridLayout(1, 5, 10, 0));
        navBar.setBackground(new Color(200, 220, 245));
        navBar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        navBar.add(createNavIcon("🏠"));
        navBar.add(createNavIcon("➕"));
        navBar.add(createNavIcon("📞"));
        navBar.add(createNavIcon("⚙"));
        navBar.add(createNavIcon("👤"));

        add(navBar, BorderLayout.SOUTH);
    }

    private JPanel createPetItem(String imagePath, String name) {
        JPanel panel = new JPanel(new GridLayout(1,3,10,0));
        panel.setOpaque(false);
        panel.setMaximumSize(new Dimension(260, 60));

        // LEFT: Ảnh + Tên thú cưng
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infoPanel.setOpaque(false);

        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(img));

        JLabel nameLabel = new JLabel("<html><i>" + name + "</i></html>");
        nameLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));

        infoPanel.add(imgLabel);
        infoPanel.add(Box.createHorizontalStrut(10));
        infoPanel.add(nameLabel);

        // RIGHT: Nút x để xoá
        JButton deleteButton = new JButton("x");
        deleteButton.setPreferredSize(new Dimension(30, 30));
        deleteButton.setFocusPainted(false);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setBorderPainted(false);
        deleteButton.setForeground(Color.RED);
        deleteButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hành động xoá thú cưng
        deleteButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    panel,
                    "Bạn có chắc chắn muốn xóa thú cưng này?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {
                Container parent = panel.getParent();
                parent.remove(panel);
                parent.revalidate();
                parent.repaint();
            }
        });


        panel.add(infoPanel);

        panel.add(deleteButton);

        return panel;
    }




    private JButton createButton(String icon, String label) {
        JButton button = new JButton(icon + " " + label);
        button.setBackground(new Color(90, 150, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setMaximumSize(new Dimension(240, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    private JLabel createNavIcon(String emoji) {
        JLabel label = new JLabel(emoji, SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.PLAIN, 20));
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return label;
    }

    private ImageIcon getRoundedAvatar(String path, int size) {
        try {
            BufferedImage master = ImageIO.read(new File(path));
            BufferedImage scaled = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = scaled.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setClip(new Ellipse2D.Float(0, 0, size, size));
            g2.drawImage(master.getScaledInstance(size, size, Image.SCALE_SMOOTH), 0, 0, null);
            g2.dispose();
            return new ImageIcon(scaled);
        } catch (Exception e) {
            return new ImageIcon(); // fallback nếu lỗi
        }
    }


}
