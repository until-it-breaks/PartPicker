package it.unibo.application.view.insertion;

import java.awt.*;
import javax.swing.*;

import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.components.Manufacturer;
import it.unibo.application.data.entities.insertion.ComponentInsert;
import it.unibo.application.data.entities.insertion.StorageInsert;

import java.util.List;

public class StorageInsertDialog {
    private final Controller controller;

    public StorageInsertDialog(Controller controller) {
        this.controller = controller;
    }

    public void showDialog() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        JTextField capacityField = new JTextField();
        JTextField rpmField = new JTextField();
        JTextField cacheAmountField = new JTextField();
        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Hdd", "Ssd"});

        JTextField nameField = new JTextField();
        JTextField launchYearField = new JTextField();
        JTextField msrpField = new JTextField();
        JComboBox<Manufacturer> manufacturerComboBox = new JComboBox<>();

        List<Manufacturer> manufacturers = controller.getManufacturers();
        for (Manufacturer manufacturer : manufacturers) {
            manufacturerComboBox.addItem(manufacturer);
        }

        panel.add(new JLabel("Capacity (GB):"));
        panel.add(capacityField);
        panel.add(new JLabel("RPM (for HDD only):"));
        panel.add(rpmField);
        panel.add(new JLabel("Cache Amount (MB, for HDD only):"));
        panel.add(cacheAmountField);
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

        rpmField.setEnabled(false);
        cacheAmountField.setEnabled(false);

        typeComboBox.addActionListener(e -> {
            String selectedType = (String) typeComboBox.getSelectedItem();
            if ("Hdd".equals(selectedType)) {
                rpmField.setEnabled(true);
                cacheAmountField.setEnabled(true);
            } else {
                rpmField.setEnabled(false);
                cacheAmountField.setEnabled(false);
                rpmField.setText("");
                cacheAmountField.setText("");
            }
        });

        int result = JOptionPane.showConfirmDialog(null, panel, "Add New Storage", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                if (nameField.getText().trim().isEmpty() || launchYearField.getText().trim().isEmpty() ||
                        msrpField.getText().trim().isEmpty() || capacityField.getText().trim().isEmpty() ||
                        typeComboBox.getSelectedItem() == null || manufacturerComboBox.getSelectedItem() == null) {
                    throw new IllegalArgumentException("All fields must be filled.");
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

                int rpm = 0;
                int cacheAmount = 0;
                String type = (String) typeComboBox.getSelectedItem();

                if ("Hdd".equals(type)) {
                    try {
                        rpm = Integer.parseInt(rpmField.getText().trim());
                        if (rpm <= 0) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("RPM must be a positive integer for HDD.");
                    }

                    try {
                        cacheAmount = Integer.parseInt(cacheAmountField.getText().trim());
                        if (cacheAmount < 0) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Cache Amount must be a non-negative integer for HDD.");
                    }
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

                String name = nameField.getText().trim();
                Manufacturer selectedManufacturer = (Manufacturer) manufacturerComboBox.getSelectedItem();
                int manufacturerId = selectedManufacturer.getId();

                int newComponentId = controller.getLatestComponendId() + 1;

                ComponentInsert newComponent = new ComponentInsert(newComponentId, name, "Storage", launchYear, msrp, manufacturerId);
                controller.insertComponent(newComponent);

                StorageInsert newStorage = new StorageInsert(newComponentId, capacity, rpm, cacheAmount, type);
                controller.insertStorage(newStorage);

                JOptionPane.showMessageDialog(null, "Storage and Component inserted successfully!");
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