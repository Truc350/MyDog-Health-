package view;

import javax.swing.*;
import java.awt.*;

public class BottomMenuPanel extends JPanel {
    private JButton homeButton, plusButton, callButton, userButton, settingButton;
    public BottomMenuPanel() {
        setLayout(new GridLayout(1, 5, 10, 0)); // khoảng cách giữa các nút
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // padding xung quanh


         homeButton = createIconButton("src\\image\\home.png");

         plusButton = createIconButton("src\\image\\plus.png");

         callButton = createIconButton("src\\image\\phone-call.png");

        userButton = createIconButton("src\\image\\user.png");

         settingButton = createIconButton("src\\image\\setting.png");


        add(homeButton);
        add(plusButton);
        add(callButton);
        add(userButton);
        add(settingButton);
    }

    private JButton createIconButton(String iconPath) {
        JButton button = new JButton();
        ImageIcon icon = new ImageIcon(iconPath);
        Image scaledImage = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH); // tăng kích thước một chút
        button.setIcon(new ImageIcon(scaledImage));

        // Loại bỏ mọi viền và hiệu ứng
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);

        return button;
    }

}
