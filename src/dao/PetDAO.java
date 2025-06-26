package dao;

import model.Pet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PetDAO {
    private Connection conn;

    public PetDAO() {
        try {
            conn = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=MyDogHealth",
                    "sa", "123456789"  // sửa user/pass theo máy bạn
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addPet(Pet pet) {
        String sql = "INSERT INTO Pets (petId, userId, name, breed, age, weight, gender, avatar, medicalHistory) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pet.getPetId());
            stmt.setString(2, pet.getUserId());
            stmt.setString(3, pet.getName());
            stmt.setString(4, pet.getBreed());
            stmt.setInt(5, pet.getAge());
            stmt.setFloat(6, pet.getWeight());
            stmt.setString(7, pet.getGender());
            stmt.setBytes(8, pet.getAvatar()); // có thể null
            stmt.setString(9, pet.getMedicalHistory());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
