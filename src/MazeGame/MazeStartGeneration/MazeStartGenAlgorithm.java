package src.MazeGame.MazeStartGeneration;

import src.MazeGame.Cell;

public interface MazeStartGenAlgorithm {
    /**
     * @return the new starting cell
     */
    Cell generateMazeStart(Cell[][] grid);
}
