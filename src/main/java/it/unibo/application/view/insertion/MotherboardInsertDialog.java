package it.unibo.application.view.insertion;

import java.awt.*;
import javax.swing.*;

import java.util.List;

import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.components.Manufacturer;
import it.unibo.application.data.entities.insertion.ComponentInsert;
import it.unibo.application.data.entities.insertion.MotherboardInsert;

public class MotherboardInsertDialog {
    private final Controller controller;

    public MotherboardInsertDialog(final Controller controller) {
        this.controller = controller;
    }

    public void showDialog() {
        final JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        final JComboBox<String> formFactorComboBox = new JComboBox<>(new String[]{"ATX", "MicroATX", "MiniITX"});
        final JTextField chipsetNameField = new JTextField();
        final JTextField ramSlotsField = new JTextField();
        final JTextField gpuSlotsField = new JTextField();
        final JCheckBox wifiCheckBox = new JCheckBox("Include WiFi");
        final JComboBox<String> socketNameComboBox = new JComboBox<>(new String[]{"AM4", "AM5", "LGA 1200", "LGA 1700"});
        final JComboBox<String> ramGenComboBox = new JComboBox<>(new String[]{"DDR4", "DDR5"});

        final JTextField nameField = new JTextField();
        final JTextField launchYearField = new JTextField();
        final JTextField msrpField = new JTextField();
        final JComboBox<Manufacturer> manufacturerComboBox = new JComboBox<>();

        final List<Manufacturer> manufacturers = controller.getManufacturers();
        for (final Manufacturer manufacturer : manufacturers) {
            manufacturerComboBox.addItem(manufacturer);
        }

        panel.add(new JLabel("Form Factor:"));
        panel.add(formFactorComboBox);
        panel.add(new JLabel("Chipset Name:"));
        panel.add(chipsetNameField);
        panel.add(new JLabel("RAM Slots:"));
        panel.add(ramSlotsField);
        panel.add(new JLabel("GPU Slots:"));
        panel.add(gpuSlotsField);
        panel.add(new JLabel("Include WiFi:"));
        panel.add(wifiCheckBox);
        panel.add(new JLabel("Socket Name:"));
        panel.add(socketNameComboBox);
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

        final int result = JOptionPane.showConfirmDialog(null, panel, "Add New Motherboard", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                if (chipsetNameField.getText().trim().isEmpty() || ramSlotsField.getText().trim().isEmpty() ||
                        gpuSlotsField.getText().trim().isEmpty() || socketNameComboBox.getSelectedItem() == null ||
                        ramGenComboBox.getSelectedItem() == null || nameField.getText().trim().isEmpty() ||
                        launchYearField.getText().trim().isEmpty() || msrpField.getText().trim().isEmpty() ||
                        manufacturerComboBox.getSelectedItem() == null) {
                    throw new IllegalArgumentException("All fields must be filled.");
                }

                final String formFactor = (String) formFactorComboBox.getSelectedItem();
                final String chipsetName = chipsetNameField.getText().trim();
                int ramSlots;
                try {
                    ramSlots = Integer.parseInt(ramSlotsField.getText().trim());
                    if (ramSlots <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (final NumberFormatException ex) {
                    throw new IllegalArgumentException("RAM Slots must be a positive integer.");
                }

                int gpuSlots;
                try {
                    gpuSlots = Integer.parseInt(gpuSlotsField.getText().trim());
                    if (gpuSlots < 0) {
                        throw new NumberFormatException();
                    }
                } catch (final NumberFormatException ex) {
                    throw new IllegalArgumentException("GPU Slots must be a non-negative integer.");
                }

                final String socketName = (String) socketNameComboBox.getSelectedItem();
                final String ramGen = (String) ramGenComboBox.getSelectedItem();

                final boolean wifi = wifiCheckBox.isSelected();

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

                final ComponentInsert newComponent = new ComponentInsert(newComponentId, name, "motherboard", launchYear, msrp, manufacturerId);
                controller.insertComponent(newComponent);

                final MotherboardInsert newMotherboard = new MotherboardInsert(newComponentId, formFactor, chipsetName, ramSlots, gpuSlots, wifi, socketName, ramGen);
                controller.insertMotherboard(newMotherboard);

                JOptionPane.showMessageDialog(null, "Motherboard and Component inserted successfully!");
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