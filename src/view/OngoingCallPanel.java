package view;

import model.CallService;

import javax.swing.*;
import java.awt.*;

public class OngoingCallPanel extends JPanel {
    private Timer timer;
    private int secondsElapsed = 0;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public OngoingCallPanel(String doctorName, ImageIcon doctorImage, CallService call, Runnable onCallEnd) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(230, 245, 255));

        JLabel label = new JLabel("📞 Đang gọi tới " + doctorName);
        label.setFont(new Font("Roboto", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(doctorImage);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel timerLabel = new JLabel("Thời gian: 00:10");
        timerLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton endCallButton = new JButton("Kết thúc cuộc gọi");
        endCallButton.setFont(new Font("Roboto", Font.BOLD, 16));
        endCallButton.setBackground(new Color(200, 50, 50));
        endCallButton.setForeground(Color.WHITE);
        endCallButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        endCallButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Timer để cập nhật thời gian gọi
        timer = new Timer(1000, e -> {
            secondsElapsed++;
            int minutes = secondsElapsed / 60;
            int seconds = secondsElapsed % 60;
            timerLabel.setText(String.format("Thời gian: %02d:%02d", minutes, seconds));
        });
        timer.start();


        // Khi kết thúc cuộc gọi
        endCallButton.addActionListener(e -> {
            timer.stop();
            call.endCall();
            call.record();
            call.logCallDetails();
            onCallEnd.run(); // chuyển lại giao diện chính
        });

        add(Box.createVerticalStrut(30));
        add(label);
        add(Box.createVerticalStrut(10));
        add(imageLabel);
        add(Box.createVerticalStrut(10));
        add(timerLabel);
        add(Box.createVerticalStrut(30));
        add(endCallButton);
        add(Box.createVerticalGlue());
    }

}
