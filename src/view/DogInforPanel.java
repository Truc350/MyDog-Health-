package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DogInforPanel extends JPanel {
    private JLabel lblMainSymptomContent, lblLocationContent, lblTimeContent, lblOtherSymptomsContent, imageLabel;
    private JButton btnEditInfo, btnAnalyzeAI, btnCallVet;

    public DogInforPanel() {
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
        gbc.insets = new Insets(10, 6, 10, 6); // top, left, bottom, right
        gbc.gridx = 0;
        gbc.gridy = 0;

        Font boldFont = new Font("Roboto", Font.BOLD, 16);
        Font plainFont = new Font("Roboto", Font.PLAIN, 16);

        int row = 0;

        // 1. Triệu chứng chính
        ImageIcon iconPin = new ImageIcon("src\\image\\pin.png");
        Image image1 = iconPin.getImage();
        Image newImage1 = image1.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(newImage1);
        JLabel lbIcon1 = new JLabel(icon1);

        gbc.gridx = 0;
        gbc.gridy = row;
        contentPanel.add(lbIcon1, gbc);

        JLabel lblSymptomTitle = new JLabel("Triệu chứng chính: ");
        lblSymptomTitle.setFont(boldFont);
        gbc.gridx = 1;
        contentPanel.add(lblSymptomTitle, gbc);

        lblMainSymptomContent = new JLabel("Chán ăn");
        lblMainSymptomContent.setFont(plainFont);
        gbc.gridx = 2;
        contentPanel.add(lblMainSymptomContent, gbc);
        row++;

        // 2. Vị trí
        ImageIcon iconLo = new ImageIcon("src\\image\\location.png");
        Image image2 = iconLo.getImage();
        Image newImage2 = image2.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(newImage2);
        JLabel lbIcon2 = new JLabel(icon2);

        gbc.gridx = 0;
        gbc.gridy = row;
        contentPanel.add(lbIcon2, gbc);

        JLabel lblLocationTitle = new JLabel("Vị trí: ");
        lblLocationTitle.setFont(boldFont);
        gbc.gridx = 1;
        contentPanel.add(lblLocationTitle, gbc);

        lblLocationContent = new JLabel("Miệng/ Toàn thân");
        lblLocationContent.setFont(plainFont);
        gbc.gridx = 2;
        contentPanel.add(lblLocationContent, gbc);
        row++;

        // 3. Thời gian xuất hiện
        ImageIcon iconTime = new ImageIcon("src\\image\\time.png");
        Image image3 = iconTime.getImage();
        Image newImage3 = image3.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        ImageIcon icon3 = new ImageIcon(newImage3);
        JLabel lbIcon3 = new JLabel(icon3);

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
        Image newImage4 = image4.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        ImageIcon icon4 = new ImageIcon(newImage4);
        JLabel lbIcon4 = new JLabel(icon4);

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
        Image newImage5 = image5.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        ImageIcon icon5 = new ImageIcon(newImage5);
        JLabel lbIcon5 = new JLabel(icon5);

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
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1, true));
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(imageLabel, gbc);
        row++;

        // 7. Nút chức năng
        // Chỉnh sửa thông tin
        btnEditInfo = customButton("Chỉnh sửa thông tin");
        ImageIcon iconEdit = new ImageIcon("src\\image\\pencil.png");
        Image image6 = iconEdit.getImage();
        Image newImage6 = image6.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        ImageIcon icon6 = new ImageIcon(newImage6);
        btnEditInfo.setIcon(icon6);
        btnEditInfo.setMargin(new Insets(2, 6, 2, 6));

        // Phân tích AI
        btnAnalyzeAI = customButton("Phân tích AI");
        ImageIcon iconAI = new ImageIcon("src\\image\\AI.png");
        Image image7 = iconAI.getImage();
        Image newImage7 = image7.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        ImageIcon icon7 = new ImageIcon(newImage7);
        btnAnalyzeAI.setIcon(icon7);
        btnAnalyzeAI.setMargin(new Insets(2, 6, 2, 6));

        // Gọi bác sĩ
        btnCallVet = customButtonCall("Gọi bác sĩ");
        ImageIcon iconCall = new ImageIcon("src\\image\\call.png");
        Image image8 = iconCall.getImage();
        Image newImage8 = image8.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        ImageIcon icon8 = new ImageIcon(newImage8);
        btnCallVet.setIcon(icon8);
        btnCallVet.setMargin(new Insets(2, 6, 2, 6));


        JButton[] buttons = {btnEditInfo, btnAnalyzeAI, btnCallVet};
        for (JButton btn : buttons) {
            btn.setPreferredSize(new Dimension(220, 35));
            gbc.gridx = 0;
            gbc.gridy = row;
            gbc.gridwidth = 3;
            gbc.anchor = GridBagConstraints.CENTER;
            contentPanel.add(btn, gbc);
            row++;
        }

        wrapper.add(contentPanel, BorderLayout.NORTH);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Thông tin chó");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            DogInforPanel dogInfoPanel = new DogInforPanel();
            frame.setContentPane(dogInfoPanel);

            frame.setSize(450, 700); // phù hợp kích thước mobile
            frame.setLocationRelativeTo(null); // căn giữa màn hình
            frame.setVisible(true);
        });
    }
}
