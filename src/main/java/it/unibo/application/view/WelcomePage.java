package it.unibo.application.view;

import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.login.User;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.Date;
import java.time.LocalDate;

public class WelcomePage extends JPanel {
    private final Controller controller;

    public WelcomePage(final Controller controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());

        JPanel titlePanel = createTitlePanel();
        this.add(titlePanel, BorderLayout.CENTER);

        JPanel buttonPanel = createButtonPanel();
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("Part Picker", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 72));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        titlePanel.add(titleLabel);
        return titlePanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        // Credentials panel
        JPanel credentialsPanel = new JPanel(new GridBagLayout());
        credentialsPanel.setBorder(BorderFactory.createTitledBorder("Credentials"));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);

        GridBagConstraints credentialsGbc = new GridBagConstraints();
        credentialsGbc.insets = new Insets(5, 10, 5, 10);
        credentialsGbc.anchor = GridBagConstraints.WEST;
        credentialsGbc.fill = GridBagConstraints.HORIZONTAL;
        credentialsGbc.gridx = 0;
        credentialsGbc.gridy = 0;
        credentialsPanel.add(usernameLabel, credentialsGbc);

        credentialsGbc.gridx = 1;
        credentialsPanel.add(usernameField, credentialsGbc);

        credentialsGbc.gridx = 0;
        credentialsGbc.gridy = 1;
        credentialsPanel.add(passwordLabel, credentialsGbc);

        credentialsGbc.gridx = 1;
        credentialsPanel.add(passwordField, credentialsGbc);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Login");
        JButton adminButton = new JButton("Admin");

        buttonsPanel.add(loginButton);
        buttonsPanel.add(registerButton);
        buttonsPanel.add(adminButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                showRegistrationDialog();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.loginAttempt(usernameField.getText(), new String(passwordField.getPassword()));
            }
        });

        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(credentialsPanel, BorderLayout.CENTER);
        buttonPanel.add(buttonsPanel, BorderLayout.SOUTH);

        return buttonPanel;
    }

    private void showRegistrationDialog() {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField emailField = new JTextField();
        JCheckBox isModeratorCheckBox = new JCheckBox("Moderator");

        JPanel registrationPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        registrationPanel.add(new JLabel("Username:"));
        registrationPanel.add(usernameField);
        registrationPanel.add(new JLabel("Password:"));
        registrationPanel.add(passwordField);
        registrationPanel.add(new JLabel("Email:"));
        registrationPanel.add(emailField);
        registrationPanel.add(new JLabel("Moderator:"));
        registrationPanel.add(isModeratorCheckBox);

        int result = JOptionPane.showConfirmDialog(null, registrationPanel, "Register", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String email = emailField.getText();

            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields must be filled out!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Date signUpDate = Date.valueOf(LocalDate.now());
            boolean isModerator = isModeratorCheckBox.isSelected();

            User user = new User(username, password, signUpDate, email, isModerator);
            if (controller.registerUser(user)) {
                JOptionPane.showMessageDialog(null, "User registered successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Error registering user", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}