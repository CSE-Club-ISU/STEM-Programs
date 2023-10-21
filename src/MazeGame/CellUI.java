package src.MazeGame;

import javax.swing.*;
import java.awt.*;

public class CellUI extends JPanel {
    Cell cell;
    JPanel wallPanel;
    int size;

    public CellUI(Cell cell, int size) {
        this.setLayout(null);
        this.cell = cell;
        this.setBackground(Color.lightGray);
        this.setSize(size, size);
        this.size = size;
        wallPanel = new JPanel();
        wallPanel.setBackground(Color.blue);
        this.add(wallPanel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        if (cell.walls[0])
            g.fillRect(0, 0, 3, getHeight()); //left
        if (cell.walls[1])
            g.fillRect(0, 0, getWidth(), 3); //top
        if (cell.walls[2])
            g.fillRect(0, getHeight() - 3, getWidth(), 3); //bottom
        if (cell.walls[3])
            g.fillRect(getWidth() - 3, 0, 3, getHeight()); //right
        g.setColor(Color.LIGHT_GRAY);
    }
}
