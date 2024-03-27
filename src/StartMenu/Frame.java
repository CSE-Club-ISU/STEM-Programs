package src.StartMenu;

import src.MazeGame.MazePanel;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

/**
 * Frame a singleton and the main controller of the application.
 * It manages what panel is displayed.
 */
public class Frame extends JFrame {
    private static Frame singleton;
    StartPanel startPanel;
    GameListPanel gameListPanel;

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            public void run() {
                try {
                    new Frame();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        SwingUtilities.invokeLater(r);
    }

    /**
     * Sets up the settings for the entire window.
     */
    private Frame() throws FileNotFoundException {
        singleton = this;
        setFocusable(true);
        setBackground(new Color(38,38,38));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1500, 800);
        setIconImage(new javax.swing.ImageIcon("CSEClubLogoNoTextBorder.png").getImage());
        setLocationRelativeTo(null);
        setTitle("CSE");
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        startPanel = new StartPanel(this);
        add(startPanel);
    }

    public static Frame getInstance() {
        return singleton;
    }

    public void startMazeGame() {
        remove(startPanel);
        gameListPanel = new GameListPanel(this);
        getContentPane().add(gameListPanel);
        setVisible(true);
        paintAll(getGraphics());
    }

}
