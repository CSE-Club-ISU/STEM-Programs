package Programs.BlockEscape;

import StartMenu.Program;
import Utils.RoundButton;
import Utils.RoundPanel;
import Utils.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BlockEscapePanel extends JPanel {
    BoardPanel boardPanel;

    public BlockEscapePanel(Frame frame, Program program) {
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);
        add(Box.createVerticalStrut(10));

        UIUtils.addTitle("Block Escape", this);

        add(Box.createVerticalStrut(10));

        JButton button = new RoundButton("Back", Color.WHITE, 20, Color.RED, 10);
        button.setFocusPainted(false);
        button.setFocusable(false);
        button.addActionListener((e) -> program.endProgram());
        add(button);
        add(Box.createVerticalStrut(10));

        var inputAdapter = getKeyAdapter(frame, program);
        addKeyListener(inputAdapter);

        JPanel boardHolder = new JPanel();
        boardHolder.setMaximumSize(new Dimension(1000, 1000));
        boardPanel = new BoardPanel(frame);
        boardPanel.setGame(new BlockEscapeGame(10, 10));
        boardHolder.add(boardPanel);
        add(boardHolder);
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
