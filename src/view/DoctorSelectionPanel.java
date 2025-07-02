package view;

import controller.DoctorContext;
import model.Doctor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorSelectionPanel extends JPanel {
    JPanel contentPanel, doctorListPanel, filterPanel, topPanel;
    JButton filterButton;
    private BottomMenuPanel bottomMenuPanel;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private java.util.List<Doctor> allDoctors = new ArrayList<>();



    public DoctorSelectionPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout());
        setBackground(new Color(200, 220, 245));

        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(200, 220, 245));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20)); // padding trái phải
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Tiêu đề
        JLabel titleLabel = new JLabel("Chọn bác sĩ mong muốn");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(10));

        // Khung chứa danh sách bác sĩ
        doctorListPanel = new RoundedPanel(20, Color.WHITE, new Color(0, 0, 0, 0));
        doctorListPanel.setLayout(new BoxLayout(doctorListPanel, BoxLayout.Y_AXIS));
        doctorListPanel.setMaximumSize(new Dimension(300, Integer.MAX_VALUE));
        doctorListPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        doctorListPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Nút lọc (filter)
        filterPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        filterPanel.setOpaque(false);
        ImageIcon rawIcon = new ImageIcon("src/image/filter.png");
        Image scaledImg = rawIcon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImg);

        filterButton = new JButton();
        filterButton.setIcon(resizedIcon);

        filterButton.setFocusPainted(false);
        filterButton.setContentAreaFilled(false);
        filterButton.setForeground(new Color(90, 150, 255));
        filterButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        filterButton.setPreferredSize(new Dimension(34, 34));
        filterButton.setFont(new Font("Roboto", Font.BOLD, 16));
        filterButton.setMaximumSize(new Dimension(34, 34));
        filterButton.setBorder(new RoundedBorder(36)); // hình tròn
        filterButton.addActionListener(e -> showFilterDialog());
        filterPanel.add(filterButton);

        contentPanel.add(filterPanel);
        contentPanel.add(Box.createVerticalStrut(10)); // thêm khoảng cách nhỏ dưới filter
        contentPanel.add(doctorListPanel);

        // Khởi tạo danh sách bác sĩ
        allDoctors.add(new Doctor("Bác sĩ Trần Văn A", "Khoa da liễu", "online", "src/image/doctor1.png"));
        allDoctors.add(new Doctor("Bác sĩ Trần Văn B", "Khoa tiêu hóa", "offline", "src/image/doctor2.png"));
        allDoctors.add(new Doctor("Bác sĩ Trần Văn C", "Khoa di truyền", "busy", "src/image/doctor3.png"));

        renderDoctorList(allDoctors);

        doctorListPanel.add(Box.createVerticalGlue()); // đẩy nội dung lên trên
        doctorListPanel.add(Box.createVerticalGlue()); // đẩy nội dung lên trên
        doctorListPanel.add(Box.createVerticalGlue()); // đẩy nội dung lên trên
        doctorListPanel.add(Box.createVerticalGlue()); // đẩy nội dung lên trên
        doctorListPanel.add(Box.createVerticalGlue()); // đẩy nội dung lên trên
        doctorListPanel.add(Box.createVerticalGlue()); // đẩy nội dung lên trên
        doctorListPanel.add(Box.createVerticalGlue()); // đẩy nội dung lên trên

        add(contentPanel, BorderLayout.CENTER);
        bottomMenuPanel = new BottomMenuPanel();
        bottomMenuPanel.setNavigationHandler(cardLayout, mainPanel);
        add(bottomMenuPanel, BorderLayout.SOUTH);


    }


    private void renderDoctorList(java.util.List<Doctor> doctors) {
        doctorListPanel.removeAll();

        for (Doctor d : doctors) {
            JPanel item = createDoctorItem(d);
            doctorListPanel.add(item);
            doctorListPanel.add(Box.createVerticalStrut(10));
        }

        doctorListPanel.revalidate();
        doctorListPanel.repaint();
    }

    private void showFilterDialog() {
        String[] departments = {"Tất cả", "Khoa da liễu", "Khoa tiêu hóa", "Khoa di truyền"};
        String[] statuses = {"Tất cả", "online", "offline", "busy"};

        JComboBox<String> deptBox = new JComboBox<>(departments);
        JComboBox<String> statusBox = new JComboBox<>(statuses);

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.add(new JLabel("Khoa:"));
        panel.add(deptBox);
        panel.add(new JLabel("Trạng thái:"));
        panel.add(statusBox);

        int result = JOptionPane.showConfirmDialog(this, panel, "Lọc bác sĩ", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String selectedDept = (String) deptBox.getSelectedItem();
            String selectedStatus = (String) statusBox.getSelectedItem();

            List<Doctor> filtered = allDoctors.stream()
                    .filter(d -> selectedDept.equals("Tất cả") || d.getSpecialization().equals(selectedDept))
                    .filter(d -> selectedStatus.equals("Tất cả") || d.getStatus().equals(selectedStatus))
                    .collect(Collectors.toList());

            renderDoctorList(filtered);
        }
    }

    private JPanel createDoctorItem(Doctor doctor) {
        JPanel panel = new RoundedPanel(20, Color.WHITE, new Color(13, 153, 255));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 15, 12));
        panel.setMaximumSize(new Dimension(300, 90));
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel avatar = new JLabel(new ImageIcon(new ImageIcon(doctor.getImagePath()).getImage().getScaledInstance(42, 42, Image.SCALE_SMOOTH)));
        avatar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(avatar);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        textPanel.setPreferredSize(new Dimension(170, 60));

        JLabel nameLabel = new JLabel(doctor.getName());
        nameLabel.setFont(new Font("Roboto", Font.BOLD, 17));
        nameLabel.setForeground(new Color(0, 96, 207));
        JLabel deptLabel = new JLabel(doctor.getSpecialization());
        deptLabel.setFont(new Font("Roboto", Font.PLAIN, 15));
        deptLabel.setForeground(new Color(0, 96, 207));

        textPanel.add(nameLabel);
        textPanel.add(deptLabel);

        // ==== Nút trạng thái ====
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        statusPanel.setOpaque(false);

        // Nút xanh (Online)
        JButton greenDot = new JButton();
        greenDot.setPreferredSize(new Dimension(14, 14));
        greenDot.setBorderPainted(false);
        greenDot.setFocusPainted(false);
        greenDot.setOpaque(true);
        greenDot.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        greenDot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        greenDot.setContentAreaFilled(true);
        greenDot.setBackground(Color.GRAY);

        // Nút đỏ (Busy)
        JButton redDot = new JButton();
        redDot.setPreferredSize(new Dimension(14, 14));
        redDot.setBorderPainted(false);
        redDot.setFocusPainted(false);
        redDot.setOpaque(true);
        redDot.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        redDot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        redDot.setContentAreaFilled(true);
        redDot.setBackground(Color.GRAY);

        // ======= Cập nhật giao diện nút theo trạng thái bác sĩ =======
        Runnable updateStatusUI = () -> {
            boolean isOnline = doctor.getStatus().equals("online") || doctor.getStatus().equals("busy");
            boolean isBusy = doctor.getStatus().equals("busy");

            greenDot.setBackground(isOnline ? new Color(0, 200, 0) : new Color(160, 160, 160));
            redDot.setBackground(isBusy ? Color.RED : new Color(160, 160, 160));
        };

        // ==== Action: click nút xanh ====
        greenDot.addActionListener(e -> {
            if (doctor.getStatus().equals("offline")) {
                doctor.setStatus("online");
            } else if (doctor.getStatus().equals("online")) {
                doctor.setStatus("offline");
            } else if (doctor.getStatus().equals("busy")) {
                doctor.setStatus("offline");
            }
            updateStatusUI.run();
        });

        // ==== Action: click nút đỏ ====
        redDot.addActionListener(e -> {
            if (!doctor.getStatus().equals("online") && !doctor.getStatus().equals("busy")) {
                JOptionPane.showMessageDialog(panel, "❗ Bác sĩ phải online mới đặt trạng thái bận.");
                return;
            }

            if (doctor.getStatus().equals("busy")) {
                doctor.setStatus("online");
            } else {
                doctor.setStatus("busy");
            }
            updateStatusUI.run();
        });

        updateStatusUI.run(); // gọi lần đầu để khởi tạo

        statusPanel.add(greenDot);
        statusPanel.add(redDot);

        textPanel.add(Box.createVerticalStrut(3));
        textPanel.add(statusPanel);

        panel.add(textPanel);
        panel.add(Box.createHorizontalGlue());

        // ==== Click vào panel chọn bác sĩ (nếu đang online) ====
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String status = doctor.getStatus();
                if (status.equals("offline")) {
                    JOptionPane.showMessageDialog(panel, "❌ Bác sĩ hiện không hoạt động.");
                    return;
                }

                if (status.equals("busy")) {
                    JOptionPane.showMessageDialog(panel, "❗ Bác sĩ đang trong cuộc gọi khác.");
                    return;
                }
                DoctorContext.setSelectedDoctor(doctor);
                cardLayout.show(mainPanel, "callDoctor");
            }
        });

        return panel;
    }


    // ==== Main để test ====
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Chọn bác sĩ");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 700);
//        frame.setLocationRelativeTo(null);
//        frame.setContentPane(new DoctorSelectionPanel());
//        frame.setVisible(true);
//    }
}
