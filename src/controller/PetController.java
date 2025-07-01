package controller;

import dao.PetDAO;
import model.AppSession;
import model.Pet;
import view.AddPetPanel;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PetController {
    private AddPetPanel view;
    private PetDAO petDAO;

    public PetController(AddPetPanel view) {
        this.view = view;
        this.petDAO = new PetDAO();
        init();
    }

    private void init() {
        // Xử lý nút Thêm thú cưng
        view.getBtnAdd().addActionListener(e -> addPet());

        // Kết nối sự kiện nút Xóa
        view.setDeleteListener(petName -> deletePet(petName));
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
                view.addPetToListPanel( // ✅ Thêm mới vào danh sách
                        avatarFile != null ? avatarFile.getAbsolutePath() : "src/image/default_pet.png",
                        pet.getName()
                );
                JOptionPane.showMessageDialog(null, "Đã thêm thú cưng thành công!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm thú cưng: " + ex.getMessage());
        }
    }

    private void deletePet(String petName) {
        try {
            String userId = AppSession.currentUser.getUserId();
            boolean success = petDAO.deletePetByNameAndUserId(petName, userId);
            if (success) {
                view.removePetFromListPanel(petName);
                JOptionPane.showMessageDialog(null, "Đã xóa thú cưng: " + petName);
            } else {
                JOptionPane.showMessageDialog(null, "Không thể xóa thú cưng: " + petName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa thú cưng: " + e.getMessage());
        }
    }

    private byte[] readFileToBytes(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            return fis.readAllBytes();
        }
    }
}
