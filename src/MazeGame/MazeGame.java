package src.MazeGame;

import src.MyQueue;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeGame {
    Cell[][] maze;
    int pastVisitedValue;
    Cell startCell;
    Cell endCell;
    int endDistance;

    public MazeGame(int rows, int columns) {
        pastVisitedValue = 0;
        maze = new Cell[rows][columns];
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[0].length; c++) {
                maze[r][c] = new Cell(this, r, c);
            }
        }
        startMazeGame();
    }

    public void startMazeGame() {
        setAllWalls();
        generateStartPosition();
        Random rand = new Random();
        int randMaze = rand.nextInt(3);
        if (randMaze == 0) {
            generateMazePrims();
        } else {
            generateMazeDFS();
        }
        generateEndPosition();
    }

    public void setAllWalls() {
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[0].length; c++) {
                maze[r][c].setAllWalls();
            }
        }
    }

    public void generateStartPosition() {
        Random rand = new Random();
        startCell = maze[rand.nextInt(maze.length)][rand.nextInt(maze[0].length)];
    }

    public void generateStartPositionOnSide() {
        Random rand = new Random();
        int side = rand.nextInt(4);
        if (side == 0)
            startCell = maze[0][rand.nextInt(maze[0].length)];
        else if (side == 1)
            startCell = maze[maze.length - 1][rand.nextInt(maze[0].length)];
        else if (side == 2)
            startCell = maze[rand.nextInt(maze.length)][0];
        else
            startCell = maze[rand.nextInt(maze.length)][maze[0].length - 1];
    }


    public void generateEndPosition() {
        int startR = startCell.r;
        int startC = startCell.c;
        pastVisitedValue++;
        MyQueue<Cell> queue = new MyQueue<>();
        MyQueue<Integer> queueDist = new MyQueue<>();
        queue.add(maze[startR][startC]);
        queueDist.add(0);
        queue.peek().visited = pastVisitedValue;
        Cell currentCell = null;
        ArrayList<Cell> ends = new ArrayList<>();
        ArrayList<Integer> endsDist = new ArrayList<>();
        int maxEndSize = (int) Math.min(20, Math.max(1, 2 * maze.length / 5f));
        while (queue.hasNext()) {
            currentCell = queue.remove();
            Integer dist = queueDist.remove();
            ArrayList<Cell> neighbors = currentCell.getUnvisitedNeighborsWithWalls(pastVisitedValue, false);
            for (int i = 0; i < neighbors.size(); i++) {
                neighbors.get(i).visited = pastVisitedValue;
                queue.add(neighbors.get(i));
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
    }


    public void generateMazeDFS() {
        int startR = startCell.r;
        int startC = startCell.c;
        pastVisitedValue++;
        setAllWalls();
        Stack<Cell> stack = new Stack<>();
        stack.push(maze[startR][startC]);
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
        pastVisitedValue++;
        setAllWalls();
        ArrayList<Cell> toAdd = new ArrayList<>();
        toAdd.add(startCell);

        Random rand = new Random();
        while (!toAdd.isEmpty()) {
            int random = rand.nextInt(toAdd.size());
            int nextIndex = (int)Math.sqrt(random);
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

    public Cell[][] getMaze() {
        return maze;
    }
}
