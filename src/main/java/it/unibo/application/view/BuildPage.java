package it.unibo.application.view;

import javax.swing.*;
import java.awt.*;

public class BuildPage extends JPanel {

    public BuildPage(AppGUI appGUI) {
        this.setLayout(new BorderLayout());
        JPanel topBar  = new JPanel();
        topBar.setLayout(new BoxLayout(topBar, BoxLayout.Y_AXIS));
        topBar.add(new JLabel("BUILD"));
        topBar.add(new JLabel("Build Name"));
        topBar.add(new JLabel("By user123"));
        topBar.setBackground(Color.CYAN);

        JPanel middleSection = new JPanel();
        middleSection.setLayout(new BoxLayout(middleSection, BoxLayout.Y_AXIS));
        middleSection.add(new JLabel("Description"));
        middleSection.add(new JTextArea(100, 50));

        middleSection.add(new JLabel("List of parts"));
        middleSection.add(new JLabel("Part 1"));
        middleSection.add(new JLabel("Part 2"));
        middleSection.add(new JLabel("Part 3"));
        middleSection.add(new JLabel("Part 4"));
        middleSection.add(new JLabel("Part 5"));
        middleSection.add(new JLabel("Part 6"));
        middleSection.add(new JLabel("Part 7"));

        JPanel commentSection = new JPanel();
        commentSection.setLayout(new BoxLayout(commentSection, BoxLayout.Y_AXIS));
        commentSection.add(new JLabel("Comments"));
        commentSection.add(new JLabel("Comment 1"));
        commentSection.add(new JLabel("Comment 2"));
        commentSection.add(new JLabel("Comment 3"));

        this.add(topBar, BorderLayout.NORTH);
        this.add(middleSection, BorderLayout.CENTER);
        this.add(commentSection, BorderLayout.SOUTH);
    }
}
