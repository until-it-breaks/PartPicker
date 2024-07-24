package it.unibo.application.view;

import javax.swing.JPanel;

import it.unibo.application.controller.Controller;
import it.unibo.application.model.enums.Part;
import it.unibo.application.view.premades.BottomBar;
import it.unibo.application.view.premades.TopBar;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.JTextArea;
import java.awt.FlowLayout;

import javax.swing.JLabel;

public class BuilderPage extends JPanel {
    public BuilderPage(Controller controller) {
        this.setLayout(new BorderLayout());

        JPanel topBar = new JPanel();
        topBar.setLayout(new BoxLayout(topBar, BoxLayout.Y_AXIS));
        topBar.add(new TopBar(controller));

        JPanel buildNamePanel = new JPanel();
        buildNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    
        JLabel buildNameLabel = new JLabel("Build Name:");
        JTextArea buildName = new JTextArea("myBuild");
        buildName.setColumns(32);
        buildNamePanel.add(buildNameLabel);
        buildNamePanel.add(buildName);

        topBar.add(buildNamePanel);



        JPanel middleSection = new JPanel();
        middleSection.setLayout(new BoxLayout(middleSection, BoxLayout.Y_AXIS));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(1, 4));
        headerPanel.add(new JLabel("Component"));
        headerPanel.add(new JLabel("Selection"));
        headerPanel.add(new JLabel("Base Price"));
        headerPanel.add(new JLabel("Current Price"));

        middleSection.add(headerPanel);

        middleSection.add(createBlankItemPanel(Part.CPU));
        middleSection.add(createBlankItemPanel(Part.CPU_COOLER));
        middleSection.add(createBlankItemPanel(Part.MOTHERBOARD));
        middleSection.add(createBlankItemPanel(Part.MEMORY));
        middleSection.add(createBlankItemPanel(Part.STORAGE));
        middleSection.add(createBlankItemPanel(Part.VIDEO_CARD));
        middleSection.add(createBlankItemPanel(Part.CASE));
        middleSection.add(createBlankItemPanel(Part.POWER_SUPPLY));


        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        JPanel totalPanel = new JPanel();
        totalPanel.add(new JLabel("Total : 300$"));
        JButton saveButton = new JButton("Save Build");
        bottomPanel.add(totalPanel);
        bottomPanel.add(saveButton);
        
        middleSection.add(bottomPanel);

        this.add(topBar, BorderLayout.NORTH);
        this.add(middleSection, BorderLayout.CENTER);
        this.add(new BottomBar(controller), BorderLayout.SOUTH);
    }

    private JPanel createBlankItemPanel(Part part) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new GridLayout(1, 4));
        itemPanel.add(new JLabel(part.toString()));
        
        JButton itemButton = new JButton("Choose A " + part.toString());
        itemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itemPanel.removeAll();
                JPanel newItemPanel = createItemPanel(part);
                for (Component comp : newItemPanel.getComponents()) {
                    itemPanel.add(comp);
                }
                itemPanel.repaint();
                itemPanel.revalidate();
            }
        });

        itemPanel.add(itemButton);
        itemPanel.add(new JLabel("")); // Placeholder for base price
        itemPanel.add(new JLabel("")); // Placeholder for current price
        return itemPanel;
    }

    private JPanel createItemPanel(Part part) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new GridLayout(1, 4));
        itemPanel.add(new JLabel(part.toString()));
        itemPanel.add(new JLabel("AMD Ryzen 7 7800X3D"));
        itemPanel.add(new JLabel("384$"));
        itemPanel.add(new JLabel("400$"));
        return itemPanel;
    }
}
