package src.MazeGame;

import src.MazeGame.MazeGeneration.DFS;
import src.MazeGame.MazeGeneration.MazeGenAlgorithm;
import src.MazeGame.MazeGoalGeneneration.RandomGoalGen;
import src.MazeGame.MazeGoalGeneneration.MazeGoalGenAlgorithm;
import src.MazeGame.MazeStartGeneration.MazeStartGenAlgorithm;
import src.MazeGame.MazeStartGeneration.RandomStartGen;

import java.util.ArrayList;

public class MazeGame {
    private Cell[][] grid;
    Integer pastVisitedValue;
    Cell startCell;
    Cell endCell;
    String algorithmName;
    ArrayList<Integer> solutionInstructions;

    public MazeGame(int rows, int columns) {
        this(rows,columns, new RandomStartGen(), new DFS(), new RandomGoalGen());
    }

    public MazeGame(int rows, int columns, MazeStartGenAlgorithm startGen, MazeGenAlgorithm mazeGen, MazeGoalGenAlgorithm goalGen) {
        pastVisitedValue = 0;
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

    public void startMazeGame(MazeStartGenAlgorithm startGen, MazeGenAlgorithm mazeGen, MazeGoalGenAlgorithm goalGen) {
        setAllWalls();
        startCell = startGen.generateMazeStart(grid);
        setAllWalls();
        pastVisitedValue = mazeGen.generateMaze(grid, startCell, pastVisitedValue);
        algorithmName = mazeGen.getMazeGenerationName();
        solutionInstructions.clear();
        endCell = goalGen.generateMazeGoal(grid, startCell, pastVisitedValue, solutionInstructions);
    }

    public void setAllWalls() {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                grid[r][c].setAllWalls();
            }
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }
}
