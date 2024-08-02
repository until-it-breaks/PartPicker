package it.unibo.application.view;

import javax.swing.*;
import java.awt.*;
import it.unibo.application.controller.Controller;
import it.unibo.application.model.enums.State;
import it.unibo.application.view.premades.TopBar;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class BuildPage extends JPanel {

    public BuildPage(Controller controller) {
        this.setLayout(new BorderLayout());

        JPanel middleSection = new JPanel();
        middleSection.setLayout(new BoxLayout(middleSection, BoxLayout.Y_AXIS));
        
        JPanel buildInfo = new JPanel();
        JPanel buildDescription = new JPanel();
        JPanel buildParts = new JPanel();
        JPanel buildComments = new JPanel();

        buildInfo.setLayout(new BoxLayout(buildInfo, BoxLayout.Y_AXIS));
        JLabel buildLabel = new JLabel("BUILD", JLabel.CENTER);
        buildLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buildInfo.add(buildLabel);
    
        JLabel buildNameLabel = new JLabel("Build Name", JLabel.CENTER);
        buildNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buildInfo.add(buildNameLabel);


        JLabel userLabel = new JLabel(
                "<HTML><U>" + controller.getProfileSelector().getCurrentProfile().getProfileName() + "</U></HTML>",
                JLabel.CENTER
        );
        userLabel.setForeground(Color.BLUE);
        userLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.getProfileSelector().setCurrentProfile(controller.getProfileSelector().getCurrentProfile());
                controller.setAppState(State.VIEWING_PROFILE);
            }
        });
        buildInfo.add(userLabel);

        buildInfo.add(new JButton("Upvote"));
        buildInfo.add(new JButton("Downvote"));


        buildDescription.setLayout(new BoxLayout(buildDescription, BoxLayout.Y_AXIS));
        buildDescription.add(new JLabel("Description"));
        JTextArea descriptionArea = new JTextArea(100, 20);
        descriptionArea.setEnabled(false);
        buildDescription.add(descriptionArea);

        buildParts.setLayout(new BoxLayout(buildParts, BoxLayout.Y_AXIS));
        buildParts.add(new JLabel("List of parts"));
        buildParts.add(new JLabel("Part 1"));
        buildParts.add(new JLabel("Part 2"));
        buildParts.add(new JLabel("Part 3"));
        buildParts.add(new JLabel("Part 4"));
        buildParts.add(new JLabel("Part 5"));
        buildParts.add(new JLabel("Part 6"));
        buildParts.add(new JLabel("Part 7"));

        buildComments.setLayout(new BoxLayout(buildComments, BoxLayout.Y_AXIS));
        buildComments.add(new JLabel("Comments"));
        buildComments.add(new JLabel("Comment 1"));
        buildComments.add(new JLabel("Comment 2"));
        buildComments.add(new JLabel("Comment 3"));

        middleSection.add(buildInfo);
        middleSection.add(buildDescription);
        middleSection.add(buildParts);
        middleSection.add(buildComments);

        this.add(new TopBar(controller), BorderLayout.NORTH);
        this.add(middleSection, BorderLayout.CENTER);
    }
}
