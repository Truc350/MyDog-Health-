package model;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private String name;
    private String specialization;// chuyen mon cua bac si
    private String status;// online, offline, busy
    private String imagePath;

    // Danh sách tĩnh quản lý tất cả bác sĩ (nếu không dùng DB)
    private static List<Doctor> doctorList = new ArrayList<>();

    public Doctor(String name, String specialization, String status, String imagePath) {
        this.name = name;
        this.specialization = specialization;
        this.status = status;
        this.imagePath = imagePath;
    }

    public Doctor(String name, String specialization, String status) {
        this.name = name;
        this.specialization = specialization;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // Chuyển trạng thái online/offline
    public void toggleStatus() {
        if ("online".equalsIgnoreCase(status)) {
            status = "offline";
        } else {
            status = "online";
        }
    }

    // Kiểm tra bác sĩ có sẵn sàng cho loại gọi cụ thể không
    public boolean isAvailableFor(CallService.CallType callType) {
        return "online".equalsIgnoreCase(status);
        // sau này có thể mở rộng thêm nếu từng bác sĩ có quyền khác nhau với callType
    }

    // Ghi thông tin bác sĩ gọn gàng
    @Override
    public String toString() {
        return String.format("👨‍⚕️ %s - %s (%s)", name, specialization, status);
    }

    // ==== Danh sách bác sĩ (quản lý trong bộ nhớ) ====

    public static void addDoctor(Doctor doctor) {
        doctorList.add(doctor);
    }

    public static List<Doctor> getAllDoctors() {
        return doctorList;
    }

    public static List<Doctor> getAvailableDoctors() {
        List<Doctor> onlineDoctors = new ArrayList<>();
        for (Doctor d : doctorList) {
            if ("online".equalsIgnoreCase(d.getStatus())) {
                onlineDoctors.add(d);
            }
        }
        return onlineDoctors;
    }

    public static Doctor findByName(String name) {
        for (Doctor d : doctorList) {
            if (d.getName().equalsIgnoreCase(name)) {
                return d;
            }
        }
        return null;
    }

    // Cập nhật trạng thái theo tên (dùng nếu chỉ có tên trong UI)
    public static void updateStatus(String name, String newStatus) {
        Doctor d = findByName(name);
        if (d != null) {
            d.setStatus(newStatus);
        }
    }
}
