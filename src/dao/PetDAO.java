package dao;

import config.DBConnection;
import model.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public boolean deletePet(String userId, String petName) {
        String sql = "DELETE FROM Pets WHERE userId = ? AND name = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            ps.setString(2, petName);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePetByNameAndUserId(String petName, String userId) throws SQLException {
        String sql = "DELETE FROM Pets WHERE name = ? AND userId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, petName);
            stmt.setString(2, userId);
            return stmt.executeUpdate() > 0;
        }
    }

    public Pet findPetByNameAndUserId(String name, String userId) {
        String sql = "SELECT * FROM Pets WHERE name = ? AND userId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Pet pet = new Pet(
                        rs.getString("name"),
                        rs.getString("breed"),
                        rs.getInt("age"),
                        rs.getFloat("weight"),
                        rs.getString("gender"),
                        rs.getString("medicalHistory")
                );
                pet.setUserId(rs.getString("userId"));
                pet.setPetId(rs.getString("petId"));
                pet.setAvatar(rs.getBytes("avatar"));
                return pet;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePet(Pet pet) {
        String sql = "UPDATE Pets SET name = ?, breed = ?, age = ?, weight = ?, gender = ?, medicalHistory = ?, avatar = ? WHERE petId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pet.getName());
            stmt.setString(2, pet.getBreed());
            stmt.setInt(3, pet.getAge());
            stmt.setFloat(4, pet.getWeight());
            stmt.setString(5, pet.getGender());
            stmt.setString(6, pet.getMedicalHistory());
            stmt.setBytes(7, pet.getAvatar());
            stmt.setString(8, pet.getPetId());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



    public List<Pet> getPetsByUserId(String userId) {
        List<Pet> petList = new ArrayList<>();
        String sql = "SELECT * FROM Pets WHERE userId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pet pet = new Pet(
                        rs.getString("name"),
                        rs.getString("breed"),
                        rs.getInt("age"),
                        rs.getFloat("weight"),
                        rs.getString("gender"),
                        rs.getString("medicalHistory")
                );
                pet.setPetId(rs.getString("petId"));
                pet.setUserId(rs.getString("userId"));
                pet.setAvatar(rs.getBytes("avatar"));

                petList.add(pet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return petList;
    }

}


