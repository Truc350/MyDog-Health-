package dao;

import config.DBConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    public boolean register(User user) {
        String sql = "INSERT INTO Users (email, password) VALUES (?, ?)";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){


            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());

            int rows = stmt.executeUpdate();
            return rows > 0;

        }catch (SQLException e) {
            System.err.println("❌ Đăng ký thất bại: " + e.getMessage());
            return false;
        }

    }
}
