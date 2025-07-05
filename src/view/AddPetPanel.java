package view;

import controller.PetController;
import model.Pet;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.function.Consumer;

import dao.PetDAO;
import model.AppSession;


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
    private Consumer<String> deleteListener;
    private Consumer<String> editListener;
    private JButton btnUpdate;
    private PetDAO petDAO;
    private DashboardPanel dashboardPanel;


    private JScrollPane scrollPane;
    private String editingPetId = null;// luu Id thu cung dang sua
    private JButton btnEdit;

    public AddPetPanel(CardLayout cardLayout, JPanel mainPanel, DashboardPanel dashboardPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.dashboardPanel = dashboardPanel;
        setPreferredSize(new Dimension(400, 700));
        setBackground(new Color(200, 220, 245));
        setLayout(null);
        this.petDAO = new PetDAO();


        initComponents();
        layoutComponents();
        loadPetListFromDatabase();// khi login lai thi se hien thi danh sach thu cung
    }

    public void loadPetListFromDatabase() {
        petListPanel.removeAll(); // Xóa danh sách cũ nếu có

        if (AppSession.currentUser == null) return;

        List<Pet> pets = petDAO.getPetsByUserId(AppSession.currentUser.getUserId());
        for (Pet pet : pets) {
            ImageIcon avatarIcon = null;
            if (pet.getAvatar() != null) {
                avatarIcon = new ImageIcon(pet.getAvatar());
            }
            addPetToListPanel(avatarIcon, pet.getName()); // ✅ dùng ImageIcon thay vì String path
        }

        syncDashboard(); // ✅ Đồng bộ Dashboard sau khi load
    }



    private void initComponents() {
        btnBack = new JButton(new ImageIcon("src/image/back.png"));
        styleIconButton(btnBack);
        btnBack.addActionListener(e -> cardLayout.show(mainPanel, "dashboard"));

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
        rdoMale.setFont(new Font("Roboto", Font.BOLD, 13));
        rdoFemale.setFont(new Font("Roboto", Font.BOLD, 13));
        genderGroup = new ButtonGroup();
        genderGroup.add(rdoMale);
        genderGroup.add(rdoFemale);

        btnUploadAvatar = new JButton(new ImageIcon("src/image/uploadImg.png"));
        btnUploadAvatar.setFont(new Font("Roboto", Font.BOLD, 12));
        btnUploadAvatar.setForeground(Color.BLACK);
        btnUploadAvatar.setBackground(Color.WHITE);
        btnUploadAvatar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        btnUploadAvatar.setFocusPainted(false);
        btnUploadAvatar.addActionListener(e -> chooseAvatarImage());


        btnAdd = new JButton("Thêm");
        btnAdd.setFont(new Font("Roboto", Font.BOLD, 14));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setBackground(new Color(90, 150, 255));
        btnAdd.setBorder(new RoundedBorder(12));
        btnAdd.setFocusPainted(false);
        btnUpdate = new JButton("Cập nhật");
        btnUpdate.setFont(new Font("Roboto", Font.BOLD, 14));
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setBackground(new Color(90, 150, 255));
        btnUpdate.setBorder(new RoundedBorder(12));
        btnUpdate.setFocusPainted(false);
        btnUpdate.setVisible(false); // ẩn ban đầu

        bottomMenuPanel = new BottomMenuPanel();
        bottomMenuPanel.setNavigationHandler(cardLayout, mainPanel);
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
        lblTitle.setFont(new Font("Roboto", Font.BOLD, 18));
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
        lblList.setFont(new Font("Roboto", Font.BOLD, 15));
        lblList.setBounds(20, 315, 300, 25);
        add(lblList);

        petListPanel = new JPanel();
        petListPanel.setLayout(new BoxLayout(petListPanel, BoxLayout.Y_AXIS));
        petListPanel.setBackground(Color.WHITE);

// Bọc trong JScrollPane
        scrollPane = new JScrollPane(petListPanel);
        scrollPane.setBounds(20, 345, 360, 160);
        scrollPane.setBorder(new LineBorder(new Color(220, 220, 220), 1, true));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(scrollPane);

//        petListPanel.add(createPetItem("src/image/dog1.jpg", "Miu"));
//        petListPanel.add(createPetItem("src/image/dog2.jpg", "Đốm"));


        btnAdd.setBounds(80, 515, 240, 45);
        add(btnAdd);
        btnUpdate.setBounds(80, 515, 240, 45);
        add(btnUpdate);


        bottomMenuPanel.setBounds(0, 606, 400, 60);
        add(bottomMenuPanel);
    }

    public JButton getBtnUpdate() {
        return btnUpdate;
    }

    private JPanel createPetItem(String imagePath, String name) {
        JPanel panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(360, 70));
        panel.setBackground(Color.WHITE);

        JLabel imgLabel = new JLabel(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        imgLabel.setBounds(10, 10, 50, 50);
        panel.add(imgLabel);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Roboto", Font.BOLD, 13));
        nameLabel.setBounds(70, 25, 100, 20);
        panel.add(nameLabel);

        btnEdit = new JButton("Sửa");
        btnEdit.setBounds(200, 20, 60, 30);
        btnEdit.addActionListener(e -> {
            if (editListener != null) {
                editListener.accept(name); // name là tên pet
            }
        });

        styleSmallButton(btnEdit);
        panel.add(btnEdit);

        JButton btnDelete = new JButton("Xóa");
        btnDelete.setBounds(270, 20, 60, 30);
        styleSmallButton(btnDelete);
        btnDelete.addActionListener(e -> {
            if (deleteListener != null) {
                deleteListener.accept(name); // Gửi tên thú cưng về controller
            }
        });
        panel.add(btnDelete);


        return panel;
    }

    public void removePetFromListPanel(String name) {
        Component[] components = petListPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel panel) {
                for (Component child : panel.getComponents()) {
                    if (child instanceof JLabel label) {
                        String labelText = label.getText();
                        if (labelText != null && labelText.equals(name)) {
                            petListPanel.remove(panel);
                            petListPanel.revalidate();
                            petListPanel.repaint();
                            return;
                        }
                    }
                }
            }
        }
    }


    private JTextField createRoundedTextField(String placeholder) {
        JTextField field = new JTextField(placeholder);
        field.setFont(new Font("Roboto", Font.PLAIN, 13));
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
        btn.setFont(new Font("Roboto", Font.BOLD, 12));
        btn.setBackground(new Color(90, 150, 255));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(new RoundedBorder(10));
    }

    public void addPetToListPanel(ImageIcon avatarIcon, String name) {
        JPanel panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(360, 70));
        panel.setBackground(Color.WHITE);

        JLabel imgLabel;
        if (avatarIcon != null) {
            Image scaled = avatarIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            BufferedImage rounded = makeRoundedImage(scaled, 50);
            imgLabel = new JLabel(new ImageIcon(rounded));
        } else {
            imgLabel = new JLabel(new ImageIcon("src/image/defaultAvatar.png"));
        }
        imgLabel.setBounds(10, 10, 50, 50);
        panel.add(imgLabel);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Roboto", Font.BOLD, 13));
        nameLabel.setBounds(70, 25, 100, 20);
        panel.add(nameLabel);

        JButton btnEdit = new JButton("Sửa");
        btnEdit.setBounds(200, 20, 60, 30);
        styleSmallButton(btnEdit);
        btnEdit.addActionListener(e -> {
            if (editListener != null) editListener.accept(name);
        });
        panel.add(btnEdit);

        JButton btnDelete = new JButton("Xóa");
        btnDelete.setBounds(270, 20, 60, 30);
        styleSmallButton(btnDelete);
        btnDelete.addActionListener(e -> {
            if (deleteListener != null) deleteListener.accept(name);
        });
        panel.add(btnDelete);

        petListPanel.add(panel);
        petListPanel.revalidate();
        petListPanel.repaint();
    }



    /**
     * Đây là một callback function để khi bạn nhấn nút "Xóa" ở từng thú cưng,
     * view sẽ gọi deleteListener.accept(tên_thú_cưng);
     *
     * @param listener
     */
    public void setDeleteListener(Consumer<String> listener) {
        this.deleteListener = listener;
    }

    public void setEditListener(Consumer<String> listener) {
        this.editListener = listener;
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

    public String getNamePet() {
        return txtName.getText();
    }

    public String getBreed() {
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

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public String getMedicalHistory() {
        return txtMedicalHistory.getText().trim();
    }

    public void clear() {
        // Xóa nội dung các trường nhập
        txtName.setText("");
        txtBreed.setText("");
        txtAge.setText("");
        txtWeight.setText("");
        txtMedicalHistory.setText("");

        // Xóa giới tính được chọn
        genderGroup.clearSelection();

        // Reset ảnh đại diện về mặc định
        ImageIcon defaultIcon = new ImageIcon("src/image/uploadImg.png");
        btnUploadAvatar.setIcon(defaultIcon);
        btnUploadAvatar.setText(""); // Nếu có text thì xóa luôn

        // Xóa file ảnh đã chọn
        selectedAvatarFile = null;

        // Thoát chế độ chỉnh sửa
        editingPetId = null;
        btnAdd.setVisible(true);
        if (btnUpdate != null) {
            btnUpdate.setVisible(false);
        }
    }


    public JButton getBtnAdd() {
        return btnAdd;
    }

    public void loadPetToEdit(Pet pet, ImageIcon avatarIcon) {
        txtName.setText(pet.getName());
        txtBreed.setText(pet.getBreed());
        txtAge.setText(String.valueOf(pet.getAge()));
        txtWeight.setText(String.valueOf(pet.getWeight()));
        txtMedicalHistory.setText(pet.getMedicalHistory());

        if ("Đực".equals(pet.getGender())) {
            rdoMale.setSelected(true);
        } else {
            rdoFemale.setSelected(true);
        }

        if (avatarIcon != null) {
            Image scaledImage = avatarIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            BufferedImage rounded = makeRoundedImage(scaledImage, 80);
            btnUploadAvatar.setIcon(new ImageIcon(rounded));
        } else {
            btnUploadAvatar.setIcon(new ImageIcon("src/image/uploadImg.png"));
        }

        selectedAvatarFile = null; // reset vì chưa chọn ảnh mới
        editingPetId = pet.getPetId();
        btnAdd.setVisible(false);
        if (btnUpdate != null) btnUpdate.setVisible(true);
    }


    public void loadPetToEdit(Pet pet) {
        txtName.setText(pet.getName());
        txtBreed.setText(pet.getBreed());
        txtAge.setText(String.valueOf(pet.getAge()));
        txtWeight.setText(String.valueOf(pet.getWeight()));
        txtMedicalHistory.setText(pet.getMedicalHistory());

        if ("Đực".equals(pet.getGender())) {
            rdoMale.setSelected(true);
        } else {
            rdoFemale.setSelected(true);
        }

        // ✅ Load avatar từ byte[] nếu có
        if (pet.getAvatar() != null) {
            ImageIcon icon = new ImageIcon(pet.getAvatar());
            Image scaledImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            BufferedImage rounded = makeRoundedImage(scaledImage, 80);
            btnUploadAvatar.setIcon(new ImageIcon(rounded));
        } else {
            btnUploadAvatar.setIcon(new ImageIcon("src/image/uploadImg.png"));
        }

        selectedAvatarFile = null; // reset lại
        editingPetId = pet.getPetId();
        btnAdd.setVisible(false);
        if (btnUpdate != null) btnUpdate.setVisible(true);
    }


    public boolean isEditMode() {
        return editingPetId != null;
    }

    public String getEditingPetId() {
        return editingPetId;
    }


    public void exitEditMode() {
        editingPetId = null;
        btnAdd.setVisible(true);
        btnEdit.setVisible(false);
    }

    //
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Thêm thú cưng");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 700);
//
//        JPanel mainPanel = new JPanel(new CardLayout());
//        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
//
//        AddPetPanel addPetPanel = new AddPetPanel(cardLayout, mainPanel, );
//        new PetController(addPetPanel); // Controller gắn sự kiện
//
//        mainPanel.add(addPetPanel, "AddPet");
//
//        frame.setContentPane(mainPanel);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//
//        // Gán userId đã tồn tại trong DB
//        model.User fakeUser = new model.User("3acce9e5-0285-4c6a-9db9-6e58377c6816");
//        model.AppSession.currentUser = fakeUser;
//    }
    public void syncDashboard() {
        if (dashboardPanel != null) {
            dashboardPanel.loadPetsFromDatabase();
        }
    }



}