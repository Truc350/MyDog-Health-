package controller;

import dao.PetDAO;
import model.AppSession;
import model.Pet;
import view.AddPetPanel;

import javax.swing.*;
import java.awt.*;

public class PetController {
    private AddPetPanel addPetPanel;
    private PetDAO dao;

    public PetController(AddPetPanel addPetPanel) {
        this.addPetPanel = addPetPanel;
        this.dao = new PetDAO();

        addPetPanel.getBtnAdd().addActionListener(e -> addPet());



    }



    private void addPet() {
        try {
            Pet pet = new Pet();
            pet.setUserId(AppSession.currentUser.getUserId()); // từ phiên đăng nhập
            pet.setName(addPetPanel.getNamePet());
            pet.setBreed(addPetPanel.getBreed());
            pet.setAge(addPetPanel.getAge());
            pet.setWeight(addPetPanel.getWeight());
            pet.setGender(addPetPanel.getGender());
            pet.setMedicalHistory(addPetPanel.getMedicalHistory());


            if (dao.addPet(pet)){
                JOptionPane.showMessageDialog(addPetPanel, "Thêm thú cưng thành công!");
                addPetPanel.clear();
            }else {
                JOptionPane.showMessageDialog(addPetPanel, "Thêm không thành công", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(addPetPanel,"Vui lòng nhập đúng thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
