package Programs.MazeGame;

import StartMenu.Frame;
import StartMenu.Program;
import Utils.RoundButton;
import Utils.RoundInputField;
import Utils.RoundTextField;
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
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(10));
        title = UIUtils.addTitle("Maze",  this);
        mazeUI = new MazeUI(frame, 10, 10);

        JPanel top = new JPanel();
        top.setOpaque(false);
        top.setBorder(new EmptyBorder(0,0,0,0));
        top.setLayout(new BoxLayout(top, BoxLayout.LINE_AXIS));
        top.add(createBackButton());
        UIUtils.addSpace(10,10,top);
        top.add(createRegenerateMazeButton());
        UIUtils.addSpace(10,10,top);
        top.add(createSizeInputField());
        add(top);
        add(Box.createVerticalStrut(10));

        Box mazeAndInstructionHolder = Box.createHorizontalBox();
        mazeAndInstructionHolder.add(mazeUI);
        mazeAndInstructionHolder.add(Box.createHorizontalStrut(10));
        instructionPanel = new InstructionPanel(this, frame);
        mazeAndInstructionHolder.add(instructionPanel);
        add(mazeAndInstructionHolder);
        generateMaze();
    }

    private JButton createBackButton() {
        JButton backButton = new RoundButton("Back", Color.WHITE, 20, Color.RED, 10);
        backButton.setFocusPainted(false);
        backButton.setFocusable(false);
        backButton.addActionListener((e) -> program.endProgram());
        return backButton;
    }

    private JButton createRegenerateMazeButton() {
        JButton regenerateButton = new RoundButton("Regenerate", Color.WHITE, 20, Color.BLUE, 10);
        regenerateButton.addActionListener((l) -> {
            this.requestFocusInWindow();
            generateMaze();
        });
        regenerateButton.setFocusPainted(false);
        regenerateButton.setFocusable(false);
        return regenerateButton;
    }

    private JTextField createSizeInputField() {
        sizeInput = new RoundTextField(Integer.toString(mazeUI.getGridRows()), 10);
        sizeInput.setMaximumSize(new Dimension(100, 38));
        sizeInput.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
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
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    generateMaze();
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
                mazeUI.resizeMazeUI(newSize, newSize);
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
