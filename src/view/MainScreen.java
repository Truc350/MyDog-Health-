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
    private DoctorSelectionPanel doctorSelectionPanel;
    private DogInforPanel dogInforPanel;
    private AIAnalysisResultsPanel aiAnalysisResultsPanel;
    private CareGuidePanel careGuidePanel;
    private MedicalResultPanel medicalResultPanel;
    private ChatboxPanel chatboxPanel;
    private CallDoctorPanel callDoctorPanel;

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

        addPetPanel = new AddPetPanel(cardLayout, mainPanel); // ✅ truyền đúng layout & panel

        loginPanel = new LoginPanel(cardLayout, mainPanel);
        registerPanel = new RegisterPanel(cardLayout, mainPanel);
        dashboardPanel = new DashboardPanel(cardLayout, mainPanel);
//        checkSymptomsPanel = new CheckSymptomsPanel(cardLayout, mainPanel);
//        doctorSelectionPanel = new DoctorSelectionPanel(cardLayout, mainPanel);
//        dogInforPanel = new DogInforPanel(cardLayout, mainPanel);
//        aiAnalysisResultsPanel = new AIAnalysisResultsPanel(cardLayout, mainPanel);
//        careGuidePanel = new CareGuidePanel(cardLayout, mainPanel);
//        medicalResultPanel = new MedicalResultPanel(cardLayout, mainPanel);
//        chatboxPanel = new ChatboxPanel(cardLayout, mainPanel);
//        callDoctorPanel = new CallDoctorPanel(cardLayout, mainPanel);

//        aiAnalysisResultsPanel.setCareGuidePanel(careGuidePanel);

//        mainPanel.add(callDoctorPanel, "callDoctor");
//        mainPanel.add(chatboxPanel, "chatBoxAI");
//        mainPanel.add(medicalResultPanel, "medicalResult");
//        mainPanel.add(careGuidePanel, "careGuide");
//        mainPanel.add(aiAnalysisResultsPanel, "aiAnalysisResults");
//        mainPanel.add(dogInforPanel, "dogInfor");
//        mainPanel.add(checkSymptomsPanel, "checkSymptoms");
//        mainPanel.add(doctorSelectionPanel, "doctorSelection");
//        mainPanel.add(addPetPanel, "addPet");

        mainPanel.add(new SettingPanel(cardLayout, mainPanel), "setting");
        mainPanel.add(new ChangePasswordPanel(cardLayout, mainPanel), "changePassword");

        mainPanel.add(dashboardPanel, "dashboard");
        mainPanel.add(loginPanel, "login");
        mainPanel.add(registerPanel, "register");
        add(mainPanel);
        cardLayout.show(mainPanel, "login");

        this.setVisible(true);

    }
}
