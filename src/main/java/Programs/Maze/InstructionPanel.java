package Programs.Maze;

import Utils.RoundPanel;
import Utils.RoundButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles validating the list of instructions and managing the list of user instructions.
 */
class InstructionPanel extends RoundPanel {

    JScrollPane scrollPane;
    JTextArea instructionInput;
    MazePanel mazePanel;
    InstructionPanelInput instructionPanelInput;
    ArrayList<Cell.Direction> instructions;
    JButton simulateButton;
    JButton resetButton;
    private boolean isSimulating = false;
    private int currentSimStep = 0;
    private Timer simulationTimer;

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

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        
        simulateButton = new RoundButton("Simulate", Color.WHITE, 16, new Color(34, 139, 34), 10);
        simulateButton.setFocusable(false);
        simulateButton.addActionListener(this::runSimulation);
        buttonPanel.add(simulateButton);
        
        buttonPanel.add(Box.createHorizontalStrut(10));
        
        resetButton = new RoundButton("Reset", Color.WHITE, 16, new Color(220, 20, 60), 10);
        resetButton.setFocusable(false);
        resetButton.addActionListener(e -> clearPath());
        buttonPanel.add(resetButton);
        
        add(buttonPanel);
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

    private void runSimulation(ActionEvent e) {
        if (isSimulating) {
            stopSimulation();
            return;
        }
        if (instructions.isEmpty()) return;
        
        isSimulating = true;
        currentSimStep = 0;
        simulateButton.setText("Stop");
        
        List<Cell.Direction> simPath = new ArrayList<>();
        mazePanel.mazeUI.simulationPath = new ArrayList<>(simPath);
        mazePanel.mazeUI.isSimulating = true;
        
        simulationTimer = new Timer(400, null);
        simulationTimer.addActionListener(evt -> {
            if (currentSimStep >= instructions.size()) {
                stopSimulation();
                MazeUI.PathState finalState = validateSimulationPath(simPath);
                mazePanel.mazeUI.pathState = finalState;
                mazePanel.mazeUI.repaint();
                return;
            }
            
            Cell.Direction nextDir = instructions.get(currentSimStep);
            Cell currentCell = simPath.isEmpty() ? mazePanel.mazeGame.getStartCell() : getLastCell(simPath);
            
            if (currentCell.hasWallInDirection(nextDir)) {
                simPath.add(nextDir);
                mazePanel.mazeUI.simulationPath = new ArrayList<>(simPath);
                mazePanel.mazeUI.pathState = MazeUI.PathState.Invalid;
                mazePanel.mazeUI.repaint();
                stopSimulation();
                return;
            }
            
            simPath.add(nextDir);
            mazePanel.mazeUI.simulationPath = new ArrayList<>(simPath);
            mazePanel.mazeUI.repaint();
            currentSimStep++;
        });
        simulationTimer.start();
    }

    private Cell getLastCell(List<Cell.Direction> path) {
        Cell current = mazePanel.mazeGame.getStartCell();
        for (Cell.Direction dir : path) {
            current = current.getCellInDir(dir);
        }
        return current;
    }

    private MazeUI.PathState validateSimulationPath(List<Cell.Direction> path) {
        Cell currentCell = mazePanel.mazeGame.getStartCell();
        for (Cell.Direction instruction : path) {
            if (currentCell.hasWallInDirection(instruction)) return MazeUI.PathState.Invalid;
            currentCell = currentCell.getCellInDir(instruction);
        }
        if (currentCell.isEndCell()) return MazeUI.PathState.Finished;
        return MazeUI.PathState.Normal;
    }

    private void stopSimulation() {
        isSimulating = false;
        if (simulationTimer != null) {
            simulationTimer.stop();
            simulationTimer = null;
        }
        simulateButton.setText("Simulate");
        mazePanel.mazeUI.isSimulating = false;
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
        stopSimulation();
        instructions.clear();
        instructionInput.setText("");
        mazePanel.mazeUI.path = instructions;
        mazePanel.mazeUI.pathState = MazeUI.PathState.Normal;
        mazePanel.mazeUI.simulationPath.clear();
        mazePanel.mazeUI.repaint();
    }

    public void addInstruction(String instruction) {
        instructionInput.setText(instructionInput.getText() + instructions.size() + ": " + instruction + "\n");
    }

    public void removeFirstInstruction() {
        if (instructions.isEmpty()) return;

        instructions.remove(instructions.size() - 1);
        instructionInput.setText(getTextMinusLastLine(instructionInput.getText()));
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
