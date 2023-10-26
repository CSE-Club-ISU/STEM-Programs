package src.MazeGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InstructionPanelInput extends KeyAdapter {
    InstructionPanel instructionPanel;

    public InstructionPanelInput(InstructionPanel instructionPanel) {
        this.instructionPanel = instructionPanel;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            instructionPanel.instructionInput.setText(instructionPanel.instructionInput.getText() + "Up\n");
        } else if (keyCode == KeyEvent.VK_DOWN) {
            instructionPanel.instructionInput.setText(instructionPanel.instructionInput.getText() + "Down\n");
        } else if (keyCode == KeyEvent.VK_LEFT) {
            instructionPanel.instructionInput.setText(instructionPanel.instructionInput.getText() + "Left\n");
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            instructionPanel.instructionInput.setText(instructionPanel.instructionInput.getText() + "Right\n");
        } else if (keyCode == KeyEvent.VK_BACK_SPACE) {
            if (instructionPanel.instructionInput.getText().lastIndexOf('\n') != -1)
                instructionPanel.instructionInput.setText(instructionPanel.instructionInput.getText().substring(0, instructionPanel.instructionInput.getText().lastIndexOf('\n')));
            else instructionPanel.instructionInput.setText("");
        }

    }

    @Override
    public void keyReleased(KeyEvent event) {
    }
}
