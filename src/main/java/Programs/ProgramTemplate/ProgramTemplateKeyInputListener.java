package Programs.ProgramTemplate;

import StartMenu.Frame;
import StartMenu.Program;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * By extending KeyAdapter we can subscribe to keyboard events while the components we are attached to are focused.
 * This could be condensed into an anonymous function, however this class needs to be added to any UI element
 * that can be focused in the panel or else we won't receive the key events.
 */
public class ProgramTemplateKeyInputListener extends KeyAdapter {
    Frame frame;
    Program program;
    ProgramTemplatePanel programTemplatePanel;
    JTextArea inputField;

    public ProgramTemplateKeyInputListener(Frame frame, Program program, ProgramTemplatePanel programTemplatePanel, JTextArea inputField) {
        this.frame = frame;
        this.program = program;
        this.programTemplatePanel = programTemplatePanel;
        this.inputField = inputField;

        programTemplatePanel.addKeyListener(this);
        inputField.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        System.out.println(event);
        if (keyCode == KeyEvent.VK_ESCAPE) {
            if (frame.hasFocus() || programTemplatePanel.hasFocus()) {
                // Return to instruction list
                program.endProgram();
            } else {
                // Stop focusing the text input
                programTemplatePanel.requestFocusInWindow();
            }
        }
    }
}
