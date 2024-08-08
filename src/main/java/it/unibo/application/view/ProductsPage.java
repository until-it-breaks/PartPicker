package it.unibo.application.view;

import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.components.BaseInfo;
import it.unibo.application.data.entities.components.Component;
import it.unibo.application.data.entities.enums.Specs;
import it.unibo.application.data.entities.price.ComponentPrice;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class ProductsPage extends JPanel {

    private final Controller controller;

    public ProductsPage(final Controller controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());
        this.add(new TopBar(controller), BorderLayout.NORTH);

        final List<Component> components = controller.getComponents(controller.getDesiredPart());

        if (components.isEmpty()) {
            this.add(new JLabel("No components available"), BorderLayout.CENTER);
            return;
        }

        final List<String> baseInfoColumns = List.of("ID", "Name", "Manufacturer", "Launch Year", "MSRP");

        final Map<Specs, String> firstComponentSpecs = components.get(0).getSpecificAttributes();
        final List<String> columnNames = new ArrayList<>(baseInfoColumns);
        for (final Specs spec : firstComponentSpecs.keySet()) {
            columnNames.add(spec.getFieldName());
        }

        final DefaultTableModel tableModel = new DefaultTableModel(columnNames.toArray(), 0) {
            @Override
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };

        for (final Component component : components) {
            final BaseInfo baseInfo = component.getBaseInfo();
            final Map<Specs, String> specs = component.getSpecificAttributes();

            final List<String> rowData = new ArrayList<>();
            rowData.add(String.valueOf(baseInfo.getId()));
            rowData.add(baseInfo.getName());
            rowData.add(baseInfo.getManufacturer());
            rowData.add(String.valueOf(baseInfo.getLaunchYear()));
            rowData.add(baseInfo.getMsrp() + " €");

            for (final Specs spec : firstComponentSpecs.keySet()) {
                final String value = specs.getOrDefault(spec, "N/A");
                final String suffix = spec.getSuffix();
                rowData.add(value + (suffix != null ? " " + suffix : ""));
            }
            tableModel.addRow(rowData.toArray());
        }

        final JTable table = new JTable(tableModel);
        final JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 2) {
                    final int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        final Component component = components.get(selectedRow);
                        showComponentDetails(component);
                    }
                }
            }
        });
    }

    private void showComponentDetails(final Component component) {
        final BaseInfo baseInfo = component.getBaseInfo();

        final ComponentPrice scrapedPrice = controller.getScrapedPrice(baseInfo.getId());

        final StringBuilder details = new StringBuilder();

        if (scrapedPrice != null) {
            details.append("\nScraped Price:\n");
            details.append("Reseller: ").append(scrapedPrice.getResellerName()).append("\n");
            details.append("Date: ").append(scrapedPrice.getScrapeDate()).append("\n");
            details.append("Price: ").append(scrapedPrice.getComponentPrice()).append(" €");
        } else {
            details.append("\nScraped Price: Not available");
        }

        JOptionPane.showMessageDialog(
                this,
                details.toString(),
                "Component Details",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}