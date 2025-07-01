package controller;

import dao.PetDAO;
import model.AppSession;
import model.Pet;
import view.AddPetPanel;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

public class PetController {
    private AddPetPanel view;
    private PetDAO petDAO;

    public PetController(AddPetPanel view) {
        this.view = view;
        this.petDAO = new PetDAO();
        init();
    }

    private void init() {
        view.getBtnAdd().addActionListener(e -> addPet());
    }

    private void addPet() {
        try {
            Pet pet = new Pet(
                    view.getNamePet(),
                    view.getBreed(),
                    view.getAge(),
                    view.getWeight(),
                    view.getGender(),
                    view.getMedicalHistory()
            );

            pet.setUserId(AppSession.currentUser.getUserId());

            // Avatar nếu có
            File avatarFile = view.getAvatarFile();
            if (avatarFile != null) {
                byte[] avatarBytes = readFileToBytes(avatarFile);
                pet.setAvatar(avatarBytes);
            }

            boolean success = petDAO.addPet(pet);
            if (success) {
                view.clear();
                JOptionPane.showMessageDialog(null, "Đã thêm thú cưng thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "Không thể thêm thú cưng.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm thú cưng: " + ex.getMessage());
        }
    }

    private byte[] readFileToBytes(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            return fis.readAllBytes();
        }
    }
}
