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

    public GpuInsertDialog(Controller controller) {
        this.controller = controller;
    }

    public void showDialog() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        JTextField familyField = new JTextField();
        JComboBox<String> memoryTypeComboBox = new JComboBox<>(new String[]{"GDDR5", "GDDR6", "GDDR6X"});
        JTextField memoryAmountField = new JTextField();
        JTextField frequencyField = new JTextField();
        JTextField tgpField = new JTextField();

        JTextField nameField = new JTextField();
        JTextField launchYearField = new JTextField();
        JTextField msrpField = new JTextField();
        JComboBox<Manufacturer> manufacturerComboBox = new JComboBox<>();

        List<Manufacturer> manufacturers = controller.getManufacturers();
        for (Manufacturer manufacturer : manufacturers) {
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

        int result = JOptionPane.showConfirmDialog(null, panel, "Add New GPU", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                if (familyField.getText().trim().isEmpty() || memoryAmountField.getText().trim().isEmpty() ||
                        frequencyField.getText().trim().isEmpty() || tgpField.getText().trim().isEmpty() ||
                        nameField.getText().trim().isEmpty() || launchYearField.getText().trim().isEmpty() ||
                        msrpField.getText().trim().isEmpty() || manufacturerComboBox.getSelectedItem() == null) {
                    throw new IllegalArgumentException("All fields must be filled.");
                }

                String family = familyField.getText().trim();
                String memoryType = (String) memoryTypeComboBox.getSelectedItem();

                int memoryAmount;
                try {
                    memoryAmount = Integer.parseInt(memoryAmountField.getText().trim());
                    if (memoryAmount <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Memory Amount must be a positive integer.");
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

                int tgp;
                try {
                    tgp = Integer.parseInt(tgpField.getText().trim());
                    if (tgp <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("TGP must be a positive integer.");
                }

                int launchYear;
                try {
                    launchYear = Integer.parseInt(launchYearField.getText().trim());
                    int currentYear = java.time.Year.now().getValue();
                    if (launchYear < 1970 || launchYear > currentYear) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Launch Year must be a valid year between 1900 and " + java.time.Year.now().getValue() + ".");
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

                String name = nameField.getText().trim();
                Manufacturer selectedManufacturer = (Manufacturer) manufacturerComboBox.getSelectedItem();
                int manufacturerId = selectedManufacturer.getId();

                int newComponentId = controller.getLatestComponendId() + 1;

                ComponentInsert newComponent = new ComponentInsert(newComponentId, name, "gpu", launchYear, msrp, manufacturerId);
                controller.insertComponent(newComponent);

                GpuInsert newGpu = new GpuInsert(newComponentId, family, memoryType, memoryAmount, frequency, tgp);
                controller.insertGpu(newGpu);

                JOptionPane.showMessageDialog(null, "GPU and Component inserted successfully!");
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