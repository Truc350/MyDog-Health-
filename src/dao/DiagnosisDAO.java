package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import config.DBConnection;
import model.CareAdvice;
import model.DiagnosisResult;
import model.Symptom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DiagnosisDAO {
    private final ObjectMapper mapper = new ObjectMapper(); // Dùng để convert List -> JSON string

    public void saveDiagnosis(DiagnosisResult result) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO diagnosis_result (id, symptoms, ai_analysis, care_advices, analysis_time) " +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, result.getId());

            // convert list -> JSON string để lưu xuống DB
            String symptomsJson = mapper.writeValueAsString(result.getSymptoms());
            String careAdvicesJson = mapper.writeValueAsString(result.getCareAdvices());

            stmt.setString(2, symptomsJson);
            stmt.setString(3, result.getAiAnalysis());
            stmt.setString(4, careAdvicesJson);
            stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // lỗi JSON
            e.printStackTrace();
        }
    }
}
