package Programs.MazeGame;

import javax.swing.*;
import java.awt.*;

/**
 * MazeUI is a panel that holds the grid
 */
public class MazeUI extends JPanel {
    /** first array is the rows, the second is columns */
    private CellUI[][] gridUI;

    private MazePanel mazePanel;

    public MazeUI(MazePanel mazePanel,int rows, int columns) {
        this.mazePanel = mazePanel;
        setBackground(Color.LIGHT_GRAY);
        setMaximumSize(new Dimension(700, 700));
        setMinimumSize(new Dimension(700, 700));
        gridUI = new CellUI[rows][columns];
    }

    protected void generateMaze(Cell[][] grid) {
        GridLayout gridLayout = new GridLayout();
        gridLayout.setRows(grid.length);
        gridLayout.setColumns(grid[0].length);
        setLayout(gridLayout);

        for (int r = 0; r < getGridRows(); r++) {
            for (int c = 0; c < getGridColumns(); c++) {
                CellUI newCellUI = setCellAt(r, c, new CellUI(grid[r][c], getMaximumSize().width / (grid.length + 2), this));
                grid[r][c].cellUI = newCellUI;
                add(newCellUI);
            }
        }
        paintAll(getGraphics());
    }

    protected void resizeMazeUI(int rows, int columns) {
        removeAll();
        repaint();
        gridUI = new CellUI[rows][columns];
    }

    public CellUI getCellAt(int row, int column) {
        return gridUI[row][column];
    }

    public CellUI setCellAt(int row, int column, CellUI newCellUI) {
        gridUI[row][column] = newCellUI;
        return newCellUI;
    }

    public int getGridRows() {
        return gridUI.length;
    }

    public int getGridColumns() {
        return gridUI[0].length;
    }
}
