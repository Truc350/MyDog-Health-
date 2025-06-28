package model;

/**
 * dùng để lưu user khi đã đăng nhập dashboard á
 */
public class AppSession {
    public static User currentUser = null;

    public static void logoutCurrentUser() {
        currentUser = null;
    }
}
