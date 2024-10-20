package Programs.BlockEscape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

class BoardPanel extends JPanel {
    BlockEscapeGame blockEscapeGame;
    private int selectedRow;
    private int selectedColumn;
    private Frame frame;

    BoardPanel(Frame frame) {
        this.frame = frame;
        setBackground(Color.DARK_GRAY);
        selectedRow = -1;
        selectedColumn = -1;
        setPreferredSize(new Dimension(1000, 1000));

        addMouseListener(getMouseListener());
        addMouseMotionListener(getMouseMotionListener());
    }

    void setGame(BlockEscapeGame blockEscapeGame) {
        this.blockEscapeGame = blockEscapeGame;
    }

    private MouseListener getMouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int row = blockEscapeGame.getRows() * e.getY() / getHeight();
                int column = blockEscapeGame.getColumns() * e.getX() / getWidth();
                selectedRow = row;
                selectedColumn = column;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectedRow = -1;
                selectedColumn = -1;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
    }

    private MouseMotionListener getMouseMotionListener() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int row = blockEscapeGame.getRows() * e.getY() / getHeight();
                int column = blockEscapeGame.getColumns() * e.getX() / getWidth();

                if (row == selectedRow && column == selectedColumn) return;
                if (blockEscapeGame.getBlockAtCell(selectedColumn, selectedRow) == null) return;

                BlockEscapeGame.Direction direction;
                if (row > selectedRow) {
                    direction = BlockEscapeGame.Direction.Down;
                } else if (row < selectedRow) {
                    direction = BlockEscapeGame.Direction.Up;
                } else if (column > selectedColumn) {
                    direction = BlockEscapeGame.Direction.Right;
                } else if (column < selectedColumn) {
                    direction = BlockEscapeGame.Direction.Left;
                } else {
                    return;
                }
                blockEscapeGame.moveBlockInDirection(blockEscapeGame.getBlockAtCell(selectedColumn, selectedRow), direction);
                selectedRow = row;
                selectedColumn = column;
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cols = blockEscapeGame.getColumns();
        int rows = blockEscapeGame.getRows();
        int blockSize = getHeight() / rows;
        double blockSized = (double) getHeight() / rows;

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.gray);
        g2.fillRect(blockSize, blockSize, getWidth() - blockSize * 2, getHeight() - blockSize * 2);
        g2.setColor(Color.YELLOW);
        g2.fillRect((int) (blockEscapeGame.getEscapeX() * blockSized), (int) (blockEscapeGame.getEscapeY() * blockSized), blockSize + 1, blockSize);
        g2.setStroke(new BasicStroke(Math.min(15, Math.max(1, 60 / rows)), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2.setColor(Color.WHITE);
        for (int i = 1; i < rows; i++) {
            g2.drawLine(0, i * getHeight() / rows, getWidth(), i * getHeight() / rows);
        }
        for (int i = 1; i < cols; i++) {
            g2.drawLine(i * getWidth() / cols, 0, i * getWidth() / cols, getHeight());
        }

        g2.setColor(Color.BLUE);
        for (Block block : blockEscapeGame.getBlocks()) {
            int xPos = block.getX() * getWidth() / cols;
            int yPos = block.getY() * getHeight() / rows;
            if (block.getDirection() == BlockEscapeGame.Direction.Up || block.getDirection() == BlockEscapeGame.Direction.Down) {
                g2.fillRoundRect(xPos, yPos, blockSize, blockSize * block.getLength(), 10, 10);
            } else {
                g2.fillRoundRect(xPos, yPos, blockSize * block.getLength(), blockSize, 10, 10);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        int maxSize = frame.getBounds().height - getParent().getY() - 50;
        return new Dimension(maxSize, maxSize);
    }
}
