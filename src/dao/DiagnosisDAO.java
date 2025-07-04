package dao;

import config.DBConnection;
import model.DiagnosisResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DiagnosisDAO {
    public void saveDiagnosis(DiagnosisResult result) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO diagnosis_result (id, pet_id, disease_name, probability, status_note, analysis_time) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, result.getId());
            stmt.setString(2, result.getPetId());
            stmt.setString(3, result.getDiseaseName());
            stmt.setDouble(4, result.getProbability());
            stmt.setString(5, result.getStatusNote());
            stmt.setTimestamp(6, Timestamp.valueOf(result.getAnalysisTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
