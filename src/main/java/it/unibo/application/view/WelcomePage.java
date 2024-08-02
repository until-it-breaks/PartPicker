package it.unibo.application.view;

import it.unibo.application.controller.Controller;
import it.unibo.application.data.entities.User;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.Date;
import java.time.LocalDate;

public class WelcomePage extends JPanel {
    private Controller controller;

    public WelcomePage(Controller controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());
        final JPanel centerPanel = new JPanel();
        final JPanel lowerPanel = new JPanel();

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(lowerPanel, BorderLayout.SOUTH);

        JLabel largeLabel = new JLabel("Welcome to Part Picker!", SwingConstants.CENTER);
        largeLabel.setFont(new Font("Arial", Font.BOLD, 72));
        largeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(largeLabel);

        lowerPanel.setLayout(new GridBagLayout());
        JPanel credentialsPanel = new JPanel(new GridBagLayout());
        JPanel buttonsPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);

        credentialsPanel.add(usernameLabel, gbc);
        credentialsPanel.add(usernameField, gbc);
        credentialsPanel.add(passwordLabel, gbc);
        credentialsPanel.add(passwordField, gbc);

        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Login");
        JButton adminButton = new JButton("Admin");

        buttonsPanel.add(loginButton, gbc);
        buttonsPanel.add(registerButton, gbc);
        buttonsPanel.add(adminButton, gbc);

        lowerPanel.add(credentialsPanel, gbc);
        lowerPanel.add(buttonsPanel, gbc);

        //EVENT LISTENERS
        registerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showRegistrationDialog();
            }
        });

        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.loginAttempt(usernameField.getText(), passwordField.getText());
            }
        });
    }


    private void showRegistrationDialog() {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField emailField = new JTextField();
        JCheckBox isModeratorCheckBox = new JCheckBox();

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Moderator:"));
        panel.add(isModeratorCheckBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Register", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            Date signUpDate = Date.valueOf(LocalDate.now());
            String email = emailField.getText();
            Boolean isModerator = isModeratorCheckBox.isSelected();

            User user = new User(username, password, signUpDate, email, isModerator);
            if (controller.registerUser(user)) {
                JOptionPane.showMessageDialog(null, "User registered successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Error registering user", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}