package model;

/**
 * this is class represented instructive take after pet
 */

public class CareAdvice {
    private String diseaseName;//ten benh
    private String advice;// goi y cham soc
    private String dangerSigns; // Dấu hiệu nguy hiểm
    private String extraNotes;  // Ghi chú

    public CareAdvice(String diseaseName, String advice, String dangerSigns, String extraNotes) {
        this.diseaseName = diseaseName;
        this.advice = advice;
        this.dangerSigns = dangerSigns;
        this.extraNotes = extraNotes;
    }

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

    // 1. Hàm hiển thị gợi ý chăm sóc theo định dạng đẹp
    public String formatForDisplay() {
        return "📌 **Tên bệnh: " + diseaseName + "\n"
                + "💡 **Hướng dẫn chăm sóc: " + advice + "\n"
                + "⚠️ **Dấu hiệu nguy hiểm: " + dangerSigns + "\n"
                + "📝 **Lưu ý thêm: " + extraNotes;
    }

    // 2. Hàm kiểm tra nếu lời khuyên là khẩn cấp
    public boolean isCriticalAdvice() {
        return dangerSigns != null && !dangerSigns.trim().isEmpty();
    }

    // 3. Hàm gợi ý rút gọn (dùng cho preview danh sách)
    public String getSummary() {
        return diseaseName + " - " + advice.substring(0, Math.min(40, advice.length())) + "...";
    }

    // 4. Kiểm tra có ghi chú đặc biệt không
    public boolean hasExtraNotes() {
        return extraNotes != null && !extraNotes.trim().isEmpty();
    }
}

