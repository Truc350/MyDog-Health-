package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CheckSymptomsPanel extends JPanel {

    private JButton backButton, uploadButton, continueButton;
    private JLabel titleLabel, dateButton;
    private JTextField dateField;
    private JTextArea symptomArea;
    private JCheckBox[] symptomChecks;
    private BottomMenuPanel bottomMenuPanel;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public CheckSymptomsPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
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
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Checkbox triệu chứng
        String[] symptoms = {"Nôn", "Chán ăn", "Tiêu chảy", "Sốt", "Hô hấp"};
        symptomChecks = new JCheckBox[symptoms.length];
        for (int i = 0; i < symptoms.length; i++) {
            symptomChecks[i] = new JCheckBox(symptoms[i]);
            symptomChecks[i].setFont(new Font("Arial", Font.PLAIN, 13));
            symptomChecks[i].setOpaque(false);
        }

        // Ngày
        dateField = new JTextField();
        dateField.setFont(new Font("Arial", Font.PLAIN, 13));
        dateField.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));

        dateButton = new JLabel("ngày");
        dateButton.setFont(new Font("Arial", Font.PLAIN, 13));

        symptomArea = new JTextArea("Nhập mô tả triệu chứng...");
        symptomArea.setForeground(Color.GRAY);
        symptomArea.setFont(new Font("SansSerif", Font.ITALIC, 13));
        symptomArea.setLineWrap(true);
        symptomArea.setWrapStyleWord(true);

        symptomArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (symptomArea.getText().equals("Nhập mô tả triệu chứng...")) {
                    symptomArea.setText("");
                    symptomArea.setForeground(Color.BLACK);
                    symptomArea.setFont(new Font("SansSerif", Font.PLAIN, 13));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (symptomArea.getText().trim().isEmpty()) {
                    symptomArea.setText("Nhập mô tả triệu chứng...");
                    symptomArea.setForeground(Color.GRAY);
                    symptomArea.setFont(new Font("SansSerif", Font.ITALIC, 13));
                }
            }
        });


        // Nút tải ảnh/video
        uploadButton = new JButton("Tải ảnh/ video", new ImageIcon("src/image/uploadImg.png"));
        uploadButton.setFont(new Font("Arial", Font.BOLD, 13));
        uploadButton.setHorizontalAlignment(SwingConstants.CENTER);
        uploadButton.setIconTextGap(8);
        styleUploadButton(uploadButton);

        // Nút tiếp tục
        continueButton = new JButton("Tiếp tục");
        continueButton.addActionListener(e -> {
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

        JLabel dateLabel = new JLabel("Bắt đầu khi nào?");
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        dateLabel.setBounds(20, 195, 120, 30);
        add(dateLabel);

        dateField.setBounds(140, 195, 100, 30);
        add(dateField);

        dateButton.setBounds(250, 195, 70, 30);
        add(dateButton);

        JScrollPane scroll = new JScrollPane(symptomArea);
        scroll.setBounds(20, 240, 360, 60);
        scroll.setBorder(new LineBorder(new Color(220, 220, 220), 1, true));
        add(scroll);

        uploadButton.setBounds((400 - 160) / 2, 310, 160, 40);
        add(uploadButton);

        continueButton.setBounds(20, 370, 360, 40);
        add(continueButton);

        // Footer cố định
//        bottomMenuPanel.setBounds(0, 640, 400, 60);
        bottomMenuPanel.setBounds(0, 610, 400, 60); // Đẩy lên 10px

        add(bottomMenuPanel);
    }

    // ============ STYLE ============

    private void styleFlatButton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.PLAIN, 13));
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

    // ============ ROUNDED PANEL ============
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
