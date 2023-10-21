package src.MazeGame;

import src.Frame;

import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel {
    MazeGame mazeGame;
    public MazePanel() {
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);

        mazeGame = new MazeGame(10, 10);
        Cell[][] maze = mazeGame.getMaze();
        Frame.addTextToComp("Maze Game", this);

        Panel mazeP = new Panel();
        mazeP.setMaximumSize(new Dimension(500,500));
        GridLayout gridLayout = new GridLayout();
        gridLayout.setRows(maze.length);
        gridLayout.setColumns(maze[0].length);
        gridLayout.setHgap(2);
        gridLayout.setVgap(2);

        mazeP.setLayout(gridLayout);
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[r].length; c++) {
                CellUI cellUI = new CellUI(maze[r][c],mazeP.getWidth() / (maze.length + 2));
                mazeP.add(cellUI);
            }
        }
        this.add(mazeP);
    }
}
