package it.unibo.application.view;

import javax.swing.JPanel;

import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.components.Component;

import java.util.List;
import java.util.Map;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ProductsPage extends JPanel {
    public ProductsPage(final Controller controller) {
        this.setLayout(new BorderLayout());
        this.add(new TopBar(controller), BorderLayout.NORTH);

        List<Component> components = controller.getComponents(controller.getDesiredPart());
        
        // Define column names for the table
        String[] columnNames = {"ID", "Name", "Launch Year", "MSRP"};
        
        // Create a table model and set the column names
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        
        // Add rows to the table model
        for (Component component : components) {
            Map<String, String> specs = component.getSpecificAttributes();
            String[] rowData = new String[columnNames.length];
            for (int i = 0; i < columnNames.length; i++) {
                rowData[i] = specs.get(columnNames[i]).toString();
            }
            tableModel.addRow(rowData);
        }
        
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);
    }
}