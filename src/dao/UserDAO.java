package dao;

import config.DBConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserDAO {
    public boolean register(User user) {

        if (user.getUserId() == null || user.getUserId().isEmpty()){
            user.setUserId(UUID.randomUUID().toString());
        }


        String sql = "INSERT INTO Users (userId, name, email, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUserId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("❌ Đăng ký thất bại: " + e.getMessage());
            return false;
        }

    }

    public User login(String email, String password) {
        String sql = "SELECT * FROM Users WHERE email = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("userId"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                //chua co avatar
                return user;
            }

        } catch (SQLException e) {
            System.err.println("Lỗi đăng nhập: " + e.getMessage());
        }

        return null;
    }

    public boolean delete(String userId) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "DELETE FROM Users WHERE userId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
            int rowsAffected = ps.executeUpdate();
            ps.close();
            conn.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
