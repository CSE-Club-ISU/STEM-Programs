package Programs.Maze;

import Utils.RoundPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Handles validating the list of instructions and managing the list of user instructions.
 */
class InstructionPanel extends RoundPanel {

    JScrollPane scrollPane;
    JTextArea instructionInput;
    MazePanel mazePanel;
    InstructionPanelInput instructionPanelInput;
    ArrayList<Cell.Direction> instructions;

    InstructionPanel(MazePanel mazePanel, Frame frame) {
        super(Color.LIGHT_GRAY, 20);
        this.mazePanel = mazePanel;
        instructions = new ArrayList<>();
        setMaximumSize(new Dimension(200, 1000));
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);
        JLabel instructionTitle = new JLabel("Instructions");
        instructionTitle.setMaximumSize(new Dimension(200, 30));
        instructionTitle.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        instructionTitle.setAlignmentX(CENTER_ALIGNMENT);
        add(instructionTitle);
        add(Box.createVerticalStrut(10));


        instructionInput = new JTextArea("");
        instructionInput.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
        instructionInput.setForeground(Color.BLACK);
        instructionInput.setDisabledTextColor(Color.black);
        instructionInput.setEnabled(false);
        setBorder(new EmptyBorder(0, 20, 0, 0));
        instructionInput.setBackground(Color.lightGray);
        instructionInput.isCursorSet();

        instructionPanelInput = new InstructionPanelInput(this, mazePanel.program);
        mazePanel.addKeyListener(instructionPanelInput);

        scrollPane = new JScrollPane(instructionInput);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.setBorder(new EmptyBorder(0, 20, 0, 0));
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane);
        add(Box.createVerticalStrut(10));

        setFocusable(true);
    }

    void updatePath() {
        mazePanel.mazeUI.path = instructions;
        mazePanel.mazeUI.pathState = validatePath();
        mazePanel.mazeUI.repaint();
    }

    void visualizeSolution() {
        mazePanel.mazeUI.path = mazePanel.mazeGame.solutionInstructions;
        mazePanel.mazeUI.pathState = MazeUI.PathState.Solution;
        mazePanel.mazeUI.repaint();
    }

    MazeUI.PathState validatePath() {
        Cell currentCell = mazePanel.mazeGame.getStartCell();
        for (Cell.Direction instruction : instructions) {
            if (currentCell.hasWallInDirection(instruction)) return MazeUI.PathState.Invalid;
            currentCell = currentCell.getCellInDir(instruction);
        }
        if (currentCell.isEndCell()) return MazeUI.PathState.Finished;
        return MazeUI.PathState.Normal;
    }

    void clearPath() {
        instructions.clear();
        instructionInput.setText("");
        mazePanel.mazeUI.path = instructions;
        mazePanel.mazeUI.pathState = MazeUI.PathState.Normal;
        mazePanel.mazeUI.repaint();
    }

    public void addInstruction(String instruction) {
        instructionInput.setText(instructionInput.getText() + instructions.size() + ": " + instruction + "\n");
    }

    public void removeFirstInstruction() {
        if (instructions.isEmpty()) return;

        instructions.removeLast();
        instructionInput.setText(getTextMinusLastLine(instructionInput.getText()));
        updatePath();
    }

    int getStartIndexOfSecondToLastLine(String text) {
        int index = instructionInput.getText().lastIndexOf(':') - 1;
        if (index == -1) return -1;
        while (index > -1 && (Character.isDigit(text.charAt(index)) || text.charAt(index) == '>')) {
            index--;
        }
        return index;
    }

    String getTextMinusLastLine(String text) {
        int lastIndex = getStartIndexOfSecondToLastLine(text);
        if (lastIndex > 0) {
            return instructionInput.getText().substring(0, lastIndex + 1);
        } else return "";

    }

}
