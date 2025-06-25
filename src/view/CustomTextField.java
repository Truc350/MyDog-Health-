package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CustomTextField extends JTextField {
    private Color borderColor = new Color(13, 153, 255);
    private Color startGradientColor = new Color(13, 153, 255);
    private Color endGradientColor = new Color(13, 153, 255);
    private Color backgroundColor = Color.WHITE;
    private Color hoverColor;
    private Color textColor = Color.BLACK;
    private int thickness = 1;
    private int borderRadius = 10;
    private boolean drawBorder = false;  // Mặc định không vẽ viền
    private boolean isHovered = false;

    private String placeholder = "";
    private Color placeholderColor = Color.LIGHT_GRAY;
    private boolean showingPlaceholder = true;

    public CustomTextField(int columns) {
        super(columns);
        setOpaque(false); // để tự vẽ background
//        setBorder(new EmptyBorder(0, 0, 0, 0)); // padding bên trong
        setFont(new Font("Roboto", Font.PLAIN, 16));
        setForeground(textColor);
//        showingPlaceholder = true;

//        addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                if (showingPlaceholder) {
//                    setText("");
//                    setForeground(textColor);
//                    showingPlaceholder = false;
//                }
//                setDrawBorder(true);
//            }
//        });

        // Thêm mouse listener để phát hiện hover
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                isHovered = true;
                repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                isHovered = false;
                repaint();
            }
        });
        // Thêm FocusListener để hiện viền khi được focus
        addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                setDrawBorder(true);  // hiện viền khi focus
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                setDrawBorder(false); // ẩn viền khi mất focus
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ nền với màu hover nếu đang hover
        if (isHovered && hoverColor != null) {
            g2d.setColor(hoverColor);
        } else {
            g2d.setColor(backgroundColor);
        }

        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);

        super.paintComponent(g);

        // Vẽ placeholder nếu rỗng và chưa focus
        if (getText().isEmpty() && !isFocusOwner() && placeholder != null) {
            g2d.setFont(getFont());
            g2d.setColor(placeholderColor);
            Insets ins = getInsets();
            FontMetrics fm = g2d.getFontMetrics();
            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
            g2d.drawString(placeholder, ins.left + 5, y);
        }

        g2d.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (drawBorder) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setStroke(new BasicStroke(thickness));

            Color gradientStart = startGradientColor;
            Color gradientEnd = endGradientColor;

            GradientPaint gradient = new GradientPaint(0, 0, gradientStart, getWidth(), getHeight(), gradientEnd, true);
            g2d.setPaint(gradient);
            g2d.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, borderRadius, borderRadius);
        }
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

    public void setDrawBorder(boolean drawBorder) {
        this.drawBorder = drawBorder;
        repaint();
    }

//    public void setPlaceholder(String text) {
//        this.placeholder = text;
//        if (getText().isEmpty()) {
//            setText(text);
//            setForeground(placeholderColor);
//            showingPlaceholder = true;
//        }
//    }

    public void setPlaceholderColor(Color color) {
        this.placeholderColor = color;
        repaint();
    }

    public void setPlaceholder(String text) {
        this.placeholder = text;
        repaint();
    }

    // Nếu bạn muốn kiểm tra từ ngoài
    public boolean isShowingPlaceholder() {
        return showingPlaceholder;
    }
}
