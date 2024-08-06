package it.unibo.application.view;

import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.builds.Build;
import it.unibo.application.data.entities.components.Gpu;
import it.unibo.application.data.entities.components.Ram;
import it.unibo.application.data.entities.components.Storage;
import it.unibo.application.data.entities.enums.Part;
import it.unibo.application.data.entities.enums.State;
import it.unibo.application.data.entities.login.UserDetails;

import javax.swing.*;
import java.awt.*;
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

        // Create the central area to display hot builds
        final JPanel middleSection = new JPanel();
        middleSection.setLayout(new BoxLayout(middleSection, BoxLayout.Y_AXIS));

        // Title for hot builds
        final JPanel hotBuildsTitlePanel = new JPanel();
        hotBuildsTitlePanel.add(new JLabel("Hot Builds"));

        // Panel for displaying the list of hot builds
        final JPanel recentBuildsRow = new JPanel();
        recentBuildsRow.setLayout(new BoxLayout(recentBuildsRow, BoxLayout.X_AXIS));

        // Retrieve and display hot builds
        final List<Build> latestBuilds = controller.getBuilds();
        for (final Build build : latestBuilds) {
            recentBuildsRow.add(createBuildFrame(build));
        }

        // Add the title and builds to the middle section
        middleSection.add(hotBuildsTitlePanel);
        middleSection.add(recentBuildsRow);

        // Create the bottom section with buttons
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

        // Add the top bar, middle section, and bottom section to the main panel
        this.add(new TopBar(controller), BorderLayout.NORTH);
        this.add(middleSection, BorderLayout.CENTER);
        this.add(bottomSection, BorderLayout.SOUTH);

        // Add action listeners for buttons
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
        final JPanel buildFrame = new JPanel(new GridBagLayout());
        buildFrame.setPreferredSize(new Dimension(300, 200));
        buildFrame.setBorder(BorderFactory.createEtchedBorder());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Title Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        final JLabel titleLabel = new JLabel("Build ID: " + build.getBuildId() + " | Author: " + build.getAuthor());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        titleLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                UserDetails userDetails = controller.getUserDetails(build.getAuthor());
                showUserDetails(userDetails);
            }
        });
        buildFrame.add(titleLabel, gbc);

        // Components Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        final JLabel componentsLabel = new JLabel("Components:");
        buildFrame.add(componentsLabel, gbc);

        // Components List
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        final JTextArea componentsList = new JTextArea();
        componentsList.setWrapStyleWord(true);
        componentsList.setLineWrap(true);
        componentsList.setOpaque(false);
        componentsList.setEditable(false);
        componentsList.setFocusable(false);

        final StringBuilder componentListBuilder = new StringBuilder();
        float totalPrice = 0;

        for (final Gpu gpu : build.getGpus()) {
            componentListBuilder.append(gpu.getBaseInfo().getName()).append("\n");
            totalPrice += gpu.getBaseInfo().getMsrp();
        }

        for (final Ram ram : build.getRams()) {
            componentListBuilder.append(ram.getBaseInfo().getName()).append("\n");
            totalPrice += ram.getBaseInfo().getMsrp();
        }

        for (final Storage storage : build.getStorage()) {
            componentListBuilder.append(storage.getBaseInfo().getName()).append("\n");
            totalPrice += storage.getBaseInfo().getMsrp();
        }

        componentsList.setText(componentListBuilder.toString());
        buildFrame.add(new JScrollPane(componentsList), gbc);

        // Total Price Label
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        final JLabel priceLabel = new JLabel("Total Price: $" + totalPrice);
        buildFrame.add(priceLabel, gbc);

        return buildFrame;
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