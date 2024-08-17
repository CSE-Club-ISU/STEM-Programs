package src.Programs.ProgramTemplate;

import src.StartMenu.Frame;
import src.UI.UIUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProgramTemplatePanel extends JPanel {
    private final Frame frame;
    private final ProgramTemplateKeyInputListener inputListener;

    public ProgramTemplatePanel(Frame frame) {
        this.frame = frame;

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);

        //Title
        UIUtils.addTitle("Program Template", this);

        add(Box.createVerticalStrut(10));

        //Button
        JButton button = new JButton("Go Back to program list");
        button.setVerticalTextPosition(AbstractButton.CENTER);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setBackground(Color.RED);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setFocusable(false);
        button.setBorder(new EmptyBorder(10, 10, 10, 10));
        button.addActionListener((e) -> frame.endProgram(this));
        add(button);

        add(Box.createVerticalStrut(10));

        //Input Field
        JTextField inputField = new JTextField("Input text here.");
        inputField.setSize(new Dimension(100, 30));
        inputField.setMaximumSize(new Dimension(500, 300));
        inputField.setBorder(new EmptyBorder(10, 10, 10, 10));
        inputField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        add(inputField);

        //Keyboard Input Listener
        inputListener = new ProgramTemplateKeyInputListener(frame, this, inputField);
    }


}
