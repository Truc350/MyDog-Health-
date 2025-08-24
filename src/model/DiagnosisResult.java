package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Lưu trữ toàn bộ kết quả chẩn đoán:
 * - Triệu chứng mà người dùng nhập
 * - Kết quả phân tích từ AI
 * - Danh sách gợi ý chăm sóc
 */
public class DiagnosisResult {
    private int id;
    private List<Symptom> symptoms;       // Danh sách triệu chứng
    private String aiAnalysis;            // Phân tích từ AI (chẩn đoán sơ bộ)
    private List<CareAdvice> careAdvices; // Danh sách gợi ý chăm sóc

    public DiagnosisResult() {
        this.symptoms = new ArrayList<>();
        this.careAdvices = new ArrayList<>();
    }

    public DiagnosisResult(int id, List<Symptom> symptoms, String aiAnalysis, List<CareAdvice> careAdvices) {
        this.id = id;
        this.symptoms = symptoms != null ? symptoms : new ArrayList<>();
        this.aiAnalysis = aiAnalysis;
        this.careAdvices = careAdvices != null ? careAdvices : new ArrayList<>();
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public String getAiAnalysis() {
        return aiAnalysis;
    }

    public void setAiAnalysis(String aiAnalysis) {
        this.aiAnalysis = aiAnalysis;
    }

    public List<CareAdvice> getCareAdvices() {
        return careAdvices;
    }

    public void setCareAdvices(List<CareAdvice> careAdvices) {
        this.careAdvices = careAdvices;
    }

    // ================== Các hàm tiện ích ==================

    /** Thêm triệu chứng mới */
    public void addSymptom(Symptom symptom) {
        if (symptom != null) {
            this.symptoms.add(symptom);
        }
    }

    /** Thêm gợi ý chăm sóc mới */
    public void addCareAdvice(CareAdvice advice) {
        if (advice != null) {
            this.careAdvices.add(advice);
        }
    }

    /** Xem kết quả chẩn đoán theo định dạng đẹp */
    public String formatForDisplay() {
        StringBuilder sb = new StringBuilder("📋 KẾT QUẢ CHẨN ĐOÁN\n");

        sb.append("\n🔎 Triệu chứng:\n");
        for (Symptom s : symptoms) {
            sb.append(" - ").append(s.getName())
                    .append(" (").append(s.getSeverity()).append(")\n");
        }

        sb.append("\n🤖 Phân tích từ AI:\n").append(aiAnalysis).append("\n");

        sb.append("\n💡 Gợi ý chăm sóc:\n");
        for (CareAdvice advice : careAdvices) {
            sb.append(" - ").append(advice.getDiseaseName())
                    .append(": ").append(advice.getAdvice()).append("\n");
        }

        return sb.toString();
    }
}
