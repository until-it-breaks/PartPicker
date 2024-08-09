package it.unibo.application.view;

import javax.swing.*;

import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.enums.Part;
import it.unibo.application.data.entities.enums.State;
import it.unibo.application.view.insertion.CaseInsertDialog;
import it.unibo.application.view.insertion.CoolerInsertDialog;
import it.unibo.application.view.insertion.CpuInsertDialog;
import it.unibo.application.view.insertion.GpuInsertDialog;
import it.unibo.application.view.insertion.MotherboardInsertDialog;
import it.unibo.application.view.insertion.PsuInsertDialog;
import it.unibo.application.view.insertion.RamInsertDialog;
import it.unibo.application.view.insertion.StorageInsertDialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JPanel {
    private final JComboBox<Part> partComboBox;
    private final JButton addButton;
    private final JButton backButton;

    public AdminPage(final Controller controller) {

        setLayout(new BorderLayout());

        final JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButtonPanel.setOpaque(false);

        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(80, 30));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setAppState(State.WELCOME);
            }
        });

        backButtonPanel.add(backButton);

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        partComboBox = new JComboBox<>(Part.values());
        partComboBox.setPreferredSize(new Dimension(150, 30));

        addButton = new JButton("Add");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final Part selectedPart = (Part) partComboBox.getSelectedItem();
                switch (selectedPart) {
                    case CASE:
                        new CaseInsertDialog(controller).showDialog();;
                        break;
                    case PSU:
                        new PsuInsertDialog(controller).showDialog();
                        break;
                    case RAM:
                        new RamInsertDialog(controller).showDialog();
                        break;
                    case STORAGE:
                        new StorageInsertDialog(controller).showDialog();
                        break;
                    case GPU:
                        new GpuInsertDialog(controller).showDialog();
                        break;
                    case MOTHERBOARD:
                        new MotherboardInsertDialog(controller).showDialog();
                        break;
                    case COOLER:
                        new CoolerInsertDialog(controller).showDialog();
                        break;
                    case CPU:
                        new CpuInsertDialog(controller).showDialog();
                        break;
                    default:
                        break;
                }
            }
        });
        mainPanel.add(new JLabel("Select type of component to add:"));
        mainPanel.add(partComboBox);
        mainPanel.add(addButton);

        add(backButtonPanel, BorderLayout.PAGE_START);
        add(mainPanel, BorderLayout.CENTER);
    }
}