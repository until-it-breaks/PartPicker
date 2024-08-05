package it.unibo.application.view;

import javax.swing.*;
import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.enums.State;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopBar extends JPanel {
    private static Color BAR_COLOR = new Color(84, 85, 120);
    public TopBar(final Controller controller) {
        this.setLayout(new FlowLayout());
        this.setBackground(BAR_COLOR);
    
        final JButton backButton = new JButton("Back");
        final JButton homeButton = new JButton("Home");

        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setAppState(State.OVERVIEW);
            }
        });

        homeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setAppState(State.WELCOME);
            }
        });

        this.add(homeButton);
        this.add(backButton);
    }
}
