package it.unibo.application.view.premades;

import javax.swing.*;
import it.unibo.application.controller.Controller;
import it.unibo.application.model.enums.State;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopBar extends JPanel {
    private static Color BAR_COLOR = new Color(84, 85, 120);
    public TopBar(Controller controller) {
        this.setLayout(new FlowLayout());
        this.setBackground(BAR_COLOR);
        JButton homeButton = new JButton("Home");
        JButton backButton = new JButton("Back");
        JButton refreshButton = new JButton("Refresh");

        final JLabel userInfoLabel = new JLabel("Logged in as: User");
        final JButton quitButton = new JButton("Quit");

        quitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getLoginHandler().stopConnectionCheckRoutine();
                controller.setAppState(State.WELCOME);
            }
        });

        this.add(userInfoLabel);
        this.add(quitButton);

        homeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setAppState(State.OVERVIEW);
            }
        });

        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setAppState(controller.getPreviousAppState());
            }
        });

        refreshButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO needs more work
                System.out.println("Page refreshed");
            }
        });
        this.add(homeButton);
        this.add(backButton);
        this.add(refreshButton);
    }
}
