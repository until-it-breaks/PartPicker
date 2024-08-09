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

    public CpuInsertDialog(final Controller controller) {
        this.controller = controller;
    }

    public void showDialog() {
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        final JTextField familyField = new JTextField();
        final JTextField coreCountField = new JTextField();
        final JTextField frequencyField = new JTextField();
        final JTextField tdpField = new JTextField();
        final JCheckBox smtCheckBox = new JCheckBox("Support SMT");
        final JComboBox<String> socketNameComboBox = new JComboBox<>(new String[]{"AM4", "AM5", "LGA 1200", "LGA 1700"});

        final DefaultListModel<String> ramGenListModel = new DefaultListModel<>();
        final JList<String> ramGenList = new JList<>(ramGenListModel);
        ramGenList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        ramGenListModel.addElement("DDR4");
        ramGenListModel.addElement("DDR5");

        final JTextField nameField = new JTextField();
        final JTextField launchYearField = new JTextField();
        final JTextField msrpField = new JTextField();
        final JComboBox<Manufacturer> manufacturerComboBox = new JComboBox<>();

        final List<Manufacturer> manufacturers = controller.getManufacturers();
        for (final Manufacturer manufacturer : manufacturers) {
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

        final int result = JOptionPane.showConfirmDialog(null, panel, "Add New CPU", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                if (familyField.getText().trim().isEmpty() || coreCountField.getText().trim().isEmpty() ||
                        frequencyField.getText().trim().isEmpty() || tdpField.getText().trim().isEmpty() ||
                        socketNameComboBox.getSelectedItem() == null || nameField.getText().trim().isEmpty() ||
                        launchYearField.getText().trim().isEmpty() || msrpField.getText().trim().isEmpty() ||
                        manufacturerComboBox.getSelectedItem() == null) {
                    throw new IllegalArgumentException("All fields must be filled.");
                }

                final String family = familyField.getText().trim();

                int coreCount;
                try {
                    coreCount = Integer.parseInt(coreCountField.getText().trim());
                    if (coreCount <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (final NumberFormatException ex) {
                    throw new IllegalArgumentException("Core Count must be a positive integer.");
                }

                float frequency;
                try {
                    frequency = Float.parseFloat(frequencyField.getText().trim());
                    if (frequency <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (final NumberFormatException ex) {
                    throw new IllegalArgumentException("Frequency must be a positive number.");
                }

                int tdp;
                try {
                    tdp = Integer.parseInt(tdpField.getText().trim());
                    if (tdp <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (final NumberFormatException ex) {
                    throw new IllegalArgumentException("TDP must be a positive integer.");
                }

                final String socketName = (String) socketNameComboBox.getSelectedItem();

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
                final boolean smt = smtCheckBox.isSelected();
                final Manufacturer selectedManufacturer = (Manufacturer) manufacturerComboBox.getSelectedItem();
                final int manufacturerId = selectedManufacturer.getId();

                final int newComponentId = controller.getLatestComponendId() + 1;

                final ComponentInsert newComponent = new ComponentInsert(newComponentId, name, "cpu", launchYear, msrp, manufacturerId);
                controller.insertComponent(newComponent);

                final CpuInsert newCpu = new CpuInsert(newComponentId, family, coreCount, frequency, tdp, smt, socketName);
                controller.insertCpu(newCpu);
                for (final String ramGen : ramGenList.getSelectedValuesList()) {
                    final CpuRamInsert cpuRamInsert = new CpuRamInsert(ramGen, newComponentId);
                    controller.insertCpuRamCompatibility(cpuRamInsert);
                }

                JOptionPane.showMessageDialog(null, "CPU and Component, along with RAM compatibility, inserted successfully!");
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