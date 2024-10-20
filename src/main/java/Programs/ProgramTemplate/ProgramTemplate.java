package Programs.ProgramTemplate;

import StartMenu.Frame;
import StartMenu.Program;

import javax.swing.*;

public class ProgramTemplate {
    public static Program programFactory() {
        return new Program() {
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
