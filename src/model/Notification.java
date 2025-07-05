package model;

import java.time.LocalDateTime;

public class Notification {
    private String title;
    private String message;
    private LocalDateTime timestamp;

    public Notification(String title, String message) {
        this.title = title;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
