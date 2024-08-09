package it.unibo.application.view;

import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.enums.State;
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

        final JPanel titlePanel = createTitlePanel();
        this.add(titlePanel, BorderLayout.CENTER);

        final JPanel buttonPanel = createButtonPanel();
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createTitlePanel() {
        final JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridBagLayout());

        final JLabel titleLabel = new JLabel("Part Picker", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 72));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        titlePanel.add(titleLabel);
        return titlePanel;
    }

    private JPanel createButtonPanel() {
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());

        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        final JPanel credentialsPanel = new JPanel(new GridBagLayout());
        credentialsPanel.setBorder(BorderFactory.createTitledBorder("Credentials"));

        final JLabel usernameLabel = new JLabel("Username:");
        final JLabel passwordLabel = new JLabel("Password:");
        final JTextField usernameField = new JTextField(15);
        final JPasswordField passwordField = new JPasswordField(15);

        final GridBagConstraints credentialsGbc = new GridBagConstraints();
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

        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        final JButton registerButton = new JButton("Register");
        final JButton loginButton = new JButton("Login");
        final JButton adminButton = new JButton("Admin");

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

        adminButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setAppState(State.ADMIN);
            }
            
        });

        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(credentialsPanel, BorderLayout.CENTER);
        buttonPanel.add(buttonsPanel, BorderLayout.SOUTH);

        return buttonPanel;
    }

    private void showRegistrationDialog() {
        final JTextField usernameField = new JTextField();
        final JPasswordField passwordField = new JPasswordField();
        final JTextField emailField = new JTextField();
        final JCheckBox isModeratorCheckBox = new JCheckBox("Moderator");

        final JPanel registrationPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        registrationPanel.add(new JLabel("Username:"));
        registrationPanel.add(usernameField);
        registrationPanel.add(new JLabel("Password:"));
        registrationPanel.add(passwordField);
        registrationPanel.add(new JLabel("Email:"));
        registrationPanel.add(emailField);
        registrationPanel.add(new JLabel("Moderator:"));
        registrationPanel.add(isModeratorCheckBox);

        final int result = JOptionPane.showConfirmDialog(null, registrationPanel, "Register", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            final String username = usernameField.getText();
            final String password = new String(passwordField.getPassword());
            final String email = emailField.getText();

            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields must be filled out!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            final Date signUpDate = Date.valueOf(LocalDate.now());
            final boolean isModerator = isModeratorCheckBox.isSelected();

            final User user = new User(username, password, signUpDate, email, isModerator);
            if (controller.registerUser(user)) {
                JOptionPane.showMessageDialog(null, "User registered successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Error registering user", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}