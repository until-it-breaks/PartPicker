package it.unibo.application.view;

import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.components.Component;
import it.unibo.application.data.entities.enums.Part;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuilderPage extends JPanel {
    private final Controller controller;
    private final JLabel totalPriceLabel;
    private final Map<Part, List<JComboBox<String>>> comboBoxes = new HashMap<>();
    private final Map<Part, List<JLabel>> priceLabels = new HashMap<>();
    private final JPanel middleSection;
    private final Map<Part, JButton> viewDetailsButtons = new HashMap<>();

    public BuilderPage(final Controller controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());
        this.add(new TopBar(controller), BorderLayout.NORTH);

        middleSection = new JPanel();
        middleSection.setLayout(new GridLayout(0, 4));

        middleSection.add(new JLabel("Component"));
        middleSection.add(new JLabel("Selection"));
        middleSection.add(new JLabel("Price"));
        middleSection.add(new JLabel("Actions"));

        for (final Part part : Part.values()) {
            addPartRow(part);
        }

        this.add(middleSection, BorderLayout.CENTER);

        final JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPriceLabel = new JLabel("Total: €");
        bottomPanel.add(totalPriceLabel);

        final JButton saveButton = new JButton("Save Build");
        bottomPanel.add(saveButton);

        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addPartRow(final Part part) {
        final List<Component> components = controller.getComponents(part);

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
        viewDetailsButton.setEnabled(false);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final int selectedIndex = comboBox.getSelectedIndex();
                if (selectedIndex > 0) {
                    final Component selectedComponent = components.get(selectedIndex - 1);
                    priceLabel.setText("€" + selectedComponent.getBaseInfo().getMsrp());
                    viewDetailsButton.setEnabled(true);
                    if (part == Part.GPU || part == Part.RAM || part == Part.STORAGE) {
                        addPartRow(part);
                    }
                } else {
                    priceLabel.setText("");
                    viewDetailsButton.setEnabled(false);
                    if (part == Part.GPU || part == Part.RAM || part == Part.STORAGE) {
                        removePartRow(part, comboBox);
                    }
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

        comboBoxes.computeIfAbsent(part, k -> new ArrayList<>()).add(comboBox);
        priceLabels.computeIfAbsent(part, k -> new ArrayList<>()).add(priceLabel);
        viewDetailsButtons.put(part, viewDetailsButton);

        revalidate();
        repaint();
    }

    private void removePartRow(Part part, JComboBox<String> comboBox) {
        List<JComboBox<String>> comboBoxList = comboBoxes.get(part);
        List<JLabel> priceLabelList = priceLabels.get(part);

        if (comboBoxList != null && comboBoxList.size() > 1) {
            int index = comboBoxList.indexOf(comboBox);
            middleSection.remove(comboBoxList.remove(index));
            middleSection.remove(priceLabelList.remove(index));
            middleSection.remove(viewDetailsButtons.get(part));
            revalidate();
            repaint();
        }
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

        for (final List<JLabel> priceLabelList : priceLabels.values()) {
            for (final JLabel priceLabel : priceLabelList) {
                final String text = priceLabel.getText().replace("€", "").trim();
                try {
                    if (!text.isEmpty()) {
                        totalPrice += Double.parseDouble(text);
                    }
                } catch (final NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }

        totalPriceLabel.setText("Total: €" + totalPrice);
    }
}