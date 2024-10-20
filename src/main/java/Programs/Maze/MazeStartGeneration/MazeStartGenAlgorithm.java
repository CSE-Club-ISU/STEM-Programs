package Programs.Maze.MazeStartGeneration;

import Programs.Maze.Cell;

public interface MazeStartGenAlgorithm {
    /**
     * @return the new starting cell
     */
    Cell generateMazeStart(Cell[][] grid);
}
