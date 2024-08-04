package it.unibo.application.view;

import javax.swing.JPanel;

import it.unibo.application.controller.Controller;
import it.unibo.application.model.enums.Part;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;

public class ProductsPage extends JPanel {
    public ProductsPage(final Controller controller, final Part part) {
        this.setLayout(new BorderLayout());

        final JPanel topBar = new JPanel();
        topBar.setLayout(new BoxLayout(topBar, BoxLayout.Y_AXIS));
        topBar.add(new TopBar(controller));
        topBar.add(new JLabel("Choose A " + part));


        final JPanel choiceTab = new JPanel();
        choiceTab.setLayout(new BoxLayout(choiceTab, BoxLayout.Y_AXIS));

        final JPanel headerPanel = new JPanel();

        /*
        int numberOfSpecFields = part.getSpecifications().size();
        headerPanel.setLayout(new GridLayout(1, numberOfSpecFields));
    
        headerPanel.add(new JLabel("Name"));

        for (int i = 0; i < numberOfSpecFields; i++) {
            headerPanel.add(new JLabel(part.getSpecifications().get(i)));
        }
        */

        choiceTab.add(headerPanel);

        choiceTab.add(createItemPanel(Part.CPU));
        choiceTab.add(createItemPanel(Part.CPU));
        choiceTab.add(createItemPanel(Part.CPU));
        choiceTab.add(createItemPanel(Part.CPU));
        choiceTab.add(createItemPanel(Part.CPU));
        choiceTab.add(createItemPanel(Part.CPU));
        choiceTab.add(createItemPanel(Part.CPU));
        choiceTab.add(createItemPanel(Part.CPU));

        this.add(topBar, BorderLayout.NORTH);
        this.add(choiceTab, BorderLayout.CENTER);
    }

    private JPanel createItemPanel(final Part part) {
        final JPanel itemPanel = new JPanel();
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
