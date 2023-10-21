package src.MazeGame;

public class MazeGame {
    Cell[][] maze;

    public MazeGame(int rows, int columns) {
        maze = new Cell[rows][columns];

    }

    public Cell[][] getMaze() {
        return maze;
    }
}
