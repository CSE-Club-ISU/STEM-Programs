package src.MazeGame;

import java.util.ArrayList;
import java.util.Random;

public class Cell {
    MazeGame mazeGame;
    CellUI cellUI;
    boolean[] walls; //Left, Up, Down, Right
    boolean[] visitedSides;
    public int visited;
    int r, c;
    Cell parent;

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

    public Cell moveToUnvisitedNeighbor(int visitedInt, boolean removeWalls) {
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
        if (unvisited.isEmpty()) return null;
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

    public Cell moveToVisitedNeighbor(int visitedInt, boolean removeWalls) {
        ArrayList<Cell> visited = new ArrayList(4);
        ArrayList<Integer> unvisitedDir = new ArrayList(4);
        for (int i = -2; i <= 2; i++) {
            if (i == 0) continue;
            Cell targetCell = getCellInDir(i);

            if (targetCell != null && targetCell.visited == visitedInt) {
                visited.add(targetCell);
                unvisitedDir.add(i);
            }
        }
        if (visited.isEmpty()) return null;
        int index = new Random().nextInt(0,visited.size());
        Cell targetCell = visited.get(index);
        int cellDir = unvisitedDir.get(index);
        if (targetCell == null)
            throw new IllegalStateException("Called chooseVistedNeighbor with no visited neighbors.");
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

    public boolean hasVisitedNeighbor(int visitedInt) {
        for (int i = -2; i <= 2; i++) {
            if (i == 0) continue;
            Cell targetCell = getCellInDir(i);
            if (targetCell != null && targetCell.visited == visitedInt)
                return true;
        }
        return false;
    }

    public ArrayList<Cell> getUnvisitedNeighborsWithWalls(int visitedInt, boolean inverse) {
        ArrayList<Cell> unvisitedNeighbors = new ArrayList<>(4);
        for (int i = -2; i <= 2; i++) {
            if (i == 0) continue;
            if ((!inverse && walls[convertDirectionToIndex(i)]) || (inverse && !walls[convertDirectionToIndex(i)])) continue;
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
        Cell[][] grid = mazeGame.getGrid();
        if (dir == 1 && r != grid.length - 1) {
            return grid[r+1][c];
        } else if (dir == 2 && c != grid[0].length - 1) {
            return grid[r][c+1];
        } else if (dir == -1 && r != 0) {
            return grid[r-1][c];
        } else if (dir == -2 && c != 0) {
            return grid[r][c-1];
        }
        return null;
    }

    /**
     * @param dir 1=down, 2=right, -1=up, -2=left
     */
    boolean hasWallInDirection(int dir) {
        return walls[convertDirectionToIndex(dir)];
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
