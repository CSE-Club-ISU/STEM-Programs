package src.MazeGame;

import java.util.Random;
import java.util.Stack;

public class MazeGame {
    Cell[][] maze;
    int pastVisitedValue;
    Cell startCell;
    Cell endCell;

    public MazeGame(int rows, int columns) {
        pastVisitedValue = 0;
        maze = new Cell[rows][columns];
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[0].length; c++) {
                maze[r][c] = new Cell(this,r,c);
            }
        }
        generateStartAndEndPosition();
        generateMazeDFS();
    }

    public void setAllWalls() {
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[0].length; c++) {
                maze[r][c].setAllWalls();
            }
        }
    }

    public void generateStartAndEndPosition() {
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

        do {
            side = rand.nextInt(4);
            if (side == 0)
                endCell = maze[0][rand.nextInt(maze[0].length)];
            else if (side == 1)
                endCell = maze[maze.length - 1][rand.nextInt(maze[0].length)];
            else if (side == 2)
                endCell = maze[rand.nextInt(maze.length)][0];
            else
                endCell = maze[rand.nextInt(maze.length)][maze[0].length - 1];
        } while (endCell == startCell);
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
        while(!stack.isEmpty()) {
            currentCell = stack.pop();
            if (currentCell.hasUnvisitedNeighbor(pastVisitedValue)) {
                stack.push(currentCell);
                currentCell = currentCell.moveToUnvistedNeighbor(pastVisitedValue, true);
                currentCell.visited = pastVisitedValue;
                stack.push(currentCell);
            }
        }
    }

    public Cell[][] getMaze() {
        return maze;
    }
}
