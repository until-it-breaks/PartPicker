package it.unibo.application.view;

import javax.swing.*;

import it.unibo.application.controller.Controller;
import it.unibo.application.model.enums.State;
import it.unibo.application.view.premades.BottomBar;
import it.unibo.application.view.premades.TopBar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuildPage extends JPanel {

    public BuildPage(Controller controller) {
        this.setLayout(new BorderLayout());
        JPanel topBar  = new JPanel();
        topBar.setLayout(new BoxLayout(topBar, BoxLayout.Y_AXIS));
        topBar.add(new TopBar(controller));
        topBar.add(new JLabel("BUILD"));
        topBar.add(new JLabel("Build Name"));

        topBar.add(new JLabel("By user123"));
        JButton vistAuthor = new JButton("Visit author");
        vistAuthor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setAppState(State.VIEWING_PROFILE);
                controller.getProfileSelector().setCurrentProfile(controller.getProfileSelector().getCurrentProfile()); //TODO to be modified
            }
            
        });
        topBar.add(vistAuthor);

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
        commentSection.add(new BottomBar(controller));

        this.add(topBar, BorderLayout.NORTH);
        this.add(middleSection, BorderLayout.CENTER);
        this.add(commentSection, BorderLayout.SOUTH);
    }
}
