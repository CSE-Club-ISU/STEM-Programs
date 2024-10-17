package StartMenu;

import javax.swing.*;

public abstract class Program {
    Frame frame = null;
    JPanel panel = null;

    /**
     * Called when the program should be loaded on top of the frame.
     * Use this to display the panel and set up your program.
     * @param frame the global frame that can be used for key bindings.
     * @return a new panel for the game to show
     */
    public abstract JPanel startProgram(Frame frame);

    /**
     * The program name that will be displayed on the start menu.
     */
    public abstract String getProgramName();

    /**
     * The description of the program that will be displayed on the start menu.
     */
    public abstract String getProgramDescription();

    public void endProgram() {
        frame.endProgram(this);
    }

    void setFrameAndPanel(Frame frame, JPanel panel) {
        this.panel = panel;
        this.frame = frame;
    }

    JPanel getPanel() {
        return panel;
    }
}
