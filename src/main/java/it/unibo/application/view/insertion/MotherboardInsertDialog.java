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

    public MotherboardInsertDialog(Controller controller) {
        this.controller = controller;
    }

    public void showDialog() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        JComboBox<String> formFactorComboBox = new JComboBox<>(new String[]{"ATX", "MicroATX", "MiniITX"});
        JTextField chipsetNameField = new JTextField();
        JTextField ramSlotsField = new JTextField();
        JTextField gpuSlotsField = new JTextField();
        JCheckBox wifiCheckBox = new JCheckBox("Include WiFi");
        JComboBox<String> socketNameComboBox = new JComboBox<>(new String[]{"AM4", "AM5", "LGA 1200", "LGA 1700"});
        JComboBox<String> ramGenComboBox = new JComboBox<>(new String[]{"DDR4", "DDR5"});

        JTextField nameField = new JTextField();
        JTextField launchYearField = new JTextField();
        JTextField msrpField = new JTextField();
        JComboBox<Manufacturer> manufacturerComboBox = new JComboBox<>();

        List<Manufacturer> manufacturers = controller.getManufacturers();
        for (Manufacturer manufacturer : manufacturers) {
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

        int result = JOptionPane.showConfirmDialog(null, panel, "Add New Motherboard", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                if (chipsetNameField.getText().trim().isEmpty() || ramSlotsField.getText().trim().isEmpty() ||
                        gpuSlotsField.getText().trim().isEmpty() || socketNameComboBox.getSelectedItem() == null ||
                        ramGenComboBox.getSelectedItem() == null || nameField.getText().trim().isEmpty() ||
                        launchYearField.getText().trim().isEmpty() || msrpField.getText().trim().isEmpty() ||
                        manufacturerComboBox.getSelectedItem() == null) {
                    throw new IllegalArgumentException("All fields must be filled.");
                }

                String formFactor = (String) formFactorComboBox.getSelectedItem();
                String chipsetName = chipsetNameField.getText().trim();
                int ramSlots;
                try {
                    ramSlots = Integer.parseInt(ramSlotsField.getText().trim());
                    if (ramSlots <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("RAM Slots must be a positive integer.");
                }

                int gpuSlots;
                try {
                    gpuSlots = Integer.parseInt(gpuSlotsField.getText().trim());
                    if (gpuSlots < 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("GPU Slots must be a non-negative integer.");
                }

                String socketName = (String) socketNameComboBox.getSelectedItem();
                String ramGen = (String) ramGenComboBox.getSelectedItem();

                boolean wifi = wifiCheckBox.isSelected();

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

                String name = nameField.getText().trim();
                Manufacturer selectedManufacturer = (Manufacturer) manufacturerComboBox.getSelectedItem();
                int manufacturerId = selectedManufacturer.getId();

                int newComponentId = controller.getLatestComponendId() + 1;

                ComponentInsert newComponent = new ComponentInsert(newComponentId, name, "motherboard", launchYear, msrp, manufacturerId);
                controller.insertComponent(newComponent);

                MotherboardInsert newMotherboard = new MotherboardInsert(newComponentId, formFactor, chipsetName, ramSlots, gpuSlots, wifi, socketName, ramGen);
                controller.insertMotherboard(newMotherboard);

                JOptionPane.showMessageDialog(null, "Motherboard and Component inserted successfully!");
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