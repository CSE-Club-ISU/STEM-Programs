package Programs.Maze.MazeStartGeneration;

import Programs.Maze.Cell;

import java.util.Random;

public class RandomStartGen implements MazeStartGenAlgorithm {
    @Override
    public Cell generateMazeStart(Cell[][] grid) {
        Random rand = new Random();
        Cell startCell = grid[rand.nextInt(grid.length)][rand.nextInt(grid[0].length)];
        return startCell;
    }
}
