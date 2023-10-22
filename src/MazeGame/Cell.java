package src.MazeGame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Cell {
    MazeGame mazeGame;
    boolean[] walls; //Left, Up, Down, Right
    boolean[] visitedSides;
    public int visited;
    int r, c;

    public Cell(MazeGame mazeGame, int r, int c) {
        this.mazeGame = mazeGame;
        this.r = r;
        this.c = c;
        walls = new boolean[4];
        visitedSides = new boolean[4];
        setAllWalls();
    }

    public void setAllWalls() {
        visited = 0;
        for (int i = 0; i < 4; i++) {
            walls[i] = true;
            visitedSides[i] = false;
        }
    }

    public Cell moveToUnvistedNeighbor(int visitedInt, boolean removeWalls) {
        ArrayList<Cell> unvisited = new ArrayList(4);
        ArrayList<Integer> unvisitedDir = new ArrayList(4);
        for (int i = -2; i <= 2; i++) {
            if (i == 0) continue;
            Cell targetCell = getCellInDir(i);

            if (targetCell != null && targetCell.visited != visitedInt) {
                unvisited.add(targetCell);
                unvisitedDir.add(i);
            }
        }
        int index = new Random().nextInt(0,unvisited.size());
        Cell targetCell = unvisited.get(index);
        int cellDir = unvisitedDir.get(index);
        if (targetCell == null)
            throw new IllegalStateException("Called chooseUnvistedNeighbor with no unvisted neighbors.");
        if (removeWalls) {
            int dir = convertDirectionToIndex(cellDir);
            walls[dir] = false;

            dir = convertDirectionToIndex(-cellDir);
            targetCell.walls[dir] = false;
        }
        return targetCell;
    }

    public int getNeighborCount() {
        int count = 0;
        for (int i = 0; i < walls.length; i++) {
            if (!walls[i]) {
                count++;
            }
        }
        return count;
    }

    public boolean hasUnvisitedNeighbor(int visitedInt) {
        for (int i = -2; i <= 2; i++) {
            if (i == 0) continue;
            Cell targetCell = getCellInDir(i);
            if (targetCell != null && targetCell.visited != visitedInt)
                return true;
        }
        return false;
    }

    public ArrayList<Cell> getUnvisitedNeighborsWithWalls(int visitedInt) {
        ArrayList<Cell> unvisitedNeighbors = new ArrayList<>(4);
        for (int i = -2; i <= 2; i++) {
            if (i == 0) continue;
            if (walls[convertDirectionToIndex(i)]) continue;
            Cell targetCell = getCellInDir(i);
            if (targetCell != null && targetCell.visited != visitedInt)
                unvisitedNeighbors.add(targetCell);
        }
        return unvisitedNeighbors;
    }

    /**
     * @param dir 1=down, 2=right, -1=up, -2=left
     */
    Cell getCellInDir(int dir) {
        if (dir == 1 && r != mazeGame.maze.length - 1) {
            return mazeGame.maze[r+1][c];
        } else if (dir == 2 && c != mazeGame.maze[0].length - 1) {
            return mazeGame.maze[r][c+1];
        } else if (dir == -1 && r != 0) {
            return mazeGame.maze[r-1][c];
        } else if (dir == -2 && c != 0) {
            return mazeGame.maze[r][c-1];
        }
        return null;
    }

    int convertDirectionToIndex(int dir) {
        if (dir < 0) dir += 2;
        else dir += 1;
        return dir;
    }

    public boolean isStartCell() {
        return mazeGame.startCell == this;
    }

    public boolean isEndCell() {
        return mazeGame.endCell == this;
    }
}
