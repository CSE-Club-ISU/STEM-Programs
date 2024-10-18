package Utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import static java.awt.Component.CENTER_ALIGNMENT;

public class UIUtils {
    public static final Font titleFont = new Font(Font.SANS_SERIF, Font.PLAIN, 30);

    public static JLabel addTitle(String text, JComponent component) {
        return addTitle(text, titleFont, component);
    }

    public static JLabel addTitle(String text, int size, JComponent component) {
        JLabel newLabel = new JLabel();
        newLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, size));
        newLabel.setText(text);
        newLabel.setAlignmentX(CENTER_ALIGNMENT);
        newLabel.setFocusable(false);
        component.add(newLabel);
        return newLabel;
    }

    public static JLabel addTitle(String text, Font font, JComponent component) {
        JLabel newLabel = new JLabel();
        newLabel.setFont(font);
        newLabel.setText(text);
        newLabel.setAlignmentX(CENTER_ALIGNMENT);
        component.add(newLabel);
        return newLabel;
    }

    public static JTextArea addTextArea(String text, int textSize, JComponent component) {
        JTextArea jTextArea = new JTextArea(text, 0, 0);
        jTextArea.setEditable(false);
        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);
        jTextArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, textSize));
        if (component != null) {
            component.add(jTextArea);
        }
        return jTextArea;
    }

    public static JButton addButton(String text, int textSize, int borderRadius, JComponent component) {
        JButton newButton = new RoundButton(text, Color.BLACK, textSize, borderRadius);
        component.add(newButton);
        return newButton;
    }

    public static JPanel addSpace(int width, int height, JComponent component) {
        JPanel space = new JPanel();
        space.setOpaque(false);
        space.setMaximumSize(new Dimension(width, height));
        component.add(space);
        return space;
    }

    public static Area createRoundedRectangleArea(int width, int height, int topLeftBorder, int topRightBorder, int bottomLeftBorder, int bottomRightBorder) {
        Area totalArea = new Area(new Rectangle2D.Double(0, 0, width, height));
        if (topLeftBorder > 0) {
            int roundX = Math.min(width, topLeftBorder);
            int roundY = Math.min(height, topLeftBorder);
            Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
            area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
            area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
            totalArea.intersect(area);
        }
        if (topRightBorder > 0) {
            int roundX = Math.min(width, topRightBorder);
            int roundY = Math.min(height, topRightBorder);
            Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
            area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
            area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
            totalArea.intersect(area);
        }
        if (bottomLeftBorder > 0) {
            int roundX = Math.min(width, bottomLeftBorder);
            int roundY = Math.min(height, bottomLeftBorder);
            Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
            area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
            area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
            totalArea.intersect(area);
        }
        if (bottomRightBorder > 0) {
            int roundX = Math.min(width, bottomRightBorder);
            int roundY = Math.min(height, bottomRightBorder);
            Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
            area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
            area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
            totalArea.intersect(area);
        }
        return totalArea;
    }
}
