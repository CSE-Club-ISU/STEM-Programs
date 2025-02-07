package StartMenu;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

import static StartMenu.ProgramListPanel.createStartMenuProgram;

/**
 * Frame uses the singleton pattern and is main window of the application.
 * Any UI element such as a panel, button or text will be attached to this frame.
 */
public class Frame extends JFrame {
    private static Frame singleton;
    StartPanel startPanel;
    Program currentProgram;

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            public void run() {
                try {
                    new Frame();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        SwingUtilities.invokeLater(r);
    }

    /**
     * Sets up the settings for the entire window and shows the initial screen.
     */
    private Frame() throws FileNotFoundException {
        singleton = this;
        setFocusable(true);
        setBackground(new Color(38, 38, 38));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1500, 800);
        setIconImage(new javax.swing.ImageIcon("CSEClubLogoNoTextBorder.png").getImage());
        setLocationRelativeTo(null);
        setTitle("CSE");
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        startPanel = new StartPanel(this);
        add(startPanel);
    }

    public static Frame getInstance() {
        return singleton;
    }

    public void showGameList() {
        remove(startPanel);
        startProgram(createStartMenuProgram());
    }

    /**
     * Displays and starts the given program and adds it to the frame.
     */
    public void startProgram(Program program) {
        if (currentProgram != null) remove(currentProgram.getPanel());
        currentProgram = program;
        JPanel programPanel = program.initializeAndStartProgram(this);
        getContentPane().add(programPanel);
        setVisible(true);
        programPanel.requestFocusInWindow();
        paintAll(getGraphics());
    }

    /**
     * Hides the program and returns to the start menu if the program was active.
     */
    public void endProgram(Program program) {
        remove(program.getPanel());
        if (currentProgram == program) {
            startProgram(createStartMenuProgram());
        }
    }

}
