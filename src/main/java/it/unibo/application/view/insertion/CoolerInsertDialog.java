package it.unibo.application.view.insertion;

import java.awt.*;
import javax.swing.*;

import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.components.Manufacturer;
import it.unibo.application.data.entities.insertion.ComponentInsert;
import it.unibo.application.data.entities.insertion.CoolerInsert;

import java.util.List;

public class CoolerInsertDialog {
    private final Controller controller;

    public CoolerInsertDialog(final Controller controller) {
        this.controller = controller;
    }

    public void showDialog() {
        final JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        final JTextField rpmField = new JTextField();
        final JTextField noiseLevelField = new JTextField();
        final JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Aria", "AIO"});

        final JTextField nameField = new JTextField();
        final JTextField launchYearField = new JTextField();
        final JTextField msrpField = new JTextField();
        final JComboBox<Manufacturer> manufacturerComboBox = new JComboBox<>();

        final List<Manufacturer> manufacturers = controller.getManufacturers();
        for (final Manufacturer manufacturer : manufacturers) {
            manufacturerComboBox.addItem(manufacturer);
        }

        panel.add(new JLabel("RPM:"));
        panel.add(rpmField);
        panel.add(new JLabel("Noise Level (dB):"));
        panel.add(noiseLevelField);
        panel.add(new JLabel("Type:"));
        panel.add(typeComboBox);
        panel.add(new JLabel("Component Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Launch Year (YYYY):"));
        panel.add(launchYearField);
        panel.add(new JLabel("MSRP:"));
        panel.add(msrpField);
        panel.add(new JLabel("Manufacturer:"));
        panel.add(manufacturerComboBox);

        final int result = JOptionPane.showConfirmDialog(null, panel, "Add New Cooler", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                if (rpmField.getText().trim().isEmpty() || noiseLevelField.getText().trim().isEmpty() ||
                        typeComboBox.getSelectedItem() == null || nameField.getText().trim().isEmpty() ||
                        launchYearField.getText().trim().isEmpty() || msrpField.getText().trim().isEmpty() ||
                        manufacturerComboBox.getSelectedItem() == null) {
                    throw new IllegalArgumentException("All fields must be filled.");
                }

                int rpm;
                try {
                    rpm = Integer.parseInt(rpmField.getText().trim());
                    if (rpm < 0) {
                        throw new NumberFormatException();
                    }
                } catch (final NumberFormatException ex) {
                    throw new IllegalArgumentException("RPM must be a non-negative integer.");
                }

                float noiseLevel;
                try {
                    noiseLevel = Float.parseFloat(noiseLevelField.getText().trim());
                    if (noiseLevel < 0) {
                        throw new NumberFormatException();
                    }
                } catch (final NumberFormatException ex) {
                    throw new IllegalArgumentException("Noise Level must be a non-negative number.");
                }

                final String type = (String) typeComboBox.getSelectedItem();

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

                final String name = nameField.getText().trim();
                final Manufacturer selectedManufacturer = (Manufacturer) manufacturerComboBox.getSelectedItem();
                final int manufacturerId = selectedManufacturer.getId();

                final int newComponentId = controller.getLatestComponendId() + 1;

                final ComponentInsert newComponent = new ComponentInsert(newComponentId, name, "cooler", launchYear, msrp, manufacturerId);
                controller.insertComponent(newComponent);

                final CoolerInsert newCooler = new CoolerInsert(newComponentId, rpm, noiseLevel, type);
                controller.insertCooler(newCooler);

                JOptionPane.showMessageDialog(null, "Cooler and Component inserted successfully!");
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