package Programs.Maze;

import StartMenu.Program;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

 class InstructionPanelInput extends KeyAdapter {
    InstructionPanel instructionPanel;
    ArrayList<Integer> instructions;
    Program program;
    boolean outOfBounds = false;

     InstructionPanelInput(InstructionPanel instructionPanel, Program program) {
        this.instructionPanel = instructionPanel;
        this.program = program;
        instructions = new ArrayList<>(100);
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
            doInstruction(-1);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            doInstruction(1);
        } else if (keyCode == KeyEvent.VK_LEFT) {
            doInstruction(-2);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            doInstruction(2);
        } else if (keyCode == KeyEvent.VK_DELETE) {
            instructionPanel.clearPath();
            clearInstructions();
        } else if (keyCode == KeyEvent.VK_BACK_SPACE) {
            removeFirstInstruction();
            instructionPanel.visualisePath(instructions);
        } else if (keyCode == KeyEvent.VK_ENTER) {
            instructionPanel.clearPath();
//            clearInstructions();
            instructionPanel.visualisePath(instructionPanel.mazePanel.mazeGame.solutionInstructions);
        }
    }

    void doInstruction(int direction) {
        if (isBackWardsInstruction(direction)) {
            removeFirstInstruction();
            return;
        }
        if (outOfBounds) {
            return;
        }
        instructions.add(direction);
        if (direction == 1) {
            instructionPanel.instructionInput.setText(instructionPanel.instructionInput.getText() + instructions.size() + ": Down\n");
        } else if (direction == -1) {
            instructionPanel.instructionInput.setText(instructionPanel.instructionInput.getText() + instructions.size() + ": Up\n");
        } else if (direction == 2) {
            instructionPanel.instructionInput.setText(instructionPanel.instructionInput.getText() + instructions.size() + ": Right\n");
        } else if (direction == -2) {
            instructionPanel.instructionInput.setText(instructionPanel.instructionInput.getText() + instructions.size() + ": Left\n");
        }

        // if out of bounds, disable forward movement
        int errorLine = instructionPanel.visualisePath(instructions);
        if (errorLine == -1) {
            this.outOfBounds = true;
        }

        //TODO: Add text signal to error line
    }

    boolean isBackWardsInstruction(int direction) {
        if (instructions.isEmpty())
            return false;
        return instructions.getLast() == -direction;
    }

    void removeFirstInstruction() {
        if (instructions.isEmpty()) return;

        // on removal, should never be out of bounds
        this.outOfBounds = false;

        instructions.removeLast();
        instructionPanel.instructionInput.setText(getTextMinusLastLine(instructionPanel.instructionInput.getText()));
        instructionPanel.visualisePath(instructions);
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
        instructions.clear();
    }
}
