package src.MazeGame.MazeGeneration;

import src.MazeGame.Cell;

public interface MazeGenAlgorithm {
    /**
     * Generates the maze by removing walls from cells.
     * @param visitedValue the value of cells that haven't been visited yet.
     * @return the name of the algorithm to display.
     */
    int generateMaze(Cell[][] grid, Cell startCell, int visitedValue);

    String getMazeGenerationName();
}
