package it.unibo.application.view;

import java.awt.*;
import javax.swing.*;

import it.unibo.application.controller.Controller;
import it.unibo.application.view.premades.TopBar;

public class ProfilePage extends JPanel {

    public ProfilePage(Controller controller) {
        this.setLayout(new BorderLayout());

        JPanel middleSection = new JPanel();
        JPanel profileInfo = new JPanel();
        profileInfo.setLayout(new BoxLayout(profileInfo, BoxLayout.Y_AXIS));
        profileInfo.add(new JLabel("Profile of:" + controller.getProfileSelector().getCurrentProfile().getProfileName()));
        profileInfo.add(new JLabel("Registered since 2001"));
        profileInfo.add(new JLabel("Rating:" + controller.getProfileSelector().getCurrentProfile().getRating()));

        JPanel buildInfo = new JPanel();
        buildInfo.setLayout(new BoxLayout(buildInfo, BoxLayout.Y_AXIS));
        buildInfo.add(new JLabel("Builds:"));
        buildInfo.add(new JLabel("Build 1"));
        buildInfo.add(new JLabel("Build 2"));
        buildInfo.add(new JLabel("Build 3"));

        middleSection.add(profileInfo);
        middleSection.add(buildInfo);

        this.add(new TopBar(controller), BorderLayout.NORTH);
        this.add(middleSection, BorderLayout.CENTER);
    }
}
