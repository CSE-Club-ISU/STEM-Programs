package src.MazeGame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InstructionPanel extends JPanel {
    JTextArea instructionInput;
    public InstructionPanel() {
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);
        instructionInput = new JTextArea("");
        instructionInput.setMaximumSize(new Dimension(400,700));
        instructionInput.setMinimumSize(new Dimension(400,700));
        instructionInput.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        setBorder(new EmptyBorder(0,20,0,0));
        instructionInput.setBackground(Color.lightGray);
        add(instructionInput);
    }
}
