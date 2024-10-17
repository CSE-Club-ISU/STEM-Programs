package Programs.ProgramTemplate;

import StartMenu.Frame;
import StartMenu.Program;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ProgramTemplateKeyInputListener extends KeyAdapter {
    Frame frame;
    Program program;
    ProgramTemplatePanel programTemplatePanel;
    JTextField inputField;

    public ProgramTemplateKeyInputListener(Frame frame, Program program, ProgramTemplatePanel programTemplatePanel, JTextField inputField) {
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
        } else if (keyCode == KeyEvent.VK_ENTER) {
            if (inputField.hasFocus()) {
                programTemplatePanel.requestFocusInWindow();
            }
        }
    }
}
