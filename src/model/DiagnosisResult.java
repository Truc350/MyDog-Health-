package model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * class Công cụ chẩn đoán AI
 */

public class DiagnosisResult {
    private String id;
    private String petId;
    private String diseaseName;
    private double probability;
    private String statusNote;
    private LocalDateTime analysisTime;

    public DiagnosisResult(String id, String prtId, String diseaseName, double probability, String statusNote, LocalDateTime analysisTime) {
        this.id = UUID.randomUUID().toString();
        this.petId = prtId;
        this.diseaseName = diseaseName;
        this.probability = probability;
        this.statusNote = statusNote;
        this.analysisTime = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String prtId) {
        this.petId = prtId;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public String getStatusNote() {
        return statusNote;
    }

    public void setStatusNote(String statusNote) {
        this.statusNote = statusNote;
    }

    public LocalDateTime getAnalysisTime() {
        return analysisTime;
    }

    public void setAnalysisTime(LocalDateTime analysisTime) {
        this.analysisTime = analysisTime;
    }


}
