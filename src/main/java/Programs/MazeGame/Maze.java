package Programs.MazeGame;

import Programs.MazeGame.MazeGeneration.DFS;
import Programs.MazeGame.MazeGeneration.DFS2;
import Programs.MazeGame.MazeGeneration.MazeGenAlgorithm;
import Programs.MazeGame.MazeGeneration.Prims;
import Programs.MazeGame.MazeGoalGeneneration.RandomGoalGen;
import Programs.MazeGame.MazeGoalGeneneration.MazeGoalGenAlgorithm;
import Programs.MazeGame.MazeStartGeneration.MazeStartGenAlgorithm;
import Programs.MazeGame.MazeStartGeneration.RandomStartGen;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

 class Maze {
    private Cell[][] grid;
    /** pastVisitedValue relates to [cell.visited] and helps us determine if we have seen a cell before.
     * It is an AtomicInteger so that we can pass in a reference to it and modify it in the generation algorithms.
     */
    AtomicInteger pastVisitedValue;
    Cell startCell;
    Cell endCell;
    String algorithmName;
    ArrayList<Integer> solutionInstructions;

    /**
     * Initializes the maze game with random generation
     * @param rows the number of rows in the grid
     * @param columns the number of columns in the grid
     */
     Maze(int rows, int columns) {
        this(rows,columns, new RandomStartGen(), getRandomMazeGenerationAlgorithm(), new RandomGoalGen());
    }

    /**
     * Initializes the maze game with the given generation parameters using dependency injection.
     * We are employing the strategy pattern in order to determine the type of generation to use.
     * @param startGen the algorithm that determines the start cell
     * @param mazeGen the maze generation algorithm that remove the walls
     * @param goalGen the algorithm that determines the end cell
     * @param rows the number of rows in the grid
     * @param columns the number of columns in the grid
     */
     Maze(int rows, int columns, MazeStartGenAlgorithm startGen, MazeGenAlgorithm mazeGen, MazeGoalGenAlgorithm goalGen) {
        pastVisitedValue = new AtomicInteger(0);
        grid = new Cell[rows][columns];
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                grid[r][c] = new Cell(this, r, c);
            }
        }
        algorithmName = "None";
        solutionInstructions = new ArrayList<>();
        startMazeGame(startGen, mazeGen, goalGen);
    }

    private static MazeGenAlgorithm getRandomMazeGenerationAlgorithm() {
        int algorithm = new Random().nextInt(8);
        if (algorithm == 0) {
            return new Prims();
        } else if (algorithm <= 2) {
            return new DFS();
        } else {
            return new DFS2();
        }
    }

     void startMazeGame() {
        startMazeGame(new RandomStartGen(), getRandomMazeGenerationAlgorithm(), new RandomGoalGen());
    }

     void startMazeGame(MazeStartGenAlgorithm startGen, MazeGenAlgorithm mazeGen, MazeGoalGenAlgorithm goalGen) {
        setAllWalls();
        startCell = startGen.generateMazeStart(grid);
        mazeGen.generateMaze(grid, startCell, pastVisitedValue);
        algorithmName = mazeGen.getMazeGenerationName();
        solutionInstructions.clear();
        endCell = goalGen.generateMazeGoal(grid, startCell, pastVisitedValue, solutionInstructions);
        System.out.println("Distance from start to end: " + solutionInstructions.size());
    }

     void setAllWalls() {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                grid[r][c].setAllWalls();
            }
        }
    }

     Cell[][] getGrid() {
        return grid;
    }
}
