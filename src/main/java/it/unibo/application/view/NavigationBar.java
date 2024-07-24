package it.unibo.application.view;

import javax.swing.*;
import it.unibo.application.controller.Controller;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavigationBar extends JPanel {
    private static Color BAR_COLOR = new Color(84, 85, 120);
    public NavigationBar(Controller controller) {
        this.setLayout(new FlowLayout());
        this.setBackground(BAR_COLOR);
        JButton backButton = new JButton("Back");
        JButton refreshButton = new JButton("Refresh");

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
        this.add(backButton);
        this.add(refreshButton);
    }
}
