package Programs.BlockEscape;

import java.util.ArrayList;
import java.util.function.Consumer;

class BlockEscapeGame {
    public enum Direction {
        Up,
        Down,
        Left,
        Right,
    }

    private final ArrayList<Block> blocks;
    private final int rows;
    private final int columns;
    private final int escapeX;
    private final int escapeY;
    Consumer<Integer> onwin;


    BlockEscapeGame(int rows, int columns, Consumer<Integer> onWin) {
        blocks = new ArrayList<>();
        blocks.add(new Block(1,4, Direction.Left, 2, true));
        blocks.add(new Block(4,2, Direction.Up, 3, false));
        blocks.add(new Block(2,1, Direction.Left, 3, false));
        blocks.add(new Block(1,1, Direction.Up, 3, false));
        blocks.add(new Block(3,2, Direction.Up, 3, false));
        blocks.add(new Block(6,1, Direction.Left, 3, false));
        blocks.add(new Block(7,6, Direction.Up, 2, false));
        blocks.add(new Block(4,7, Direction.Up, 2, false));
        blocks.add(new Block(5,2, Direction.Left, 2, false));
        blocks.add(new Block(1,5, Direction.Left, 4, false));
        blocks.add(new Block(1,7, Direction.Left, 3, false));
        blocks.add(new Block(5,6, Direction.Up, 3, false));
        blocks.add(new Block(8,2, Direction.Up, 2, false));
        escapeX = rows - 1;
        escapeY = 4;
        this.rows = rows;
        this.columns = columns;
        this.onwin = onWin;
    }


    public Block getBlockAtCell(int x, int y) {
        return blocks.stream().filter(b -> b.contains(x, y)).findFirst().orElse(null);
    }

    public void moveBlockInDirection(Block block, Direction direction) {
        // Check if the block can move in the direction given
        if ((direction == Direction.Up || direction == Direction.Down) && (block.getDirection() == Direction.Left || block.getDirection() == Direction.Right))
            return;
        if ((direction == Direction.Left || direction == Direction.Right) && (block.getDirection() == Direction.Up || block.getDirection() == Direction.Down))
            return;

        int x = block.getX();
        int y = block.getY();

        switch (direction) {
            case Up -> y--;
            case Down -> y += block.getLength();
            case Left -> x--;
            case Right -> x += block.getLength();
        }

        if (getBlockAtCell(x, y) != null) {
            return;
        } else if (x == escapeX && y == escapeY) {
            //Game won!
            onwin.accept(1);
        } else if (x < 1 || y < 1 || x >= columns - 1 || y >= rows - 1) {
            return;
        }

        switch (direction) {
            case Up -> block.setY(block.getY() - 1);
            case Down -> block.setY(block.getY() + 1);
            case Left -> block.setX(block.getX() - 1);
            case Right -> block.setX(block.getX() + 1);
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public int getEscapeX() {
        return escapeX;
    }

    public int getEscapeY() {
        return escapeY;
    }
}
