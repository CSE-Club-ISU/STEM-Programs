package src.StartMenu;


import src.UIUtils;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.StrokeBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.metal.MetalBorders;
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
        title.setForeground(Color.white);
        // Create a JPanel to hold a list of labels.
        programListContainer = new JPanel();
        programListContainer.setLayout(new BoxLayout(programListContainer, BoxLayout.Y_AXIS));

        // Add a large number of labels to the panel.
        for (Program newProgram : getPrograms()) {

            JPanel newProgramPanel = new JPanel();
            newProgramPanel.setLayout(new BoxLayout(newProgramPanel, BoxLayout.Y_AXIS));
            newProgramPanel.setMaximumSize(new Dimension(400,1000));
            newProgramPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
            UIUtils.addTextToComp(newProgram.name, newProgramPanel);
            UIUtils.addTextToComp(newProgram.description, 20, newProgramPanel);
            programListContainer.add(newProgramPanel);

        }

        // Create a JScrollPane and set the panel as its viewport.
        programList = new JScrollPane(programListContainer);
        programList.setMaximumSize(new Dimension(500, 1000));
        programList.setBorder(new EmptyBorder(10,10,10,10));
        programList.setBackground(Color.lightGray);
        programListContainer.setBackground(Color.darkGray);
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
