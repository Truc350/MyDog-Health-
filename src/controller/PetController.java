package controller;

import dao.PetDAO;
import model.AppSession;
import model.Pet;
import view.AddPetPanel;
import view.DashboardPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

public class PetController {
    private AddPetPanel view;
    private DashboardPanel dashboardPanel; // ✅ Thêm thuộc tính
    private PetDAO petDAO;
    private AddPetPanel addPetPanel;


    public PetController(AddPetPanel view, DashboardPanel dashboardPanel, AddPetPanel addPetPanel) {
        this.view = view;
        this.dashboardPanel = dashboardPanel; // ✅ Gán vào
        this.petDAO = new PetDAO();
        this.addPetPanel = addPetPanel;

        init();
    }

    private void init() {
        view.getBtnAdd().addActionListener(e -> addPet());
        view.setDeleteListener(this::deletePet);
        view.setEditListener(this::editPet);
        view.getBtnUpdate().addActionListener(e -> updatePet());
    }

    private void editPet(String name) {
        Pet pet = petDAO.findPetByNameAndUserId(name, AppSession.currentUser.getUserId());
        if (pet != null) {
            ImageIcon avatarIcon = null;
            if (pet.getAvatar() != null) {
                avatarIcon = new ImageIcon(pet.getAvatar());
            }
            addPetPanel.loadPetToEdit(pet, avatarIcon);
        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy thú cưng!");
        }
    }

    private void updatePet() {
        try {
            Pet pet = new Pet(
                    addPetPanel.getNamePet(),
                    addPetPanel.getBreed(),
                    addPetPanel.getAge(),
                    addPetPanel.getWeight(),
                    addPetPanel.getGender(),
                    addPetPanel.getMedicalHistory()
            );
            pet.setUserId(AppSession.currentUser.getUserId());
            pet.setPetId(addPetPanel.getEditingPetId());

            File avatarFile = addPetPanel.getAvatarFile();
            if (avatarFile != null) {
                byte[] avatarBytes = Files.readAllBytes(avatarFile.toPath());
                pet.setAvatar(avatarBytes);
            } else {
                // nếu không chọn ảnh mới thì giữ ảnh cũ
                Pet oldPet = petDAO.findPetByNameAndUserId(pet.getName(), pet.getUserId());
                if (oldPet != null) {
                    pet.setAvatar(oldPet.getAvatar());
                }
            }

            boolean success = petDAO.updatePet(pet);
            if (success) {
                JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
                addPetPanel.clear();
                addPetPanel.loadPetListFromDatabase();
            } else {
                JOptionPane.showMessageDialog(null, "Cập nhật thất bại!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật thú cưng: " + ex.getMessage());
        }
    }

    private void addPet() {
        try {
            Pet pet = new Pet(
                    addPetPanel.getNamePet(),
                    addPetPanel.getBreed(),
                    addPetPanel.getAge(),
                    addPetPanel.getWeight(),
                    addPetPanel.getGender(),
                    addPetPanel.getMedicalHistory()
            );
            pet.setUserId(AppSession.currentUser.getUserId());

            File avatarFile = addPetPanel.getAvatarFile();
            if (avatarFile != null) {
                byte[] avatarBytes = Files.readAllBytes(avatarFile.toPath());
                pet.setAvatar(avatarBytes);
            }

            boolean success = petDAO.addPet(pet);
            if (success) {
                JOptionPane.showMessageDialog(null, "Thêm thú cưng thành công!");
                addPetPanel.clear();
                addPetPanel.loadPetListFromDatabase();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm thú cưng thất bại!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm thú cưng: " + ex.getMessage());
        }
    }

    private void deletePet(String name) {
        int confirm = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc chắn muốn xóa thú cưng này?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = petDAO.deletePet(AppSession.currentUser.getUserId(), name);
            if (success) {
                addPetPanel.removePetFromListPanel(name);
                addPetPanel.syncDashboard();
                JOptionPane.showMessageDialog(null, "Xóa thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "Xóa thất bại!");
            }
        }
    }


    private byte[] readFileToBytes(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            return fis.readAllBytes();
        }
    }

    public void reloadPetList() {
        view.loadPetListFromDatabase();
        dashboardPanel.loadPetsFromDatabase(); // ✅ Đồng bộ dashboard
    }
}
