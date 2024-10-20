package Programs.BlockEscape;

import Programs.ProgramTemplate.ProgramTemplatePanel;
import StartMenu.Frame;
import StartMenu.Program;

import javax.swing.*;

public class BlockEscape {
    public static Program programFactory() {
        return new Program() {
            @Override
            public JPanel startProgram(Frame frame) {
                return new BlockEscapePanel(frame, this);
            }

            @Override
            public String getProgramName() {
                return "Block Escape";
            }

            @Override
            public String getProgramDescription() {
                return "Move blocks around to try and get the special block out of the grid.";
            }
        };
    }
}
