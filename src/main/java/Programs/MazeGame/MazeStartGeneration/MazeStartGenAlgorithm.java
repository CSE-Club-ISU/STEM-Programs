package Programs.MazeGame.MazeStartGeneration;

import Programs.MazeGame.Cell;

public interface MazeStartGenAlgorithm {
    /**
     * @return the new starting cell
     */
    Cell generateMazeStart(Cell[][] grid);
}
