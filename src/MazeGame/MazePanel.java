package src.MazeGame;

import src.Frame;
import src.UIUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MazePanel extends JPanel {
    JLabel title;
    MazeGame mazeGame;
    InstructionPanel instructionPanel;
    Panel mazeDisplay;
    CellUI[][] mazeUI;
    JTextField sizeInput;
    public MazePanel(Frame frame) {
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);
        mazeUI = new CellUI[10][10];
        title = UIUtils.addTextToComp("Maze Game", this);
        Box top = Box.createHorizontalBox();

        JButton regenerateButton = new JButton("Regenerate");
        regenerateButton.setVerticalTextPosition(AbstractButton.CENTER);
        regenerateButton.setAlignmentX(CENTER_ALIGNMENT);
        regenerateButton.addActionListener((l) -> {
            frame.requestFocusInWindow();
            regenerateMaze();
        });
        regenerateButton.setBackground(Color.BLUE);
        regenerateButton.setForeground(Color.white);
        regenerateButton.setFocusPainted(false);
        regenerateButton.setFocusable(false);
        regenerateButton.setBorder(new EmptyBorder(10, 10, 10, 10));
        top.add(regenerateButton);

        sizeInput = new JTextField(Integer.toString(mazeUI.length));
        sizeInput.setMaximumSize(new Dimension(100, 30));
        sizeInput.setBorder(new EmptyBorder(10, 10, 10, 10));
        top.add(sizeInput);
        add(top);

        Box mazeInstructionPanel = Box.createHorizontalBox();
        mazeInstructionPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mazeDisplay = new Panel();
        mazeDisplay.setBackground(Color.LIGHT_GRAY);
        mazeDisplay.setMaximumSize(new Dimension(700, 700));
        mazeDisplay.setMinimumSize(new Dimension(700, 700));
        mazeInstructionPanel.add(mazeDisplay);

        generateMaze();

        instructionPanel = new InstructionPanel(this, frame);
        mazeInstructionPanel.add(instructionPanel);
        add(mazeInstructionPanel);

    }

    public void refreshCells() {
        for (int r = 0; r < mazeUI.length; r++) {
            for (int c = 0; c < mazeUI[r].length; c++) {
                mazeUI[r][c].paintAll(mazeUI[r][c].getGraphics());
            }
        }
    }

    public void regenerateMaze() {
        instructionPanel.clearPath();
        instructionPanel.instructionPanelInput.clearInstructions();
        try {
            int size = Integer.parseInt(sizeInput.getText());
            if (size != mazeUI.length) {
                mazeDisplay.removeAll();
                mazeDisplay.repaint();
//                this.paintAll(getGraphics());
                mazeUI = new CellUI[size][size];
                generateMaze();
            } else {
                mazeGame.startMazeGame();
                title.setText("Maze: " + mazeGame.algorithmName);
                refreshCells();
            }
        } catch (NumberFormatException e) {
            System.out.println("Input not valid");
        }
        paintAll(getGraphics());
    }

    public void generateMaze() {
        mazeGame = new MazeGame(mazeUI.length, mazeUI[0].length);
        title.setText("Maze: " + mazeGame.algorithmName);
        Cell[][] maze = mazeGame.getMaze();
        GridLayout gridLayout = new GridLayout();
        gridLayout.setRows(maze.length);
        gridLayout.setColumns(maze[0].length);
        mazeDisplay.setLayout(gridLayout);
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[r].length; c++) {
                mazeUI[r][c] = new CellUI(maze[r][c], mazeDisplay.getMaximumSize().width / (maze.length + 2), this);
                maze[r][c].cellUI = mazeUI[r][c];
                mazeDisplay.add(mazeUI[r][c]);
            }
        }
        paintAll(getGraphics());
    }
}
