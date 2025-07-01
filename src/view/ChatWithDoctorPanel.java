package view;

import javax.swing.*;
import java.awt.*;

public class ChatWithDoctorPanel extends JPanel {
    public ChatWithDoctorPanel(String doctorName, Runnable onExit) {
        setLayout(new BorderLayout());

        Font font = new Font("Roboto", Font.PLAIN, 14);

        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(font);
        chatArea.setText("💬 Bắt đầu trò chuyện với Dr. " + doctorName + "...\n");

        JTextField input = new JTextField();
        input.setFont(font);
        JButton send = new JButton("Gửi");
        send.setFont(new Font("Roboto", Font.BOLD, 16));

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(input, BorderLayout.CENTER);
        bottom.add(send, BorderLayout.EAST);

        send.addActionListener(e -> {
            String text = input.getText().trim();
            if (!text.isEmpty()) {
                chatArea.append("Bạn: " + text + "\n");
                input.setText("");
            }
        });

        JButton back = new JButton("⬅ Quay lại");
        back.setFont(new Font("Roboto", Font.BOLD, 16));
        back.addActionListener(e -> onExit.run());

        add(new JScrollPane(chatArea), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        add(back, BorderLayout.NORTH);
    }
}
