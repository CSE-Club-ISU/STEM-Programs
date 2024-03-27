package src.StartMenu;


import src.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;

public class GameListPanel extends JPanel {

    JScrollPane programList;
    JPanel programListContainer;

    public GameListPanel(Frame frame) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(frame.getSize());
        setBackground(Color.darkGray);

        JLabel title = UIUtils.addTextToComp("Program List", new Font(Font.SANS_SERIF, Font.BOLD, 30), this);
//        programListContainer = new JPanel();
//        programListContainer.setLayout(new BoxLayout(programListContainer, BoxLayout.Y_AXIS));
//        for (Program newProgram : getPrograms()) {
//            JPanel newProgramPanel = new JPanel();
//            UIUtils.addTextToComp(newProgram.name, newProgramPanel);
//            UIUtils.addTextToComp(newProgram.description, newProgramPanel);
//            newProgramPanel.setBackground(new Color(169, 169, 169));
//            programListContainer.add(newProgramPanel);
//            newProgramPanel.setSize(400,100);
//        }
//        programList = new JScrollPane(programListContainer);
//        programList.setSize(500, 1000);
//        programListContainer.setSize(500, 1000);
//        programList.setLayout(new ScrollPaneLayout());
//        programListContainer.setBackground(new Color(255, 0, 0));
//        add(programList);
        // Create a JPanel to hold a list of labels.
        programListContainer = new JPanel();
        programListContainer.setLayout(new BoxLayout(programListContainer, BoxLayout.Y_AXIS));

        // Add a large number of labels to the panel.
        for (Program newProgram : getPrograms()) {

//            JLabel label = new JLabel("Label " + i);
//            programListContainer.add(label);
            UIUtils.addTextToComp(newProgram.name, programListContainer);

        }

        // Create a JScrollPane and set the panel as its viewport.
        programList = new JScrollPane(programListContainer);
        programList.setMaximumSize(new Dimension(500, 1000));
        // Add the JScrollPane to the frame.
        this.add(programList);
        frame.setVisible(true);

    }


    private static List<Program> getPrograms() {
        //Basic functionality for now, replace later
        Program mazeProgram = new Program("Maze", "Solve a maze!");
        ArrayList<Program> programs = new ArrayList<>();
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        programs.add(mazeProgram);
        return programs;
    }

    private static class Program {
        private String name;
        private String description;

        public Program(String name, String description) {
            this.name = name;
            this.description = description;
        }
    }
}
