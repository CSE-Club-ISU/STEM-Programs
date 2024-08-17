package Programs.MazeGame.MazeGeneration;

import Programs.MazeGame.Cell;
import Utils.MyQueue;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class DFS2 implements MazeGenAlgorithm {
    @Override
    public void generateMaze(Cell[][] grid, Cell startCell, AtomicInteger visitedValue) {
        int startR = startCell.getRow();
        int startC = startCell.getColumn();
        visitedValue.incrementAndGet();
        MyQueue<Cell> stack = new MyQueue<>();
        stack.addFront(grid[startR][startC]);
        stack.peek().visited = visitedValue.get();
        Cell currentCell = null;
        int currentPathLength = 0;
        Random random = new Random();
        while (!stack.isEmpty()) {
            currentCell = stack.remove();
            if (random.nextInt(grid.length / 2) + 2 < currentPathLength && !currentCell.equals(startCell)) {
                stack.addBack(currentCell);
                currentPathLength = 0;
            }

            if (currentCell.hasUnvisitedNeighbor(visitedValue.get())) {
                if (!currentCell.isStartCell())
                    stack.addBack(currentCell);
                currentCell = currentCell.moveToUnvisitedNeighbor(visitedValue.get(), true);
                currentCell.visited = visitedValue.get();
                currentPathLength++;
                stack.addFront(currentCell);
            } else {
                currentPathLength = 0;
            }
        }
    }

    @Override
    public String getMazeGenerationName() {
        return "Depth-First-Search-2";
    }
}
