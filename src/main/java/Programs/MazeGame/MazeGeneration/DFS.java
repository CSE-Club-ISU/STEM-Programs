package Programs.MazeGame.MazeGeneration;

import Programs.MazeGame.Cell;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class DFS implements MazeGenAlgorithm{
    @Override
    public void generateMaze(Cell[][] grid, Cell startCell, AtomicInteger visitedValue) {
        int startR = startCell.getRow();
        int startC = startCell.getColumn();
        visitedValue.incrementAndGet();
        Stack<Cell> stack = new Stack<>();
        stack.push(grid[startR][startC]);
        stack.peek().visited = visitedValue.get();
        Cell currentCell = null;
        while (!stack.isEmpty()) {
            currentCell = stack.pop();
            if (currentCell.hasUnvisitedNeighbor(visitedValue.get())) {
                stack.push(currentCell);
                currentCell = currentCell.moveToUnvisitedNeighbor(visitedValue.get(), true);
                currentCell.visited = visitedValue.get();
                stack.push(currentCell);
            }
        }
    }

    @Override
    public String getMazeGenerationName() {
        return "Depth-First-Search";
    }
}
