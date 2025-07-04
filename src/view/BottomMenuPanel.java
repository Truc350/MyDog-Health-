package view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BottomMenuPanel extends JPanel {
    private final Color selectedColor = new Color(70, 150, 236);
    private final Color defaultColor = Color.WHITE;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    private Map<String, JButton> buttonMap = new HashMap<>();
    private JButton selectedButton = null;

    public BottomMenuPanel() {
        setLayout(new GridLayout(1, 5, 10, 0));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addButton("dashboard", "src/image/home.png");
        addButton("addPet", "src/image/plus.png");
        addButton("notification", "src/image/notification.png");
        addButton("doctorSelection", "src/image/phone-call.png");
        addButton("setting", "src/image/setting.png");
    }

    private void addButton(String cardName, String iconPath) {
        JButton button = new JButton();
        button.setName(cardName);

        ImageIcon icon = new ImageIcon(iconPath);
        Image scaledImage = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImage));

        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBackground(defaultColor);

        button.addActionListener(e -> handleNavigation(cardName));

        buttonMap.put(cardName, button);
        add(button);
    }

    public void setNavigationHandler(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        SwingUtilities.invokeLater(() -> handleNavigation("dashboard"));  // Chọn mặc định
    }

    private void handleNavigation(String cardName) {
        // Đổi màu nút
        JButton newSelected = buttonMap.get(cardName);
        if (newSelected == null) return;

        if (selectedButton != null) {
            selectedButton.setBackground(defaultColor);
        }

        newSelected.setBackground(selectedColor);
        selectedButton = newSelected;

        // Chuyển card
        if (cardLayout != null && mainPanel != null) {
            cardLayout.show(mainPanel, cardName);
        }
    }
}
