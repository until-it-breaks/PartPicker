package it.unibo.application.view.insertion;

import java.awt.*;
import javax.swing.*;

import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.components.Manufacturer;
import it.unibo.application.data.entities.insertion.ComponentInsert;
import it.unibo.application.data.entities.insertion.RamInsert;

import java.util.List;

public class RamInsertDialog {
    private final Controller controller;

    public RamInsertDialog(final Controller controller) {
        this.controller = controller;
    }

    public void showDialog() {
        final JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        final JTextField frequencyField = new JTextField();
        final JTextField capacityField = new JTextField();
        final JTextField latencyField = new JTextField();
        final JCheckBox eccCheckBox = new JCheckBox("ECC (Error-Correcting Code)");
        final JComboBox<String> ramGenComboBox = new JComboBox<>(new String[]{"DDR4", "DDR5"});

        final JTextField nameField = new JTextField();
        final JTextField launchYearField = new JTextField();
        final JTextField msrpField = new JTextField();
        final JComboBox<Manufacturer> manufacturerComboBox = new JComboBox<>();

        final List<Manufacturer> manufacturers = controller.getManufacturers();
        for (final Manufacturer manufacturer : manufacturers) {
            manufacturerComboBox.addItem(manufacturer);
        }

        panel.add(new JLabel("Frequency (MHz):"));
        panel.add(frequencyField);
        panel.add(new JLabel("Capacity (GB):"));
        panel.add(capacityField);
        panel.add(new JLabel("Latency (CL):"));
        panel.add(latencyField);
        panel.add(new JLabel("ECC:"));
        panel.add(eccCheckBox);
        panel.add(new JLabel("RAM Generation:"));
        panel.add(ramGenComboBox);
        panel.add(new JLabel("Component Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Launch Year (YYYY):"));
        panel.add(launchYearField);
        panel.add(new JLabel("MSRP:"));
        panel.add(msrpField);
        panel.add(new JLabel("Manufacturer:"));
        panel.add(manufacturerComboBox);

        final int result = JOptionPane.showConfirmDialog(null, panel, "Add New RAM", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                if (nameField.getText().trim().isEmpty() || launchYearField.getText().trim().isEmpty() ||
                        msrpField.getText().trim().isEmpty() || frequencyField.getText().trim().isEmpty() ||
                        capacityField.getText().trim().isEmpty() || latencyField.getText().trim().isEmpty() ||
                        manufacturerComboBox.getSelectedItem() == null) {
                    throw new IllegalArgumentException("All fields must be filled.");
                }

                int launchYear;
                try {
                    launchYear = Integer.parseInt(launchYearField.getText().trim());
                    final int currentYear = java.time.Year.now().getValue();
                    if (launchYear < 1970 || launchYear > currentYear) {
                        throw new NumberFormatException();
                    }
                } catch (final NumberFormatException ex) {
                    throw new IllegalArgumentException("Launch Year must be a valid year between 1970 and " + java.time.Year.now().getValue() + ".");
                }

                float msrp;
                try {
                    msrp = Float.parseFloat(msrpField.getText().trim());
                    if (msrp < 0) {
                        throw new NumberFormatException();
                    }
                } catch (final NumberFormatException ex) {
                    throw new IllegalArgumentException("MSRP must be a positive number.");
                }

                int frequency;
                try {
                    frequency = Integer.parseInt(frequencyField.getText().trim());
                    if (frequency <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (final NumberFormatException ex) {
                    throw new IllegalArgumentException("Frequency must be a positive integer.");
                }

                int capacity;
                try {
                    capacity = Integer.parseInt(capacityField.getText().trim());
                    if (capacity <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (final NumberFormatException ex) {
                    throw new IllegalArgumentException("Capacity must be a positive integer.");
                }

                final String latency = latencyField.getText().trim();
                final boolean isEcc = eccCheckBox.isSelected();
                final String ramGen = (String) ramGenComboBox.getSelectedItem();
                final String name = nameField.getText().trim();
                final Manufacturer selectedManufacturer = (Manufacturer) manufacturerComboBox.getSelectedItem();
                final int manufacturerId = selectedManufacturer.getId();

                final int newComponentId = controller.getLatestComponendId() + 1;

                final ComponentInsert newComponent = new ComponentInsert(newComponentId, name, "Ram", launchYear, msrp, manufacturerId);
                controller.insertComponent(newComponent);

                final RamInsert newRam = new RamInsert(newComponentId, frequency, capacity, latency, isEcc, ramGen);
                controller.insertRam(newRam);

                JOptionPane.showMessageDialog(null, "RAM and Component inserted successfully!");
            } catch (final IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Validation Error", JOptionPane.WARNING_MESSAGE);
                showDialog();
            } catch (final Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}