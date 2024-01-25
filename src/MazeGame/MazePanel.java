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
    MazeUI mazeUI;
    JTextField sizeInput;
    public MazePanel(Frame frame) {
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);
        title = UIUtils.addTextToComp("Maze Game", this);
        mazeUI = new MazeUI(this, 10, 10);

        Box top = Box.createHorizontalBox();
        top.add(createRegenerateMazeButton());
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
        JButton regenerateButton = new JButton("Regenerate");
        regenerateButton.setVerticalTextPosition(AbstractButton.CENTER);
        regenerateButton.setAlignmentX(CENTER_ALIGNMENT);
        regenerateButton.addActionListener((l) -> {
            Frame.getInstance().requestFocusInWindow();
            generateMaze();
        });
        regenerateButton.setBackground(Color.BLUE);
        regenerateButton.setForeground(Color.white);
        regenerateButton.setFocusPainted(false);
        regenerateButton.setFocusable(false);
        regenerateButton.setBorder(new EmptyBorder(10, 10, 10, 10));
        return regenerateButton;
    }

    private JTextField createSizeInputField() {
        sizeInput = new JTextField(Integer.toString(mazeUI.getGridRows()));
        sizeInput.setMaximumSize(new Dimension(100, 30));
        sizeInput.setBorder(new EmptyBorder(10, 10, 10, 10));
        return sizeInput;
    }

    public void generateMaze() {
        if (mazeGame != null) {
            regenerateMaze();
            return;
        }
        mazeGame = new MazeGame(mazeUI.getGridRows(), mazeUI.getGridColumns());
        title.setText("Maze: " + mazeGame.algorithmName);
        mazeUI.generateMaze(mazeGame.grid);
    }

    private void regenerateMaze() {
        instructionPanel.clearPath();
        instructionPanel.instructionPanelInput.clearInstructions();
        try {
            int newSize = Integer.parseInt(sizeInput.getText());
            if (newSize != mazeUI.getGridColumns()) {
                mazeUI.resizeMazeUI(newSize,newSize);
                mazeGame = null;
                generateMaze();
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Input not valid");
        }
        mazeGame.startMazeGame();
        title.setText("Maze: " + mazeGame.algorithmName);
        mazeUI.repaint();
    }
}
