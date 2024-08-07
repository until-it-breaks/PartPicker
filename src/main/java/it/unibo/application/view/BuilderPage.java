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
import java.text.DecimalFormat;

public class BuilderPage extends JPanel {
    private final Controller controller;
    private final JLabel totalPriceLabel;
    private final Map<Part, List<JComboBox<String>>> comboBoxes = new HashMap<>();
    private final Map<Part, List<JLabel>> priceLabels = new HashMap<>();
    private final JPanel middleSection;
    private final JPanel gpuPanel;
    private final JPanel ramPanel;
    private final JPanel storagePanel;
    private final Map<Part, List<JButton>> viewDetailsButtons = new HashMap<>();

    public BuilderPage(final Controller controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());
        this.add(new TopBar(controller), BorderLayout.NORTH);

        middleSection = new JPanel();
        middleSection.setLayout(new BoxLayout(middleSection, BoxLayout.Y_AXIS));

        JPanel headerRow = new JPanel(new GridLayout(1, 4));
        headerRow.add(new JLabel("Component"));
        headerRow.add(new JLabel("Selection"));
        headerRow.add(new JLabel("Price"));
        headerRow.add(new JLabel("Actions"));
        middleSection.add(headerRow);

        for (final Part part : Part.values()) {
            if (part != Part.GPU && part != Part.RAM && part != Part.STORAGE) {
                addPartRow(part, middleSection);
            }
        }

        // Initialize special panels for GPU, RAM, and STORAGE
        gpuPanel = new JPanel();
        gpuPanel.setLayout(new BoxLayout(gpuPanel, BoxLayout.Y_AXIS));
        middleSection.add(gpuPanel);

        ramPanel = new JPanel();
        ramPanel.setLayout(new BoxLayout(ramPanel, BoxLayout.Y_AXIS));
        middleSection.add(ramPanel);

        storagePanel = new JPanel();
        storagePanel.setLayout(new BoxLayout(storagePanel, BoxLayout.Y_AXIS));
        middleSection.add(storagePanel);

        for (final Part part : Part.values()) {
            if (part == Part.GPU || part == Part.RAM || part == Part.STORAGE) {
                addPartRow(part, getPanelForPart(part));
            }
        }

        this.add(new JScrollPane(middleSection), BorderLayout.CENTER);

        final JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPriceLabel = new JLabel("Total: €");
        bottomPanel.add(totalPriceLabel);

        final JButton saveButton = new JButton("Save Build");
        bottomPanel.add(saveButton);

        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addPartRow(final Part part, JPanel parentPanel) {
        final List<Component> components = controller.getComponents(part);

        JPanel partRow = new JPanel(new GridLayout(1, 4));
        JLabel partLabel = new JLabel(part.toString().toUpperCase());
        final JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("Select a " + part.toString());
        for (final Component component : components) {
            comboBox.addItem(component.getBaseInfo().getName());
        }

        JLabel priceLabel = new JLabel();
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
                        addPartRow(part, getPanelForPart(part));  // Add to respective panel
                    }
                } else {
                    priceLabel.setText("");
                    viewDetailsButton.setEnabled(false);
                    if (part == Part.GPU || part == Part.RAM || part == Part.STORAGE) {
                        removePartRow(part, comboBox, getPanelForPart(part));
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

        partRow.add(partLabel);
        partRow.add(comboBox);
        partRow.add(priceLabel);
        partRow.add(viewDetailsButton);

        parentPanel.add(partRow);
        parentPanel.revalidate();
        parentPanel.repaint();

        comboBoxes.computeIfAbsent(part, k -> new ArrayList<>()).add(comboBox);
        priceLabels.computeIfAbsent(part, k -> new ArrayList<>()).add(priceLabel);
        viewDetailsButtons.computeIfAbsent(part, k -> new ArrayList<>()).add(viewDetailsButton);

        revalidate();
        repaint();
    }

    private void removePartRow(Part part, JComboBox<String> comboBox, JPanel parentPanel) {
        List<JComboBox<String>> comboBoxList = comboBoxes.get(part);
        List<JLabel> priceLabelList = priceLabels.get(part);
        List<JButton> viewDetailsButtonList = viewDetailsButtons.get(part);

        if (comboBoxList != null && comboBoxList.size() > 1) {
            int index = comboBoxList.indexOf(comboBox);
            comboBoxList.remove(index);
            priceLabelList.remove(index);
            viewDetailsButtonList.remove(index);

            java.awt.Component rowToRemove = parentPanel.getComponent(index);
            parentPanel.remove(rowToRemove);

            parentPanel.revalidate();
            parentPanel.repaint();
        }
    }

    private JPanel getPanelForPart(Part part) {
        switch (part) {
            case GPU:
                return gpuPanel;
            case RAM:
                return ramPanel;
            case STORAGE:
                return storagePanel;
            default:
                throw new IllegalArgumentException("Unexpected part: " + part);
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
    
        // Create a DecimalFormat instance to format the price with two decimal places
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String formattedPrice = decimalFormat.format(totalPrice);
    
        totalPriceLabel.setText("Total: €" + formattedPrice);
    }
}