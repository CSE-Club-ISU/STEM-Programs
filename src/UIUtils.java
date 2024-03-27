package src;

import javax.swing.*;
import java.awt.*;

import static java.awt.Component.CENTER_ALIGNMENT;

public  class UIUtils {
    public static final Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 30);

    public static JLabel addTextToComp(String text, JComponent component) {
        return addTextToComp(text, font, component);
    }

    public static JLabel addTextToComp(String text, int size, JComponent component) {
        JLabel newLabel = new JLabel();
        newLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, size));
        newLabel.setText(text);
        newLabel.setAlignmentX(CENTER_ALIGNMENT);
        component.add(newLabel);
        return newLabel;
    }

    public static JLabel addTextToComp(String text, Font font, JComponent component) {
        JLabel newLabel = new JLabel();
        newLabel.setFont(font);
        newLabel.setText(text);
        newLabel.setAlignmentX(CENTER_ALIGNMENT);
        component.add(newLabel);
        return newLabel;
    }
}
