package StartMenu;


import Utils.RoundButton;
import Utils.RoundPanel;
import Utils.UIUtils;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * ProgramListPanel manages finding the various programs under the Programs directory
 * and manages a panel in which the user can select the program that they want.
 */
public class ProgramListPanel extends JPanel {
    JScrollPane programList;
    JPanel programListContainer;

    public ProgramListPanel(Frame frame) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(frame.getSize());
        setBackground(Color.darkGray);
        add(Box.createVerticalStrut(10));
        JLabel title = UIUtils.addTitle("Program List", this);
        title.setForeground(Color.white);
        add(Box.createVerticalStrut(20));

        // Create a JPanel to hold a list of labels.
        programListContainer = new RoundPanel(Color.LIGHT_GRAY, 50);
        programListContainer.setLayout(new BoxLayout(programListContainer, BoxLayout.Y_AXIS));
        programListContainer.add(Box.createVerticalStrut(10));

        // Add each program to the panel.
        for (Program newProgram : getPrograms()) {
            JButton newProgramPanel = new RoundButton("", Color.WHITE, 10,Color.WHITE, 20);
            newProgramPanel.setLayout(new BoxLayout(newProgramPanel, BoxLayout.Y_AXIS));

            newProgramPanel.addActionListener((l) -> frame.startProgram(newProgram));

            // Add the text of the program
            UIUtils.addTitle(newProgram.getProgramName(), 30, newProgramPanel);
            JTextArea text = UIUtils.addTextArea(newProgram.getProgramDescription(), 20, null);
            text.setHighlighter(null);
            text.setOpaque(false);
            text.setFocusable(false);
            text.setBackground(new Color(0,0,0,0));

            // Add a scroll pane around the text in case it gets too big
            JScrollPane scrollPane = new JScrollPane(text);
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);
            scrollPane.setFocusable(false);
            scrollPane.setEnabled(false);
            scrollPane.setBorder(new EmptyBorder(0,0,0,0));

            newProgramPanel.add(scrollPane);
            newProgramPanel.setMaximumSize(new Dimension(600, 180));
            programListContainer.add(newProgramPanel);
            programListContainer.add(Box.createVerticalStrut(10));
        }

        // Create a JScrollPane and set the panel as its viewport.
        programList = new JScrollPane(programListContainer);
        programList.setMaximumSize(new Dimension(800, 1000));
        programList.setOpaque(false);
        programList.getViewport().setOpaque(false);
        programList.setBorder(new EmptyBorder(0,0,0,0));
        programList.getVerticalScrollBar().setUnitIncrement(20);
        // Add the JScrollPane to the frame.
        this.add(programList);
        this.add(Box.createVerticalStrut(10));
        frame.setVisible(true);

    }

    /**
     * Scans the Programs directory for all subdirectories, parses them and returns them in a list.
     */
    private static List<Program> getPrograms() {
        ArrayList<Program> programs = new ArrayList<>();

        File file = new File("src/main/java/Programs");
        try {
            // Find all directories/folders in the Program directory
            List<String> programNames = Arrays.stream(file.listFiles())
                    .filter(File::isDirectory)
                    .map(File::getName).toList();
            // Each directory name will match a file with a class that has a method called programFactory()
            // which will return the program
            programNames.forEach(n -> {
                try {
                    // Now we use Java reflection to find the programFactory() method.
                    // There are many ways in which this could fail, so we try and give the best help we can.
                    programs.add((Program) ClassLoader
                            .getSystemClassLoader()
                            .loadClass("Programs." + n + "." + n)
                            .getMethod("programFactory")
                            .invoke(null));
                } catch (ClassNotFoundException e) {
                    System.err.println("Failed to load program: " + n + ". It is missing a class of the name " + n + " that can be used to create a program.");
                } catch (NoSuchMethodException e) {
                    System.err.println("Failed to find corresponding programFactory() function in: " + n);
                } catch (InvocationTargetException e) {
                    System.err.println("The static method programFactory() in the program " + n + " does not exist.");
                } catch (IllegalAccessException e) {
                    System.err.println("The static method programFactory() in the program " + n + " was not public.");
                } catch (NullPointerException e) {
                    System.err.println("The static method programFactory() in the program " + n + " was not static.");
                } catch (ClassCastException e) {
                    System.err.println("The static method programFactory() in the program " + n + " returned an object that does not extend from Program.");
                }
            });
        } catch (Exception e) {
            System.out.println("Failed to load programs!");
        }
        // Now we sort each of the programs based on their priority and return them in a list.
        return programs.stream().sorted(Comparator.comparingInt(Program::getProgramPriority)).toList();
    }

    static Program createStartMenuProgram() {
        return new Program() {
            @Override
            public JPanel startProgram(Frame frame) {
                return new ProgramListPanel(frame);
            }

            @Override
            public String getProgramName() {
                return "Start Menu";
            }

            @Override
            public String getProgramDescription() {
                return null;
            }

            @Override
            public int getProgramPriority() {
                return 0;
            }
        };
    }
}
