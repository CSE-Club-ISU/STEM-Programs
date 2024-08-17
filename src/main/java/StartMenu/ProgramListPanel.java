package StartMenu;


import src.Programs.MazeGame.MazePanel;
import src.Programs.ProgramTemplate.ProgramTemplatePanel;
import src.UI.UIUtils;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProgramListPanel extends JPanel {

    JScrollPane programList;
    JPanel programListContainer;

    public ProgramListPanel(java.awt.Frame frame) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(frame.getSize());
        setBackground(Color.darkGray);

        JLabel title = UIUtils.addTitle("Program List", new Font(Font.SANS_SERIF, Font.BOLD, 30), this);
        title.setForeground(Color.white);
        add(Box.createVerticalStrut(10));

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
            programListContainer.add(Box.createVerticalStrut(10));
        }

        // Create a JScrollPane and set the panel as its viewport.
        programList = new JScrollPane(programListContainer);
        programList.setMaximumSize(new Dimension(800, 1000));
        programList.setBorder(new LineBorder(Color.LIGHT_GRAY, 10, true));
        programList.setBackground(Color.darkGray);
        programListContainer.setBackground(Color.darkGray);
        programList.getVerticalScrollBar().setUnitIncrement(20);
        // Add the JScrollPane to the frame.
        this.add(programList);
        frame.setVisible(true);

    }

    private static List<Program> getPrograms() {
        //Basic functionality for now, replace later
        Program mazeProgram = new Program("Maze", "Solve a maze! Try to get from the green square to the red square!", (e) -> java.awt.Frame.getInstance().startProgram(new MazePanel(java.awt.Frame.getInstance())));
        Program demoProgram = new Program("Demo", "Here is a template of a program with example elements", (e) -> java.awt.Frame.getInstance().startProgram(new ProgramTemplatePanel(Frame.getInstance())));

        ArrayList<Program> programs = new ArrayList<>();
        programs.add(mazeProgram);
        programs.add(demoProgram);

        return programs;
    }

    private static class Program {
        private String name;
        private String description;

        private ActionListener action;

        public Program(String name, String description, ActionListener action) {
            this.name = name;
            this.description = description;
            this.action = action;
        }
    }
}
