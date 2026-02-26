package Programs.Maze;

import StartMenu.Program;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

        if (keyCode == KeyEvent.VK_W) {
            doDirectMovement(Cell.Direction.Up);
        } else if (keyCode == KeyEvent.VK_S) {
            doDirectMovement(Cell.Direction.Down);
        } else if (keyCode == KeyEvent.VK_A) {
            doDirectMovement(Cell.Direction.Left);
        } else if (keyCode == KeyEvent.VK_D) {
            doDirectMovement(Cell.Direction.Right);
        } else if (keyCode == KeyEvent.VK_ENTER) {
            instructionPanel.visualizeSolution();
        } else if (keyCode == KeyEvent.VK_DELETE) {
            instructionPanel.mazePanel.mazeUI.clearDirectPath();
            instructionPanel.mazePanel.mazeUI.pathState = MazeUI.PathState.Normal;
            instructionPanel.mazePanel.mazeUI.repaint();
        }
        
        if (keyCode == KeyEvent.VK_UP) {
            doInstruction(Cell.Direction.Up);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            doInstruction(Cell.Direction.Down);
        } else if (keyCode == KeyEvent.VK_LEFT) {
            doInstruction(Cell.Direction.Left);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            doInstruction(Cell.Direction.Right);
        } else if (keyCode == KeyEvent.VK_BACK_SPACE) {
            instructionPanel.removeFirstInstruction();
        }
    }

    void doDirectMovement(Cell.Direction direction) {
        instructionPanel.mazePanel.mazeUI.addDirectMovement(direction);
        instructionPanel.mazePanel.mazeUI.pathState = instructionPanel.mazePanel.mazeUI.validateDirectPath();
        instructionPanel.mazePanel.mazeUI.repaint();
    }

    void doInstruction(Cell.Direction direction) {
        if (isBackWardsInstruction(direction)) {
            instructionPanel.removeFirstInstruction();
            return;
        }

        instructionPanel.instructions.add(direction);
        switch (direction) {
            case Up -> instructionPanel.addInstruction("Up");
            case Down -> instructionPanel.addInstruction("Down");
            case Left -> instructionPanel.addInstruction("Left");
            case Right -> instructionPanel.addInstruction("Right");
        }
    }

    boolean isBackWardsInstruction(Cell.Direction direction) {
        if (instructionPanel.instructions.isEmpty()) return false;
        return instructionPanel.instructions.get(instructionPanel.instructions.size() - 1) == Cell.getOppositeDir(direction);
    }
}
