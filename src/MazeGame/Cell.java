package src.MazeGame;

import java.util.Random;

public class Cell {
    boolean[] walls; //Left, Up, Down, Right

    public Cell() {
        walls = new boolean[4];
        for (int i = 0; i < 4; i++) {
            walls[i] = new Random().nextBoolean();
        }
    }
}
