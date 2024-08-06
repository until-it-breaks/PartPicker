package it.unibo.application.view;


import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.builds.Build;
import it.unibo.application.data.entities.components.Gpu;
import it.unibo.application.data.entities.components.Ram;
import it.unibo.application.data.entities.components.Storage;
import it.unibo.application.data.entities.login.UserDetails;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;

public class BuildPage extends JPanel {
    private final Controller controller;

    public BuildPage(final Controller controller) {
        this.controller = controller;
        initializeUI();
    }

    private void initializeUI() {
        this.setLayout(new BorderLayout());

        this.add(new TopBar(controller), BorderLayout.NORTH);

        final JPanel mainPanel = new JPanel(new BorderLayout());

        final int targetBuild = controller.getTargetBuild();
        final Build build = controller.findBuildById(targetBuild);

        if (build != null) {
            final UserDetails userDetails = controller.getUserDetails(build.getAuthor());

            final JPanel infoPanel = new JPanel(new GridLayout(2, 1));
            infoPanel.add(new JLabel("Build ID: " + build.getBuildId()));

            final JLabel authorLabel = new JLabel("Author: " + build.getAuthor());
            authorLabel.setForeground(Color.BLUE);
            authorLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            authorLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    showUserDetails(userDetails);
                }
            });

            infoPanel.add(authorLabel);
            mainPanel.add(infoPanel, BorderLayout.NORTH);

            final JPanel componentsPanel = new JPanel(new GridLayout(0, 1));
            componentsPanel.add(new JLabel("Components:"));

            componentsPanel.add(new JLabel("Cooler: " + build.getCooler().getBaseInfo().getName()));
            componentsPanel.add(new JLabel("Case: " + build.get_case().getBaseInfo().getName()));
            componentsPanel.add(new JLabel("PSU: " + build.getPsu().getBaseInfo().getName()));
            componentsPanel.add(new JLabel("CPU: " + build.getCpu().getBaseInfo().getName()));
            componentsPanel.add(new JLabel("Motherboard: " + build.getMotherboard().getBaseInfo().getName()));

            final List<Gpu> gpus = build.getGpus();
            for (final Gpu gpu : gpus) {
                componentsPanel.add(new JLabel("GPU: " + gpu.getBaseInfo().getName()));
            }

            final List<Ram> rams = build.getRams();
            for (final Ram ram : rams) {
                componentsPanel.add(new JLabel("RAM: " + ram.getBaseInfo().getName()));
            }

            final List<Storage> storage = build.getStorage();
            for (final Storage stor : storage) {
                componentsPanel.add(new JLabel("Storage: " + stor.getBaseInfo().getName()));
            }

            final JScrollPane componentsScrollPane = new JScrollPane(componentsPanel);

            final JPanel commentsPanel = new JPanel(new BorderLayout());
            commentsPanel.add(new JLabel("Comments:"), BorderLayout.NORTH);

            final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, componentsScrollPane, commentsPanel);
            splitPane.setDividerLocation(600); // Adjust as necessary
            mainPanel.add(splitPane, BorderLayout.CENTER);
        } else {
            mainPanel.add(new JLabel("Build not found"), BorderLayout.CENTER);
        }

        this.add(mainPanel, BorderLayout.CENTER);
    }

    private void showUserDetails(final UserDetails userDetails) {
        final StringBuilder userDetailsText = new StringBuilder();
        userDetailsText.append("Username: ").append(userDetails.getUsername()).append("\n")
                .append("Registration Date: ").append(userDetails.getRegistrationDate()).append("\n")
                .append("Email: ").append(userDetails.getEmail()).append("\n")
                .append("Moderator: ").append(userDetails.getIsModerator() ? "Yes" : "No").append("\n")
                .append("Average Rating: ").append(userDetails.getAverageRating()).append("\n")
                .append("Build Count: ").append(userDetails.getBuildCount());

        JOptionPane.showMessageDialog(this, userDetailsText.toString(), "User Details", JOptionPane.INFORMATION_MESSAGE);
    }
}