package src;

import src.MazeGame.MazePanel;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class Frame extends JFrame {
    static Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 30);
    StartPanel startPanel;
    MazePanel mazeGame;
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            public void run() {
                try {
                    new Frame().create();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        SwingUtilities.invokeLater(r);
    }

    private void create() throws FileNotFoundException {
        startPanel = new StartPanel(this);
        add(startPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1500, 800);
        setIconImage(new javax.swing.ImageIcon("CSEClubLogoNoTextBorder.png").getImage());
        setLocationRelativeTo(null);
        setTitle("CSE");
        setVisible(true);
        startMazeGame();
    }

    public void startMazeGame() {
        remove(startPanel);
        mazeGame = new MazePanel();
        add(mazeGame);
        setVisible(true);
        paintAll(getGraphics());
    }

    public static JLabel addTextToComp(String text, JComponent component) {
        JLabel newLabel = new JLabel();
        newLabel.setFont(font);
        newLabel.setText(text);
        newLabel.setAlignmentX(CENTER_ALIGNMENT);
        component.add(newLabel);
        return newLabel;
    }

}
