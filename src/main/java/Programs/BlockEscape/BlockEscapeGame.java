package Programs.BlockEscape;

import java.util.ArrayList;

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

    BlockEscapeGame(int rows, int columns) {
        blocks = new ArrayList<>();
        blocks.add(new Block(1,4, Direction.Left, 2));
        blocks.add(new Block(4,1, Direction.Up, 3));
        escapeX = rows - 1;
        escapeY = 4;
        this.rows = rows;
        this.columns = columns;
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

        //TODO: Add bounds checking
        //TODO: Check for block collision

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
