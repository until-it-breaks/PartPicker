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
import java.util.HashMap;

public class BuilderPage extends JPanel {
    private Controller controller;
    public BuilderPage(Controller controller) {
        this.controller = controller;
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
            middleSection.add(comboBox);
            JLabel priceLabel = new JLabel();
            middleSection.add(priceLabel);

            JButton viewDetailsButton = new JButton("View Details");

            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedIndex = comboBox.getSelectedIndex();
                    if (selectedIndex > 0) {
                        Component selectedComponent = components.get(selectedIndex - 1);
                        priceLabel.setText("$" + selectedComponent.msrp);
                        viewDetailsButton.setEnabled(true);
                        viewDetailsButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
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
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(new JLabel("Total : 300$"));
        JButton saveButton = new JButton("Save Build");
        bottomPanel.add(saveButton);

        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void showPartDetails(Component component) {
        Map<String, String> specs;
        switch (component.componentType) {
            case "Gpu":
                Gpu gpu = controller.getGpuById(component.componentId);
                specs = gpu.toStringMap();
                break;
            case "Cpu":
                Cpu cpu = controller.getCpuById(component.componentId);
                specs = cpu.toStringMap();
                break;
            case "Motherboard":
                Motherboard motherboard = controller.getMotherboardById(component.componentId);
                specs = motherboard.toStringMap();
                break;
            case "Ram":
                Ram ram = controller.getRamById(component.componentId);
                specs = ram.toStringMap();
                break;
            case "Psu":
                Psu psu = controller.getPsuById(component.componentId);
                specs = psu.toStringMap();
                break;
            case "Case":
                Case _case = controller.getCaseById(component.componentId);
                specs = _case.toStringMap();
                break;
            case "Storage":
                Storage storage = controller.getStorageById(component.componentId);
                specs = storage.toStringMap();
                break;
            case "Cooler":
                Cooler cooler = controller.getCoolerById(component.componentId);
                specs = cooler.toStringMap();
                break;
            default:
                specs = new HashMap<String, String>();
                break;
        }
        JOptionPane.showMessageDialog(this,
                specs.toString(),
                "Part Details",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
