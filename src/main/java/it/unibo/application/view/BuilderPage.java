package it.unibo.application.view;

import it.unibo.application.controller.Controller;
import it.unibo.application.model.enums.Part;
import it.unibo.application.data.entities.Case;
import it.unibo.application.data.entities.Component;
import it.unibo.application.data.entities.Cooler;
import it.unibo.application.data.entities.Cpu;
import it.unibo.application.data.entities.Gpu;
import it.unibo.application.data.entities.Motherboard;
import it.unibo.application.data.entities.Psu;
import it.unibo.application.data.entities.Ram;
import it.unibo.application.data.entities.Storage;

import java.util.List;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

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
            final List<Component> components = controller.getComponentsByType(part);
            middleSection.add(new JLabel(part.toString().toUpperCase()));

            final JComboBox<String> comboBox = new JComboBox<>();
            comboBox.addItem("Select a " + part.toString());
            for (final Component component : components) {
                comboBox.addItem(component.componentName);
            }
            middleSection.add(comboBox);
            final JLabel priceLabel = new JLabel();
            middleSection.add(priceLabel);

            final JButton viewDetailsButton = new JButton("View Details");

            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    final int selectedIndex = comboBox.getSelectedIndex();
                    if (selectedIndex > 0) {
                        final Component selectedComponent = components.get(selectedIndex - 1);
                        priceLabel.setText("$" + selectedComponent.msrp);
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
        Map<String, String> specs;
        switch (component.componentType) {
            case "Gpu":
                final Gpu gpu = controller.getGpuById(component.componentId);
                specs = ComponentSpecsUtility.getGpuSpecs(gpu);
                break;
            case "Cpu":
                final Cpu cpu = controller.getCpuById(component.componentId);
                specs = ComponentSpecsUtility.getCpuSpecs(cpu);
                break;
            case "Motherboard":
                final Motherboard motherboard = controller.getMotherboardById(component.componentId);
                specs = ComponentSpecsUtility.getMotherboardSpecs(motherboard);
                break;
            case "Ram":
                final Ram ram = controller.getRamById(component.componentId);
                specs = ComponentSpecsUtility.getRamSpecs(ram);
                break;
            case "Psu":
                final Psu psu = controller.getPsuById(component.componentId);
                specs = ComponentSpecsUtility.getPsuSpecs(psu);
                break;
            case "Case":
                final Case _case = controller.getCaseById(component.componentId);
                specs = ComponentSpecsUtility.getCaseSpecs(_case);
                break;
            case "Storage":
                final Storage storage = controller.getStorageById(component.componentId);
                specs = ComponentSpecsUtility.getStorageSpecs(storage);
                break;
            case "Cooler":
                final Cooler cooler = controller.getCoolerById(component.componentId);
                specs = ComponentSpecsUtility.getCoolerSpecs(cooler);
                break;
            default:
                specs = Map.of();
                break;
        }
        JOptionPane.showMessageDialog(this,
                ComponentSpecsUtility.formatSpecs(specs),
                component.componentName +" specifications",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
