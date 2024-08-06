package it.unibo.application.view;


import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.ban.Ban;
import it.unibo.application.data.entities.builds.Build;
import it.unibo.application.data.entities.components.Gpu;
import it.unibo.application.data.entities.components.Ram;
import it.unibo.application.data.entities.components.Storage;
import it.unibo.application.data.entities.login.UserDetails;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
            splitPane.setDividerLocation(600);
            mainPanel.add(splitPane, BorderLayout.CENTER);
        } else {
            mainPanel.add(new JLabel("Build not found"), BorderLayout.CENTER);
        }

        this.add(mainPanel, BorderLayout.CENTER);
    }

    private void showUserDetails(final UserDetails userDetails) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
    
        final JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText(
                "Username: " + userDetails.getUsername() + "\n" +
                "Registration Date: " + userDetails.getRegistrationDate() + "\n" +
                "Email: " + userDetails.getEmail() + "\n" +
                "Moderator: " + (userDetails.getIsModerator() ? "Yes" : "No") + "\n" +
                "Average Rating: " + userDetails.getAverageRating() + "\n" +
                "Build Count: " + userDetails.getBuildCount()
        );
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
    
        if (controller.getLoggedUser().isModerator()) {
            final JButton banButton = new JButton("Ban User");
            banButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    showBanDialog(userDetails);
                }
            });
            panel.add(banButton, BorderLayout.SOUTH);
        }
        JOptionPane.showMessageDialog(this, panel, "User Details", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showBanDialog(final UserDetails userDetails) {
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
    
        final JComboBox<String> banLengthComboBox = new JComboBox<>(new String[]{"1 week", "Permanent"});
        final JTextField motiveField = new JTextField();
    
        panel.add(new JLabel("Ban Length:"));
        panel.add(banLengthComboBox);
        panel.add(new JLabel("Motive:"));
        panel.add(motiveField);
    
        final int result = JOptionPane.showConfirmDialog(this, panel, "Ban User", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    
        if (result == JOptionPane.OK_OPTION) {
            final String banLength = (String) banLengthComboBox.getSelectedItem();
            final String motive = motiveField.getText();
            LocalDate endDate;
    
            if ("Permanent".equals(banLength)) {
                endDate = null;
            } else if ("1 week".equals(banLength)) {
                endDate = LocalDate.now().plus(1, ChronoUnit.WEEKS);
            } else {
                endDate = null;
            }
    
            final Ban ban = new Ban(userDetails.getUsername(), LocalDate.now(), endDate, motive, controller.getLoggedUser().getUsername());
            controller.banUser(ban);
        }
    }
}