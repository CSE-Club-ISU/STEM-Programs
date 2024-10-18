package Utils;

import javax.swing.*;
import java.awt.*;

public class RoundPanel extends JPanel {
    private int topLeftBorder, topRightBorder, bottomLeftBorder, bottomRightBorder;
    public RoundPanel(Color color, int borderRadius) {
        setBackground(color);
        setOpaque(false);
        topLeftBorder = borderRadius;
        topRightBorder = borderRadius;
        bottomLeftBorder = borderRadius;
        bottomRightBorder = borderRadius;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(UIUtils.createRoundedRectangleArea(getWidth(),getHeight(),topLeftBorder,topRightBorder,bottomLeftBorder,bottomRightBorder));
        g2.dispose();
        super.paintComponent(g);
    }

}
