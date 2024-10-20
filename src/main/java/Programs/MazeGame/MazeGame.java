package Programs.MazeGame;

import StartMenu.Frame;
import StartMenu.Program;

import javax.swing.*;

public class MazeGame {
    public static Program programFactory() {
        return new Program() {
            @Override
            public JPanel startProgram(Frame frame) {
                return new MazePanel(frame, this);
            }

            @Override
            public String getProgramName() {
                return "Maze";
            }

            @Override
            public String getProgramDescription() {
                return "Solve a maze! Try to get from the green square to the red square!";
            }

            @Override
            public int getProgramPriority() {
                return 0;
            }
        };
    }
}
