package src;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {

    public StartPanel() {
        super();
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 30);
        JLabel jLabelOne = new JLabel();
        jLabelOne.setFont(font);
        JLabel jLabelTwo = new JLabel();
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);


        jLabelOne.setText("ISU Computer Science and Software Engineering Club");
        jLabelTwo.setText("This is text two");

        jLabelOne.setAlignmentX(CENTER_ALIGNMENT);
        jLabelTwo.setAlignmentX(CENTER_ALIGNMENT);
        add(jLabelOne);
        add(jLabelTwo);

        setAlignmentX(CENTER_ALIGNMENT);

        JButton startButton = new JButton("Start game");
        startButton.setVerticalTextPosition(AbstractButton.CENTER);
        startButton.setAlignmentX(CENTER_ALIGNMENT);
        this.add(startButton);
    }
}
