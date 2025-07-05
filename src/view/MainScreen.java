package view;

import controller.PetController;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Các panel
    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    private DashboardPanel dashboardPanel;
    private CheckSymptomsPanel checkSymptomsPanel;
    private SettingPanel settingPanel;
    private AddPetPanel addPetPanel;
    private DoctorSelectionPanel doctorSelectionPanel;
    private DogInforPanel dogInforPanel;
    private AIAnalysisResultsPanel aiAnalysisResultsPanel;
    private CareGuidePanel careGuidePanel;
    private MedicalResultPanel medicalResultPanel;
    private ChatboxPanel chatboxPanel;
    private CallDoctorPanel callDoctorPanel;
    private OngoingCallPanel ongoingCallPanel;
    private ChangePasswordPanel changePasswordPanel;
    private NotificationPanel notificationPanel;

    private PetController petController;

    public MainScreen() {
        setTitle("MyDog Health+");
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // CardLayout setup
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Khởi tạo các panel
        dashboardPanel = new DashboardPanel(cardLayout, mainPanel); // phải khởi trước để truyền vào LoginPanel
        loginPanel = new LoginPanel(cardLayout, mainPanel);
        registerPanel = new RegisterPanel(cardLayout, mainPanel);
        addPetPanel = new AddPetPanel(cardLayout, mainPanel);
        aiAnalysisResultsPanel = new AIAnalysisResultsPanel(cardLayout, mainPanel);
        careGuidePanel = new CareGuidePanel(cardLayout, mainPanel);
        medicalResultPanel = new MedicalResultPanel(cardLayout, mainPanel);
        dogInforPanel = new DogInforPanel(cardLayout, mainPanel, medicalResultPanel);
        checkSymptomsPanel = new CheckSymptomsPanel(cardLayout, mainPanel, dogInforPanel);
        doctorSelectionPanel = new DoctorSelectionPanel(cardLayout, mainPanel);
        chatboxPanel = new ChatboxPanel(cardLayout, mainPanel);
        callDoctorPanel = new CallDoctorPanel(cardLayout, mainPanel);
        changePasswordPanel = new ChangePasswordPanel(cardLayout, mainPanel);
        settingPanel = new SettingPanel(cardLayout, mainPanel);
        notificationPanel = new NotificationPanel(cardLayout, mainPanel);

        // Liên kết giữa các panel (nếu cần)
        aiAnalysisResultsPanel.setCareGuidePanel(careGuidePanel);
        checkSymptomsPanel.setDogInforPanel(dogInforPanel);

        // Controller
        petController = new PetController(addPetPanel);

        // Thêm tất cả các panel vào mainPanel
        mainPanel.add(loginPanel, "login");
        mainPanel.add(registerPanel, "register");
        mainPanel.add(dashboardPanel, "dashboard");
        mainPanel.add(addPetPanel, "addPet");
        mainPanel.add(checkSymptomsPanel, "checkSymptoms");
        mainPanel.add(doctorSelectionPanel, "doctorSelection");
        mainPanel.add(dogInforPanel, "dogInfor");
        mainPanel.add(medicalResultPanel, "medicalResult");
        mainPanel.add(aiAnalysisResultsPanel, "aiAnalysisResults");
        mainPanel.add(careGuidePanel, "careGuide");
        mainPanel.add(chatboxPanel, "chatBoxAI");
        mainPanel.add(callDoctorPanel, "callDoctor");
        mainPanel.add(changePasswordPanel, "changePassword");
        mainPanel.add(settingPanel, "setting");
        mainPanel.add(notificationPanel, "notification");

        // Set mainPanel làm nội dung chính
        add(mainPanel);

        // Mở màn hình login ban đầu
        cardLayout.show(mainPanel, "login");

        setVisible(true);
    }

    public void showLoginPanel() {
        cardLayout.show(mainPanel, "login");
    }

}
