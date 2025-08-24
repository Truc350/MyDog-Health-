package model;

/**
 * Class đại diện cho gợi ý chăm sóc thú cưng dựa trên kết quả chẩn đoán AI
 */
public class CareAdvice {
    private String diseaseName;   // Tên bệnh
    private String advice;        // Gợi ý chăm sóc
    private String dangerSigns;   // Dấu hiệu nguy hiểm
    private String extraNotes;    // Ghi chú thêm

    public CareAdvice(String diseaseName, String advice, String dangerSigns, String extraNotes) {
        this.diseaseName = diseaseName;
        this.advice = advice;
        this.dangerSigns = dangerSigns;
        this.extraNotes = extraNotes;
    }

    // Getter - Setter
    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getDangerSigns() {
        return dangerSigns;
    }

    public void setDangerSigns(String dangerSigns) {
        this.dangerSigns = dangerSigns;
    }

    public String getExtraNotes() {
        return extraNotes;
    }

    public void setExtraNotes(String extraNotes) {
        this.extraNotes = extraNotes;
    }

    // 1. Hiển thị gợi ý chăm sóc dạng đẹp
    public String formatForDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("📌 Tên bệnh: ").append(diseaseName).append("\n\n")
                .append("💡 Hướng dẫn chăm sóc: ").append(advice).append("\n\n");

        if (dangerSigns != null && !dangerSigns.trim().isEmpty()) {
            sb.append("⚠️ Dấu hiệu nguy hiểm: ").append(dangerSigns).append("\n\n");
        }
        if (extraNotes != null && !extraNotes.trim().isEmpty()) {
            sb.append("📝 Lưu ý thêm: ").append(extraNotes);
        }
        return sb.toString();
    }

    // 2. Kiểm tra xem có dấu hiệu khẩn cấp không
    public boolean isCriticalAdvice() {
        return dangerSigns != null && !dangerSigns.trim().isEmpty();
    }

    // 3. Gợi ý rút gọn (preview trong danh sách)
    public String getSummary() {
        if (advice == null || advice.isEmpty()) return diseaseName;
        String shortAdvice = advice.length() > 40 ? advice.substring(0, 37) + "..." : advice;
        return diseaseName + " - " + shortAdvice;
    }

    // 4. Kiểm tra có ghi chú đặc biệt không
    public boolean hasExtraNotes() {
        return extraNotes != null && !extraNotes.trim().isEmpty();
    }
}
