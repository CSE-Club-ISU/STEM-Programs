package Programs.Maze;

import StartMenu.Program;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

class InstructionPanelInput extends KeyAdapter {
    InstructionPanel instructionPanel;
    Program program;
    boolean outOfBounds = false;

    InstructionPanelInput(InstructionPanel instructionPanel, Program program) {
        this.instructionPanel = instructionPanel;
        this.program = program;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.VK_ESCAPE) {
            if (!instructionPanel.mazePanel.sizeInput.hasFocus()) {
                program.endProgram();
            }
        } else if (keyCode == KeyEvent.VK_R) {
            instructionPanel.mazePanel.generateMaze();
        }
        if (instructionPanel.mazePanel.mazeGame == null) return;

        if (keyCode == KeyEvent.VK_UP) {
            doInstruction(Cell.Direction.Up);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            doInstruction(Cell.Direction.Down);
        } else if (keyCode == KeyEvent.VK_LEFT) {
            doInstruction(Cell.Direction.Left);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            doInstruction(Cell.Direction.Right);
        } else if (keyCode == KeyEvent.VK_DELETE) {
            instructionPanel.clearPath();
            clearInstructions();
        } else if (keyCode == KeyEvent.VK_BACK_SPACE) {
            removeFirstInstruction();
        } else if (keyCode == KeyEvent.VK_ENTER) {
            instructionPanel.visualizeSolution();
        }
    }

    void doInstruction(Cell.Direction direction) {
        if (isBackWardsInstruction(direction)) {
            removeFirstInstruction();
            return;
        }
        if (instructionPanel.mazePanel.mazeUI.pathState == MazeUI.PathState.Invalid) return;

        instructionPanel.instructions.add(direction);
        switch (direction) {
            case Up ->
                    instructionPanel.instructionInput.setText(instructionPanel.instructionInput.getText() + instructionPanel.instructions.size() + ": Up\n");
            case Down ->
                    instructionPanel.instructionInput.setText(instructionPanel.instructionInput.getText() + instructionPanel.instructions.size() + ": Down\n");

            case Left ->
                    instructionPanel.instructionInput.setText(instructionPanel.instructionInput.getText() + instructionPanel.instructions.size() + ": Left\n");

            case Right ->
                    instructionPanel.instructionInput.setText(instructionPanel.instructionInput.getText() + instructionPanel.instructions.size() + ": Right\n");

        }

        instructionPanel.updatePath();
    }

    boolean isBackWardsInstruction(Cell.Direction direction) {
        if (instructionPanel.instructions.isEmpty())
            return false;
        return instructionPanel.instructions.getLast() == Cell.getOppositeDir(direction);
    }

    void removeFirstInstruction() {
        if (instructionPanel.instructions.isEmpty()) return;

        // on removal, should never be out of bounds
        this.outOfBounds = false;

        instructionPanel.instructions.removeLast();
        instructionPanel.instructionInput.setText(getTextMinusLastLine(instructionPanel.instructionInput.getText()));
        instructionPanel.updatePath();
    }

    int getStartIndexOfSecondToLastLine(String text) {
        int index = instructionPanel.instructionInput.getText().lastIndexOf(':') - 1;
        if (index == -1) return -1;
        while (index > -1 && (Character.isDigit(text.charAt(index)) || text.charAt(index) == '>')) {
            index--;
        }
        return index;
    }

    String getTextMinusLastLine(String text) {
        int lastIndex = getStartIndexOfSecondToLastLine(text);
        if (lastIndex > 0) {
            return instructionPanel.instructionInput.getText().substring(0, lastIndex + 1);
        } else return "";

    }

    void clearInstructions() {
        instructionPanel.instructionInput.setText("");
        instructionPanel.instructions.clear();
        this.outOfBounds = false;
    }
}
