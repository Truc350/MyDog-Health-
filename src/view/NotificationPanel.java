package view;

import controller.NotificationService;
import model.Notification;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NotificationPanel extends JPanel {
    private static JPanel listPanel;
    private BottomMenuPanel bottomMenuPanel;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public NotificationPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BorderLayout());
        setBackground(new Color(200, 220, 245));
        setPreferredSize(new Dimension(400, 700));

        // Tiêu đề
        JLabel title = new JLabel("🔔 Thông báo từ bác sĩ", SwingConstants.CENTER);
        title.setFont(new Font("Roboto", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // Danh sách thông báo
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(new Color(200, 220, 245));
        listPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // JScrollPane
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(12); // cuộn mượt hơn
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0)); // thanh cuộn gọn
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setBackground(new Color(230, 240, 255));

        add(scrollPane, BorderLayout.CENTER);

        // Nạp thông báo ban đầu
        loadNotifications();

        // Menu dưới cùng
        bottomMenuPanel = new BottomMenuPanel();
        bottomMenuPanel.setNavigationHandler(cardLayout, mainPanel);
        add(bottomMenuPanel, BorderLayout.SOUTH);
    }

    public static void updateUI(Notification noti) {
        if (listPanel != null) {
            listPanel.add(createNotificationItem(noti), 0); // Thêm lên đầu
            listPanel.revalidate();
            listPanel.repaint();
        }
    }

    private void loadNotifications() {
        List<Notification> notifications = NotificationService.getAllNotifications();
        listPanel.removeAll();
        for (int i = notifications.size() - 1; i >= 0; i--) {
            listPanel.add(createNotificationItem(notifications.get(i)));
            listPanel.add(Box.createVerticalStrut(12));
        }
    }

    private static JPanel createNotificationItem(Notification noti) {
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        wrapper.setOpaque(false);

        JPanel panel = new RoundedPanel(16, Color.WHITE, new Color(70, 150, 236));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
        panel.setPreferredSize(new Dimension(350, 130)); // 👈 chiều ngang nhỏ lại
        panel.setMaximumSize(new Dimension(320, 130));  // 👈 giới hạn max
        panel.setOpaque(true);

        JLabel titleLabel = new JLabel(noti.getTitle());
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 14));

        JLabel messageLabel = new JLabel("<html>" + noti.getMessage() + "</html>");
        messageLabel.setFont(new Font("Roboto", Font.PLAIN, 13));
        messageLabel.setForeground(Color.DARK_GRAY);

        JLabel timeLabel = new JLabel(noti.getTimestamp().toString());
        timeLabel.setFont(new Font("Roboto", Font.ITALIC, 11));
        timeLabel.setForeground(new Color(100, 100, 100));

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(4));
        panel.add(messageLabel);
        panel.add(Box.createVerticalStrut(4));
        panel.add(timeLabel);

        wrapper.add(panel);
        return wrapper;
    }

}
