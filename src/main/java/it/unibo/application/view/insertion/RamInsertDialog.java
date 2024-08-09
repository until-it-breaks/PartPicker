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

    public RamInsertDialog(Controller controller) {
        this.controller = controller;
    }

    public void showDialog() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        JTextField frequencyField = new JTextField();
        JTextField capacityField = new JTextField();
        JTextField latencyField = new JTextField();
        JCheckBox eccCheckBox = new JCheckBox("ECC (Error-Correcting Code)");
        JComboBox<String> ramGenComboBox = new JComboBox<>(new String[]{"DDR4", "DDR5"});

        JTextField nameField = new JTextField();
        JTextField launchYearField = new JTextField();
        JTextField msrpField = new JTextField();
        JComboBox<Manufacturer> manufacturerComboBox = new JComboBox<>();

        List<Manufacturer> manufacturers = controller.getManufacturers();
        for (Manufacturer manufacturer : manufacturers) {
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

        int result = JOptionPane.showConfirmDialog(null, panel, "Add New RAM", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
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
                    int currentYear = java.time.Year.now().getValue();
                    if (launchYear < 1970 || launchYear > currentYear) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Launch Year must be a valid year between 1970 and " + java.time.Year.now().getValue() + ".");
                }

                float msrp;
                try {
                    msrp = Float.parseFloat(msrpField.getText().trim());
                    if (msrp < 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("MSRP must be a positive number.");
                }

                int frequency;
                try {
                    frequency = Integer.parseInt(frequencyField.getText().trim());
                    if (frequency <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Frequency must be a positive integer.");
                }

                int capacity;
                try {
                    capacity = Integer.parseInt(capacityField.getText().trim());
                    if (capacity <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Capacity must be a positive integer.");
                }

                String latency = latencyField.getText().trim();
                boolean isEcc = eccCheckBox.isSelected();
                String ramGen = (String) ramGenComboBox.getSelectedItem();
                String name = nameField.getText().trim();
                Manufacturer selectedManufacturer = (Manufacturer) manufacturerComboBox.getSelectedItem();
                int manufacturerId = selectedManufacturer.getId();

                int newComponentId = controller.getLatestComponendId() + 1;

                ComponentInsert newComponent = new ComponentInsert(newComponentId, name, "Ram", launchYear, msrp, manufacturerId);
                controller.insertComponent(newComponent);

                RamInsert newRam = new RamInsert(newComponentId, frequency, capacity, latency, isEcc, ramGen);
                controller.insertRam(newRam);

                JOptionPane.showMessageDialog(null, "RAM and Component inserted successfully!");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Validation Error", JOptionPane.WARNING_MESSAGE);
                showDialog();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}