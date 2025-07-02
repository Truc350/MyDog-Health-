package view;

import model.OpenAIService;
import model.Symptom;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class DogInforPanel extends JPanel {
    private JLabel lblMainSymptomContent, lblLocationContent, lblTimeContent, lblOtherSymptomsContent, imageLabel;
    private JButton btnEditInfo, btnAnalyzeAI, btnCallVet;
    private CardLayout cardLayout;
    private JPanel mainPanel;


    public DogInforPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BorderLayout());
        setBackground(new Color(200, 220, 245));

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(200, 220, 245));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(6, 6, 6, 6); // top, left, bottom, right
        gbc.gridx = 0;
        gbc.gridy = 0;

        Font boldFont = new Font("Roboto", Font.BOLD, 14);
        Font plainFont = new Font("Roboto", Font.PLAIN, 14);

        int row = 0;

        // 1. Triệu chứng chính
        ImageIcon iconPin = new ImageIcon("src\\image\\pin.png");
        Image image1 = iconPin.getImage();
        Image newImage1 = image1.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(newImage1);
        JLabel lbIcon1 = new JLabel(icon1);
        lbIcon1.setVerticalAlignment(SwingConstants.CENTER);  // Căn giữa icon theo chiều dọc
        lbIcon1.setPreferredSize(new Dimension(16, 16));       // Đảm bảo kích thước không làm dòng thấp hơn

        gbc.gridx = 0;
        gbc.gridy = row;
        contentPanel.add(lbIcon1, gbc);

        JLabel lblSymptomTitle = new JLabel("Triệu chứng chính: ");
        lblSymptomTitle.setFont(boldFont);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        contentPanel.add(lblSymptomTitle, gbc);

        lblMainSymptomContent = new JLabel("Chán ăn");
        lblMainSymptomContent.setFont(plainFont);
        gbc.gridx = 2;
        contentPanel.add(lblMainSymptomContent, gbc);
        row++;

        // 2. Vị trí
        ImageIcon iconLo = new ImageIcon("src\\image\\location.png");
        Image image2 = iconLo.getImage();
        Image newImage2 = image2.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(newImage2);
        JLabel lbIcon2 = new JLabel(icon2);
        lbIcon2.setVerticalAlignment(SwingConstants.CENTER);  // Căn giữa icon theo chiều dọc
        lbIcon2.setPreferredSize(new Dimension(16, 16));       // Đảm bảo kích thước không làm dòng thấp hơn


        gbc.gridx = 0;
        gbc.gridy = row;
        contentPanel.add(lbIcon2, gbc);

        JLabel lblLocationTitle = new JLabel("Vị trí: ");
        lblLocationTitle.setFont(boldFont);
        gbc.gridx = 1;
        contentPanel.add(lblLocationTitle, gbc);

        lblLocationContent = new JLabel("Miệng");
        lblLocationContent.setFont(plainFont);
        gbc.gridx = 2;
        contentPanel.add(lblLocationContent, gbc);
        row++;

        // 3. Thời gian xuất hiện
        ImageIcon iconTime = new ImageIcon("src\\image\\time.png");
        Image image3 = iconTime.getImage();
        Image newImage3 = image3.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon icon3 = new ImageIcon(newImage3);
        JLabel lbIcon3 = new JLabel(icon3);
        lbIcon3.setVerticalAlignment(SwingConstants.CENTER);  // Căn giữa icon theo chiều dọc
        lbIcon3.setPreferredSize(new Dimension(16, 16));       // Đảm bảo kích thước không làm dòng thấp hơn


        gbc.gridx = 0;
        gbc.gridy = row;
        contentPanel.add(lbIcon3, gbc);

        JLabel lblTimeTitle = new JLabel("Thời gian xuất hiện: ");
        lblTimeTitle.setFont(boldFont);
        gbc.gridx = 1;
        contentPanel.add(lblTimeTitle, gbc);

        lblTimeContent = new JLabel("2 ngày trước");
        lblTimeContent.setFont(plainFont);
        gbc.gridx = 2;
        contentPanel.add(lblTimeContent, gbc);
        row++;

        // 4. Các triệu chứng khác
        ImageIcon iconTrieuChungKhac = new ImageIcon("src\\image\\menu.png");
        Image image4 = iconTrieuChungKhac.getImage();
        Image newImage4 = image4.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon icon4 = new ImageIcon(newImage4);
        JLabel lbIcon4 = new JLabel(icon4);
        lbIcon4.setVerticalAlignment(SwingConstants.CENTER);  // Căn giữa icon theo chiều dọc
        lbIcon4.setPreferredSize(new Dimension(16, 16));       // Đảm bảo kích thước không làm dòng thấp hơn


        gbc.gridx = 0;
        gbc.gridy = row;
        contentPanel.add(lbIcon4, gbc);

        JLabel lblOtherTitle = new JLabel("Các triệu chứng khác: ");
        lblOtherTitle.setFont(boldFont);
        gbc.gridx = 1;
        contentPanel.add(lblOtherTitle, gbc);

        lblOtherSymptomsContent = new JLabel("lười vận động, ít uống nước");
        lblOtherSymptomsContent.setFont(plainFont);
        gbc.gridx = 2;
        contentPanel.add(lblOtherSymptomsContent, gbc);
        row++;

        // 5. Ảnh/ Video
        ImageIcon iconCamera = new ImageIcon("src\\image\\camera.png");
        Image image5 = iconCamera.getImage();
        Image newImage5 = image5.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon icon5 = new ImageIcon(newImage5);
        JLabel lbIcon5 = new JLabel(icon5);
        lbIcon5.setVerticalAlignment(SwingConstants.CENTER);  // Căn giữa icon theo chiều dọc
        lbIcon5.setPreferredSize(new Dimension(16, 16));       // Đảm bảo kích thước không làm dòng thấp hơn


        gbc.gridx = 0;
        gbc.gridy = row;
        contentPanel.add(lbIcon5, gbc);

        JLabel lblImageTitle = new JLabel("Ảnh/ Video:");
        lblImageTitle.setFont(boldFont);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        contentPanel.add(lblImageTitle, gbc);
        row++;

        // 6. Ảnh chó
        imageLabel = new JLabel();
        setDogImage("src\\image\\dog1.jpg");
        imageLabel.setBorder(BorderFactory.createLineBorder(new Color(13, 153, 255), 1, true));
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(imageLabel, gbc);
        row++;

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(Box.createVerticalStrut(10), gbc); // khoảng cách 20px
        row++;

        // 7. Nút chức năng
        // Chỉnh sửa thông tin
        btnEditInfo = customButton("Chỉnh sửa thông tin");
        ImageIcon iconEdit = new ImageIcon("src\\image\\pencil.png");
        Image image6 = iconEdit.getImage();
        Image newImage6 = image6.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon icon6 = new ImageIcon(newImage6);
        btnEditInfo.setIcon(icon6);
        btnEditInfo.setMargin(new Insets(2, 6, 2, 6));
        btnEditInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cardLayout.show(mainPanel, "checkSymptoms");
            }
        });

        // Phân tích AI
        btnAnalyzeAI = customButton("Phân tích AI");
        ImageIcon iconAI = new ImageIcon("src\\image\\AI.png");
        Image image7 = iconAI.getImage();
        Image newImage7 = image7.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon icon7 = new ImageIcon(newImage7);
        btnAnalyzeAI.setIcon(icon7);
        btnAnalyzeAI.setMargin(new Insets(2, 6, 2, 6));
        btnAnalyzeAI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "aiAnalysisResults");
//                String mainSymptom = lblMainSymptomContent.getText();
//                String location = lblLocationContent.getText();
//                String time = lblTimeContent.getText();
//                String otherSymptoms = lblOtherSymptomsContent.getText();
//
//                String prompt = String.format(
//                        "Chó có triệu chứng chính là '%s' ở vị trí '%s', xuất hiện %s. Các triệu chứng khác gồm: %s. "
//                                + "Hãy chẩn đoán bệnh và đưa ra hướng điều trị phù hợp.",
//                        mainSymptom, location, time, otherSymptoms
//                );
//
//                // Gọi AI trong thread riêng
//                new Thread(() -> {
//                    OpenAIService ai = new OpenAIService();
//                    try {
//                        String result = ai.ask(prompt);
//
//                        // Gọi lại UI ở EDT
//                        SwingUtilities.invokeLater(() -> {
//                            Component comp = findComponentByName("aiAnalysisResults");
//                            if (comp instanceof AIAnalysisResultsPanel aiPanel) {
//                                aiPanel.setAnalysisResult(result);
//                                cardLayout.show(mainPanel, "aiAnalysisResults");
//                            }
//                        });
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                        SwingUtilities.invokeLater(() ->
//                                JOptionPane.showMessageDialog(DogInforPanel.this,
//                                        "Không thể kết nối tới AI. Hãy kiểm tra Ollama server.",
//                                        "Lỗi AI", JOptionPane.ERROR_MESSAGE)
//                        );
//                    }
//                }).start();
            }
        });




        // Gọi bác sĩ
        btnCallVet = customButtonCall("Gọi bác sĩ");
        btnCallVet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cardLayout.show(mainPanel, "doctorSelection");
            }
        });
        ImageIcon iconCall = new ImageIcon("src\\image\\call.png");
        Image image8 = iconCall.getImage();
        Image newImage8 = image8.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon icon8 = new ImageIcon(newImage8);
        btnCallVet.setIcon(icon8);
        btnCallVet.setMargin(new Insets(2, 6, 2, 6));


        JButton[] buttons = {btnEditInfo, btnAnalyzeAI, btnCallVet};
        for (JButton btn : buttons) {
            btn.setPreferredSize(new Dimension(180, 22));
            btn.setMargin(new Insets(4, 8, 5, 8));
            gbc.gridx = 0;
            gbc.gridy = row;
            gbc.gridwidth = 3;
            gbc.anchor = GridBagConstraints.CENTER;
            contentPanel.add(btn, gbc);
            row++;
        }

        wrapper.add(contentPanel, BorderLayout.CENTER);
        add(wrapper, BorderLayout.CENTER);
        add(new BottomMenuPanel(), BorderLayout.SOUTH);
    }

    public void setDogImage(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(img));
    }

    private CustomButton customButton(String text) {
        CustomButton button = new CustomButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(1000, 250));
        button.setBackgroundColor(new Color(70, 150, 236));
        button.setTextColor(Color.WHITE);
        button.setBorderRadius(10);
        return button;
    }
    private CustomButton customButtonCall(String text) {
        CustomButton button = new CustomButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(140, 35));
        button.setBackgroundColor(new Color(70, 150, 236));
        button.setTextColor(Color.WHITE);
        button.setBorderRadius(10);
        return button;
    }

    public void updateDogInfo(Symptom symptom) {
        lblMainSymptomContent.setText(symptom.getName());
        lblLocationContent.setText(symptom.getLocation());

        // Format thời gian từ Date -> String
        lblTimeContent.setText(symptom.getDateNoticed());

        lblOtherSymptomsContent.setText(symptom.getDescription());

        setDogImage(symptom.getImagePath());
    }

    private Component findComponentByName(String name) {
        for (Component comp : mainPanel.getComponents()) {
            if (mainPanel.getComponentZOrder(comp) != -1 && name.equals(mainPanel.getComponentZOrder(comp) + "")) {
                return comp;
            }
            if (mainPanel.getLayout() instanceof CardLayout layout) {
                for (Component c : mainPanel.getComponents()) {
                    if (mainPanel.isAncestorOf(c) && name.equals(mainPanel.getComponentZOrder(c) + "")) {
                        return c;
                    }
                }
            }
            if (mainPanel.getComponentZOrder(comp) != -1 && name.equals(mainPanel.getComponentZOrder(comp) + "")) {
                return comp;
            }
            if (mainPanel.getComponentZOrder(comp) != -1 && name.equals(comp.getName())) {
                return comp;
            }
        }
        return mainPanel.getComponent(1); // fallback
    }



//    public static void main(String[] args) {
//        CardLayout cardLayout = new CardLayout();
//        JPanel mainPanel = new JPanel(cardLayout);
//
//        DogInforPanel dogInforPanel = new DogInforPanel(cardLayout, mainPanel);
//        AIAnalysisResultsPanel aiAnalysisResultsPanel = new AIAnalysisResultsPanel(cardLayout, mainPanel);
//        DoctorSelectionPanel doctorSelectionPanel = new DoctorSelectionPanel(cardLayout, mainPanel);
//
//        mainPanel.add(dogInforPanel, "dogInfor");
//        mainPanel.add(aiAnalysisResultsPanel, "aiAnalysisResults");
//        mainPanel.add(doctorSelectionPanel, "doctorSelection");
//
//        JFrame frame = new JFrame("Test");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 700);
//        frame.setLocationRelativeTo(null);
//        frame.setContentPane(mainPanel);
//        cardLayout.show(mainPanel, "dogInfor");
//        frame.setVisible(true);
//    }
}