package dao;

import config.DBConnection;
import model.Pet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PetDAO {
    private Connection conn;

    public PetDAO() {

    }


    public boolean addPet(Pet pet) {
        String sql = "INSERT INTO Pets (petId, userId, name, breed, age, weight, gender, medicalHistory, avatar) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pet.getPetId()); // UUID tự sinh trong model
            stmt.setString(2, pet.getUserId());
            stmt.setString(3, pet.getName());
            stmt.setString(4, pet.getBreed());
            stmt.setInt(5, pet.getAge());
            stmt.setFloat(6, pet.getWeight());
            stmt.setString(7, pet.getGender());
            stmt.setString(8, pet.getMedicalHistory());
            stmt.setBytes(9, pet.getAvatar());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}


