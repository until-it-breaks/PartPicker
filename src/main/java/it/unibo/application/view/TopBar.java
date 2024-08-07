package it.unibo.application.view;

import javax.swing.*;
import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.enums.State;
import it.unibo.application.data.entities.login.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopBar extends JPanel {
    private static final Color BAR_COLOR = new Color(84, 85, 120);
    private static final Color TEXT_COLOR = Color.WHITE;
    
    public TopBar(final Controller controller) {
        this.setLayout(new BorderLayout());
        this.setBackground(BAR_COLOR);

        final JButton backButton = new JButton("Back");
        final JButton homeButton = new JButton("Home");

        final JLabel currentUserLabel = new JLabel();
        final User currentUser = controller.getLoggedUser();
        currentUserLabel.setText(currentUser.getUsername() + (currentUser.isModerator() ? " (Moderator)" : ""));
        currentUserLabel.setForeground(TEXT_COLOR);

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

        final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setOpaque(false);
        buttonPanel.add(homeButton);
        buttonPanel.add(backButton);

        final JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        userPanel.setOpaque(false);
        userPanel.add(currentUserLabel);

        this.add(buttonPanel, BorderLayout.WEST);
        this.add(userPanel, BorderLayout.EAST);
    }
}
