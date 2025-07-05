package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SplashScreen extends JFrame {
    public SplashScreen() throws HeadlessException {
        setTitle("MyDog Health+");
        setSize(400, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);

        // JLayeredPane chứa các lớp
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(400, 700));

        // Panel nền xanh dương
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(new Color(70, 150, 236)); // xanh dương
        backgroundPanel.setBounds(0, 0, 400, 700);
        backgroundPanel.setOpaque(true);

        // Panel hình tròn trắng
        JPanel circlePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillOval(0, 0, getWidth(), getHeight());
            }
        };
        circlePanel.setOpaque(false);
        circlePanel.setBounds(100, 270, 200, 200);
        circlePanel.setLayout(new BorderLayout());

        // Chữ và icon bên trong hình tròn
        JLabel label = new JLabel("<html><div style='text-align: center;'>MyDog Health+<br>🐶</div></html>", SwingConstants.CENTER);
        label.setFont(new Font("Roboto", Font.BOLD, 20));
        label.setForeground(new Color(100, 149, 237));
        circlePanel.add(label, BorderLayout.CENTER);

        // Thêm các lớp vào LayeredPane
        layeredPane.add(backgroundPanel, Integer.valueOf(0)); // lớp nền
        layeredPane.add(circlePanel, Integer.valueOf(1));     // lớp tròn

        setContentPane(layeredPane);


        // Timer chuyển sang MainScreen -> login
        Timer timer = new Timer(3000, (ActionEvent e) -> {
            System.out.println("⏳ Splash done. Opening Login...");
            MainScreen mainScreen = new MainScreen();
            mainScreen.setVisible(true);

            // Ép chuyển sang login khi mở (nếu panel mặc định bị sai)
            mainScreen.showLoginPanel(); // thêm hàm này vào MainScreen
            this.dispose(); // tắt Splash
        });

        timer.setRepeats(false);
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SplashScreen().setVisible(true));
    }
}
