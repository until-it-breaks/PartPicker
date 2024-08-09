package it.unibo.application.view.insertion;

import java.awt.*;
import javax.swing.*;

import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.components.Manufacturer;
import it.unibo.application.data.entities.insertion.ComponentInsert;
import it.unibo.application.data.entities.insertion.GpuInsert;

import java.util.List;

public class GpuInsertDialog {
    private final Controller controller;

    public GpuInsertDialog(final Controller controller) {
        this.controller = controller;
    }

    public void showDialog() {
        final JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        final JTextField familyField = new JTextField();
        final JComboBox<String> memoryTypeComboBox = new JComboBox<>(new String[]{"GDDR5", "GDDR6", "GDDR6X"});
        final JTextField memoryAmountField = new JTextField();
        final JTextField frequencyField = new JTextField();
        final JTextField tgpField = new JTextField();

        final JTextField nameField = new JTextField();
        final JTextField launchYearField = new JTextField();
        final JTextField msrpField = new JTextField();
        final JComboBox<Manufacturer> manufacturerComboBox = new JComboBox<>();

        final List<Manufacturer> manufacturers = controller.getManufacturers();
        for (final Manufacturer manufacturer : manufacturers) {
            manufacturerComboBox.addItem(manufacturer);
        }

        panel.add(new JLabel("Family:"));
        panel.add(familyField);
        panel.add(new JLabel("Memory Type:"));
        panel.add(memoryTypeComboBox);
        panel.add(new JLabel("Memory Amount (GB):"));
        panel.add(memoryAmountField);
        panel.add(new JLabel("Frequency (MHz):"));
        panel.add(frequencyField);
        panel.add(new JLabel("TGP (W):"));
        panel.add(tgpField);
        panel.add(new JLabel("Component Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Launch Year (YYYY):"));
        panel.add(launchYearField);
        panel.add(new JLabel("MSRP:"));
        panel.add(msrpField);
        panel.add(new JLabel("Manufacturer:"));
        panel.add(manufacturerComboBox);

        final int result = JOptionPane.showConfirmDialog(null, panel, "Add New GPU", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                if (familyField.getText().trim().isEmpty() || memoryAmountField.getText().trim().isEmpty() ||
                        frequencyField.getText().trim().isEmpty() || tgpField.getText().trim().isEmpty() ||
                        nameField.getText().trim().isEmpty() || launchYearField.getText().trim().isEmpty() ||
                        msrpField.getText().trim().isEmpty() || manufacturerComboBox.getSelectedItem() == null) {
                    throw new IllegalArgumentException("All fields must be filled.");
                }

                final String family = familyField.getText().trim();
                final String memoryType = (String) memoryTypeComboBox.getSelectedItem();

                int memoryAmount;
                try {
                    memoryAmount = Integer.parseInt(memoryAmountField.getText().trim());
                    if (memoryAmount <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (final NumberFormatException ex) {
                    throw new IllegalArgumentException("Memory Amount must be a positive integer.");
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

                int tgp;
                try {
                    tgp = Integer.parseInt(tgpField.getText().trim());
                    if (tgp <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (final NumberFormatException ex) {
                    throw new IllegalArgumentException("TGP must be a positive integer.");
                }

                int launchYear;
                try {
                    launchYear = Integer.parseInt(launchYearField.getText().trim());
                    final int currentYear = java.time.Year.now().getValue();
                    if (launchYear < 1970 || launchYear > currentYear) {
                        throw new NumberFormatException();
                    }
                } catch (final NumberFormatException ex) {
                    throw new IllegalArgumentException("Launch Year must be a valid year between 1900 and " + java.time.Year.now().getValue() + ".");
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

                final ComponentInsert newComponent = new ComponentInsert(newComponentId, name, "gpu", launchYear, msrp, manufacturerId);
                controller.insertComponent(newComponent);

                final GpuInsert newGpu = new GpuInsert(newComponentId, family, memoryType, memoryAmount, frequency, tgp);
                controller.insertGpu(newGpu);

                JOptionPane.showMessageDialog(null, "GPU and Component inserted successfully!");
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