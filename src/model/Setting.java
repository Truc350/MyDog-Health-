package model;

public class Setting {
    private String nameUser,email;
    private boolean appointmentReminder;// canh bao lich kham ebnh cho thu
    private boolean doctorNotification;// nhan thong bao tu bac si

    public Setting(String nameUser, String email, boolean appointmentReminder, boolean doctorNotification) {
        this.nameUser = nameUser;
        this.email = email;
        this.appointmentReminder = appointmentReminder;
        this.doctorNotification = doctorNotification;
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
}
