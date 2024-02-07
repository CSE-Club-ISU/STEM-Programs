package src.MazeGame.MazeGeneration;

import src.MazeGame.Cell;

import java.util.concurrent.atomic.AtomicInteger;

public interface MazeGenAlgorithm {
    /**
     * Generates the maze by removing walls from cells.
     * @param visitedValue the value of cells that haven't been visited yet.
     */
    void generateMaze(Cell[][] grid, Cell startCell, AtomicInteger visitedValue);

    String getMazeGenerationName();
}
