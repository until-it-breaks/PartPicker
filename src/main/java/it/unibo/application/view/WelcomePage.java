package it.unibo.application.view;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

import it.unibo.application.controller.Controller;
import it.unibo.application.model.enums.State;

import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Component;

public class WelcomePage extends JPanel {

    public WelcomePage(Controller controller) {
        this.setLayout(new BorderLayout());
        final JPanel upperPanel = new JPanel();
        final JPanel centerPanel = new JPanel();
        final JPanel lowerPanel = new JPanel();

        this.add(upperPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(lowerPanel, BorderLayout.SOUTH);

        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.Y_AXIS));
        JLabel largeLabel = new JLabel("Welcome to Part Picker!", SwingConstants.CENTER);
        largeLabel.setFont(new Font("Arial", Font.BOLD, 36));
        largeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        upperPanel.add(largeLabel);

        centerPanel.setBackground(Color.BLACK);

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

        buttonsPanel.add(loginButton, gbc);
        buttonsPanel.add(registerButton, gbc);

        lowerPanel.add(credentialsPanel, gbc);
        lowerPanel.add(buttonsPanel, gbc);

        //EVENT LISTENERS
        registerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You have succesfully registered");
            }
        });

        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller.getLoginHandler().performLogin(usernameField.getText(), passwordField.getPassword())) {
                    JOptionPane.showMessageDialog(null, "You have succesfully logged in");
                    controller.setAppState(State.OVERVIEW);
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong credentials");
                }
            }
        });
    }
}