package view;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {
    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    private CheckSymptomsPanel checkSymptomsPanel;
    private SettingPanel settingPanel;
    private DashboardPanel dashboardPanel;
    private AddPetPanel addPetPanel;
    public MainScreen() throws HeadlessException {
        setTitle("MyDog Health+");
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

//        loginPanel = new LoginPanel();
        addPetPanel = new AddPetPanel();
        add(addPetPanel);
        this.setVisible(true);
    }
}
