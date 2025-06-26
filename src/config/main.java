package config;

import java.sql.*;

public class main {


    public static void main(String[] args) {

        // URL kết nối SQL Server
        String url = "jdbc:sqlserver://DESKTOP-CPC2IRT\\SQLEXPRESS:1433;databaseName=MyDogHealth;encrypt=true;trustServerCertificate=true";
        String user = "sa"; // thay bằng tên đăng nhập của bạn
        String password = "123456789"; // thay bằng mật khẩu thật

        try (
                Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("✅ Kết nối thành công SQL Server!");

            String sql = "SELECT * FROM Pets";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String breed = rs.getString("breed");
                int age = rs.getInt("age");
                double weight = rs.getDouble("weight");

                System.out.println(id + ". " + name + " - " + breed + " (" + age + " tuổi, " + weight + "kg)");
            }

        } catch (SQLException e) {
            System.out.println("❌ Kết nối thất bại:");
            e.printStackTrace();
        }
    }

}

