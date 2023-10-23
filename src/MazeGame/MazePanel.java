package src.MazeGame;

import src.Frame;

import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel {
    MazeGame mazeGame;
    Panel mazePanel;
    CellUI[][] mazeUI;
    public MazePanel() {
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);
        mazeUI = new CellUI[10][10];
        Frame.addTextToComp("Maze Game", this);
        JButton resetButton = new JButton("Regenerate");
        resetButton.setVerticalTextPosition(AbstractButton.CENTER);
        resetButton.setAlignmentX(CENTER_ALIGNMENT);
        resetButton.addActionListener((l) -> {clearMaze(); generateMaze(); });
        this.add(resetButton);

        generateMaze();
    }

    public void clearMaze() {
        remove(mazePanel);
    }

    public void generateMaze() {
        mazeGame = new MazeGame(mazeUI.length, mazeUI[0].length);
        Cell[][] maze = mazeGame.getMaze();
        mazePanel = new Panel();
        mazePanel.setBackground(Color.LIGHT_GRAY);
        mazePanel.setMaximumSize(new Dimension(700,700));
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
        this.add(mazePanel);
        paintAll(getGraphics());
    }
}
