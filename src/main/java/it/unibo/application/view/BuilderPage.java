package it.unibo.application.view;

import javax.swing.JPanel;

import it.unibo.application.controller.Controller;
import it.unibo.application.model.enums.Part;
import it.unibo.application.data.entities.Component;
import java.util.List;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

public class BuilderPage extends JPanel {
    public BuilderPage(Controller controller) {
        this.setLayout(new BorderLayout());

        this.add(new TopBar(controller), BorderLayout.NORTH);

        JPanel middleSection = new JPanel();
        middleSection.setLayout(new GridLayout(9, 4));

        middleSection.add(new JLabel("Component"));
        middleSection.add(new JLabel("Selection"));
        middleSection.add(new JLabel("Price"));
        middleSection.add(new JLabel("Actions"));

        Part[] parts = Part.values();
        for (Part part : parts) {
            List<Component> components = controller.getComponentsByType(part);
            middleSection.add(new JLabel(part.toString().toUpperCase()));

            JComboBox<String> comboBox = new JComboBox<>();
            comboBox.addItem("Select a " + part.toString());
            for (Component component : components) {
                comboBox.addItem(component.componentName);
            }
            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            });
            middleSection.add(comboBox);
            JLabel priceLabel = new JLabel();
            middleSection.add(priceLabel);

            JButton viewDetailsButton = new JButton("View Details");
            viewDetailsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showPartDetails(part);
                }
            });
            middleSection.add(viewDetailsButton);
        }

        // Add middle section to the center of the panel
        this.add(middleSection, BorderLayout.CENTER);

        // Add Total and Save Button to the bottom
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(new JLabel("Total : 300$"));
        JButton saveButton = new JButton("Save Build");
        bottomPanel.add(saveButton);

        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void showPartDetails(Part part) {
        // Example details display
        JOptionPane.showMessageDialog(this,
                "Details for " + part.toString() + ":\n" +
                "Example detail information for " + part.toString(),
                "Part Details",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
