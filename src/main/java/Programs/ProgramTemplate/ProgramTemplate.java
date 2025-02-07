package Programs.ProgramTemplate;

import StartMenu.Frame;
import StartMenu.Program;

import javax.swing.*;

/**
 * Here is an example program that shows how to set up programs.
 * Note that this file name and the class name must match the directory name in the programs directory.
 */
public class ProgramTemplate {
    public static Program programFactory() {

        // Here we are returning a new anonymous class which defines the name and description
        // of the program that we want to display.
        return new Program() {
            // We also define how to display our program by returning a panel that will set up our program
            @Override
            public JPanel startProgram(Frame frame) {
                return new ProgramTemplatePanel(frame, this);
            }

            @Override
            public String getProgramName() {
                return "Demo Program";
            }

            @Override
            public String getProgramDescription() {
                return "Here is a template of a program with example elements to use";
            }

            @Override
            public int getProgramPriority() {
                return 1000;
            }
        };
    }
}
