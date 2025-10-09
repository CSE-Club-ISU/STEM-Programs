package Programs.BlockEscape;

class Block {
    private int x;
    private int y;
    private final BlockEscapeGame.Direction direction;
    private final int length;
    private boolean finalBlock;

    public Block(int x, int y, BlockEscapeGame.Direction direction, int length, boolean finalBlock) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.length = length;
        this.finalBlock = finalBlock;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BlockEscapeGame.Direction getDirection() {
        return direction;
    }

    public int getLength() {
        return length;
    }

    public boolean isFinalBlock() {
        return finalBlock;
    }

    public boolean contains(int x, int y) {
        if (direction == BlockEscapeGame.Direction.Up || direction == BlockEscapeGame.Direction.Down) {
            return this.x == x && this.y <= y && this.y + length - 1 >= y;
        } else {
            return this.x <= x && this.x + length - 1 >= x && this.y == y;
        }
    }
}
