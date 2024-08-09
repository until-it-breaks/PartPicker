package it.unibo.application.view.insertion;

import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.components.Manufacturer;
import it.unibo.application.data.entities.insertion.ComponentInsert;
import it.unibo.application.data.entities.insertion.PsuInsert;

import java.awt.*;
import javax.swing.*;

import java.util.List;

public class PsuInsertDialog {
    private final Controller controller;

    public PsuInsertDialog(Controller controller) {
        this.controller = controller;
    }

    public void showDialog() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        JComboBox<String> formFactorComboBox = new JComboBox<>(new String[]{"ATX", "MicroATX", "MiniITX"});
        JComboBox<String> efficiencyComboBox = new JComboBox<>(new String[]{"Bronze", "Silver", "Gold", "Platinum", "Titanium"});
        JTextField wattageField = new JTextField();
        JComboBox<String> modularityComboBox = new JComboBox<>(new String[]{"Full", "Semi", "No"});

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
        panel.add(new JLabel("Efficiency:"));
        panel.add(efficiencyComboBox);
        panel.add(new JLabel("Wattage:"));
        panel.add(wattageField);
        panel.add(new JLabel("Modularity:"));
        panel.add(modularityComboBox);
        panel.add(new JLabel("Component Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Launch Year (YYYY):"));
        panel.add(launchYearField);
        panel.add(new JLabel("MSRP:"));
        panel.add(msrpField);
        panel.add(new JLabel("Manufacturer:"));
        panel.add(manufacturerComboBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add New PSU", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                if (nameField.getText().trim().isEmpty() || launchYearField.getText().trim().isEmpty() ||
                        msrpField.getText().trim().isEmpty() || wattageField.getText().trim().isEmpty() ||
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

                int wattage;
                try {
                    wattage = Integer.parseInt(wattageField.getText().trim());
                    if (wattage <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Wattage must be a positive integer.");
                }

                String formFactor = (String) formFactorComboBox.getSelectedItem();
                String efficiency = (String) efficiencyComboBox.getSelectedItem();
                String modularity = (String) modularityComboBox.getSelectedItem();
                String name = nameField.getText().trim();
                Manufacturer selectedManufacturer = (Manufacturer) manufacturerComboBox.getSelectedItem();
                int manufacturerId = selectedManufacturer.getId();

                int newComponentId = controller.getLatestComponendId() + 1;

                ComponentInsert newComponent = new ComponentInsert(newComponentId, name, "Psu", launchYear, msrp, manufacturerId);
                controller.insertComponent(newComponent);

                PsuInsert newPsu = new PsuInsert(newComponentId, formFactor, efficiency, wattage, modularity);
                controller.insertPsu(newPsu);

                JOptionPane.showMessageDialog(null, "PSU and Component inserted successfully!");
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
