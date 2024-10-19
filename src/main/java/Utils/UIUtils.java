package Utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import static java.awt.Component.CENTER_ALIGNMENT;

public class UIUtils {
    public static final Font titleFont = new Font(Font.SANS_SERIF, Font.PLAIN, 40);

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

    public static JPanel addSpace(int width, int height, JComponent component) {
        JPanel space = new JPanel();
        space.setOpaque(false);
        space.setMaximumSize(new Dimension(width, height));
        component.add(space);
        return space;
    }
}
