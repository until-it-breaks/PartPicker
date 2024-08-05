package it.unibo.application.view;

import it.unibo.application.controller.Controller;
import it.unibo.application.model.enums.Part;
import it.unibo.application.data.entities.Component;

import java.util.List;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuilderPage extends JPanel {
    private final Controller controller;
    public BuilderPage(final Controller controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());
        this.add(new TopBar(controller), BorderLayout.NORTH);

        final JPanel middleSection = new JPanel();
        middleSection.setLayout(new GridLayout(9, 4));

        middleSection.add(new JLabel("Component"));
        middleSection.add(new JLabel("Selection"));
        middleSection.add(new JLabel("Price"));
        middleSection.add(new JLabel("Actions"));

        final Part[] parts = Part.values();
        for (final Part part : parts) {
            final List<Component> components;
            components = controller.getComponents(part);

            middleSection.add(new JLabel(part.toString().toUpperCase()));

            final JComboBox<String> comboBox = new JComboBox<>();
            comboBox.addItem("Select a " + part.toString());
            for (final Component component : components) {
                comboBox.addItem(component.getBaseInfo().getName());
            }
            middleSection.add(comboBox);
            final JLabel priceLabel = new JLabel();
            middleSection.add(priceLabel);

            final JButton viewDetailsButton = new JButton("View Details");

            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    controller.setDesiredPart(part);
                    final int selectedIndex = comboBox.getSelectedIndex();
                    if (selectedIndex > 0) {
                        final Component selectedComponent = components.get(selectedIndex - 1);
                        priceLabel.setText("$" + selectedComponent.getBaseInfo().getMsrp());
                        viewDetailsButton.setEnabled(true);
                        viewDetailsButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(final ActionEvent e) {
                                showPartDetails(selectedComponent);
                            }
                        });
                    } else {
                        priceLabel.setText("");
                        viewDetailsButton.setEnabled(false);
                    }
                }
            });
            middleSection.add(viewDetailsButton);
        }

        this.add(middleSection, BorderLayout.CENTER);
        final JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(new JLabel("Total : 300$"));
        final JButton saveButton = new JButton("Save Build");
        bottomPanel.add(saveButton);

        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void showPartDetails(final Component component) {
        String specs;
        switch (controller.getDesiredPart()) {
            case Part.CPU:
                specs = ComponentSpecsUtility.formatSpecs(ComponentSpecsUtility.getCpuSpecs(component));
                break;
            case Part.COOLER:
                specs = ComponentSpecsUtility.formatSpecs(ComponentSpecsUtility.getCoolerSpecs(component));
                break;
            case Part.RAM:
                specs = ComponentSpecsUtility.formatSpecs(ComponentSpecsUtility.getRamSpecs(component));
                break;
            case Part.PSU:
                specs = ComponentSpecsUtility.formatSpecs(ComponentSpecsUtility.getPsuSpecs(component));
                break;
            case Part.MOTHERBOARD:
                specs = ComponentSpecsUtility.formatSpecs(ComponentSpecsUtility.getMotherboardSpecs(component));
                break;
            case Part.STORAGE:
                specs = ComponentSpecsUtility.formatSpecs(ComponentSpecsUtility.getStorageSpecs(component));
                break;
            case Part.GPU:
                specs = ComponentSpecsUtility.formatSpecs(ComponentSpecsUtility.getGpuSpecs(component));
                break;
            case Part.CASE:
                specs = ComponentSpecsUtility.formatSpecs(ComponentSpecsUtility.getCaseSpecs(component));
                break;
            default:
                throw new IllegalStateException();
        }
        JOptionPane.showMessageDialog(this, component.getBaseInfo().getName() + "specs", specs, JOptionPane.INFORMATION_MESSAGE);
    }
}
