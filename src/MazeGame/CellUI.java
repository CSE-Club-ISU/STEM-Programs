package src.MazeGame;

import javax.swing.*;
import java.awt.*;

public class CellUI extends JPanel {
    MazePanel mazePanel;
    Cell cell;
    int size;
    int inLineDir;
    int outLineDir;

    public CellUI(Cell cell, int size, MazePanel mazePanel) {
        this.mazePanel = mazePanel;
        setLayout(null);
        this.cell = cell;

        setSize(size, size);
        this.size = size;
    }

    /**
     * @param dir 1=down, 2=right, -1=up, -2=left
     */
    CellUI getCellInDir(int dir) {
        if (dir == 1 && cell.r != mazePanel.mazeUI.length - 1) {
            return mazePanel.mazeUI[cell.r + 1][cell.c];
        } else if (dir == 2 && cell.c != mazePanel.mazeUI[0].length - 1) {
            return mazePanel.mazeUI[cell.r][cell.c + 1];
        } else if (dir == -1 && cell.r != 0) {
            return mazePanel.mazeUI[cell.r - 1][cell.c];
        } else if (dir == -2 && cell.c != 0) {
            return mazePanel.mazeUI[cell.r][cell.c - 1];
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (cell.isStartCell())
            setBackground(Color.green);
        else if (cell.isEndCell())
            setBackground(Color.red);
        else
            setBackground(Color.lightGray);
        g.setColor(Color.BLACK);

        int thickness = Math.max(2, 50 / cell.mazeGame.maze.length);
        // Walls
        if (cell.walls[0])
            g.fillRect(0, 0, thickness, getHeight()); //left
        if (cell.walls[1])
            g.fillRect(0, 0, getWidth(), thickness); //top
        if (cell.walls[2])
            g.fillRect(0, getHeight() - thickness, getWidth(), thickness); //bottom
        if (cell.walls[3])
            g.fillRect(getWidth() - thickness, 0, thickness, getHeight()); //right

        // Cell borders
        g.fillRect(0, 0, 1, getHeight()); //left
        g.fillRect(0, 0, getWidth(), 1); //top
        g.fillRect(0, getHeight(), getWidth(), 1); //bottom
        g.fillRect(getWidth(), 0, 1, getHeight()); //right
        Graphics2D g2 = (Graphics2D) g;
        if (inLineDir != 0 || outLineDir != 0) {
            g2.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
            g2.setColor(Color.BLUE);
            drawLine(g2, inLineDir);
            drawLine(g2, outLineDir);
        }

        g.setColor(Color.LIGHT_GRAY);
    }

    private void drawLine(Graphics2D g2, int dir) {
        if (cell.hasWallInDirection(dir))
            g2.setColor(Color.RED);
        else g2.setColor(Color.blue);
        if (dir == 1) {
            g2.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, getHeight());
        } else if (dir == -1) {
            g2.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, 0);
        } else if (dir == 2) {
            g2.drawLine(getWidth() / 2, getHeight() / 2, getWidth(), getHeight() / 2);
        } else if (dir == -2) {
            g2.drawLine(getWidth() / 2, getHeight() / 2, 0, getHeight() / 2);
        }
    }
}
