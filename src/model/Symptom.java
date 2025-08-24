package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a symptom observed in a pet.
 */
public class Symptom {
    private int id;
    private String name;          // Tên triệu chứng (ví dụ: ho, sốt, bỏ ăn)
    private String location;      // Vị trí (tai, mắt, bụng...)
    private String description;   // Mô tả chi tiết
    private String dateNoticed; // Ngày phát hiện (dùng LocalDate cho chuẩn)
    private String severity;      // Mức độ: Nhẹ / Trung bình / Nặng
    private String imagePath;     // Đường dẫn ảnh triệu chứng (nếu có)

    // ===== Constructor =====
    public Symptom(int id, String name, String location, String description, String dateNoticed, String severity, String imagePath) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.dateNoticed = dateNoticed;
        this.severity = severity;
        this.imagePath = imagePath;
    }

    public Symptom(String name, String location, String dateNoticed, String description, String imagePath) {
        this.name = name;
        this.location = location;
        this.dateNoticed = dateNoticed;
        this.description = description;
        this.imagePath = imagePath;
    }

    // Constructor không có id (dùng khi thêm mới)
    public Symptom(String name, String location, String description, String dateNoticed, String severity, String imagePath) {
        this(-1, name, location, description, dateNoticed, severity, imagePath);
    }

    // ===== Getter & Setter =====
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateNoticed() {
        return dateNoticed;
    }

    public void setDateNoticed(String dateNoticed) {
        this.dateNoticed = dateNoticed;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // ===== Helper methods =====

    /** Format hiển thị gọn gàng (dùng trong UI, list symptom) */
    public String formatForDisplay() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "📍 Vị trí: " + location + "\n"
                + "🩺 Triệu chứng: " + name + "\n"
                + "📝 Mô tả: " + description + "\n"
                + "📅 Ngày phát hiện: " + (dateNoticed != null && dateNoticed.isEmpty() ? dateNoticed : "Không rõ") + "\n"
                + "⚠️ Mức độ: " + severity;
    }

    /** Trả về true nếu triệu chứng nặng */
    public boolean isSevere() {
        return severity != null && severity.equalsIgnoreCase("nặng");
    }
}
