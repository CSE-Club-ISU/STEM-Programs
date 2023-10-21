package src.MazeGame;

import javax.swing.*;
import java.awt.*;

public class CellUI extends JPanel {
    Cell cell;
    public CellUI(Cell cell, int size) {
        this.cell = cell;
        this.setBackground(Color.GRAY);
        this.setSize(size,size);
    }
}
