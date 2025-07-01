package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pet {
    private String petId;
    private String userId;
    private String name;
    private String breed;
    private int age;
    private float weight;
    private String gender;
    private String medicalHistory;
    private byte[] avatar;
    private List<Symptom> symptoms;


    public Pet(String name, String breed, int age, float weight, String gender, String medicalHistory) {
        this.petId = UUID.randomUUID().toString();
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
        this.medicalHistory = medicalHistory;
        List<Symptom> symptoms = new ArrayList<>();
    }



    public Pet() {
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public byte[] getAvatar() {
        return avatar;
    }


    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void addSymptom(Symptom symptom) {
        this.symptoms.add(symptom);
    }
    public  void removeSymptom(Symptom symptom) {
        this.symptoms.remove(symptom);
    }
    public String getBasicInfo(){
        return toString();
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petId='" + petId + '\'' +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", gender='" + gender + '\'' +
                ", medicalHistory='" + medicalHistory + '\'' +
                ", symptoms=" + symptoms +
                '}';
    }



}
