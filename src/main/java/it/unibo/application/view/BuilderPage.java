package it.unibo.application.view;

import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.components.Component;
import it.unibo.application.data.entities.enums.Part;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BuilderPage extends JPanel {
    private Controller controller;
    private final JLabel totalPriceLabel;
    private final Map<Part, JLabel> priceLabels = new HashMap<>();

    public BuilderPage(final Controller controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());
        this.add(new TopBar(controller), BorderLayout.NORTH);

        JPanel middleSection = new JPanel();
        middleSection.setLayout(new GridLayout(9, 4));

        middleSection.add(new JLabel("Component"));
        middleSection.add(new JLabel("Selection"));
        middleSection.add(new JLabel("Price"));
        middleSection.add(new JLabel("Actions"));

        final Part[] parts = Part.values();
        for (final Part part : parts) {
            final List<Component> components = controller.getComponents(part);

            middleSection.add(new JLabel(part.toString().toUpperCase()));

            final JComboBox<String> comboBox = new JComboBox<>();
            comboBox.addItem("Select a " + part.toString());
            for (final Component component : components) {
                comboBox.addItem(component.getBaseInfo().getName());
            }
            middleSection.add(comboBox);

            final JLabel priceLabel = new JLabel();
            priceLabels.put(part, priceLabel);
            middleSection.add(priceLabel);

            final JButton viewDetailsButton = new JButton("View Details");
            viewDetailsButton.setEnabled(false);

            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    final int selectedIndex = comboBox.getSelectedIndex();
                    if (selectedIndex > 0) {
                        final Component selectedComponent = components.get(selectedIndex - 1);
                        priceLabel.setText("€" + selectedComponent.getBaseInfo().getMsrp());
                        viewDetailsButton.setEnabled(true);
                    } else {
                        priceLabel.setText("");
                        viewDetailsButton.setEnabled(false);
                    }
                    updateTotalPrice();
                }
            });


            viewDetailsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    controller.setDesiredPart(part);
                    final int selectedIndex = comboBox.getSelectedIndex();
                    if (selectedIndex > 0) {
                        final Component selectedComponent = components.get(selectedIndex - 1);
                        showPartDetails(selectedComponent);
                    }
                }
            });

            middleSection.add(viewDetailsButton);
        }

        this.add(middleSection, BorderLayout.CENTER);

        final JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPriceLabel = new JLabel("Total: €");
        bottomPanel.add(totalPriceLabel);

        final JButton saveButton = new JButton("Save Build");
        bottomPanel.add(saveButton);

        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void showPartDetails(final Component component) {
        String specs;
        switch (controller.getDesiredPart()) {
            case CPU:
                specs = ComponentSpecsUtility.formatSpecs(ComponentSpecsUtility.getCpuSpecs(component));
                break;
            case COOLER:
                specs = ComponentSpecsUtility.formatSpecs(ComponentSpecsUtility.getCoolerSpecs(component));
                break;
            case RAM:
                specs = ComponentSpecsUtility.formatSpecs(ComponentSpecsUtility.getRamSpecs(component));
                break;
            case PSU:
                specs = ComponentSpecsUtility.formatSpecs(ComponentSpecsUtility.getPsuSpecs(component));
                break;
            case MOTHERBOARD:
                specs = ComponentSpecsUtility.formatSpecs(ComponentSpecsUtility.getMotherboardSpecs(component));
                break;
            case STORAGE:
                specs = ComponentSpecsUtility.formatSpecs(ComponentSpecsUtility.getStorageSpecs(component));
                break;
            case GPU:
                specs = ComponentSpecsUtility.formatSpecs(ComponentSpecsUtility.getGpuSpecs(component));
                break;
            case CASE:
                specs = ComponentSpecsUtility.formatSpecs(ComponentSpecsUtility.getCaseSpecs(component));
                break;
            default:
                throw new IllegalStateException();
        }
        JOptionPane.showMessageDialog(this, specs, component.getBaseInfo().getName() + " Specs", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateTotalPrice() {
        double totalPrice = 0.0;

        for (JLabel priceLabel : priceLabels.values()) {
            String text = priceLabel.getText().replace("€", "").trim();
            try {
                if (!text.isEmpty()) {
                    totalPrice += Double.parseDouble(text);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        totalPriceLabel.setText("Total: €" + totalPrice);
    }
}