package Utils;

import javax.swing.*;
import java.awt.*;

public class RoundButton extends JButton {
    private String text;
    private Color color;

    private int topLeftBorder, topRightBorder, bottomLeftBorder, bottomRightBorder;

    public RoundButton(String text, Color color, int textSize, int borderRadius) {
        super(text);
        setContentAreaFilled(false);
        this.text = text;
        this.color = color;
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, textSize));
        topLeftBorder = borderRadius;
        topRightBorder = borderRadius;
        bottomLeftBorder = borderRadius;
        bottomRightBorder = borderRadius;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (getModel().isArmed()) {
            g2.setColor(Color.lightGray);
        } else {
            g2.setColor(getBackground());
        }
        g2.fill(UIUtils.createRoundedRectangleArea(getWidth(),getHeight(),topLeftBorder,topRightBorder,bottomLeftBorder,bottomRightBorder));
        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
    }
}
