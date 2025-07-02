package view;

import javax.swing.*;
import java.awt.*;

public class VideoCallPanel extends JPanel {

    public VideoCallPanel(String doctorName, ImageIcon doctorImage, Runnable onCallEnd) {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        // Ảnh GIF giả lập video call
        Image img = doctorImage.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH); // scale nếu cần
        JLabel gifLabel = new JLabel(new ImageIcon(img));
        gifLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel nameLabel = new JLabel("🧑‍⚕️ Đang gọi cho bác sĩ " + doctorName + "...");
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Nút kết thúc cuộc gọi
        JButton endCallBtn = new JButton("Kết thúc cuộc gọi");
        endCallBtn.setFont(new Font("Roboto", Font.BOLD, 15));
        endCallBtn.setBackground(Color.RED);
        endCallBtn.setForeground(Color.WHITE);
        endCallBtn.addActionListener(e -> onCallEnd.run());

        // Nút tắt âm
        JButton muteBtn = new JButton("🔇 Tắt âm");
        muteBtn.setFont(new Font("Roboto", Font.BOLD, 15));
        muteBtn.setBackground(new Color(70, 70, 70));
        muteBtn.setForeground(Color.WHITE);
        muteBtn.addActionListener(e -> {
            // Xử lý logic tắt/mở âm ở đây nếu có
            JOptionPane.showMessageDialog(this, "Âm thanh đã bị tắt");
        });

        // Panel chứa 2 nút
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.BLACK); // cùng màu với nền
        buttonPanel.add(muteBtn);
        buttonPanel.add(endCallBtn);

        add(nameLabel, BorderLayout.NORTH);
        add(gifLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
