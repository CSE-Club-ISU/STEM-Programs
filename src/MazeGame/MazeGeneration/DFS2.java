package src.MazeGame.MazeGeneration;

import src.MazeGame.Cell;
import src.MyQueue;

import java.util.Random;

public class DFS2 implements MazeGenAlgorithm {
    @Override
    public int generateMaze(Cell[][] grid, Cell startCell, int visitedValue) {
        int startR = startCell.getRow();
        int startC = startCell.getColumn();
        visitedValue++;
        MyQueue<Cell> stack = new MyQueue<>();
        stack.addFront(grid[startR][startC]);
        stack.peek().visited = visitedValue;
        Cell currentCell = null;
        int currentPathLength = 0;
        Random random = new Random();
        while (!stack.isEmpty()) {
            currentCell = stack.remove();
            if (random.nextInt(grid.length / 2) + 2 < currentPathLength && !currentCell.equals(startCell)) {
                stack.addBack(currentCell);
                currentPathLength = 0;
            }

            if (currentCell.hasUnvisitedNeighbor(visitedValue)) {
                stack.addFront(currentCell);
                currentCell = currentCell.moveToUnvisitedNeighbor(visitedValue, true);
                currentCell.visited = visitedValue;
                currentPathLength++;
                stack.addFront(currentCell);
            } else {
                currentPathLength = 0;
            }
        }
        return visitedValue;
    }

    @Override
    public String getMazeGenerationName() {
        return "Depth-First-Search-2";
    }
}
