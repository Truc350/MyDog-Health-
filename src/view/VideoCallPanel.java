package view;

import javax.swing.*;
import java.awt.*;

public class VideoCallPanel extends JPanel {
    public VideoCallPanel(String doctorName, ImageIcon doctorImage, Runnable onCallEnd) {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        JLabel videoArea = new JLabel("🔴 [Giả lập camera đang hoạt động...]");
        videoArea.setForeground(Color.WHITE);
        videoArea.setHorizontalAlignment(SwingConstants.CENTER);
        videoArea.setFont(new Font("Roboto", Font.BOLD, 20));

        JButton endCallBtn = new JButton("Kết thúc cuộc gọi");
        endCallBtn.setFont(new Font("Roboto", Font.BOLD, 14));
        endCallBtn.addActionListener(e -> onCallEnd.run());

        add(videoArea, BorderLayout.CENTER);
        add(endCallBtn, BorderLayout.SOUTH);
    }

}
