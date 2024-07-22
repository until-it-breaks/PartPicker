package it.unibo.application.view;

import javax.swing.JPanel;

import it.unibo.application.model.Part;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class BuilderPage extends JPanel {
    public BuilderPage() {
        this.setLayout(new BorderLayout());
        
        JPanel topBar = new JPanel();
        JButton backButton = new JButton("Back");
        topBar.add(backButton);
        this.add(topBar, BorderLayout.NORTH);

        JPanel choiceTab = new JPanel();
        choiceTab.setLayout(new BoxLayout(choiceTab, BoxLayout.Y_AXIS));
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        headerPanel.add(new JLabel("Component"));
        headerPanel.add(new JLabel("Selection"));
        headerPanel.add(new JLabel("Base Price"));
        headerPanel.add(new JLabel("Current Price"));

        choiceTab.add(headerPanel);
        choiceTab.add(createBlankItemPanel(Part.CPU));
        choiceTab.add(createBlankItemPanel(Part.CPU_COOLER));
        choiceTab.add(createBlankItemPanel(Part.MOTHERBOARD));
        choiceTab.add(createBlankItemPanel(Part.MEMORY));
        choiceTab.add(createBlankItemPanel(Part.STORAGE));
        choiceTab.add(createBlankItemPanel(Part.VIDEO_CARD));
        choiceTab.add(createBlankItemPanel(Part.CASE));
        choiceTab.add(createBlankItemPanel(Part.POWER_SUPPLY));

        this.add(choiceTab, BorderLayout.CENTER);
        
        JPanel totalPanel = new JPanel();
        totalPanel.add(new JLabel("Total : 300$"));

        this.add(totalPanel, BorderLayout.SOUTH);
    }

    private JPanel createBlankItemPanel(Part part) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new FlowLayout());
        itemPanel.add(new JLabel(part.toString()));
        JButton itemButton = new JButton("Choose A " + part.toString());
        itemButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                itemPanel.removeAll();
                itemPanel.add(createItemPanel(part));
                itemPanel.repaint();
                itemPanel.revalidate();
            }
            
        });
        itemPanel.add(itemButton);
        return itemPanel;
    }

    private JPanel createItemPanel(Part part) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new FlowLayout());
        itemPanel.add(new JLabel(part.toString()));
        itemPanel.add(new JLabel("AMD Ryzen 7 7800X3D"));
        itemPanel.add(new JLabel("384$"));
        itemPanel.add(new JLabel("400$"));
        return itemPanel;
    }
}
