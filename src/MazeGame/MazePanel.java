package src.MazeGame;

import src.Frame;

import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel {
    MazeGame mazeGame;
    CellUI[][] mazeUI;
    public MazePanel() {
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);

        mazeGame = new MazeGame(40, 40);
        Cell[][] maze = mazeGame.getMaze();
        mazeUI = new CellUI[maze.length][maze[0].length];
        Frame.addTextToComp("Maze Game", this);

        Panel mazeP = new Panel();
        mazeP.setMaximumSize(new Dimension(800,800));
        GridLayout gridLayout = new GridLayout();
        gridLayout.setRows(maze.length);
        gridLayout.setColumns(maze[0].length);

        mazeP.setLayout(gridLayout);
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[r].length; c++) {
                mazeUI[r][c] = new CellUI(maze[r][c],mazeP.getMaximumSize().width / (maze.length + 2));
                mazeP.add(mazeUI[r][c]);
            }
        }
        this.add(mazeP);
        paintAll(getGraphics());
    }
}
