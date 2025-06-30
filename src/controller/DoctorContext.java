package controller;

import model.Doctor;

public class DoctorContext {
    private static Doctor selectedDoctor;

    public static Doctor getSelectedDoctor() {
        return selectedDoctor;
    }

    public static void setSelectedDoctor(Doctor doctor) {
        selectedDoctor = doctor;
    }
}
