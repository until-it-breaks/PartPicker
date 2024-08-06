package it.unibo.application.view;

import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.builds.Build;
import it.unibo.application.data.entities.enums.Part;
import it.unibo.application.data.entities.enums.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class OverviewPage extends JPanel {
    private Controller controller;

    public OverviewPage(Controller controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());

        this.add(new TopBar(controller), BorderLayout.NORTH);
        this.add(createMiddleSection(), BorderLayout.CENTER);
        this.add(createBottomSection(), BorderLayout.SOUTH);
    }

    private JPanel createMiddleSection() {
        JPanel middleSection = new JPanel();
        middleSection.setLayout(new BorderLayout());

        JPanel latestBuildsTitlePanel = new JPanel();
        latestBuildsTitlePanel.add(new JLabel("Latest Builds"));

        JPanel latestBuildsList = new JPanel();
        latestBuildsList.setLayout(new BoxLayout(latestBuildsList, BoxLayout.Y_AXIS));

        List<Build> latestBuilds = controller.getBuilds();
        for (Build build : latestBuilds) {
            latestBuildsList.add(createBuildRow(build));
        }

        JScrollPane scrollPane = new JScrollPane(latestBuildsList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        middleSection.add(latestBuildsTitlePanel, BorderLayout.NORTH);
        middleSection.add(scrollPane, BorderLayout.CENTER);

        return middleSection;
    }

    private JPanel createBottomSection() {
        JPanel bottomSection = new JPanel();
        bottomSection.setLayout(new BoxLayout(bottomSection, BoxLayout.X_AXIS));
        bottomSection.setBackground(new Color(84, 85, 120));

        JButton buildOwnButton = new JButton("Create a build");
        bottomSection.add(createPartButton("CPUs", Part.CPU));
        bottomSection.add(createPartButton("Video Cards", Part.GPU));
        bottomSection.add(createPartButton("Memory", Part.RAM));
        bottomSection.add(createPartButton("Storage", Part.STORAGE));
        bottomSection.add(createPartButton("CPU Coolers", Part.COOLER));
        bottomSection.add(createPartButton("Cases", Part.CASE));
        bottomSection.add(createPartButton("Motherboards", Part.MOTHERBOARD));
        bottomSection.add(createPartButton("Power Supplies", Part.PSU));
        bottomSection.add(Box.createHorizontalGlue());
        bottomSection.add(buildOwnButton);

        buildOwnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setAppState(State.BUILDING);
            }
        });

        return bottomSection;
    }

    private JButton createPartButton(String label, Part part) {
        JButton button = new JButton(label);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setDesiredPart(part);
                controller.setAppState(State.PRODUCTS);
            }
        });
        return button;
    }

    private JPanel createBuildRow(final Build build) {
        JPanel buildRow = new JPanel();
        buildRow.setLayout(new BorderLayout());
        buildRow.setBorder(BorderFactory.createEtchedBorder());
        JLabel titleLabel = new JLabel("Build ID: " + build.getBuildId() + " | Author: " + build.getAuthor());
        titleLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        titleLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.setTargetBuild(build.getBuildId());
                controller.setAppState(State.VIEW_BUILD);
            }
        });
        buildRow.add(titleLabel, BorderLayout.CENTER);
        return buildRow;
    }
}