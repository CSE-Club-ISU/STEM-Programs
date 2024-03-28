package src.Programs.ProgramTemplate;

import src.StartMenu.Frame;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ProgramTemplateKeyInputListener extends KeyAdapter {
    Frame frame;
    ProgramTemplatePanel programTemplatePanel;
    JTextField inputField;

    public ProgramTemplateKeyInputListener(Frame frame, ProgramTemplatePanel programTemplatePanel, JTextField inputField) {
        this.frame = frame;
        this.programTemplatePanel = programTemplatePanel;
        this.inputField = inputField;

        frame.addKeyListener(this);
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
                frame.endProgram(programTemplatePanel);
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
