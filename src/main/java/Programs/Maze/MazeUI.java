package Programs.Maze;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * MazeUI is a panel that holds the grid
 */
class MazeUI extends JPanel {
    private Frame frame;
    int rows, columns;
    MazePanel mazePanel;
    MazeGame mazeGame;

    MazeUI(Frame frame, MazePanel mazePanel, int rows, int columns) {
        this.frame = frame;
        this.mazePanel = mazePanel;
        setBackground(Color.LIGHT_GRAY);
        setMaximumSize(new Dimension(1000, 1000));
        setMinimumSize(new Dimension(700, 700));
        this.rows = rows;
        this.columns = columns;
    }

    protected void generateMaze(MazeGame mazeGame) {
        this.mazeGame = mazeGame;
        paintAll(getGraphics());
        repaint();
    }

    protected void resizeMazeUI(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        removeAll();
        repaint();
    }

    int getGridRows() {
        return rows;
    }

    int getGridColumns() {
        return columns;
    }


    @Override
    public Dimension getMaximumSize() {
        int maxSize = frame.getBounds().height - getParent().getY();
        return new Dimension(maxSize, maxSize);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (mazeGame == null) return;
        drawStartAndEnd((Graphics2D) g);
        drawBorders((Graphics2D) g);
        drawLines((Graphics2D) g);
        drawWalls((Graphics2D) g);
        visualizePath((Graphics2D) g);
    }

    private void drawStartAndEnd(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        double cellWidth = getWidth() / (double) columns;
        double cellHeight = getHeight() / (double) columns;
        Cell startCell = mazeGame.getStartCell();
        g2.setColor(Color.GREEN);
        g2.fillRect((int) (startCell.getColumn() * cellWidth), (int) (startCell.getRow() * cellHeight), (int) cellWidth, (int) cellHeight);
        g2.setColor(Color.RED);
        Cell endCell = mazeGame.getEndCell();
        g2.fillRect((int) (endCell.getColumn() * cellWidth), (int) (endCell.getRow() * cellHeight), (int) cellWidth, (int) cellHeight);
    }

    private void drawBorders(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(Math.min(15, Math.max(3, 100 / rows)), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2.drawLine(0, 0, getWidth(), 0);
        g2.drawLine(0, getHeight(), getWidth(), getHeight());
        g2.drawLine(0, 0, 0, getHeight());
        g2.drawLine(getWidth(), 0, getWidth(), getHeight());
    }

    private void drawLines(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(Math.min(2, Math.max(1, 10 / rows)), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        for (int i = 1; i < rows; i++) {
            g2.drawLine(0, (getHeight() * i) / rows, getWidth(), (getHeight() * i) / rows);
        }
        for (int i = 1; i < columns; i++) {
            g2.drawLine((getWidth() * i) / rows, getWidth(), (getWidth() * i) / rows, 0);
        }
    }

    private void drawWalls(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(Math.min(15, Math.max(2, 100 / rows)), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        double cellWidth = getWidth() / (double) columns;
        double cellHeight = getHeight() / (double) columns;
        for (Cell[] rows : mazeGame.getGrid()) {
            for (Cell cell : rows) {
                // Calculate the upper left coordinates of the cell
                double x = cell.getColumn() * cellWidth;
                double y = cell.getRow() * cellHeight;
                if (cell.hasWallInDirection(1)) {
                    // Down
                    g2.drawLine((int) x, (int) (y + cellHeight), (int) (x + cellWidth), (int) (y + cellHeight));
                }
                if (cell.hasWallInDirection(2)) {
                    // Right
                    g2.drawLine((int) (x + cellWidth), (int) y, (int) (x + cellWidth), (int) (y + cellHeight));
                }
                if (cell.hasWallInDirection(-1)) {
                    // Up
                    g2.drawLine((int) x, (int) y, (int) (x + cellWidth), (int) y);
                }
                if (cell.hasWallInDirection(-2)) {
                    // Left
                    g2.drawLine((int) x, (int) y, (int) x, (int) (y + cellHeight));
                }
            }
        }
    }

    private void visualizePath(Graphics2D g2) {
        ArrayList<Integer> instructions = mazePanel.instructionPanel.instructions;
        switch (mazePanel.instructionPanel.pathState) {
            case Normal -> g2.setColor(Color.BLUE);
            case Finished -> g2.setColor(Color.GREEN);
            case Invalid -> g2.setColor(Color.RED);
        }
        g2.setStroke(new BasicStroke(Math.min(15, Math.max(3, 100 / rows)), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        Cell currentCell = mazeGame.getStartCell();
        double cellWidth = getWidth() / (double) columns;
        double cellHeight = getHeight() / (double) columns;
        for (Integer instruction : instructions) {
            if (currentCell.hasWallInDirection(instruction)) {
                double xOffset = 0;
                double yOffset = 0;
                if (instruction == 1) yOffset += cellHeight / 2;
                if (instruction == 2) xOffset += cellWidth / 2;
                if (instruction == -1) yOffset -= cellHeight / 2;
                if (instruction == -2) xOffset -= cellWidth / 2;
                g2.drawLine((int) ((currentCell.getColumn() + .5) * cellWidth), (int) ((currentCell.getRow() + .5) * cellHeight),
                        (int) ((currentCell.getColumn() + .5) * cellWidth + xOffset), (int) ((currentCell.getRow() + .5) * cellHeight + yOffset));
                return;
            }
            Cell nextCell = currentCell.getCellInDir(instruction);
            g2.drawLine((int) ((currentCell.getColumn() + .5) * cellWidth), (int) ((currentCell.getRow() + .5) * cellHeight),
                    (int) ((nextCell.getColumn() + .5) * cellWidth), (int) ((nextCell.getRow() + .5) * cellHeight));
            currentCell = nextCell;
        }
    }
}
