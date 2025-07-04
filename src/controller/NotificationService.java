package controller;

import model.AppSession;
import model.Notification;
import model.Setting;
import util.EmailSender;
import view.NotificationPanel;
import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private static final List<Notification> notifications = new ArrayList<>();

    public static void sendNotification(String title, String message) {
        Notification noti = new Notification(title, message);
        notifications.add(noti);

        // Hiện trong giao diện nếu đang đăng nhập
        NotificationPanel.updateUI(noti);

        // Gửi email nếu người dùng bật thông báo và có email
        if (AppSession.currentUser != null) {
            Setting setting = AppSession.currentUser.getSetting();
            if (setting != null && setting.isDoctorNotification()) {
                String email = AppSession.currentUser.getEmail();
                if (email != null && !email.isEmpty()) {
                    EmailSender.send(email, title, message);
                }
            }
        }
    }

    public static List<Notification> getAllNotifications() {
        return notifications;
    }
}
