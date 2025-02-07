package StartMenu;

import Utils.RoundButton;
import Utils.RoundPanel;
import Utils.UIUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * StartPanel is the first screen that the user will see, all it shows is the CSE logo.
 */
public class StartPanel extends JPanel {

    /**
     * This is the start screen to display first.
     */
    public StartPanel(Frame frame) {
        super();
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);
        setSize(frame.getSize());
        setAlignmentX(CENTER_ALIGNMENT);

        add(createBackgroundImage());

        JLabel title = UIUtils.addTitle("At Iowa State University", new Font(Font.SANS_SERIF, Font.BOLD, 50),this);
        title.setForeground(new Color(250, 180, 0));

        add(Box.createVerticalStrut(20));

        add(createStartButton(frame));
    }

    private JLabel createBackgroundImage() {
        BufferedImage imageResource;
        try {
            imageResource = ImageIO.read(new File("src/main/resources/CSEClubLogo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JLabel image = new JLabel(new ImageIcon(imageResource));
        image.setAlignmentX(CENTER_ALIGNMENT);
        return image;
    }

    private JButton createStartButton(Frame frame) {
        JButton startButton = new RoundButton("Start Program", Color.WHITE, 24, new Color(140, 0, 0), 10);
        startButton.setVerticalTextPosition(AbstractButton.CENTER);
        startButton.addActionListener((l) -> frame.showGameList());
        startButton.setFocusPainted(false);
        startButton.setForeground(new Color(255, 255, 200));
        return startButton;
    }

    @Override
    protected void paintComponent(Graphics g) {

    }
}
