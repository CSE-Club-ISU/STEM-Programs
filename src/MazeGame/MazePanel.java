package src.MazeGame;

import src.Frame;

import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel {
    MazeGame mazeGame;
    Panel mazePanel;
    CellUI[][] mazeUI;
    JTextField sizeInput;
    public MazePanel() {
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);
        mazeUI = new CellUI[10][10];
        Frame.addTextToComp("Maze Game", this);
        Box top = Box.createHorizontalBox();

        JButton resetButton = new JButton("Regenerate");
        resetButton.setVerticalTextPosition(AbstractButton.CENTER);
        resetButton.setAlignmentX(CENTER_ALIGNMENT);
        resetButton.addActionListener((l) -> regenerateMaze());
        resetButton.setBackground(Color.BLUE);
        resetButton.setForeground(Color.white);
        resetButton.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        resetButton.setFocusPainted(false);
        top.add(resetButton);

        sizeInput = new JTextField(Integer.toString(mazeUI.length));
        sizeInput.setMaximumSize(new Dimension(100,30));
        top.add(sizeInput);
        add(top);
        mazePanel = new Panel();
        mazePanel.setBackground(Color.LIGHT_GRAY);
        mazePanel.setMaximumSize(new Dimension(700,700));
        this.add(mazePanel);
        generateMaze();
    }

    public void refreshCells() {
        for (int r = 0; r < mazeUI.length; r++) {
            for (int c = 0; c < mazeUI[r].length; c++) {
                mazeUI[r][c].paintAll(mazeUI[r][c].getGraphics());
            }
        }
    }

    public void regenerateMaze() {
        try {
            int size = Integer.parseInt(sizeInput.getText());
            if (size != mazeUI.length) {
                for (int r = 0; r < mazeUI.length; r++) {
                    for (int c = 0; c < mazeUI[r].length; c++) {
                        mazePanel.remove(mazeUI[r][c]);
                    }
                }
                this.paintAll(getGraphics());
                mazeUI = new CellUI[size][size];
                generateMaze();
            } else {
                mazeGame.startMazeGame();
                refreshCells();
            }
        } catch (NumberFormatException e) {
            System.out.println("Input not valid");
        }
    }

    public void generateMaze() {
        mazeGame = new MazeGame(mazeUI.length, mazeUI[0].length);
        Cell[][] maze = mazeGame.getMaze();
        GridLayout gridLayout = new GridLayout();
        gridLayout.setRows(maze.length);
        gridLayout.setColumns(maze[0].length);
        mazePanel.setLayout(gridLayout);
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[r].length; c++) {
                mazeUI[r][c] = new CellUI(maze[r][c],mazePanel.getMaximumSize().width / (maze.length + 2));
                mazePanel.add(mazeUI[r][c]);
            }
        }
        paintAll(getGraphics());
    }
}
