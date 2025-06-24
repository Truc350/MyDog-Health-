package model;

import java.util.Date;

/**
 * this is class which description symptom of animal
 */
public class Symptom {
    private int id;
    private String location;
    private String name;
    private String description;
    private Date dateNoticed;
    private  String severity;// tình trạng : nặng , nhẹ, trung bình này kia

    public Symptom(int id, String location, String name, String description, Date dateNoticed, String severity) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.description = description;
        this.dateNoticed = dateNoticed;
        this.severity = severity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateNoticed(Date dateNoticed) {
        this.dateNoticed = dateNoticed;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateNoticed() {
        return dateNoticed;
    }

    public String getSeverity() {
        return severity;
    }
}
