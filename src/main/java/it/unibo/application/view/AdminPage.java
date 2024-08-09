package it.unibo.application.view;

import javax.swing.*;

import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.enums.Part;
import it.unibo.application.data.entities.enums.State;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JPanel {
    private final Controller controller;
    private JComboBox<Part> partComboBox;
    private JButton addButton;
    private JButton backButton;

    public AdminPage(Controller controller) {
        this.controller = controller;

        setLayout(new BorderLayout());

        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButtonPanel.setOpaque(false);

        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(80, 30));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setAppState(State.WELCOME);
            }
        });

        backButtonPanel.add(backButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        partComboBox = new JComboBox<>(Part.values());
        partComboBox.setPreferredSize(new Dimension(150, 30));

        addButton = new JButton("Add");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Part selectedPart = (Part) partComboBox.getSelectedItem();
            }
        });

        mainPanel.add(new JLabel("Select type of component to add:"));
        mainPanel.add(partComboBox);
        mainPanel.add(addButton);

        add(backButtonPanel, BorderLayout.PAGE_START);
        add(mainPanel, BorderLayout.CENTER);
    }

}