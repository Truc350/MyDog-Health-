package view;

import dao.PetDAO;
import model.AppSession;
import model.Pet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

public class DashboardPanel extends JPanel {
    private JLabel avatarLabel, nameLabel, petTitle;
    private JPanel topPanel, centerWrapper, cardPanel;
    private JButton btnCheckSymptoms, btnCallDoctor, btnAddPet;
    private BottomMenuPanel bottomMenuPanel;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel petListPanel;
    private JScrollPane petScrollPane;

    public DashboardPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout());
        setBackground(new Color(200, 220, 245));

        // ==== TOP - Avatar + Name ====
        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));

        avatarLabel = new JLabel(getRoundedAvatar("src/image/avatarDefault.png", 60));
        avatarLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        avatarLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        avatarLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                chooseNewAvatar();
            }
        });

        nameLabel = new JLabel(AppSession.currentUser.getName());

        nameLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        topPanel.add(avatarLabel);
        topPanel.add(nameLabel);
        add(topPanel, BorderLayout.NORTH);

        // ==== CENTER ====
        centerWrapper = new JPanel();
        centerWrapper.setOpaque(false);
        centerWrapper.setLayout(new BoxLayout(centerWrapper, BoxLayout.Y_AXIS));

        cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 0, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        cardPanel.setMaximumSize(new Dimension(300, 500));
        cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        petTitle = new JLabel("Thú cưng đã thêm");
        petTitle.setFont(new Font("Roboto", Font.BOLD, 18));
        petTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnCheckSymptoms = createButton("⚕", "Kiểm tra triệu chứng");
        btnCheckSymptoms.addActionListener(e -> cardLayout.show(mainPanel, "checkSymptoms"));

        btnCallDoctor = createButton("📞", "Gọi bác sĩ");
        btnCallDoctor.addActionListener(e -> cardLayout.show(mainPanel, "doctorSelection"));

        btnAddPet = createButton("➕", "Thêm thú cưng");
        btnAddPet.addActionListener(e -> cardLayout.show(mainPanel, "addPet"));

        centerWrapper.add(Box.createVerticalStrut(10));
        centerWrapper.add(cardPanel);
        centerWrapper.add(Box.createVerticalGlue());

        add(centerWrapper, BorderLayout.CENTER);

        // ==== BOTTOM MENU ====
        bottomMenuPanel = new BottomMenuPanel();
        bottomMenuPanel.setNavigationHandler(cardLayout, mainPanel);
        add(bottomMenuPanel, BorderLayout.SOUTH);

        loadPetsFromDatabase(); // Load pet list khi khởi tạo
    }

    public void loadPetsFromDatabase() {
        cardPanel.removeAll();
        cardPanel.add(petTitle);
        cardPanel.add(Box.createVerticalStrut(10));

        petListPanel = new JPanel();
        petListPanel.setLayout(new BoxLayout(petListPanel, BoxLayout.Y_AXIS));
        petListPanel.setOpaque(false);

        PetDAO petDAO = new PetDAO();
        List<Pet> petList = petDAO.findPetsByUserId(AppSession.currentUser.getUserId());

        for (Pet pet : petList) {
            ImageIcon icon;
            if (pet.getAvatar() != null) {
                Image img = new ImageIcon(pet.getAvatar()).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
                BufferedImage rounded = makeRoundedImage(img, 45);
                icon = new ImageIcon(rounded);
            } else {
                ImageIcon defaultIcon = new ImageIcon("src/image/default_pet.png");
                Image img = defaultIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
                BufferedImage rounded = makeRoundedImage(img, 45);
                icon = new ImageIcon(rounded);
            }

            JPanel petPanel = createPetItem(icon, pet.getName());
            petListPanel.add(petPanel);
            petListPanel.add(Box.createVerticalStrut(10));
        }

        petScrollPane = new JScrollPane(petListPanel);
        petScrollPane.setPreferredSize(new Dimension(260, 130));
        petScrollPane.setMaximumSize(new Dimension(260, 130));
        petScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        petScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        petScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        cardPanel.add(petScrollPane);
        cardPanel.add(Box.createVerticalStrut(15));
        cardPanel.add(btnCheckSymptoms);
        cardPanel.add(Box.createVerticalStrut(10));
        cardPanel.add(btnCallDoctor);
        cardPanel.add(Box.createVerticalStrut(10));
        cardPanel.add(btnAddPet);

        cardPanel.revalidate();
        cardPanel.repaint();
    }

    private BufferedImage makeRoundedImage(Image scaledImage, int size) {
        BufferedImage rounded = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = rounded.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setClip(new Ellipse2D.Float(0, 0, size, size));
        g2.drawImage(scaledImage, 0, 0, size, size, null);
        g2.dispose();
        return rounded;
    }

    private JPanel createPetItem(ImageIcon avatarIcon, String name) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setOpaque(false);
        panel.setMaximumSize(new Dimension(260, 60));

        JLabel imgLabel = new JLabel(avatarIcon);
        JLabel nameLabel = new JLabel("<html><i>" + name + "</i></html>");
        nameLabel.setFont(new Font("Roboto", Font.ITALIC, 15));

        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infoPanel.setOpaque(false);
        infoPanel.add(imgLabel);
        infoPanel.add(Box.createHorizontalStrut(10));
        infoPanel.add(nameLabel);

        JButton deleteButton = new JButton("x");
        deleteButton.setPreferredSize(new Dimension(30, 30));
        deleteButton.setFocusPainted(false);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setBorderPainted(false);
        deleteButton.setForeground(Color.RED);
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        deleteButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(panel,
                    "Bạn có chắc chắn muốn xóa thú cưng \"" + name + "\"?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                PetDAO petDAO = new PetDAO();
                boolean success = false;
                try {
                    success = petDAO.deletePetByNameAndUserId(name, AppSession.currentUser.getUserId());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (success) {
                    JOptionPane.showMessageDialog(panel, "Đã xóa thú cưng: " + name);
                    loadPetsFromDatabase();
                } else {
                    JOptionPane.showMessageDialog(panel, "Không thể xóa thú cưng: " + name);
                }
            }
        });

        panel.add(infoPanel, BorderLayout.CENTER);
        panel.add(deleteButton, BorderLayout.EAST);
        return panel;
    }

    private JButton createButton(String icon, String label) {
        JButton button = new JButton(icon + " " + label);
        button.setBackground(new Color(90, 150, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Roboto", Font.BOLD, 16));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setMaximumSize(new Dimension(240, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    private void chooseNewAvatar() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn ảnh đại diện mới");
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String extension = getFileExtension(selectedFile.getName());
            if (!extension.matches("jpg|jpeg|png")) {
                JOptionPane.showMessageDialog(this, "Chỉ chấp nhận ảnh JPG hoặc PNG!");
                return;
            }

            try {
                File destDir = new File("user_data/avatar");
                if (!destDir.exists()) destDir.mkdirs();
                String fileName = "avatar_" + AppSession.currentUser.getUserId() + "." + extension;
                File destFile = new File(destDir, fileName);

                java.nio.file.Files.copy(selectedFile.toPath(), destFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                AppSession.currentUser.setAvatarPath(destFile.getAbsolutePath());
                avatarLabel.setIcon(getRoundedAvatar(destFile.getAbsolutePath(), 60));
                JOptionPane.showMessageDialog(this, "✅ Cập nhật ảnh đại diện thành công!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "❌ Lỗi khi cập nhật ảnh đại diện.");
            }
        }
    }

    private String getFileExtension(String name) {
        int lastDot = name.lastIndexOf(".");
        return (lastDot >= 0) ? name.substring(lastDot + 1).toLowerCase() : "";
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
            return new ImageIcon();
        }
    }
    public void updateUserInfo() {
        if (AppSession.currentUser != null) {
            nameLabel.setText(AppSession.currentUser.getName());
        }
    }

}
