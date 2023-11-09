package src.MazeGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InstructionPanelInput extends KeyAdapter {
    InstructionPanel instructionPanel;
    int instructionCount;

    public InstructionPanelInput(InstructionPanel instructionPanel) {
        this.instructionPanel = instructionPanel;
        instructionCount = 0;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            if (!isBackwardsMovement("Down")) {
                instructionPanel.instructionInput.setText(instructionPanel.instructionInput.getText() + instructionCount + ": Up\n");
                instructionCount++;
            } else removeFirstInstruction();
            instructionPanel.visualisePath();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            if (!isBackwardsMovement("Up")) {
                instructionPanel.instructionInput.setText(instructionPanel.instructionInput.getText() + instructionCount + ": Down\n");
                instructionCount++;
            } else removeFirstInstruction();
            instructionPanel.visualisePath();
        } else if (keyCode == KeyEvent.VK_LEFT) {
            if (!isBackwardsMovement("Right")) {
                instructionPanel.instructionInput.setText(instructionPanel.instructionInput.getText() + instructionCount + ": Left\n");
                instructionCount++;
            } else removeFirstInstruction();
            instructionPanel.visualisePath();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            if (!isBackwardsMovement("Left")) {
                instructionPanel.instructionInput.setText(instructionPanel.instructionInput.getText() + instructionCount + ": Right\n");
                instructionCount++;
            } else removeFirstInstruction();
            instructionPanel.visualisePath();
        } else if (keyCode == KeyEvent.VK_DELETE) {
            instructionPanel.clearPath();
            clearInstructions();
        } else if (keyCode == KeyEvent.VK_BACK_SPACE) {
            removeFirstInstruction();
            instructionPanel.visualisePath();
        } else if (keyCode == KeyEvent.VK_ENTER) {
            instructionPanel.visualisePath();
        }

    }

    boolean isBackwardsMovement(String backWardsMove) {
        int index = instructionPanel.instructionInput.getText().lastIndexOf(':') + 2;
        if (index == 1) return false;
        if (index + backWardsMove.length() >= instructionPanel.instructionInput.getText().length()) return false;
        String previousMove = instructionPanel.instructionInput.getText().substring(index, index + backWardsMove.length());
        if (previousMove.equals(backWardsMove)) {
            return true;
        }
        return false;
    }


    void removeFirstInstruction() {
        instructionPanel.instructionInput.setText(getTextMinusLastLine(instructionPanel.instructionInput.getText()));
        instructionCount = Math.max(0,instructionCount - 1);
    }

    int getStartIndexOfSecondToLastLine(String text) {
        int index = instructionPanel.instructionInput.getText().lastIndexOf(':') - 1;
        if (index == -1) return -1;
        while(index > -1 && (Character.isDigit(text.charAt(index)) || text.charAt(index) == '>')) {
            index--;
        }
        return index;
    }

    String getTextMinusLastLine(String text) {
        int lastIndex = getStartIndexOfSecondToLastLine(text);
        if (lastIndex > 0) {
            return instructionPanel.instructionInput.getText().substring(0, lastIndex+1);
        } else return "";

    }

    public void clearInstructions() {
        instructionPanel.instructionInput.setText("");
        instructionCount = 0;
    }
}
