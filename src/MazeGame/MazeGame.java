package src.MazeGame;

public class MazeGame {
    Cell[][] maze;

    public MazeGame(int rows, int columns) {
        maze = new Cell[rows][columns];
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[0].length; c++) {
                maze[r][c] = new Cell();
            }
        }
    }

    public Cell[][] getMaze() {
        return maze;
    }
}
