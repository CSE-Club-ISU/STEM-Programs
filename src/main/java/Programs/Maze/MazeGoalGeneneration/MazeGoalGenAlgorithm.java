package Programs.Maze.MazeGoalGeneneration;

import Programs.Maze.Cell;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public interface MazeGoalGenAlgorithm {
    /**
     * Generates the goal of the maze
     *
     * @param visitedValue the value of cells that haven't been visited yet.
     * @return the new visited value attached to each cell after the algorithm or null if it couldn't be generated.
     */
    Cell generateMazeGoal(Cell[][] grid, Cell startCell, AtomicInteger visitedValue, ArrayList<Cell.Direction> solutions);
}
