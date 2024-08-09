package it.unibo.application.view.insertion;

import java.awt.*;
import javax.swing.*;

import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.components.Manufacturer;
import it.unibo.application.data.entities.insertion.ComponentInsert;
import it.unibo.application.data.entities.insertion.CpuInsert;
import it.unibo.application.data.entities.insertion.CpuRamInsert;

import java.util.List;

public class CpuInsertDialog {
    private final Controller controller;

    public CpuInsertDialog(Controller controller) {
        this.controller = controller;
    }

    public void showDialog() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        JTextField familyField = new JTextField();
        JTextField coreCountField = new JTextField();
        JTextField frequencyField = new JTextField();
        JTextField tdpField = new JTextField();
        JCheckBox smtCheckBox = new JCheckBox("Support SMT");
        JComboBox<String> socketNameComboBox = new JComboBox<>(new String[]{"AM4", "AM5", "LGA 1200", "LGA 1700"});

        DefaultListModel<String> ramGenListModel = new DefaultListModel<>();
        JList<String> ramGenList = new JList<>(ramGenListModel);
        ramGenList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        ramGenListModel.addElement("DDR4");
        ramGenListModel.addElement("DDR5");

        JTextField nameField = new JTextField();
        JTextField launchYearField = new JTextField();
        JTextField msrpField = new JTextField();
        JComboBox<Manufacturer> manufacturerComboBox = new JComboBox<>();

        List<Manufacturer> manufacturers = controller.getManufacturers();
        for (Manufacturer manufacturer : manufacturers) {
            manufacturerComboBox.addItem(manufacturer);
        }

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Family:"), gbc);

        gbc.gridx = 1;
        panel.add(familyField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Core Count:"), gbc);

        gbc.gridx = 1;
        panel.add(coreCountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Frequency (GHz):"), gbc);

        gbc.gridx = 1;
        panel.add(frequencyField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("TDP (W):"), gbc);

        gbc.gridx = 1;
        panel.add(tdpField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(smtCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Socket Name:"), gbc);

        gbc.gridx = 1;
        panel.add(socketNameComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("RAM Generations Supported:"), gbc);

        gbc.gridx = 1;
        gbc.gridheight = 2;
        gbc.weighty = 1.0;
        panel.add(new JScrollPane(ramGenList), gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridheight = 1;
        gbc.weighty = 0.0;
        panel.add(new JLabel("Component Name:"), gbc);

        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(new JLabel("Launch Year (YYYY):"), gbc);

        gbc.gridx = 1;
        panel.add(launchYearField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        panel.add(new JLabel("MSRP:"), gbc);

        gbc.gridx = 1;
        panel.add(msrpField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        panel.add(new JLabel("Manufacturer:"), gbc);

        gbc.gridx = 1;
        panel.add(manufacturerComboBox, gbc);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add New CPU", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                if (familyField.getText().trim().isEmpty() || coreCountField.getText().trim().isEmpty() ||
                        frequencyField.getText().trim().isEmpty() || tdpField.getText().trim().isEmpty() ||
                        socketNameComboBox.getSelectedItem() == null || nameField.getText().trim().isEmpty() ||
                        launchYearField.getText().trim().isEmpty() || msrpField.getText().trim().isEmpty() ||
                        manufacturerComboBox.getSelectedItem() == null) {
                    throw new IllegalArgumentException("All fields must be filled.");
                }

                String family = familyField.getText().trim();

                int coreCount;
                try {
                    coreCount = Integer.parseInt(coreCountField.getText().trim());
                    if (coreCount <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Core Count must be a positive integer.");
                }

                float frequency;
                try {
                    frequency = Float.parseFloat(frequencyField.getText().trim());
                    if (frequency <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Frequency must be a positive number.");
                }

                int tdp;
                try {
                    tdp = Integer.parseInt(tdpField.getText().trim());
                    if (tdp <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("TDP must be a positive integer.");
                }

                String socketName = (String) socketNameComboBox.getSelectedItem();

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
                boolean smt = smtCheckBox.isSelected();
                Manufacturer selectedManufacturer = (Manufacturer) manufacturerComboBox.getSelectedItem();
                int manufacturerId = selectedManufacturer.getId();

                int newComponentId = controller.getLatestComponendId() + 1;

                ComponentInsert newComponent = new ComponentInsert(newComponentId, name, "cpu", launchYear, msrp, manufacturerId);
                controller.insertComponent(newComponent);

                CpuInsert newCpu = new CpuInsert(newComponentId, family, coreCount, frequency, tdp, smt, socketName);
                controller.insertCpu(newCpu);
                for (String ramGen : ramGenList.getSelectedValuesList()) {
                    CpuRamInsert cpuRamInsert = new CpuRamInsert(ramGen, newComponentId);
                    controller.insertCpuRamCompatibility(cpuRamInsert);
                }

                JOptionPane.showMessageDialog(null, "CPU and Component, along with RAM compatibility, inserted successfully!");
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