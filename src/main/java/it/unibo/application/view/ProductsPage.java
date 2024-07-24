package it.unibo.application.view;

import javax.swing.JPanel;

import it.unibo.application.controller.Controller;
import it.unibo.application.model.enums.Part;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JLabel;

public class ProductsPage extends JPanel {
    public ProductsPage(Controller controller, Part part) {
        this.setLayout(new BorderLayout());
        JPanel topBar = new JPanel();
        topBar.add(new NavigationBar(controller));
        topBar.setBackground(Color.LIGHT_GRAY);
        topBar.add(new JLabel("Choose A " + part));
        this.add(topBar, BorderLayout.NORTH);
        

        JPanel choiceTab = new JPanel();
        choiceTab.setLayout(new BoxLayout(choiceTab, BoxLayout.Y_AXIS));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(1, 5));
        headerPanel.add(new JLabel("Name"));
        headerPanel.add(new JLabel("Core Count"));
        headerPanel.add(new JLabel("Boost Clock"));
        headerPanel.add(new JLabel("TDP"));
        headerPanel.add(new JLabel("Integrated Graphics"));

        choiceTab.add(headerPanel);

        choiceTab.add(createItemPanel(Part.CPU));
        choiceTab.add(createItemPanel(Part.CPU));
        choiceTab.add(createItemPanel(Part.CPU));
        choiceTab.add(createItemPanel(Part.CPU));
        choiceTab.add(createItemPanel(Part.CPU));
        choiceTab.add(createItemPanel(Part.CPU));
        choiceTab.add(createItemPanel(Part.CPU));
        choiceTab.add(createItemPanel(Part.CPU));

        this.add(choiceTab, BorderLayout.CENTER);
    }

    private JPanel createItemPanel(Part part) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new GridLayout(1, 5));
        itemPanel.add(new JLabel("AMD Ryzen 7 7800X3D"));
        itemPanel.add(new JLabel("8"));
        itemPanel.add(new JLabel("4.7 GHz"));
        itemPanel.add(new JLabel("95W"));
        itemPanel.add(new JLabel("None"));
        itemPanel.add(new JButton("Add"));
        return itemPanel;
    }
}
