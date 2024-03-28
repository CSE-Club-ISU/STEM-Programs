package src.Programs.MazeGame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class InstructionPanel extends JPanel {

    JScrollPane scrollPane;
    JTextArea instructionInput;
    MazePanel mazePanel;
    InstructionPanelInput instructionPanelInput;
    ArrayList<CellUI> previousPath;

    public InstructionPanel(MazePanel mazePanel, Frame frame) {
        this.mazePanel = mazePanel;
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);
        JLabel instructionTitle = new JLabel("Instructions");
        instructionTitle.setMaximumSize(new Dimension(200, 30));
        instructionTitle.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        instructionTitle.setAlignmentX(CENTER_ALIGNMENT);
        add(instructionTitle);


        instructionInput = new JTextArea("");
        instructionInput.setMaximumSize(new Dimension(200, 600));
        instructionInput.setMinimumSize(new Dimension(200, 600));
        instructionInput.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
        instructionInput.setForeground(Color.BLACK);
        instructionInput.setDisabledTextColor(Color.black);
        instructionInput.setEnabled(false);
        setBorder(new EmptyBorder(0, 20, 0, 0));
        instructionInput.setBackground(Color.lightGray);
        instructionInput.isCursorSet();
        instructionPanelInput = new InstructionPanelInput(this);
        frame.addKeyListener(instructionPanelInput);

        scrollPane = new JScrollPane(instructionInput);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setMaximumSize(new Dimension(200, 600));
        scrollPane.setMinimumSize(new Dimension(200, 600));
        add(scrollPane);

        setFocusable(true);
        previousPath = new ArrayList<>();
    }

    public int visualisePath(ArrayList<Integer> directions) {
        int returnValue = generatePath(directions);
        Color lineColor = Color.BLUE;
        if (returnValue == -1) {
            lineColor = Color.RED;
        } else if (returnValue == -2) {
            lineColor = Color.GREEN;
        }
        for (CellUI cellUI : previousPath) {
            cellUI.lineColor = lineColor;
            cellUI.paintComponent(cellUI.getGraphics());
        }
//        mazePanel.paintAll(mazePanel.getGraphics());
        return returnValue;
    }

    public int generatePath(ArrayList<Integer> directions) {
        clearPath();
        CellUI currentCell = mazePanel.mazeUI.getCellAt(mazePanel.mazeGame.startCell.getRow(),mazePanel.mazeGame.startCell.getColumn());
        previousPath.add(currentCell);
        for (int i = 0; i < directions.size(); i++) {
            if (currentCell == null) break;
            Cell nextGameCell = currentCell.cell.getCellInDir(directions.get(i));
            currentCell.outLineDir = directions.get(i);
            if (currentCell.cell.hasWallInDirection(directions.get(i)) || nextGameCell == null) {
                return -1;
            }
            CellUI nextCell = nextGameCell.cellUI;
            nextCell.inLineDir = -directions.get(i);
            currentCell = nextCell;
            previousPath.add(currentCell);
        }
        if (currentCell != null && currentCell.cell.isEndCell()) {
            System.out.println("You Won!");
            return -2;
        }
        return -3;
    }

    public void clearPath() {
        for (int i = previousPath.size() - 1; i >= 0; i--) {
            previousPath.get(i).inLineDir = 0;
            previousPath.get(i).outLineDir = 0;
            previousPath.get(i).repaint();
        }
        previousPath.clear();
    }

    int getDirectionFromChar(char c) {
        if (c == 'U') {
            return -1;
        } else if (c == 'D') {
            return 1;
        } else if (c == 'L') {
            return -2;
        } else if (c == 'R') {
            return 2;
        }
        return 0;
    }
}
