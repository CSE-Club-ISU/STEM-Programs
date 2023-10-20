package src;

import javax.swing.*;
import java.awt.*;

public class Paragraph extends JPanel {
    private String text;
    Font font;
    JPanel panel;
    public Paragraph(String text, Font font, JPanel panel) {
        this.text = text;
        this.font = font;
        this.panel = panel;
    }

    @Override
    public void paintComponent(Graphics g) {
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
        g.setFont(font);
        JTextArea text = new JTextArea(1, 1);
        text.append("ISU Computer Science and Software Engineering Club");
        text.setEditable(false);
        text.setFont(font);
        panel.add(this);
    }
}
