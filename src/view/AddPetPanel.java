package view;

import controller.PetController;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AddPetPanel extends JPanel {
    private JButton btnBack, btnUploadAvatar, btnAdd;
    private JTextField txtName, txtBreed, txtAge, txtWeight, txtMedicalHistory;
    private JRadioButton rdoMale, rdoFemale;
    private ButtonGroup genderGroup;
    private JPanel petListPanel;
    private BottomMenuPanel bottomMenuPanel;
    private File selectedAvatarFile;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public AddPetPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setPreferredSize(new Dimension(400, 700));
        setBackground(new Color(200, 220, 245));
        setLayout(null);

        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        btnBack = new JButton(new ImageIcon("src/image/back.png"));
        styleIconButton(btnBack);

        txtName = createRoundedTextField("Tên thú cưng");
        txtBreed = createRoundedTextField("Giống loài");
        txtAge = createRoundedTextField("Tuổi");
        txtWeight = createRoundedTextField("Cân nặng(kg)");
        txtMedicalHistory = createRoundedTextField("Tiền sử bệnh");

        // Giới tính
        rdoMale = new JRadioButton("Đực");
        rdoFemale = new JRadioButton("Cái");
        rdoMale.setOpaque(false);
        rdoFemale.setOpaque(false);
        rdoMale.setFont(new Font("Arial", Font.BOLD, 13));
        rdoFemale.setFont(new Font("Arial", Font.BOLD, 13));
        genderGroup = new ButtonGroup();
        genderGroup.add(rdoMale);
        genderGroup.add(rdoFemale);

        btnUploadAvatar = new JButton(new ImageIcon("src/image/uploadImg.png"));
        btnUploadAvatar.setFont(new Font("Arial", Font.BOLD, 12));
        btnUploadAvatar.setForeground(Color.BLACK);
        btnUploadAvatar.setBackground(Color.WHITE);
        btnUploadAvatar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        btnUploadAvatar.setFocusPainted(false);
        btnUploadAvatar.addActionListener(e -> chooseAvatarImage());




        btnAdd = new JButton("Thêm");
        btnAdd.setFont(new Font("Arial", Font.BOLD, 14));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setBackground(new Color(90, 150, 255));
        btnAdd.setBorder(new RoundedBorder(12));
        btnAdd.setFocusPainted(false);

        bottomMenuPanel = new BottomMenuPanel();
    }
    public File getAvatarFile() {
        return selectedAvatarFile;
    }
    private void chooseAvatarImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn ảnh đại diện");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                BufferedImage originalImage = ImageIO.read(selectedFile);

                // Scale ảnh về đúng kích thước hiển thị
                Image scaledImage = originalImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);

                // Làm ảnh tròn
                BufferedImage roundedImage = makeRoundedImage(scaledImage, 80);

                // Hiển thị ảnh lên nút
                btnUploadAvatar.setIcon(new ImageIcon(roundedImage));
                btnUploadAvatar.setText(""); // Xóa text nếu có

                // Lưu file ảnh đã chọn để dùng khi lưu vào DB
                selectedAvatarFile = selectedFile;

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Không thể tải ảnh: " + ex.getMessage());
            }
        }
    }


    private BufferedImage makeRoundedImage(Image scaledImage, int size) {
        BufferedImage rounded = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = rounded.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, size, size);
        g2.setClip(circle);
        g2.drawImage(scaledImage, 0, 0, size, size, null);
        g2.dispose();

        return rounded;
    }


    private void layoutComponents() {
        btnBack.setBounds(10, 10, 32, 32);
        add(btnBack);

        JLabel lblTitle = new JLabel("Thêm thú cưng");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setBounds(20, 50, 300, 30);
        add(lblTitle);

        txtName.setBounds(20, 90, 250, 35);
        add(txtName);

        btnUploadAvatar.setBounds(290, 90, 80, 80);
        add(btnUploadAvatar);

        txtBreed.setBounds(20, 135, 250, 35);
        add(txtBreed);

        txtAge.setBounds(20, 180, 350, 35);
        add(txtAge);

        txtWeight.setBounds(20, 225, 160, 35);
        add(txtWeight);

        rdoMale.setBounds(200, 225, 60, 35);
        rdoFemale.setBounds(270, 225, 60, 35);
        add(rdoMale);
        add(rdoFemale);

        txtMedicalHistory.setBounds(20, 270, 350, 35);
        add(txtMedicalHistory);

        JLabel lblList = new JLabel("Danh sách thú cưng đã có");
        lblList.setFont(new Font("Arial", Font.BOLD, 15));
        lblList.setBounds(20, 315, 300, 25);
        add(lblList);

        petListPanel = new JPanel();
        petListPanel.setLayout(new BoxLayout(petListPanel, BoxLayout.Y_AXIS));
        petListPanel.setBackground(Color.WHITE);
        petListPanel.setBorder(new LineBorder(new Color(220, 220, 220), 1, true));
        petListPanel.setBounds(20, 345, 360, 160);
        petListPanel.add(createPetItem("src/image/dog1.jpg", "Miu"));
        petListPanel.add(createPetItem("src/image/dog2.jpg", "Đốm"));
        add(petListPanel);

        btnAdd.setBounds(80, 515, 240, 45);
        add(btnAdd);

        bottomMenuPanel.setBounds(0, 610, 400, 60);
        add(bottomMenuPanel);
    }

    private JPanel createPetItem(String imagePath, String name) {
        JPanel panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(360, 70));
        panel.setBackground(Color.WHITE);

        JLabel imgLabel = new JLabel(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        imgLabel.setBounds(10, 10, 50, 50);
        panel.add(imgLabel);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 13));
        nameLabel.setBounds(70, 25, 100, 20);
        panel.add(nameLabel);

        JButton btnEdit = new JButton("Sửa");
        btnEdit.setBounds(200, 20, 60, 30);
        styleSmallButton(btnEdit);
        panel.add(btnEdit);

        JButton btnDelete = new JButton("Xóa");
        btnDelete.setBounds(270, 20, 60, 30);
        styleSmallButton(btnDelete);
        panel.add(btnDelete);

        return panel;
    }

    private JTextField createRoundedTextField(String placeholder) {
        JTextField field = new JTextField(placeholder);
        field.setFont(new Font("Arial", Font.PLAIN, 13));
        field.setForeground(Color.GRAY);
        field.setBorder(new CompoundBorder(
                new LineBorder(new Color(130, 170, 255), 1, true),
                new EmptyBorder(5, 10, 5, 10)
        ));

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });

        return field;
    }

    private void styleIconButton(JButton btn) {
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
    }

    private void styleSmallButton(JButton btn) {
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setBackground(new Color(90, 150, 255));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(new RoundedBorder(10));
    }

    public void addPetToListPanel(String imagePath, String name) {
        petListPanel.add(createPetItem(imagePath, name));
        petListPanel.revalidate(); // cập nhật layout
        petListPanel.repaint();    // vẽ lại giao diện
    }



    static class RoundedBorder extends AbstractBorder {
        private final int radius;
        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(new Color(150, 200, 255));
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
    public  String getNamePet(){
        return txtName.getText();
    }
    public String getBreed(){
        return txtBreed.getText();
    }
    public int getAge() {
        return Integer.parseInt(txtAge.getText().trim());
    }


    public float getWeight() {
        return Float.parseFloat(txtWeight.getText().trim());
    }

    public String getGender() {
        return rdoMale.isSelected() ? "Đực" : "Cái";
    }

    public String getMedicalHistory() {
        return txtMedicalHistory.getText().trim();
    }

    public void clear(){
        txtName.setText("");
        txtBreed.setText("");
        txtAge.setText("");
        txtWeight.setText("");
        txtMedicalHistory.setText("");
        genderGroup.clearSelection();
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }




    public static void main(String[] args) {
        JFrame frame = new JFrame("Thêm thú cưng");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);

        JPanel mainPanel = new JPanel(new CardLayout());
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();

        AddPetPanel addPetPanel = new AddPetPanel(cardLayout, mainPanel);
        new PetController(addPetPanel); // Controller gắn sự kiện

        mainPanel.add(addPetPanel, "AddPet");

        frame.setContentPane(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Gán userId đã tồn tại trong DB
        model.User fakeUser = new model.User("3acce9e5-0285-4c6a-9db9-6e58377c6816");
        model.AppSession.currentUser = fakeUser;
    }


}
