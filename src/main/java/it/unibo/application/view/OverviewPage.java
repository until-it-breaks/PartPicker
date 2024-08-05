package it.unibo.application.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.SwingConstants;

import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.builds.Build;
import it.unibo.application.data.entities.components.Gpu;
import it.unibo.application.data.entities.components.Ram;
import it.unibo.application.data.entities.components.Storage;
import it.unibo.application.data.entities.enums.Part;
import it.unibo.application.data.entities.enums.State;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.List;

public class OverviewPage extends JPanel {
    Controller controller;

    public OverviewPage(final Controller controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());

        final JPanel middleSection = new JPanel();
        middleSection.setLayout(new BoxLayout(middleSection, BoxLayout.Y_AXIS));

        final JPanel hotBuildsTitlePanel = new JPanel();
        hotBuildsTitlePanel.add(new JLabel("Hot Builds"));

        final JPanel recentBuildsRow = new JPanel();
        recentBuildsRow.setLayout(new BoxLayout(recentBuildsRow, BoxLayout.X_AXIS));

        final List<Build> latestBuilds = controller.getBuilds();
        for (final Build build : latestBuilds) {
            recentBuildsRow.add(createBuildFrame(build));
        }

        final JPanel viewAllBuildsRow = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        final JPanel ownBuildsTitlePanel = new JPanel();
        ownBuildsTitlePanel.add(new JLabel("Own Builds"));

        final JPanel ownBuildsRow = new JPanel();
        ownBuildsRow.setLayout(new BoxLayout(ownBuildsRow, BoxLayout.X_AXIS));

        final List<Build> ownBuilds = controller.getBuilds();
        for (final Build build : ownBuilds) {
            ownBuildsRow.add(createBuildFrame(build));
        }

        middleSection.add(hotBuildsTitlePanel);
        middleSection.add(recentBuildsRow);
        middleSection.add(viewAllBuildsRow);
        middleSection.add(ownBuildsTitlePanel);
        middleSection.add(ownBuildsRow);

        final JPanel bottomSection = new JPanel();
        bottomSection.setLayout(new BoxLayout(bottomSection, BoxLayout.X_AXIS));
        bottomSection.setBackground(Color.GRAY);
        final JButton buildOwnButton = new JButton("Create a build");
        final JButton CPUsButton = new JButton("CPUs");
        final JButton VideoCardButton = new JButton("Video Cards");
        final JButton MemoryButton = new JButton("Memory");
        final JButton StorageButton = new JButton("Storage");
        final JButton CPUCoolersButton = new JButton("CPU Coolers");
        final JButton CasesButton = new JButton("Cases");
        final JButton MotherboardsButton = new JButton("Motherboards");
        final JButton PowerSuppliesButton = new JButton("Power Supplies");
        final JButton ProfileDetailsButton = new JButton("Your details");
        buildOwnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomSection.add(CPUsButton);
        bottomSection.add(VideoCardButton);
        bottomSection.add(MemoryButton);
        bottomSection.add(StorageButton);
        bottomSection.add(CPUCoolersButton);
        bottomSection.add(CasesButton);
        bottomSection.add(MotherboardsButton);
        bottomSection.add(PowerSuppliesButton);
        bottomSection.add(Box.createHorizontalGlue());
        bottomSection.add(ProfileDetailsButton);
        bottomSection.add(buildOwnButton);
        middleSection.add(bottomSection);

        this.add(new TopBar(controller), BorderLayout.NORTH);
        this.add(middleSection, BorderLayout.CENTER);

        buildOwnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setAppState(State.BUILDING);
            }
        });

        CPUsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setDesiredPart(Part.CPU);
                controller.setAppState(State.PRODUCTS);
            }
        });

        VideoCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setDesiredPart(Part.GPU);
                controller.setAppState(State.PRODUCTS);
            }
        });

        MemoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setDesiredPart(Part.RAM);
                controller.setAppState(State.PRODUCTS);
            }
        });

        StorageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setDesiredPart(Part.STORAGE);
                controller.setAppState(State.PRODUCTS);
            }
        });

        CasesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setDesiredPart(Part.CASE);
                controller.setAppState(State.PRODUCTS);
            }
        });

        MotherboardsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setDesiredPart(Part.MOTHERBOARD);
                controller.setAppState(State.PRODUCTS);
            }
        });

        CPUCoolersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setDesiredPart(Part.COOLER);
                controller.setAppState(State.PRODUCTS);
            }
        });

        PowerSuppliesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setDesiredPart(Part.PSU);
                controller.setAppState(State.PRODUCTS);
            }
        });

    }

    private JPanel createBuildFrame(final Build build) {
        final JPanel buildFrame = new JPanel();
        buildFrame.setLayout(new BorderLayout());
        buildFrame.setPreferredSize(new Dimension(300, 200));
        buildFrame.setBorder(BorderFactory.createEtchedBorder());

        final JLabel titleLabel = new JLabel("Build ID: " + build.getBuildId() + " | Author: " + build.getAuthor(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        buildFrame.add(titleLabel, BorderLayout.NORTH);

        final JTextArea description = new JTextArea();
        description.setWrapStyleWord(true);
        description.setLineWrap(true);
        description.setOpaque(false);
        description.setEditable(false);
        description.setFocusable(false);
        buildFrame.add(description, BorderLayout.CENTER);

        final StringBuilder componentList = new StringBuilder();
        float totalPrice = 0;

        for (final Gpu gpu : build.getGpus()) {
            componentList.append(gpu.getBaseInfo().getName()).append(", ");
            totalPrice += gpu.getBaseInfo().getMsrp();
        }

        for (final Ram ram : build.getRams()) {
            componentList.append(ram.getBaseInfo().getName()).append(", ");
            totalPrice += ram.getBaseInfo().getMsrp();
        }

        for (final Storage storage : build.getStorage()) {
            componentList.append(storage.getBaseInfo().getName()).append(", ");
            totalPrice += storage.getBaseInfo().getMsrp();
        }

        if (componentList.length() > 0) {
            // Remove trailing comma and space
            componentList.setLength(componentList.length() - 2);
        }

        description.setText("Components: " + componentList.toString() + "\nTotal Price: $" + totalPrice);

        buildFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                controller.setAppState(State.VIEWING_BUILD);
            }
        });

        return buildFrame;
    }
}