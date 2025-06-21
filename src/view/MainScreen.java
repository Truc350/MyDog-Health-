package view;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {
    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    public MainScreen() throws HeadlessException {
        setTitle("MyDog Health+");
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        loginPanel = new LoginPanel();
        add(loginPanel);
        this.setVisible(true);
    }
}
