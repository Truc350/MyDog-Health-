package model;

import dao.UserDAO;

/**
 * dùng để lưu user khi đã đăng nhập dashboard á
 */
public class AppSession {
    public static User currentUser = new User();

    public static void logoutCurrentUser() {
        currentUser = null;
    }
}
