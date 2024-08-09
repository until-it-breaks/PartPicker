package it.unibo.application.view;

import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.ban.Ban;
import it.unibo.application.data.entities.builds.Build;
import it.unibo.application.data.entities.builds.Review;
import it.unibo.application.data.entities.components.Component;
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
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(new TopBar(controller), BorderLayout.NORTH);

        final JPanel mainPanel = createMainPanel();
        this.add(mainPanel, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }

    private JPanel createMainPanel() {
        final JPanel mainPanel = new JPanel(new BorderLayout());
        final int targetBuild = controller.getTargetBuild();
        final Build build = controller.findBuildById(targetBuild);

        if (build != null) {
            mainPanel.add(createInfoPanel(build), BorderLayout.NORTH);
            mainPanel.add(createComponentsAndCommentsPanel(build), BorderLayout.CENTER);
            mainPanel.add(createReviewButtonPanel(build), BorderLayout.SOUTH);
        } else {
            mainPanel.add(new JLabel("Build not found"), BorderLayout.CENTER);
        }
        return mainPanel;
    }

    private JPanel createInfoPanel(final Build build) {
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

        return infoPanel;
    }

    private JPanel createComponentsAndCommentsPanel(final Build build) {
        final JPanel componentsPanel = createComponentsPanel(build);
        final JScrollPane componentsScrollPane = new JScrollPane(componentsPanel);

        final JPanel commentsPanel = createCommentsPanel(build);

        final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, componentsScrollPane, commentsPanel);
        splitPane.setDividerLocation(600);

        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(splitPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createComponentsPanel(final Build build) {
        final JPanel componentsPanel = new JPanel(new GridLayout(0, 1));
        componentsPanel.add(new JLabel("Components:"));
        componentsPanel.add(new JLabel("Cooler: " + build.getCooler().getBaseInfo().getName()));
        componentsPanel.add(new JLabel("Case: " + build.get_case().getBaseInfo().getName()));
        componentsPanel.add(new JLabel("PSU: " + build.getPsu().getBaseInfo().getName()));
        componentsPanel.add(new JLabel("CPU: " + build.getCpu().getBaseInfo().getName()));
        componentsPanel.add(new JLabel("Motherboard: " + build.getMotherboard().getBaseInfo().getName()));

        for (final Component gpu : build.getGpus()) {
            componentsPanel.add(new JLabel("GPU: " + gpu.getBaseInfo().getName()));
        }

        for (final Component ram : build.getRams()) {
            componentsPanel.add(new JLabel("RAM: " + ram.getBaseInfo().getName()));
        }

        for (final Component storage : build.getStorage()) {
            componentsPanel.add(new JLabel("Storage: " + storage.getBaseInfo().getName()));
        }

        return componentsPanel;
    }

    private JPanel createCommentsPanel(final Build build) {
        final JPanel commentsPanel = new JPanel(new BorderLayout());
        commentsPanel.add(new JLabel("Comments:"), BorderLayout.NORTH);

        final List<Review> reviews = controller.getReviewsByBuild(build.getBuildId());
        final JPanel reviewsPanel = new JPanel();
        reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.Y_AXIS));
        reviewsPanel.setBackground(Color.WHITE);

        for (final Review review : reviews) {
            reviewsPanel.add(createReviewPanel(review));
            reviewsPanel.add(Box.createVerticalStrut(10));
        }

        commentsPanel.add(new JScrollPane(reviewsPanel), BorderLayout.CENTER);
        return commentsPanel;
    }

    private JPanel createReviewPanel(final Review review) {
        final JPanel reviewPanel = new JPanel(new BorderLayout());
        reviewPanel.setBackground(new Color(245, 245, 245));
        reviewPanel.setPreferredSize(new Dimension(500, 100));
        reviewPanel.setMaximumSize(new Dimension(600, 120));

        final JLabel userLabel = new JLabel(review.getUsername());
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        reviewPanel.add(userLabel, BorderLayout.NORTH);

        final JPanel ratingAndDatePanel = new JPanel(new BorderLayout());
        ratingAndDatePanel.setBackground(new Color(245, 245, 245));
        final JLabel ratingLabel = new JLabel("Rating: " + review.getReviewRating());
        ratingLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        ratingAndDatePanel.add(ratingLabel, BorderLayout.WEST);

        final JLabel dateLabel = new JLabel(review.getLastEditDate().toString());
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        ratingAndDatePanel.add(dateLabel, BorderLayout.EAST);
        reviewPanel.add(ratingAndDatePanel, BorderLayout.CENTER);

        final JLabel commentLabel = new JLabel("<html><p style=\"width:400px;\">" + review.getComment() + "</p></html>");
        commentLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        reviewPanel.add(commentLabel, BorderLayout.SOUTH);

        return reviewPanel;
    }

    private JPanel createReviewButtonPanel(final Build build) {
        final JPanel buttonPanel = new JPanel();
        final String loggedInUsername = controller.getLoggedUser().getUsername();
        final boolean hasReview = controller.getReviewsByBuild(build.getBuildId()).stream()
                .anyMatch(r -> r.getUsername().equals(loggedInUsername));

        final JButton reviewButton = new JButton(hasReview ? "Update Review" : "Insert Review");
        reviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                showReviewDialog(build.getBuildId(), loggedInUsername, hasReview);
            }
        });

        buttonPanel.add(reviewButton);
        return buttonPanel;
    }

    private void showUserDetails(final UserDetails userDetails) {
        final JPanel panel = new JPanel(new BorderLayout());
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
        final JPanel panel = new JPanel(new GridLayout(3, 2));
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
            final LocalDate endDate = "1 week".equals(banLength) ? LocalDate.now().plus(1, ChronoUnit.WEEKS) : null;

            final Ban ban = new Ban(userDetails.getUsername(), LocalDate.now(), endDate, motive, controller.getLoggedUser().getUsername());
            controller.banUser(ban);
        }
    }

    private void showReviewDialog(final int buildId, final String username, final boolean isUpdate) {
        final JPanel panel = new JPanel(new GridLayout(3, 2));
        final JComboBox<Integer> ratingComboBox = new JComboBox<>();
        for (int i = 1; i <= 10; i++) {
            ratingComboBox.addItem(i);
        }
        final JTextArea commentField = new JTextArea();

        panel.add(new JLabel("Rating:"));
        panel.add(ratingComboBox);
        panel.add(new JLabel("Comment:"));
        panel.add(new JScrollPane(commentField));

        final int result = JOptionPane.showConfirmDialog(this, panel, isUpdate ? "Update Review" : "Insert Review", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            final int rating = (int) ratingComboBox.getSelectedItem();
            final String comment = commentField.getText();
            final LocalDate date = LocalDate.now();

            final Review review = new Review(buildId, username, rating, comment, date);
            if (isUpdate) {
                controller.updateReview(review);
            } else {
                controller.insertReview(review);
            }
            initializeUI();
        }
    }
}