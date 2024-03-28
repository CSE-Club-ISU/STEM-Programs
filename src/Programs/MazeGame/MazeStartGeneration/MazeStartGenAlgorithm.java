package src.Programs.MazeGame.MazeStartGeneration;

import src.Programs.MazeGame.Cell;

public interface MazeStartGenAlgorithm {
    /**
     * @return the new starting cell
     */
    Cell generateMazeStart(Cell[][] grid);
}
