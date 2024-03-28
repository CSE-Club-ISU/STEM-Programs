package src.StartMenu;


import src.Programs.MazeGame.MazePanel;
import src.UIUtils;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProgramListPanel extends JPanel {

    JScrollPane programList;
    JPanel programListContainer;

    public ProgramListPanel(Frame frame) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(frame.getSize());
        setBackground(Color.darkGray);

        JLabel title = UIUtils.addTitle("Program List", new Font(Font.SANS_SERIF, Font.BOLD, 30), this);
        title.setForeground(Color.white);
        // Create a JPanel to hold a list of labels.
        programListContainer = new JPanel();
        programListContainer.setLayout(new BoxLayout(programListContainer, BoxLayout.Y_AXIS));
        // Add a large number of labels to the panel.
        for (Program newProgram : getPrograms()) {
            JButton newProgramPanel = new JButton();
            newProgramPanel.setLayout(new BoxLayout(newProgramPanel, BoxLayout.Y_AXIS));

            newProgramPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
            newProgramPanel.addActionListener(newProgram.action);
            newProgramPanel.setAlignmentX(CENTER_ALIGNMENT);

            //Add the text
            UIUtils.addTitle(newProgram.name, newProgramPanel);
            JTextArea text = UIUtils.addTextArea(newProgram.description,20,null);
            JScrollPane scrollPane = new JScrollPane(text);
            newProgramPanel.add(scrollPane);

            newProgramPanel.setMaximumSize(new Dimension(600,180));
            programListContainer.add(newProgramPanel);
        }

        // Create a JScrollPane and set the panel as its viewport.
        programList = new JScrollPane(programListContainer);
        programList.setMaximumSize(new Dimension(800, 1000));
        programList.setBorder(new EmptyBorder(10,10,10,10));
        programList.setBackground(Color.lightGray);
        programListContainer.setBackground(Color.darkGray);
        programList.getVerticalScrollBar().setUnitIncrement(20);
        // Add the JScrollPane to the frame.
        this.add(programList);
        frame.setVisible(true);

    }

    private static List<Program> getPrograms() {
        //Basic functionality for now, replace later
        Program mazeProgram = new Program("Maze", "Solve a maze! Try to get from the green square to the red square!");
        ArrayList<Program> programs = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
            programs.add(mazeProgram);
//        }
        return programs;
    }

    private static class Program {
        private String name;
        private String description;

        private ActionListener action;

        public Program(String name, String description) {
            this.name = name;
            this.description = description;
            action = (e) -> Frame.getInstance().startProgram(new MazePanel(Frame.getInstance()));
        }
    }
}
