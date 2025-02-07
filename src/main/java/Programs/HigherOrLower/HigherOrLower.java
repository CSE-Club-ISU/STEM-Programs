package Programs.HigherOrLower;

import StartMenu.Frame;
import StartMenu.Program;

import javax.swing.*;

/**
 * Here is an unimplemented game of Higher or Lower, only the basic UI has been added since it can be more difficult to set up.
 * The player can either choose a number and have the computer guess it or try and guess the computer's number.
 */
public class HigherOrLower {
    public static Program programFactory() {
        return new Program() {
            @Override
            public JPanel startProgram(Frame frame) {
                return new HigherOrLowerPanel(frame, this);
            }

            @Override
            public String getProgramName() {
                return "Higher Or Lower";
            }

            @Override
            public String getProgramDescription() {
                return "Guess the secret number in as few guesses as you can.";
            }

            @Override
            public int getProgramPriority() {
                return 6;
            }
        };
    }
}
