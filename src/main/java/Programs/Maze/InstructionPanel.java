package Programs.Maze;

import Utils.RoundPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

class InstructionPanel extends RoundPanel {

    JScrollPane scrollPane;
    JTextArea instructionInput;
    MazePanel mazePanel;
    InstructionPanelInput instructionPanelInput;
    ArrayList<CellUI> previousPath;

    InstructionPanel(MazePanel mazePanel, Frame frame) {
        super(Color.LIGHT_GRAY, 20);
        this.mazePanel = mazePanel;
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
        previousPath = new ArrayList<>();
    }

    int visualisePath(ArrayList<Integer> directions) {
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

    int generatePath(ArrayList<Integer> directions) {
        clearPath();
//        CellUI currentCell = mazePanel.mazeUI.getCellAt(mazePanel.mazeGame.startCell.getRow(), mazePanel.mazeGame.startCell.getColumn());
//        previousPath.add(currentCell);
//        for (int i = 0; i < directions.size(); i++) {
//            if (currentCell == null) break;
//            Cell nextGameCell = currentCell.cell.getCellInDir(directions.get(i));
//            currentCell.outLineDir = directions.get(i);
//            if (currentCell.cell.hasWallInDirection(directions.get(i)) || nextGameCell == null) {
//                return -1;
//            }
//            CellUI nextCell = nextGameCell.cellUI;
//            nextCell.inLineDir = -directions.get(i);
//            currentCell = nextCell;
//            previousPath.add(currentCell);
//        }
//        if (currentCell != null && currentCell.cell.isEndCell()) {
//            System.out.println("You Won!");
//            return -2;
//        }
        return -3;
    }

    void clearPath() {
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
