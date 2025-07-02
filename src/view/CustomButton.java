package view;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {
    private Color borderColor = new Color(70, 150, 236);
    private Color startGradientColor = Color.getHSBColor(0,0,0);
    private Color endGradientColor = Color.BLUE;
    private Color backgroundColor = Color.BLACK;
    private Color hoverColor;
    private Color textColor = Color.BLACK;
    private int thickness = 3;
    private int borderRadius = 15;
    private boolean drawBorder = true;  // Mặc định không vẽ viền
    private boolean isSelected = false;

    public CustomButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setForeground(textColor);
        setFont(new Font("Roboto", Font.BOLD, 16));
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isSelected) {
            g.setColor(new Color(0, 0, 0, 0)); // nền khi được chọn (vàng nhạt)
        } else if (getModel().isPressed()) {
            g.setColor(backgroundColor.darker());
        } else if (getModel().isRollover() && hoverColor != null) {
            g.setColor(hoverColor);
        } else {
            g.setColor(backgroundColor);
        }

        g.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (!drawBorder) return;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(thickness));

        Color gradientStart, gradientEnd;

        if (getModel().isPressed()) {
            gradientStart = startGradientColor;
            gradientEnd = endGradientColor;
        } else if (getModel().isRollover()) {
            gradientStart = new Color(180, 180, 180);
            gradientEnd = new Color(220, 220, 220);
        } else {
            // Mặc định viền tĩnh khi không hover hoặc click
            gradientStart = borderColor;
            gradientEnd = borderColor;
        }

        GradientPaint gradient = new GradientPaint(0, 0, gradientStart, getWidth(), getHeight(), gradientEnd, true);
        g2d.setPaint(gradient);
        g2d.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, borderRadius, borderRadius);
    }

    // Set màu chữ
    public void setTextColor(Color color) {
        this.textColor = color;
        setForeground(color);
        repaint();
    }

    // Set màu viền (gradient bắt đầu và kết thúc)
    public void setBorderColor(Color color) {
        this.borderColor = color;
        this.startGradientColor = color;
        this.endGradientColor = color;
        repaint();
    }

    public void setBorderThickness(int thickness) {
        this.thickness = thickness;
        repaint();
    }

    public void setGradientColors(Color startColor, Color endColor) {
        this.startGradientColor = startColor;
        this.endGradientColor = endColor;
        repaint();
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        repaint();
    }

    public void setHoverColor(Color color) {
        this.hoverColor = color;
        repaint();
    }

    public void setBorderRadius(int radius) {
        this.borderRadius = radius;
        repaint();
    }

    // Cho phép bật/tắt viền
    public void setDrawBorder(boolean drawBorder) {
        this.drawBorder = drawBorder;
        repaint();
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
        repaint();
    }

}
