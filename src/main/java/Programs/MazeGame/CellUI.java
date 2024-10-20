package Programs.MazeGame;

import Programs.MazeGame.MazeStartGeneration.RandomStartGen;

import javax.swing.*;
import java.awt.*;

class CellUI extends JPanel {
    MazeUI mazeUI;
    Cell cell;
    int size;
    int inLineDir;
    int outLineDir;
    Color lineColor;

    CellUI(Cell cell, int size, MazeUI mazeUI) {
        this.mazeUI = mazeUI;
        setLayout(null);
        this.cell = cell;

        setSize(size, size);
        this.size = size;
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

        int thickness = Math.max(2, 50 / mazeUI.getGridRows());
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
            g2.setStroke(new BasicStroke(Math.min(15, Math.max(3, 100 / mazeUI.getGridRows())), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
            drawLine(g2, inLineDir);
            drawLine(g2, outLineDir);
        }
    }

    private void drawLine(Graphics2D g2, int dir) {
        g2.setColor(lineColor);
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
