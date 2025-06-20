package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SplashScreen extends JFrame {
    JPanel panelSplash , circlePanel;
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
        backgroundPanel.setBackground(new Color(100, 149, 237)); // xanh dương
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
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(new Color(100, 149, 237));
        circlePanel.add(label, BorderLayout.CENTER);

        // Thêm các lớp vào LayeredPane
        layeredPane.add(backgroundPanel, Integer.valueOf(0)); // lớp nền
        layeredPane.add(circlePanel, Integer.valueOf(1));     // lớp tròn

        setContentPane(layeredPane);

        // Timer chuyển sang MainScreen
        Timer timer = new Timer(4000, (ActionEvent e) -> {
            new MainScreen().setVisible(true);
            dispose();
        });
        timer.setRepeats(false);
        timer.start();

    }

    public static void main(String[] args) {
        new SplashScreen().setVisible(true);
    }
}
