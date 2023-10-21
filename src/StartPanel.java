package src;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {

    public StartPanel(Frame frame) {
        super();
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);

        Frame.addTextToComp("ISU Computer Science and Software Engineering Club",this);

        setAlignmentX(CENTER_ALIGNMENT);

        JButton startButton = new JButton("Start game");
        startButton.setVerticalTextPosition(AbstractButton.CENTER);
        startButton.setAlignmentX(CENTER_ALIGNMENT);
        startButton.addActionListener((l) -> frame.startMazeGame());
        this.add(startButton);
    }
}
