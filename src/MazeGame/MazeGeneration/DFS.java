package src.MazeGame.MazeGeneration;

import src.MazeGame.Cell;

import java.util.Stack;

public class DFS implements MazeGenAlgorithm{
    @Override
    public int generateMaze(Cell[][] grid, Cell startCell, int visitedValue) {
        int startR = startCell.getRow();
        int startC = startCell.getColumn();
        visitedValue++;
        Stack<Cell> stack = new Stack<>();
        stack.push(grid[startR][startC]);
        stack.peek().visited = visitedValue;
        Cell currentCell = null;
        while (!stack.isEmpty()) {
            currentCell = stack.pop();
            if (currentCell.hasUnvisitedNeighbor(visitedValue)) {
                stack.push(currentCell);
                currentCell = currentCell.moveToUnvisitedNeighbor(visitedValue, true);
                currentCell.visited = visitedValue;
                stack.push(currentCell);
            }
        }
        return visitedValue;
    }

    @Override
    public String getMazeGenerationName() {
        return "Depth-First-Search";
    }
}
