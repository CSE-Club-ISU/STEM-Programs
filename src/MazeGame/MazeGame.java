package src.MazeGame;

import java.util.Stack;

public class MazeGame {
    Cell[][] maze;
    int pastVisitedValue;

    public MazeGame(int rows, int columns) {
        pastVisitedValue = 0;
        maze = new Cell[rows][columns];
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[0].length; c++) {
                maze[r][c] = new Cell(this,r,c);
            }
        }
        generateMazeDFS(0,0);
    }

    public void setAllWalls() {
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[0].length; c++) {
                maze[r][c].setAllWalls();
            }
        }
    }


    public void generateMazeDFS(int startR, int startC) {
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
