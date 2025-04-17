package Programs.Maze;

import StartMenu.Program;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Handles the user key input while playing the maze game.
 */
class InstructionPanelInput extends KeyAdapter {
    InstructionPanel instructionPanel;
    Program program;

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
        } else if (keyCode == KeyEvent.VK_BACK_SPACE) {
            instructionPanel.removeFirstInstruction();
        } else if (keyCode == KeyEvent.VK_ENTER) {
            instructionPanel.visualizeSolution();
        }
    }

    void doInstruction(Cell.Direction direction) {
        if (isBackWardsInstruction(direction)) {
            instructionPanel.removeFirstInstruction();
            return;
        }
        if (instructionPanel.mazePanel.mazeUI.pathState == MazeUI.PathState.Invalid) return;

        instructionPanel.instructions.add(direction);
        switch (direction) {
            case Up -> instructionPanel.addInstruction("Up");
            case Down -> instructionPanel.addInstruction("Down");
            case Left -> instructionPanel.addInstruction("Left");
            case Right -> instructionPanel.addInstruction("Right");
        }

        instructionPanel.updatePath();
    }

    boolean isBackWardsInstruction(Cell.Direction direction) {
        if (instructionPanel.instructions.isEmpty()) return false;
        return instructionPanel.instructions.getLast() == Cell.getOppositeDir(direction);
    }
}
