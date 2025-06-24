package model;

import java.util.ArrayList;
import java.util.List;

public class Pet {
    private String name, typeOfPet,age,weight, gender, historySick;
    private List<Symptom> symptoms; // danh sach trieu chung cua thu cung

    public Pet(String name, String typeOfPet, String age, String weight, String gender, String historySick, List<Symptom> symptoms) {
        this.name = name;
        this.typeOfPet = typeOfPet;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
        this.historySick = historySick;
        this.symptoms = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTypeOfPet(String typeOfPet) {
        this.typeOfPet = typeOfPet;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHistorySick(String historySick) {
        this.historySick = historySick;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public String getName() {
        return name;
    }

    public String getTypeOfPet() {
        return typeOfPet;
    }

    public String getAge() {
        return age;
    }

    public String getWeight() {
        return weight;
    }

    public String getGender() {
        return gender;
    }

    public String getHistorySick() {
        return historySick;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }
    public void addSymptom(Symptom symptom){
        this.symptoms.add(symptom);
    }
}
