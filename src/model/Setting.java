package model;

import java.awt.*;

public class Setting {
    private String nameUser,email;
    private boolean appointmentReminder;// canh bao lich kham ebnh cho thu
    private boolean doctorNotification;// nhan thong bao tu bac si
    private Image avatar;
    public Setting(String nameUser, String email, boolean appointmentReminder, boolean doctorNotification) {
        this.nameUser = nameUser;
        this.email = email;
        this.appointmentReminder = appointmentReminder;
        this.doctorNotification = doctorNotification;
    }

    public Setting(String nameUser, String email, boolean appointmentReminder, boolean doctorNotification, Image avatar) {
        this.nameUser = nameUser;
        this.email = email;
        this.appointmentReminder = appointmentReminder;
        this.doctorNotification = doctorNotification;
        this.avatar = avatar;
    }

    public Setting() {
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAppointmentReminder() {
        return appointmentReminder;
    }

    public void setAppointmentReminder(boolean appointmentReminder) {
        this.appointmentReminder = appointmentReminder;
    }

    public boolean isDoctorNotification() {
        return doctorNotification;
    }

    public void setDoctorNotification(boolean doctorNotification) {
        this.doctorNotification = doctorNotification;
    }

    // bBật/tắt nhắc lịch tái khám
    public void toggleAppointmentReminder() {
        this.appointmentReminder = !this.appointmentReminder;
    }

    // Bật/tắt nhận thông báo từ bác sĩ
    public void toggleDoctorNotification() {
        this.doctorNotification = !this.doctorNotification;
    }

    // Cập nhật email người dùng
    public void updateEmail(String newEmail) {
        if (newEmail != null && newEmail.contains("@")) {
            this.email = newEmail;
        } else {
            throw new IllegalArgumentException("Email không hợp lệ.");
        }
    }

    // Hàm hiển thị cấu hình dưới dạng chuỗi dễ đọc (dùng để hiển thị trên UI nếu cần)
    public String formatForDisplay() {
        return String.format(
                "👤 Tên người dùng: %s\n📧 Email: %s\n📅 Nhắc lịch khám: %s\n👨‍⚕️ Nhận thông báo bác sĩ: %s",
                nameUser,
                email,
                appointmentReminder ? "Bật" : "Tắt",
                doctorNotification ? "Bật" : "Tắt"
        );
    }
    public void updateAvatar(Image newAvatar) {
        if (newAvatar != null) {
            this.avatar = newAvatar;
        }else {
            throw new IllegalArgumentException("Ảnh đại diện không hợp lệ.");
        }
    }

}
