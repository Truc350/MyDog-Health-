package view;

import model.Symptom;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CheckSymptomsPanel extends JPanel {

    private JButton backButton, continueButton;
    private JLabel titleLabel, dateButton, locationLabel, dateLabel;
    private JTextField dateField, locationField;
    private JTextArea symptomArea;
    private JCheckBox[] symptomChecks;
    private BottomMenuPanel bottomMenuPanel;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JScrollPane scroll;
    private String selectedImagePath = null;
    private DogInforPanel dogInforPanel;
    private JLabel imagePreviewLabel;
    private JPanel uploadPanel;
    private JLabel uploadIconLabel;


    public CheckSymptomsPanel(CardLayout cardLayout, JPanel mainPanel, DogInforPanel dogInforPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.dogInforPanel = dogInforPanel;
        setLayout(null);
        setPreferredSize(new Dimension(400, 700));
        setBackground(new Color(214, 229, 250)); // nền xanh nhạt

        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        // Nút quay lại
        backButton = new JButton(new ImageIcon("src/image/back.png"));
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "dashboard");
        });
        styleIconButton(backButton);

        // Tiêu đề
        titleLabel = new JLabel("Kiểm tra triệu chứng");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 20));

        // Checkbox triệu chứng
        String[] symptoms = {"Nôn", "Chán ăn", "Tiêu chảy", "Sốt", "Hô hấp"};
        symptomChecks = new JCheckBox[symptoms.length];
        for (int i = 0; i < symptoms.length; i++) {
            symptomChecks[i] = new JCheckBox(symptoms[i]);
            symptomChecks[i].setFont(new Font("Roboto", Font.PLAIN, 15));
            symptomChecks[i].setOpaque(false);
        }

        // Vị trí
        locationLabel = new JLabel("Vị trí:");
        locationLabel.setFont(new Font("Roboto", Font.PLAIN, 15));

        locationField = new JTextField();
        locationField.setFont(new Font("Roboto", Font.PLAIN, 15));
        locationField.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));


        // Ngày
        dateField = new JTextField();
        dateField.setFont(new Font("Roboto", Font.PLAIN, 15));
        dateField.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));

        dateButton = new JLabel("ngày");
        dateButton.setFont(new Font("Roboto", Font.PLAIN, 15));

        symptomArea = new JTextArea("Nhập mô tả triệu chứng...");
        symptomArea.setForeground(Color.GRAY);
        symptomArea.setFont(new Font("Roboto", Font.ITALIC, 13));
        symptomArea.setLineWrap(true);
        symptomArea.setWrapStyleWord(true);

        symptomArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (symptomArea.getText().equals("Nhập mô tả triệu chứng...")) {
                    symptomArea.setText("");
                    symptomArea.setForeground(Color.BLACK);
                    symptomArea.setFont(new Font("Roboto", Font.ITALIC, 13));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (symptomArea.getText().trim().isEmpty()) {
                    symptomArea.setText("Nhập mô tả triệu chứng...");
                    symptomArea.setForeground(Color.GRAY);
                    symptomArea.setFont(new Font("Roboto", Font.ITALIC, 13));
                }
            }
        });


        // Tải ảnh/video
        uploadPanel = new JPanel();
        uploadPanel.setLayout(new BorderLayout());
        uploadPanel.setBackground(Color.WHITE);
        uploadPanel.setBorder(new LineBorder(new Color(150, 200, 255), 1, true));
        uploadPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        uploadIconLabel = new JLabel("Tải ảnh/video", new ImageIcon("src/image/uploadImg.png"), JLabel.CENTER);
        uploadIconLabel.setHorizontalTextPosition(JLabel.CENTER);
        uploadIconLabel.setVerticalTextPosition(JLabel.BOTTOM);
        uploadIconLabel.setFont(new Font("Roboto", Font.BOLD, 15));
        uploadIconLabel.setForeground(new Color(40, 100, 200));
        uploadPanel.add(uploadIconLabel, BorderLayout.CENTER);

        uploadPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Chọn ảnh hoặc video");
                int result = fileChooser.showOpenDialog(CheckSymptomsPanel.this);

                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedImagePath = fileChooser.getSelectedFile().getAbsolutePath();

                    // Hiển thị ảnh trong panel
                    ImageIcon icon = new ImageIcon(selectedImagePath);
                    Image scaledImage = icon.getImage().getScaledInstance(uploadPanel.getWidth(), uploadPanel.getHeight(), Image.SCALE_SMOOTH);
                    uploadIconLabel.setIcon(new ImageIcon(scaledImage));
                    uploadIconLabel.setText("");
                }
            }
        });

        // Nút tiếp tục
        continueButton = customButton("Tiếp tục");
        continueButton.setBounds(20, 500, 360, 40); // sau ảnh
        continueButton.addActionListener(e -> {
            String name = getSelectedSymptom(); // tên triệu chứng chính
            String location = locationField.getText();
            String dateNoticed = dateField.getText() + " ngày";
            String description = symptomArea.getText();
            String image = selectedImagePath != null ? selectedImagePath : "";

            // ❌ Kiểm tra thiếu thông tin
            boolean isSymptomEmpty = name.equals("Không chọn");
            boolean isLocationEmpty = location.isEmpty();
            boolean isDateEmpty = dateField.getText().trim().isEmpty();
            boolean isDescriptionEmpty = description.equals("Nhập mô tả triệu chứng...") || description.isEmpty();

            if (isSymptomEmpty || isLocationEmpty || isDateEmpty || isDescriptionEmpty) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ để xem kết quả", "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Symptom symptom = new Symptom(name, location, dateNoticed, description, image);

            if (dogInforPanel == null) {
                JOptionPane.showMessageDialog(this, "Chưa gán dogInforPanel! Gọi setDogInforPanel(...) trước.");
                return;
            }

            dogInforPanel.updateDogInfo(symptom);
            cardLayout.show(mainPanel, "dogInfor");
        });

        stylePrimaryButton(continueButton);

        // Footer
        bottomMenuPanel = new BottomMenuPanel();
    }

    private void layoutComponents() {
        backButton.setBounds(10, 10, 32, 32);
        add(backButton);

        titleLabel.setBounds(60, 15, 300, 25);
        add(titleLabel);

        // Panel chứa checkbox
        JPanel symptomPanel = new RoundedPanel(16, Color.WHITE, new Color(220, 220, 220));
        symptomPanel.setLayout(new GridLayout(5, 1));
        symptomPanel.setBounds(20, 55, 360, 130);
        for (JCheckBox cb : symptomChecks) {
            symptomPanel.add(cb);
        }
        add(symptomPanel);

        locationLabel.setBounds(20, 195, 80, 30);
        add(locationLabel);

        locationField.setBounds(140, 195, 240, 30);
        add(locationField);

        dateLabel = new JLabel("Bắt đầu khi nào?");

        dateLabel.setFont(new Font("Roboto", Font.PLAIN, 15));
        dateLabel.setBounds(20, 195, 120, 30);
        add(dateLabel);

        dateField.setBounds(140, 195, 100, 30);
        add(dateField);

        dateButton.setBounds(250, 195, 70, 30);
        add(dateButton);

        scroll = new JScrollPane(symptomArea);
        scroll.setBounds(20, 240, 360, 60);
        scroll.setBorder(new LineBorder(new Color(220, 220, 220), 1, true));
        add(scroll);

        uploadPanel.setBounds(20, 350, 200, 150); // chiếm đủ chiều ngang, không đè continueButton
        add(uploadPanel);

//        continueButton.setBounds(20, 520, 360, 40);
        add(continueButton);

        // Lùi các thành phần sau xuống dưới 1 bậc
        dateLabel.setBounds(20, 235, 120, 30);
        dateField.setBounds(140, 235, 100, 30);
        dateButton.setBounds(250, 235, 70, 30);
        scroll.setBounds(20, 280, 360, 60);
//        uploadPanel.setBounds((400 - 160) / 2, 350, 160, 40);
        continueButton.setBounds(20, 520, 360, 40);

        // Footer cố định
//        bottomMenuPanel.setBounds(0, 640, 400, 60);
        bottomMenuPanel.setBounds(0, 610, 400, 60); // Đẩy lên 10px

        add(bottomMenuPanel);
    }

    // ============ STYLE ============

    private void styleFlatButton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
        btn.setFont(new Font("Roboto", Font.PLAIN, 15));
        btn.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
    }

    private void styleUploadButton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(40, 100, 200));
        btn.setBorder(new LineBorder(new Color(150, 200, 255), 1, true));
    }

    private void stylePrimaryButton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(70, 130, 255));
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 16, 16);
                g2.dispose();
                super.paint(g, c);
            }
        });
    }

    private void styleIconButton(JButton btn) {
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
    }

//     ============ ROUNDED PANEL ============
    static class RoundedPanel extends JPanel {
        private final int radius;
        private final Color bgColor;
        private final Color borderColor;

        public RoundedPanel(int radius, Color bgColor, Color borderColor) {
            this.radius = radius;
            this.bgColor = bgColor;
            this.borderColor = borderColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = getWidth();
            int height = getHeight();

            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, width, height, radius, radius);

            g2.setColor(borderColor);
            g2.drawRoundRect(0, 0, width - 1, height - 1, radius, radius);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    private CustomButton customButton(String text) {
        CustomButton button = new CustomButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(140, 35));
        button.setBackgroundColor(new Color(70, 150, 236));
        button.setTextColor(Color.WHITE);
        button.setBorderRadius(10);
        return button;
    }

    private String getSelectedSymptom() {
        StringBuilder selected = new StringBuilder();
        for (JCheckBox cb : symptomChecks) {
            if (cb.isSelected()) {
                if (selected.length() > 0) selected.append(", ");
                selected.append(cb.getText());
            }
        }
        return selected.length() > 0 ? selected.toString() : "Không chọn";
    }


    public void setDogInforPanel(DogInforPanel dogInforPanel) {
        this.dogInforPanel = dogInforPanel;
    }

    // ============ MAIN TEST ============
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Kiểm tra triệu chứng");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setContentPane(new CheckSymptomsPanel());
//        frame.setSize(400, 700);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
}
