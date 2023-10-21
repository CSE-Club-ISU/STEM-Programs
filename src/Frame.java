package src;

import src.MazeGame.MazePanel;

import javax.swing.*;
import java.io.FileNotFoundException;

public class Frame extends JFrame {
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
//        JPanel mainPanel = new JPanel();
//
//        // put main panel in a window
//        JFrame frame = new JFrame("Maze Game!");
//        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        StartPanel startPanel = new StartPanel();
//        mainPanel.setLayout(new GridLayout());
//        mainPanel.add(startPanel);
//        startPanel.setPreferredSize(frame.getSize());
//
//        frame.setVisible(true);
    }

    public void StartMazeGame() {
        remove(startPanel);
        mazeGame = new MazePanel();
        add(mazeGame);
        update(getGraphics());
    }
}
