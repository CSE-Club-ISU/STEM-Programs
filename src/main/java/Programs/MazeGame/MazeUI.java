package Programs.MazeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * MazeUI is a panel that holds the grid
 */
 class MazeUI extends JPanel {
    /** first array is the rows, the second is columns */
    private CellUI[][] gridUI;

    private Frame frame;

     MazeUI(Frame frame,int rows, int columns) {
        this.frame = frame;
        setBackground(Color.LIGHT_GRAY);
        setMaximumSize(new Dimension(1000, 1000));
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

     CellUI getCellAt(int row, int column) {
        return gridUI[row][column];
    }

     CellUI setCellAt(int row, int column, CellUI newCellUI) {
        gridUI[row][column] = newCellUI;
        return newCellUI;
    }

     int getGridRows() {
        return gridUI.length;
    }

     int getGridColumns() {
        return gridUI[0].length;
    }


    @Override
    public Dimension getMaximumSize(){
         int maxSize = frame.getBounds().height - getParent().getY();
        return new Dimension(maxSize, maxSize);
    }
}
