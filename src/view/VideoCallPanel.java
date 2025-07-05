package view;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.application.Application;

import javax.swing.*;
import java.awt.*;

public class VideoCallPanel extends JPanel {

    public VideoCallPanel(String doctorName, ImageIcon doctorImage, Runnable onCallEnd) {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        // 1. JavaFX WebView dùng để mở phòng gọi video
        JFXPanel jfxPanel = new JFXPanel(); // Nhúng JavaFX vào Swing

        Platform.runLater(() -> {
            WebView webView = new WebView();
            WebEngine engine = webView.getEngine();

            // 2. Tải link phòng gọi video (Jitsi Meet miễn phí)
            String roomName = "MyDogHealthCallRoom_" + doctorName.replaceAll("\\s+", "");
            engine.load("https://meet.jit.si/" + roomName);

            Scene scene = new Scene(webView);
            jfxPanel.setScene(scene);

            SwingUtilities.invokeLater(() -> {
                jfxPanel.revalidate();
                jfxPanel.repaint();
            });
        });
        jfxPanel.setPreferredSize(new Dimension(500, 400));

        // 3. Tên bác sĩ
        JLabel nameLabel = new JLabel("Đang gọi cho bác sĩ " + doctorName);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // 4. Nút kết thúc cuộc gọi
        JButton endCallBtn = new JButton("Kết thúc cuộc gọi");
        endCallBtn.setFont(new Font("Roboto", Font.BOLD, 15));
        endCallBtn.setBackground(Color.RED);
        endCallBtn.setForeground(Color.WHITE);
        endCallBtn.addActionListener(e -> onCallEnd.run());

        // 5. Nút tắt âm (giả lập)
        JButton muteBtn = new JButton("🔇 Tắt âm");
        muteBtn.setFont(new Font("Roboto", Font.BOLD, 15));
        muteBtn.setBackground(new Color(70, 70, 70));
        muteBtn.setForeground(Color.WHITE);
        muteBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Tính năng đang phát triển"));

        // 6. Panel chứa 2 nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(muteBtn);
        buttonPanel.add(endCallBtn);

        // 7. Add các thành phần vào giao diện
        add(nameLabel, BorderLayout.NORTH);
        add(jfxPanel, BorderLayout.CENTER); // Thay vì ảnh giả lập, giờ là phòng gọi video thật
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
