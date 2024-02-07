package src.MazeGame.MazeGeneration;

import src.MazeGame.Cell;

import java.util.ArrayList;
import java.util.Random;

public class Prims implements MazeGenAlgorithm {
    @Override
    public int generateMaze(Cell[][] grid, Cell startCell, int visitedValue) {
        visitedValue++;
        ArrayList<Cell> toAdd = new ArrayList<>();
        toAdd.add(startCell);

        Random rand = new Random();
        while (!toAdd.isEmpty()) {
            int random = rand.nextInt(toAdd.size() / 2, toAdd.size());
            int nextIndex = random;
            Cell currentCell = toAdd.get(nextIndex);
            toAdd.remove(nextIndex);
            currentCell.moveToVisitedNeighbor(visitedValue, true);
            ArrayList<Cell> neighbors = currentCell.getUnvisitedNeighborsWithWalls(visitedValue, true);
            for (int n = 0; n < neighbors.size(); n++) {
                if (!neighbors.get(n).hasVisitedNeighbor(visitedValue)) {
                    toAdd.add(neighbors.get(n));
                }
            }
            currentCell.visited = visitedValue;
        }
        return visitedValue;
    }

    @Override
    public String getMazeGenerationName() {
        return "Prims";
    }
}
