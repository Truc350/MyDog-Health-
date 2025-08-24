package test_ai;

import javax.swing.*;
import java.awt.*;

import view.*;

public class AIMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("AI Features Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 700);
            frame.setLocationRelativeTo(null);

            // CardLayout chính
            CardLayout cardLayout = new CardLayout();
            JPanel mainPanel = new JPanel(cardLayout);

            // Tạo các panel AI và truyền cardLayout + mainPanel
            MedicalResultPanel medicalResultPanel = new MedicalResultPanel(cardLayout, mainPanel);
            DogInforPanel dogInforPanel = new DogInforPanel(cardLayout, mainPanel, medicalResultPanel);
            CheckSymptomsPanel checkSymptomsPanel = new CheckSymptomsPanel(cardLayout, mainPanel, dogInforPanel);
            AIAnalysisResultsPanel resultsPanel = new AIAnalysisResultsPanel(cardLayout, mainPanel);
            CareGuidePanel careGuidePanel = new CareGuidePanel(cardLayout, mainPanel);
            ChatboxPanel chatboxAI = new ChatboxPanel(cardLayout, mainPanel);

            // Thêm các panel vào mainPanel
            mainPanel.add(dogInforPanel, "dogInfor");
            mainPanel.add(checkSymptomsPanel, "checkSymptoms");
            resultsPanel.setName("aiAnalysisResults");
            resultsPanel.setCareGuidePanel(careGuidePanel);
            mainPanel.add(resultsPanel, "aiAnalysisResults");
            mainPanel.add(careGuidePanel, "careGuide");
            mainPanel.add(chatboxAI, "chatBoxAI");
            mainPanel.add(medicalResultPanel, "medicalResult");

            // Mặc định mở CheckSymptomsPanel
            cardLayout.show(mainPanel, "checkSymptoms");

            frame.setContentPane(mainPanel);
            frame.setVisible(true);
        });
    }
}
