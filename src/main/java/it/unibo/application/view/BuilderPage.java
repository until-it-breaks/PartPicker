package it.unibo.application.view;

import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.builds.Build;
import it.unibo.application.data.entities.compatibility.ComponentCompatibilityChecker;
import it.unibo.application.data.entities.components.Component;
import it.unibo.application.data.entities.enums.Part;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.*;

public class BuilderPage extends JPanel {
    private final Controller controller;
    private final ComponentCompatibilityChecker ccc;
    private final JPanel mainPanel;
    private final JPanel cpuPanel;
    private final JPanel coolerPanel;
    private final JPanel casePanel;
    private final JPanel psuPanel;
    private final JPanel motherboardPanel;
    private final JPanel gpuPanel;
    private final JPanel ramPanel;
    private final JPanel storagePanel;
    private final JPanel bottomPanel;
    private final JLabel totalPriceLabel;
    private final JButton uploadBuildButton;

    private Component selectedCpu;
    private Component selectedCooler;
    private Component selectedCase;
    private Component selectedPsu;
    private Component selectedMotherboard;
    private List<Component> selectedGpu = new ArrayList<>();
    private List<Component> selectedRam = new ArrayList<>();
    private List<Component> selectedStorage = new ArrayList<>();

    // Map to track price labels for each panel
    private final Map<JComboBox<Component>, JLabel> comboBoxToPriceLabelMap = new HashMap<>();
    private final Map<JPanel, List<Component>> panelToComponentsMap = new HashMap<>();

    public BuilderPage(final Controller controller) {
        this.controller = controller;
        this.ccc = controller.getCCC();
        this.setLayout(new BorderLayout());

        this.add(new TopBar(controller), BorderLayout.NORTH);

        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new GridLayout(4, 2, 5, 5));

        final List<Component> cpus = controller.getComponents(Part.CPU);
        final List<Component> coolers = controller.getComponents(Part.COOLER);
        final List<Component> cases = controller.getComponents(Part.CASE);
        final List<Component> psus = controller.getComponents(Part.PSU);
        final List<Component> motherboards = controller.getComponents(Part.MOTHERBOARD);
        final List<Component> gpus = controller.getComponents(Part.GPU);
        final List<Component> rams = controller.getComponents(Part.RAM);
        final List<Component> storage = controller.getComponents(Part.STORAGE);

        this.cpuPanel = createPanel("CPU", cpus);
        this.coolerPanel = createPanel("Cooler", coolers);
        this.casePanel = createPanel("Case", cases);
        this.psuPanel = createPanel("PSU", psus);
        this.motherboardPanel = createPanel("Motherboard", motherboards);
        this.gpuPanel = createScrollablePanelWithAddRemove("GPU", gpus);
        this.ramPanel = createScrollablePanelWithAddRemove("RAM", rams);
        this.storagePanel = createScrollablePanelWithAddRemove("Storage", storage);

        this.mainPanel.add(cpuPanel);
        this.mainPanel.add(coolerPanel);
        this.mainPanel.add(casePanel);
        this.mainPanel.add(psuPanel);
        this.mainPanel.add(motherboardPanel);
        this.mainPanel.add(gpuPanel);
        this.mainPanel.add(ramPanel);
        this.mainPanel.add(storagePanel);

        this.add(mainPanel, BorderLayout.CENTER);

        this.bottomPanel = new JPanel();
        this.bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        this.totalPriceLabel = new JLabel("Total Price: €0.00");
        this.bottomPanel.add(totalPriceLabel);

        this.uploadBuildButton = new JButton("Upload Build");

        uploadBuildButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                selectedGpu.clear();
                selectedRam.clear();
                selectedStorage.clear();
        
                selectedCpu = getSelectedComponent(cpuPanel);
                selectedCooler = getSelectedComponent(coolerPanel);
                selectedCase = getSelectedComponent(casePanel);
                selectedPsu = getSelectedComponent(psuPanel);
                selectedMotherboard = getSelectedComponent(motherboardPanel);
        
                selectedGpu = getSelectedComponents(gpuPanel);
                selectedRam = getSelectedComponents(ramPanel);
                selectedStorage = getSelectedComponents(storagePanel);
        
                if (ccc.areCpuMoboCompatible(selectedCpu, selectedMotherboard) &&
                    selectedRam.stream().allMatch(ram -> ccc.areRamMoboCompatible(ram, selectedMotherboard)) &&
                    selectedRam.stream().allMatch(ram -> ccc.checkCompatibility(ram, selectedCpu))) {
        
                    int id = controller.getLatestBuildId() + 1;
                    Build build = new Build(id, selectedCooler, selectedCase,
                        selectedPsu, selectedCpu, selectedMotherboard, selectedGpu,
                        selectedRam, selectedStorage, controller.getLoggedUser().getUsername());
                    controller.insertBuild(build, controller.getLoggedUser());
        
                } else {
                    JOptionPane.showMessageDialog(null,
                        "Selected CPU, RAM, and Motherboard are not compatible.",
                        "Compatibility Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.bottomPanel.add(uploadBuildButton);
        this.add(bottomPanel, BorderLayout.SOUTH);

        updateTotalPrice();
    }

    private JPanel createPanel(final String title, final List<Component> components) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        final TitledBorder border = BorderFactory.createTitledBorder(title);
        panel.setBorder(border);

        final JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;

        final JComboBox<Component> comboBox = new JComboBox<>();
        for (final Component component : components) {
            comboBox.addItem(component);
        }
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        topPanel.add(comboBox, gbc);

        final JLabel priceLabel = new JLabel("Price: €0.00");
        gbc.gridx = 1;
        gbc.weightx = 0.2;
        topPanel.add(priceLabel, gbc);

        final JButton detailsButton = new JButton("View Details");
        gbc.gridx = 2;
        gbc.weightx = 0.3;
        topPanel.add(detailsButton, gbc);

        panel.add(topPanel, BorderLayout.NORTH);

        comboBoxToPriceLabelMap.put(comboBox, priceLabel);
        panelToComponentsMap.put(panel, components);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                @SuppressWarnings("unchecked")
                final
                JComboBox<Component> sourceComboBox = (JComboBox<Component>) e.getSource();
                final Component selectedComponent = (Component) sourceComboBox.getSelectedItem();
                if (selectedComponent != null) {
                    final double price = controller.getScrapedPrice(selectedComponent.getBaseInfo().getId()).getComponentPrice();
                    final JLabel correspondingPriceLabel = comboBoxToPriceLabelMap.get(sourceComboBox);
                    correspondingPriceLabel.setText("Price: €" + String.format("%.2f", price));
                    updateTotalPrice();
                }
            }
        });

        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final Component selectedComponent = (Component) comboBox.getSelectedItem();
                if (selectedComponent != null) {
                    showComponentDetails(selectedComponent);
                }
            }
        });

        final Component initialComponent = (Component) comboBox.getSelectedItem();
        if (initialComponent != null) {
            final double initialPrice = controller.getScrapedPrice(initialComponent.getBaseInfo().getId()).getComponentPrice();
            priceLabel.setText("Price: €" + String.format("%.2f", initialPrice));
        }

        return panel;
    }

    private JPanel createScrollablePanelWithAddRemove(final String title, final List<Component> components) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        final TitledBorder border = BorderFactory.createTitledBorder(title);
        panel.setBorder(border);

        final JPanel dynamicPanel = new JPanel();
        dynamicPanel.setLayout(new GridBagLayout());

        final JScrollPane scrollPane = new JScrollPane(dynamicPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane, BorderLayout.CENTER);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        final JButton addButton = new JButton("Add");
        buttonPanel.add(addButton);

        final JButton removeButton = new JButton("Remove");
        buttonPanel.add(removeButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        addComponentSelection(dynamicPanel, components);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                addComponentSelection(dynamicPanel, components);
                updateTotalPrice();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                removeComponentSelection(dynamicPanel);
                updateTotalPrice();
            }
        });
        return panel;
    }

    private void addComponentSelection(final JPanel dynamicPanel, final List<Component> components) {
        final JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        final JComboBox<Component> comboBox = new JComboBox<>();
        for (final Component component : components) {
            comboBox.addItem(component);
        }
        newPanel.add(comboBox);

        final JLabel priceLabel = new JLabel("Price: €0.00");
        newPanel.add(priceLabel);

        final JButton detailsButton = new JButton("View Details");
        newPanel.add(detailsButton);

        comboBoxToPriceLabelMap.put(comboBox, priceLabel);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                @SuppressWarnings("unchecked")
                final
                JComboBox<Component> sourceComboBox = (JComboBox<Component>) e.getSource();
                final Component selectedComponent = (Component) sourceComboBox.getSelectedItem();
                if (selectedComponent != null) {
                    final double price = controller.getScrapedPrice(selectedComponent.getBaseInfo().getId()).getComponentPrice();
                    final JLabel correspondingPriceLabel = comboBoxToPriceLabelMap.get(sourceComboBox);
                    correspondingPriceLabel.setText("Price: €" + String.format("%.2f", price));
                    updateTotalPrice();
                }
            }
        });

        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final Component selectedComponent = (Component) comboBox.getSelectedItem();
                if (selectedComponent != null) {
                    showComponentDetails(selectedComponent);
                }
            }
        });

        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = dynamicPanel.getComponentCount();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        dynamicPanel.add(newPanel, gbc);

        dynamicPanel.revalidate();
        dynamicPanel.repaint();

        final Component initialComponent = (Component) comboBox.getSelectedItem();
        if (initialComponent != null) {
            final double initialPrice = controller.getScrapedPrice(initialComponent.getBaseInfo().getId()).getComponentPrice();
            priceLabel.setText("Price: €" + String.format("%.2f", initialPrice));
        }
    }

    @SuppressWarnings("unchecked")
    private void removeComponentSelection(final JPanel dynamicPanel) {
        if (dynamicPanel.getComponentCount() > 1) {
            final JPanel panelToRemove = (JPanel) dynamicPanel.getComponent(dynamicPanel.getComponentCount() - 1);
            
            JComboBox<Component> comboBoxToRemove = null;
            for (final java.awt.Component comp : panelToRemove.getComponents()) {
                if (comp instanceof JComboBox) {
                    comboBoxToRemove = (JComboBox<Component>) comp;
                    break;
                }
            }
            
            if (comboBoxToRemove != null) {
                comboBoxToPriceLabelMap.remove(comboBoxToRemove);
            }
            
            dynamicPanel.remove(panelToRemove);
            dynamicPanel.revalidate();
            dynamicPanel.repaint();
            
            updateTotalPrice();
        }
    }

    private void showComponentDetails(final Component component) {
        final StringBuilder details = new StringBuilder();
        details.append("Component: ").append(component.toString()).append("\n\n");

        final Map<String, String> formattedAttributes = component.getFormattedAttributes();
        for (final Map.Entry<String, String> entry : formattedAttributes.entrySet()) {
            details.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        JOptionPane.showMessageDialog(this, details.toString(), "Component Details", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateTotalPrice() {
        float totalPrice = 0;
        for (final JLabel priceLabel : comboBoxToPriceLabelMap.values()) {
            final String text = priceLabel.getText();
            if (text.startsWith("Price: €")) {
                try {
                    final float price = Float.parseFloat(text.substring(8));
                    totalPrice += price;
                } catch (final NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        totalPriceLabel.setText("Total Price: €" + String.format("%.2f", totalPrice));
    }

    private Component getSelectedComponent(final JPanel panel) {
        for (final java.awt.Component comp : panel.getComponents()) {
            if (comp instanceof JPanel) {
                // Search recursively within nested panels
                final JPanel innerPanel = (JPanel) comp;
                for (final java.awt.Component innerComp : innerPanel.getComponents()) {
                    if (innerComp instanceof JComboBox) {
                        @SuppressWarnings("unchecked")
                        final
                        JComboBox<Component> comboBox = (JComboBox<Component>) innerComp;
                        return (Component) comboBox.getSelectedItem();
                    }
                }
            } else if (comp instanceof JComboBox) {
                // Directly search if JComboBox is found
                @SuppressWarnings("unchecked")
                final
                JComboBox<Component> comboBox = (JComboBox<Component>) comp;
                final Component selectedComponent = (Component) comboBox.getSelectedItem();
                return (Component) selectedComponent;
            }
        }
        return null;
    }
    
    private List<Component> getSelectedComponents(final JPanel panel) {
        final List<Component> components = new ArrayList<>();
        for (final java.awt.Component panelComponent : panel.getComponents()) {
            if (panelComponent instanceof JScrollPane) {
                final JScrollPane scrollPane = (JScrollPane) panelComponent;
                final JPanel dynamicPanel = (JPanel) scrollPane.getViewport().getView();
                for (final java.awt.Component comp : dynamicPanel.getComponents()) {
                    if (comp instanceof JPanel) {
                        final JPanel innerPanel = (JPanel) comp;
                        for (final java.awt.Component innerComp : innerPanel.getComponents()) {
                            if (innerComp instanceof JComboBox) {
                                @SuppressWarnings("unchecked")
                                final
                                JComboBox<Component> comboBox = (JComboBox<Component>) innerComp;
                                final Component selectedComponent = (Component) comboBox.getSelectedItem();
                                components.add(selectedComponent);
                            }
                        }
                    }
                }
            }
        }
        return components;
    }
}