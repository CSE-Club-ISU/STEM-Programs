package StartMenu;

import javax.swing.*;

/**
 * Program defines abstract functionality that each concrete program needs to implement in order to display
 * them on the list and to initialize and start them.
 */
public abstract class Program {
    private Frame frame = null;
    private JPanel panel = null;

    /**
     * Initializes the programs frame and panel values and starts the program.
     * This should be called by the Frame class itself.
     */
    JPanel initializeAndStartProgram(Frame frame) {
        this.frame = frame;
        panel = startProgram(frame);
        return panel;
    }

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

    /**
     * @return the desired priority of the program in the program list sorted in ascending order.
     */
    public abstract int getProgramPriority();

    public void endProgram() {
        frame.endProgram(this);
    }

    JPanel getPanel() {
        return panel;
    }
}
