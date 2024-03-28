package src.Programs.MazeGame.MazeGoalGeneneration;

import src.Programs.MazeGame.Cell;
import src.MyQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class RandomGoalGen implements MazeGoalGenAlgorithm {
    @Override
    public Cell generateMazeGoal(Cell[][] grid, Cell startCell, AtomicInteger visitedValue, ArrayList<Integer> solutions) {
        int startR = startCell.getRow();
        int startC = startCell.getColumn();
        visitedValue.incrementAndGet();
        MyQueue<Cell> queue = new MyQueue<>();
        MyQueue<Integer> queueDist = new MyQueue<>();
        queue.addBack(grid[startR][startC]);
        queueDist.addBack(0);
        queue.peek().visited = visitedValue.get();
        queue.peek().setParent(null);
        Cell currentCell = null;
        ArrayList<Cell> ends = new ArrayList<>();
        ArrayList<Integer> endsDist = new ArrayList<>();
        int maxEndSize = (int) Math.min(8, Math.max(1, Math.sqrt(grid.length / 2f)));
        while (queue.hasNext()) {
            currentCell = queue.remove();
            Integer dist = queueDist.remove();
            ArrayList<Cell> neighbors = currentCell.getUnvisitedNeighborsWithWalls(visitedValue.get(), false);
            for (int i = 0; i < neighbors.size(); i++) {
                Cell neighborCell = neighbors.get(i);
                neighborCell.visited = visitedValue.get();
                queue.addBack(neighborCell);
                neighborCell.setParent(currentCell);
                queueDist.addBack(dist + 1);
            }
            if (currentCell.getNeighborCount() == 1 && currentCell != startCell) {
                if (ends.size() >= maxEndSize && dist > endsDist.get(0)) {
                    ends.remove(0);
                    endsDist.remove(0);
                }
                if (ends.size() < maxEndSize) {
                    ends.add(currentCell);
                    endsDist.add(dist);
                }
            }
        }
        if (ends.isEmpty()) {
            System.out.println("Failed to create end");
            return null;
        }
        int randomEnd = new Random().nextInt(ends.size());
        Cell endCell = ends.get(randomEnd);

        Cell currentNode = endCell;
        while (currentNode.getParent() != null) {
            if (currentNode.getCellInDir(1) == currentNode.getParent()) {
                currentNode = currentNode.getCellInDir(1);
                solutions.add(-1);
            } else if (currentNode.getCellInDir(2) == currentNode.getParent()) {
                currentNode = currentNode.getCellInDir(2);
                solutions.add(-2);
            } else if (currentNode.getCellInDir(-1) == currentNode.getParent()) {
                currentNode = currentNode.getCellInDir(-1);
                solutions.add(1);
            } else if (currentNode.getCellInDir(-2) == currentNode.getParent()) {
                currentNode = currentNode.getCellInDir(-2);
                solutions.add(2);
            }
        }

        Collections.reverse(solutions);
        return endCell;
    }
}
