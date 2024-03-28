package src.Programs.ProgramTemplate;

import src.StartMenu.Frame;
import src.UIUtils;

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


        //Button
        JButton button = new JButton("Regenerate");
        button.setVerticalTextPosition(AbstractButton.CENTER);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setBackground(Color.BLUE);
        button.setForeground(Color.white);
        button.setFocusPainted(false);
        button.setFocusable(false);
        button.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Input Field
        JTextField inputField = new JTextField("Input text here");
        inputField.setSize(new Dimension(100, 30));
        inputField.setBorder(new EmptyBorder(10, 10, 10, 10));


        //Keyboard Input Listener
        inputListener = new ProgramTemplateKeyInputListener(frame, this, inputField);
    }


}
