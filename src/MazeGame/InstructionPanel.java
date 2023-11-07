package src.MazeGame;

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
        instructionTitle.setMaximumSize(new Dimension(200,30));
        instructionTitle.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        instructionTitle.setAlignmentX(CENTER_ALIGNMENT);
        add(instructionTitle);


        instructionInput = new JTextArea("");
        instructionInput.setMaximumSize(new Dimension(200,600));
        instructionInput.setMinimumSize(new Dimension(200,600));
        instructionInput.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
        instructionInput.setForeground(Color.BLACK);
        instructionInput.setDisabledTextColor(Color.black);
        instructionInput.setEnabled(false);
        setBorder(new EmptyBorder(0,20,0,0));
        instructionInput.setBackground(Color.lightGray);
        instructionInput.isCursorSet();
        instructionPanelInput = new InstructionPanelInput(this);
        frame.addKeyListener(instructionPanelInput);

        scrollPane = new JScrollPane(instructionInput);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setMaximumSize(new Dimension(200,600));
        scrollPane.setMinimumSize(new Dimension(200,600));
        add(scrollPane);

        setFocusable(true);
        previousPath = new ArrayList<>();
    }

    public void visualisePath() {
        Color returnColor = generatePath();
        for (CellUI cellUI: previousPath) {
            cellUI.lineColor = returnColor;
            cellUI.paintComponent(cellUI.getGraphics());
        }
//        mazePanel.paintAll(mazePanel.getGraphics());
    }

    public Color generatePath() {
        clearPath();
        CellUI[][] grid = mazePanel.mazeUI;
        CellUI currentCell = grid[mazePanel.mazeGame.startCell.r][mazePanel.mazeGame.startCell.c];
        previousPath.add(currentCell);
        String input = instructionInput.getText();
        int inputIndex = input.indexOf(':') + 2;
        while(inputIndex > 1 && currentCell != null) {
            int dir = getDirectionFromChar(input.charAt(inputIndex));
            Cell nextGameCell = currentCell.cell.getCellInDir(dir);
            currentCell.outLineDir = dir;
            if (currentCell.cell.hasWallInDirection(dir) || nextGameCell == null) {
                int startIndex = inputIndex - 3;
                while(startIndex >= 0 && Character.isDigit(input.charAt(startIndex))) {
                    startIndex--;
                }
                startIndex++;
                instructionInput.setText(input.substring(0,startIndex) + ">" + input.substring(startIndex));
                return Color.RED;
            }
            CellUI nextCell = nextGameCell.cellUI;
            nextCell.inLineDir = -dir;
            currentCell = nextCell;
            previousPath.add(currentCell);
            inputIndex = input.indexOf(':', inputIndex) + 2;
        }
        if (currentCell != null && currentCell.cell.isEndCell()) {
            System.out.println("You Won!");
            return Color.GREEN;
        }
        return Color.BLUE;
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
        }else if (c == 'L') {
            return -2;
        }else if (c == 'R') {
            return 2;
        }
        return 0;
    }
}
