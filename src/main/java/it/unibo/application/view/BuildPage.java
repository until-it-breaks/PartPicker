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
    private Controller controller;

    public BuildPage(Controller controller) {
        this.controller = controller;
        initializeUI();
    }

    private void initializeUI() {
        this.setLayout(new BorderLayout());

        // Add the top bar
        this.add(new TopBar(controller), BorderLayout.NORTH);

        // Main panel to hold components and comments section
        JPanel mainPanel = new JPanel(new BorderLayout());

        final int targetBuild = controller.getTargetBuild();
        Build build = controller.findBuildById(targetBuild);

        if (build != null) {
            UserDetails userDetails = controller.getUserDetails(build.getAuthor());

            // Info panel with build ID and author
            JPanel infoPanel = new JPanel(new GridLayout(2, 1));
            infoPanel.add(new JLabel("Build ID: " + build.getBuildId()));

            JLabel authorLabel = new JLabel("Author: " + build.getAuthor());
            authorLabel.setForeground(Color.BLUE);
            authorLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            authorLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    showUserDetails(userDetails);
                }
            });

            infoPanel.add(authorLabel);
            mainPanel.add(infoPanel, BorderLayout.NORTH);

            // Components panel
            JPanel componentsPanel = new JPanel(new GridLayout(0, 1));
            componentsPanel.add(new JLabel("Components:"));

            componentsPanel.add(new JLabel("Cooler: " + build.getCooler().getBaseInfo().getName()));
            componentsPanel.add(new JLabel("Case: " + build.get_case().getBaseInfo().getName()));
            componentsPanel.add(new JLabel("PSU: " + build.getPsu().getBaseInfo().getName()));
            componentsPanel.add(new JLabel("CPU: " + build.getCpu().getBaseInfo().getName()));
            componentsPanel.add(new JLabel("Motherboard: " + build.getMotherboard().getBaseInfo().getName()));

            List<Gpu> gpus = build.getGpus();
            for (Gpu gpu : gpus) {
                componentsPanel.add(new JLabel("GPU: " + gpu.getBaseInfo().getName()));
            }

            List<Ram> rams = build.getRams();
            for (Ram ram : rams) {
                componentsPanel.add(new JLabel("RAM: " + ram.getBaseInfo().getName()));
            }

            List<Storage> storage = build.getStorage();
            for (Storage stor : storage) {
                componentsPanel.add(new JLabel("Storage: " + stor.getBaseInfo().getName()));
            }

            // Add components panel to a scroll pane
            JScrollPane componentsScrollPane = new JScrollPane(componentsPanel);

            // Comments section (empty panel for now)
            JPanel commentsPanel = new JPanel(new BorderLayout());
            commentsPanel.add(new JLabel("Comments:"), BorderLayout.NORTH);

            // Split pane to divide components and comments
            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, componentsScrollPane, commentsPanel);
            splitPane.setDividerLocation(600); // Adjust as necessary
            mainPanel.add(splitPane, BorderLayout.CENTER);
        } else {
            mainPanel.add(new JLabel("Build not found"), BorderLayout.CENTER);
        }

        // Add the main panel to the center of the BorderLayout
        this.add(mainPanel, BorderLayout.CENTER);
    }

    private void showUserDetails(UserDetails userDetails) {
        StringBuilder userDetailsText = new StringBuilder();
        userDetailsText.append("Username: ").append(userDetails.getUsername()).append("\n")
                .append("Registration Date: ").append(userDetails.getRegistrationDate()).append("\n")
                .append("Email: ").append(userDetails.getEmail()).append("\n")
                .append("Moderator: ").append(userDetails.getIsModerator() ? "Yes" : "No").append("\n")
                .append("Average Rating: ").append(userDetails.getAverageRating()).append("\n")
                .append("Build Count: ").append(userDetails.getBuildCount());

        JOptionPane.showMessageDialog(this, userDetailsText.toString(), "User Details", JOptionPane.INFORMATION_MESSAGE);
    }
}