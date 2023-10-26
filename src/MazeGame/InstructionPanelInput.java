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
            instructionPanel.instructionInput.setText(instructionPanel.instructionInput.getText() + instructionCount + ": Up\n");
            instructionCount++;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            instructionPanel.instructionInput.setText(instructionPanel.instructionInput.getText() + instructionCount + ": Down\n");
            instructionCount++;
        } else if (keyCode == KeyEvent.VK_LEFT) {
            instructionPanel.instructionInput.setText(instructionPanel.instructionInput.getText() + instructionCount + ": Left\n");
            instructionCount++;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            instructionPanel.instructionInput.setText(instructionPanel.instructionInput.getText() + instructionCount + ": Right\n");
            instructionCount++;
        } else if (keyCode == KeyEvent.VK_BACK_SPACE) {
            instructionPanel.instructionInput.setText(getTextMinusLastLine(instructionPanel.instructionInput.getText()));
            instructionCount = Math.max(0,instructionCount - 1);
        }

    }

    int getStartIndexOfSecondToLastLine(String text) {
        int index = instructionPanel.instructionInput.getText().lastIndexOf(':') - 1;
        if (index == -1) return -1;
        while(index > -1 && Character.isDigit(text.charAt(index))) {
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

    @Override
    public void keyReleased(KeyEvent event) {
    }
}
