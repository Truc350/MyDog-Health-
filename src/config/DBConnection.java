package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    /**
     * CẤU HÌNH KẾT NỐI SQL SERVER
     */
    private static final String URL = "jdbc:sqlserver://192.168.80.1:1433;databaseName=MyDogHealth;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa"; //  tên đăng nhập sql server
    private static final String PASSWORD = "123456789"; // mật khẩu thật sql server
    public static Connection getConnection() {
        try {
            // Nạp driver SQL Server (thường không bắt buộc nếu đã có JDBC 4.x)
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Tạo và trả về kết nối
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Không tìm thấy SQL Server JDBC driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Kết nối SQL Server thất bại!");
            e.printStackTrace();
        }

        return null;
    }
}

