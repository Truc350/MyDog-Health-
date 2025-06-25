package model;

import java.awt.*;
import java.util.List;

public class User {
    private String name, email, password;
    private Image avatar;

    public User(String name, String email, String password, Image avatar) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Image getAvatar() {
        return avatar;
    }
    public  boolean login(String email, String password){
        return false;
    }
    public  boolean register(){
        return false;
    }
    public void managerPet(){

    }
    public List<HistoryRecord> viewHistory(){
    return null;
    }
}
