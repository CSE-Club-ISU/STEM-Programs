package Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundButton extends JButton {
    private String text;
    private Color color;
    private Color armedColor;

    private int borderRadius;

    public RoundButton(String text, Color textColor, int textSize, Color color, Color armedColor, int borderRadius) {
        super(text);
        setContentAreaFilled(false);
        this.text = text;
        this.color = color;
        this.armedColor = armedColor;
        setForeground(textColor);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, textSize));
        setVerticalTextPosition(AbstractButton.CENTER);
        setAlignmentX(CENTER_ALIGNMENT);
        this.borderRadius = borderRadius;
    }

    public RoundButton(String text, Color textColor, int textSize, Color color, int borderRadius) {
        this(text, textColor, textSize, color, new Color(Math.max(0, color.getRed() - 20), Math.max(0, color.getGreen() - 20), Math.max(0, color.getBlue() - 20)), borderRadius);
    }

    public RoundButton(Color color, Color armedColor, int borderRadius) {
        this("", Color.WHITE, 20, color, armedColor, borderRadius);
    }

    public RoundButton(Color color, int borderRadius) {
        this(color, new Color(Math.max(0, color.getRed() - 20), Math.max(0, color.getGreen() - 20), Math.max(0, color.getBlue() - 20)), borderRadius);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (getModel().isArmed()) {
            g2.setColor(armedColor);
        } else {
            g2.setColor(color);
        }
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), borderRadius, borderRadius));
        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
    }
}
