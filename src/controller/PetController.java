package controller;

import dao.PetDAO;
import model.AppSession;
import model.Pet;
import view.AddPetPanel;

import javax.swing.*;
import java.awt.*;
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
        view.getBtnAdd().addActionListener(e -> addPet());
        view.setDeleteListener(petName -> deletePet(petName));
        view.setEditListener(petName -> editPet(petName));
        view.getBtnUpdate().addActionListener(e -> updatePet());

    }


//    private void editPet(String petName) {
//        Pet pet = petDAO.findPetByNameAndUserId(petName, AppSession.currentUser.getUserId());
//        if (pet != null) {
//            ImageIcon icon = (pet.getAvatar() != null) ?
//                    new ImageIcon(new ImageIcon(pet.getAvatar()).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)) :
//                    new ImageIcon("src/image/default_pet.png");
//
//            view.loadPetToEdit(pet, icon);
//        }
//    }
    private void editPet(String petName) {
        Pet pet = petDAO.findPetByNameAndUserId(petName, AppSession.currentUser.getUserId());
        if (pet != null) {
            view.loadPetToEdit(pet);
        }
    }



    private void updatePet() {
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
            pet.setPetId(view.getEditingPetId()); // Lấy id đang sửa

            File avatarFile = view.getAvatarFile();
            if (avatarFile != null) {
                pet.setAvatar(readFileToBytes(avatarFile));
            } else {
                // ❗Lấy lại avatar cũ từ DB nếu người dùng không chọn avatar mới
                Pet old = petDAO.findPetByNameAndUserId(pet.getName(), AppSession.currentUser.getUserId());
                if (old != null) {
                    pet.setAvatar(old.getAvatar());
                }
            }

            boolean success = petDAO.updatePet(pet);
            if (success) {
                view.clear();
                JOptionPane.showMessageDialog(null, "Cập nhật thú cưng thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "Không thể cập nhật thú cưng!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật: " + ex.getMessage());
        }
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
    public void reloadPetList() {
        view.loadPetListFromDatabase();
    }

}
