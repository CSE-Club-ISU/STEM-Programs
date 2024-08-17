package Programs.MazeGame.MazeGeneration;

import Programs.MazeGame.Cell;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Prims implements MazeGenAlgorithm {
    @Override
    public void generateMaze(Cell[][] grid, Cell startCell, AtomicInteger visitedValue) {
        visitedValue.incrementAndGet();
        ArrayList<Cell> toAdd = new ArrayList<>();
        toAdd.add(startCell);

        Random rand = new Random();
        while (!toAdd.isEmpty()) {
            int random = rand.nextInt(toAdd.size() / 2, toAdd.size());
            int nextIndex = random;
            Cell currentCell = toAdd.get(nextIndex);
            toAdd.remove(nextIndex);
            currentCell.moveToVisitedNeighbor(visitedValue.get(), true);
            ArrayList<Cell> neighbors = currentCell.getUnvisitedNeighborsWithWalls(visitedValue.get(), true);
            for (int n = 0; n < neighbors.size(); n++) {
                if (!neighbors.get(n).hasVisitedNeighbor(visitedValue.get())) {
                    toAdd.add(neighbors.get(n));
                }
            }
            currentCell.visited = visitedValue.get();
        }
    }

    @Override
    public String getMazeGenerationName() {
        return "Prims";
    }
}
