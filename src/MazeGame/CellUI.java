package src.MazeGame;

import javax.swing.*;
import java.awt.*;

public class CellUI extends JPanel {
    Cell cell;
    int size;

    public CellUI(Cell cell, int size) {
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

        g.setColor(Color.LIGHT_GRAY);
    }
}
