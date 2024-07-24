package it.unibo.application.view;

import javax.swing.*;
import it.unibo.application.controller.Controller;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavigationBar extends JPanel {
    Controller controller;
    public NavigationBar(Controller controller) {
        this.setLayout(new FlowLayout());
        this.setBackground(Color.BLACK);
        JButton backButton = new JButton("Back");
        JButton refreshButton = new JButton("Refresh");

        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setAppState(controller.getPreviousAppState());
            }
        });
        this.add(backButton);
        this.add(refreshButton);
    }
}
