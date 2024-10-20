package Programs.HigherOrLower;

import StartMenu.Program;
import Utils.RoundButton;
import Utils.RoundTextField;
import Utils.UIUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HigherOrLowerPanel extends JPanel {
    HigherOrLowerGame game;
    JTextField guessInput;
    JLabel responseText;

    public HigherOrLowerPanel(Frame frame, Program program) {
        game = new HigherOrLowerGame();
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);
        add(Box.createVerticalStrut(10));

        UIUtils.addTitle("Higher or lower", this);

        add(Box.createVerticalStrut(10));

        JButton button = new RoundButton("Back", Color.WHITE, 20, Color.RED, 10);
        button.setFocusPainted(false);
        button.setFocusable(false);
        button.addActionListener((e) -> program.endProgram());
        add(button);
        add(Box.createVerticalStrut(30));

        JLabel guessText = new JLabel("Guess");
        guessText.setAlignmentX(CENTER_ALIGNMENT);
        guessText.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

        add(guessText);
        add(createSizeInputField());

        responseText = new JLabel("");
        responseText.setAlignmentX(CENTER_ALIGNMENT);
        responseText.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        add(responseText);

        var inputAdapter = getKeyAdapter(frame, program);
        addKeyListener(inputAdapter);
    }

    private JTextField createSizeInputField() {
        JTextField guessInput = new RoundTextField("0", 10);
        guessInput.setMaximumSize(new Dimension(120, 50));
        guessInput.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        guessInput.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel mainPanel = this;
        // In order to escape the input field we need to bind a key listener to request the focus back to the main panel
        guessInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    mainPanel.requestFocusInWindow();
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String response;
                    try {
                        response = game.guessNumber(Integer.parseInt(guessInput.getText()));
                    } catch (NumberFormatException exe) {
                        return;
                    }
                    responseText.setText(response);
                    mainPanel.requestFocusInWindow();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        return guessInput;
    }


    private KeyAdapter getKeyAdapter(Frame frame, Program program) {
        var blockEscapePanel = this;

        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                int keyCode = event.getKeyCode();
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    if (frame.hasFocus() || blockEscapePanel.hasFocus()) {
                        // Return to instruction list
                        program.endProgram();
                    } else {
                        // Stop focusing the text input
                        blockEscapePanel.requestFocusInWindow();
                    }
                }
            }
        };
    }
}
