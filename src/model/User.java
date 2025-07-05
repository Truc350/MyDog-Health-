package model;

import dao.UserDAO;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private String userId;
    private String name, email, password;
    private Image avatar;
    private List<Pet> pets = new ArrayList<Pet>();
    private List<HistoryRecord> historyRecords = new ArrayList<>();
    private Setting setting = new Setting();
    private String avatarPath;


    public User(String userId) {
        this.userId = userId;
    }

    public User(String userId, String name, String email, String password, Image avatar, List<Pet> pets, List<HistoryRecord> historyRecords) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.pets = pets;
        this.historyRecords = historyRecords;
    }

    public User(String userId, String name, String email, String password, Image avatar, List<Pet> pets) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.pets = pets;
    }

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

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {

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

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public List<HistoryRecord> getHistoryRecords() {
        return historyRecords;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    public void managerPet() {

    }

    public List<HistoryRecord> viewHistory() {
        return null;
    }

    public boolean register() {
        return new UserDAO().register(this);
    }

    public  boolean login(String email, String password) {
        UserDAO dao = new UserDAO();
        User userFromDB = dao.login(email, password);
        if (userFromDB != null){
            this.userId = userFromDB.getUserId();
            this.name = userFromDB.getName();
            this.email = userFromDB.getEmail();
            this.password = userFromDB.getPassword();
            this.avatar = userFromDB.getAvatar();
            return true;
        }
        return false;
    }
    public void  addPet(Pet pet) {
        pets.add(pet);
    }
    public  void removePet(Pet pet) {
        pets.remove(pet);
    }
    public  void printAllPets(){
        System.out.println("🐾 Danh sách thú cưng của bạn:");
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            System.out.println((i + 1) + ". " + pet.getBasicInfo());
        }
    }

    public boolean deleteAccount() {
        return new UserDAO().delete(this.userId);
    }
    public boolean checkPassword(String oldPass) {
        return this.password.equals(oldPass); // nên hash và so sánh hash
    }

    public boolean changePassword(String oldPass, String newPass) {
        if (checkPassword(oldPass)) {
            this.password = newPass;
            return new UserDAO().updatePassword(userId, newPass);
        }
        return false;
    }


    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
        // nếu muốn đồng bộ với avatar (Image), bạn có thể load luôn tại đây:
        try {
            this.avatar = Toolkit.getDefaultToolkit().getImage(avatarPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAvatarPath() {
        return avatarPath;
    }


}
