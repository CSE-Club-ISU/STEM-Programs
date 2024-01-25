package src.MazeGame;

import src.MyQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class MazeGame {
    private Cell[][] grid;
    int pastVisitedValue;
    Cell startCell;
    Cell endCell;
    int endDistance;
    String algorithmName;
    ArrayList<Integer> solutionInstructions;

    public MazeGame(int rows, int columns) {
        pastVisitedValue = 0;
        grid = new Cell[rows][columns];
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                grid[r][c] = new Cell(this, r, c);
            }
        }
        algorithmName = "None";
        startMazeGame();
    }

    public void startMazeGame() {
        setAllWalls();
        generateStartPosition();
        Random rand = new Random();
        int randMaze = rand.nextInt(4) + 1;
        if (randMaze == 0) {
            generateMazePrims();
        } else {
            generateMazeDFS();
        }
        generateEndPosition();
    }

    public void setAllWalls() {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                grid[r][c].setAllWalls();
            }
        }
    }

    public void generateStartPosition() {
        Random rand = new Random();
        startCell = grid[rand.nextInt(grid.length)][rand.nextInt(grid[0].length)];
    }

    public void generateStartPositionOnSide() {
        Random rand = new Random();
        int side = rand.nextInt(4);
        if (side == 0)
            startCell = grid[0][rand.nextInt(grid[0].length)];
        else if (side == 1)
            startCell = grid[grid.length - 1][rand.nextInt(grid[0].length)];
        else if (side == 2)
            startCell = grid[rand.nextInt(grid.length)][0];
        else
            startCell = grid[rand.nextInt(grid.length)][grid[0].length - 1];
    }


    public void generateEndPosition() {
//        for (Cell[] row: maze) {
//            for (Cell cell: row) {
//                cell.parent = null;
//            }
//        }
        int startR = startCell.r;
        int startC = startCell.c;
        pastVisitedValue++;
        MyQueue<Cell> queue = new MyQueue<>();
        MyQueue<Integer> queueDist = new MyQueue<>();
        queue.add(grid[startR][startC]);
        queueDist.add(0);
        queue.peek().visited = pastVisitedValue;
        queue.peek().parent = null;
        Cell currentCell = null;
        ArrayList<Cell> ends = new ArrayList<>();
        ArrayList<Integer> endsDist = new ArrayList<>();
        int maxEndSize = (int) Math.min(20, Math.max(1, 2 * grid.length / 5f));
        while (queue.hasNext()) {
            currentCell = queue.remove();
            Integer dist = queueDist.remove();
            ArrayList<Cell> neighbors = currentCell.getUnvisitedNeighborsWithWalls(pastVisitedValue, false);
            for (int i = 0; i < neighbors.size(); i++) {
                Cell neighborCell = neighbors.get(i);
                neighborCell.visited = pastVisitedValue;
                queue.add(neighborCell);
                neighborCell.parent = currentCell;
                queueDist.add(dist + 1);
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
            return;
        }
        int randomEnd = new Random().nextInt(ends.size());
        endCell = ends.get(randomEnd);
        endDistance = endsDist.get(randomEnd);
        System.out.println("Distance from start to end: " + endDistance);

        solutionInstructions = new ArrayList<>(100);
        Cell currentNode = endCell;
        while(currentNode.parent != null) {
            if (currentNode.getCellInDir(1) == currentNode.parent) {
                currentNode = currentNode.getCellInDir(1);
                solutionInstructions.add(-1);
            } else if (currentNode.getCellInDir(2) == currentNode.parent) {
                currentNode = currentNode.getCellInDir(2);
                solutionInstructions.add(-2);
            } else if (currentNode.getCellInDir(-1) == currentNode.parent) {
                currentNode = currentNode.getCellInDir(-1);
                solutionInstructions.add(1);
            }else if (currentNode.getCellInDir(-2) == currentNode.parent) {
                currentNode = currentNode.getCellInDir(-2);
                solutionInstructions.add(2);
            }
        }
        Collections.reverse(solutionInstructions);
    }


    public void generateMazeDFS() {
        algorithmName = "Depth-First-Search";
        int startR = startCell.r;
        int startC = startCell.c;
        pastVisitedValue++;
        setAllWalls();
        Stack<Cell> stack = new Stack<>();
        stack.push(grid[startR][startC]);
        stack.peek().visited = pastVisitedValue;
        Cell currentCell = null;
        while (!stack.isEmpty()) {
            currentCell = stack.pop();
            if (currentCell.hasUnvisitedNeighbor(pastVisitedValue)) {
                stack.push(currentCell);
                currentCell = currentCell.moveToUnvistedNeighbor(pastVisitedValue, true);
                currentCell.visited = pastVisitedValue;
                stack.push(currentCell);
            }
        }
    }

    public void generateMazePrims() {
        algorithmName = "Prims";
        pastVisitedValue++;
        setAllWalls();
        ArrayList<Cell> toAdd = new ArrayList<>();
        toAdd.add(startCell);

        Random rand = new Random();
        while (!toAdd.isEmpty()) {
            int random = rand.nextInt(toAdd.size() / 2,toAdd.size());
            int nextIndex = random;
            Cell currentCell = toAdd.get(nextIndex);
            toAdd.remove(nextIndex);
            currentCell.moveToVisitedNeighbor(pastVisitedValue, true);
            ArrayList<Cell> neighbors = currentCell.getUnvisitedNeighborsWithWalls(pastVisitedValue, true);
            for (int n = 0; n < neighbors.size(); n++) {
                if(!neighbors.get(n).hasVisitedNeighbor(pastVisitedValue)) {
                    toAdd.add(neighbors.get(n));
                }
            }
            currentCell.visited = pastVisitedValue;
        }

        generateEndPosition();
        startCell = endCell;
        endCell = null;
    }

    public Cell[][] getGrid() {
        return grid;
    }
}
