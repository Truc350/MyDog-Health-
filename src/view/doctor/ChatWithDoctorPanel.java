package view.doctor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChatWithDoctorPanel extends JPanel {

    public ChatWithDoctorPanel(String doctorName, Runnable onExit) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        Font font = new Font("Roboto", Font.PLAIN, 15);

        // === Khu vực hiển thị tin nhắn ===
        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(font);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setMargin(new Insets(10, 10, 10, 10));
        chatArea.setText("💬 Bắt đầu trò chuyện với " + doctorName + "\n");

        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        // === Khu vực nhập tin nhắn ===
        JTextArea inputArea = new JTextArea(3, 30);
        inputArea.setFont(font);
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        inputArea.setMargin(new Insets(5, 5, 5, 5));

        JScrollPane inputScrollPane = new JScrollPane(inputArea);
        inputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JButton send = new JButton("Gửi");
        send.setFont(new Font("Roboto", Font.BOLD, 16));
        send.setBackground(new Color(90, 150, 255));
        send.setForeground(Color.WHITE);
        send.setFocusPainted(false);

        // Gửi bằng Enter, xuống dòng bằng Shift + Enter
        inputArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && !e.isShiftDown()) {
                    e.consume(); // chặn xuống dòng
                    send.doClick();
                }
            }
        });

        // Xử lý gửi tin nhắn
        send.addActionListener(e -> {
            String text = inputArea.getText().trim();
            if (!text.isEmpty()) {
                chatArea.append("Bạn: " + text + "\n");
                inputArea.setText("");

                // Cuộn xuống cuối
                SwingUtilities.invokeLater(() ->
                        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum()));
            }
        });

        JPanel bottom = new JPanel(new BorderLayout(10, 10));
        bottom.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottom.add(inputScrollPane, BorderLayout.CENTER);
        bottom.add(send, BorderLayout.EAST);
        add(bottom, BorderLayout.SOUTH);

        // Nút quay lại
        JButton back = new JButton("⬅ Quay lại");
        back.setFont(new Font("Roboto", Font.BOLD, 16));
        back.setFocusPainted(false);
        back.addActionListener(e -> onExit.run());
        back.setBackground(Color.LIGHT_GRAY);
        add(back, BorderLayout.NORTH);
    }
}
