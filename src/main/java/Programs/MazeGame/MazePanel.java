package Programs.MazeGame;

import StartMenu.Frame;
import StartMenu.Program;
import Utils.RoundButton;
import Utils.UIUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class MazePanel extends JPanel {
    Program program;
    JLabel title;
    Maze maze;
    InstructionPanel instructionPanel;
    MazeUI mazeUI;
    JTextField sizeInput;
     MazePanel(Frame frame, Program program) {
        this.program = program;
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);
        title = UIUtils.addTitle("Maze Game", this);
        mazeUI = new MazeUI(this, 10, 10);

        Box top = Box.createHorizontalBox();
        top.add(createRegenerateMazeButton());
        UIUtils.addSpace(10,10, top);
        top.add(createSizeInputField());
        add(top);

        Box mazeAndInstructionHolder = Box.createHorizontalBox();
        mazeAndInstructionHolder.setBorder(new EmptyBorder(10, 10, 10, 10));
        mazeAndInstructionHolder.add(mazeUI);
        instructionPanel = new InstructionPanel(this, frame);
        mazeAndInstructionHolder.add(instructionPanel);
        add(mazeAndInstructionHolder);
    }

    private JButton createRegenerateMazeButton() {
        JButton regenerateButton = new RoundButton("Regenerate", Color.BLUE, 20, 10);
        regenerateButton.setVerticalTextPosition(AbstractButton.CENTER);
        regenerateButton.setAlignmentX(CENTER_ALIGNMENT);
        regenerateButton.addActionListener((l) -> {
            this.requestFocusInWindow();
            generateMaze();
        });
        regenerateButton.setForeground(Color.white);
        regenerateButton.setFocusPainted(false);
        regenerateButton.setFocusable(false);
        return regenerateButton;
    }

    private JTextField createSizeInputField() {
        sizeInput = new JTextField(Integer.toString(mazeUI.getGridRows()));
        sizeInput.setMaximumSize(new Dimension(100, 30));
        sizeInput.setBorder(new EmptyBorder(10, 10, 10, 10));
        MazePanel mazePanel = this;
        // In order to escape the input field we need to bind a key listener to request the focus back to the main panel
        sizeInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    mazePanel.requestFocusInWindow();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        return sizeInput;
    }

     void generateMaze() {
        if (maze != null) {
            regenerateMaze();
            return;
        }
        maze = new Maze(mazeUI.getGridRows(), mazeUI.getGridColumns());
        title.setText("Maze: " + maze.algorithmName);
        mazeUI.generateMaze(maze.getGrid());
    }

    private void regenerateMaze() {
        instructionPanel.clearPath();
        instructionPanel.instructionPanelInput.clearInstructions();
        try {
            int newSize = Integer.parseInt(sizeInput.getText());
            if (newSize != mazeUI.getGridColumns()) {
                mazeUI.resizeMazeUI(newSize,newSize);
                maze = null;
                generateMaze();
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Input not valid");
        }
        maze.startMazeGame();
        title.setText("Maze: " + maze.algorithmName);
        mazeUI.repaint();
    }
}
