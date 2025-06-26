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
//        registerPanel = new RegisterPanel();
//        add(registerPanel);

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        addPetPanel = new AddPetPanel();
        loginPanel = new LoginPanel(cardLayout, mainPanel);
        registerPanel = new RegisterPanel(cardLayout,mainPanel);
        dashboardPanel = new DashboardPanel(cardLayout,mainPanel);


        mainPanel.add(addPetPanel,"addPet");
        mainPanel.add(dashboardPanel,"dashboard");
        mainPanel.add(loginPanel,"login");
        mainPanel.add(registerPanel, "register");
        add(mainPanel);
        cardLayout.show(mainPanel, "login");

        this.setVisible(true);
    }
}
