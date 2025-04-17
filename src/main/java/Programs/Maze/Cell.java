package Programs.Maze;

import java.util.ArrayList;
import java.util.Random;

public class Cell {
    MazeGame mazeGame;
    boolean[] walls;
    boolean[] visitedSides;
    public int visited;
    private final int r, c;
    private Cell parent;

    public enum Direction {
        Up,
        Down,
        Left,
        Right,
    }

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
        ArrayList<Cell> unvisited = new ArrayList<>(4);
        ArrayList<Direction> unvisitedDir = new ArrayList<>(4);
        for (Direction dir : Direction.values()) {
            Cell targetCell = getCellInDir(dir);

            if (targetCell != null && targetCell.visited != visitedInt) {
                unvisited.add(targetCell);
                unvisitedDir.add(dir);
            }
        }

        if (unvisited.isEmpty()) return null;
        int index = new Random().nextInt(0, unvisited.size());
        Cell targetCell = unvisited.get(index);
        Direction cellDir = unvisitedDir.get(index);
        if (targetCell == null)
            throw new IllegalStateException("Called chooseUnvistedNeighbor with no unvisted neighbors.");
        if (removeWalls) {
            walls[cellDir.ordinal()] = false;
            targetCell.walls[getOppositeDir(cellDir).ordinal()] = false;
        }
        return targetCell;
    }

    public Cell moveToVisitedNeighbor(int visitedInt, boolean removeWalls) {
        ArrayList<Cell> visited = new ArrayList(4);
        ArrayList<Direction> unvisitedDir = new ArrayList(4);
        for (Direction dir : Direction.values()) {
            Cell targetCell = getCellInDir(dir);

            if (targetCell != null && targetCell.visited == visitedInt) {
                visited.add(targetCell);
                unvisitedDir.add(dir);
            }
        }

        if (visited.isEmpty()) return null;
        int index = new Random().nextInt(0, visited.size());
        Cell targetCell = visited.get(index);
        Direction cellDir = unvisitedDir.get(index);
        if (targetCell == null)
            throw new IllegalStateException("Called chooseVistedNeighbor with no visited neighbors.");
        if (removeWalls) {
            walls[cellDir.ordinal()] = false;
            targetCell.walls[getOppositeDir(cellDir).ordinal()] = false;
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
        for (Direction dir : Direction.values()) {
            Cell targetCell = getCellInDir(dir);
            if (targetCell != null && targetCell.visited != visitedInt)
                return true;
        }
        return false;
    }

    public boolean hasVisitedNeighbor(int visitedInt) {
        for (Direction dir : Direction.values()) {
            Cell targetCell = getCellInDir(dir);
            if (targetCell != null && targetCell.visited == visitedInt)
                return true;
        }
        return false;
    }

    public ArrayList<Cell> getUnvisitedNeighborsWithWalls(int visitedInt, boolean inverse) {
        ArrayList<Cell> unvisitedNeighbors = new ArrayList<>(4);
        for (Direction dir : Direction.values()) {
            if ((!inverse && walls[dir.ordinal()]) || (inverse && !walls[dir.ordinal()]))
                continue;
            Cell targetCell = getCellInDir(dir);
            if (targetCell != null && targetCell.visited != visitedInt)
                unvisitedNeighbors.add(targetCell);
        }
        return unvisitedNeighbors;
    }

    /**
     * @param dir 1=down, 2=right, -1=up, -2=left
     */
    public Cell getCellInDir(Direction dir) {
        Cell[][] grid = mazeGame.getGrid();
        switch (dir) {
            case Up -> {
                if (r == 0) return null;
                return grid[r - 1][c];
            }
            case Down -> {
                if (r == grid.length - 1) return null;
                return grid[r + 1][c];
            }
            case Left -> {
                if (c == 0) return null;
                return grid[r][c - 1];
            }
            case Right -> {
                if (c == grid[0].length - 1) return null;
                return grid[r][c + 1];
            }
        }
        return null;
    }

    boolean hasWallInDirection(Direction dir) {
        return walls[dir.ordinal()];
    }

    int convertDirectionToIndex(int dir) {
        if (dir < 0) dir += 2;
        else dir += 1;
        return dir;
    }

    public static Direction getOppositeDir(Direction direction) {
        return switch (direction) {
            case Up -> Direction.Down;
            case Down -> Direction.Up;
            case Left -> Direction.Right;
            case Right -> Direction.Left;
        };
    }

    public boolean isStartCell() {
        return mazeGame.getStartCell() == this;
    }

    public boolean isEndCell() {
        return mazeGame.getEndCell() == this;
    }

    public int getRow() {
        return r;
    }

    public int getColumn() {
        return c;
    }

    public Cell getParent() {
        return parent;
    }

    public void setParent(Cell parent) {
        this.parent = parent;
    }
}
