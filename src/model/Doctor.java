package model;

public class Doctor {
    private String name;
    private String specialization;// chuyen mon cua bac si
    private String status;// online, offline

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
}
