package it.unibo.application.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.SwingConstants;

import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;

public class WelcomePanel extends JPanel{

    public WelcomePanel(AppGUI appGUI) {
        final JPanel upperPanel = new JPanel();
        final JPanel lowerPanel = new JPanel();

        JFrame frame = appGUI.getFrame();
        frame.setLayout(new BorderLayout());
        frame.getContentPane().add(upperPanel, BorderLayout.NORTH);
        frame.getContentPane().add(lowerPanel, BorderLayout.SOUTH);

        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.Y_AXIS));
        JLabel largeLabel = new JLabel("Welcome to Part Picker", SwingConstants.CENTER);
        largeLabel.setFont(new Font("Arial", Font.BOLD, 36));
        largeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        upperPanel.add(largeLabel);


        lowerPanel.setLayout(new GridBagLayout());
        JPanel credentialsPanel = new JPanel(new GridBagLayout());
        JPanel buttonsPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        credentialsPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        credentialsPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        credentialsPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        credentialsPanel.add(passwordField, gbc);

        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Login");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        buttonsPanel.add(loginButton, gbc);

        gbc.gridx = 1;
        buttonsPanel.add(registerButton, gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.EAST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        lowerPanel.add(credentialsPanel, gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 1;
        gbc.gridy = 0;
        lowerPanel.add(buttonsPanel, gbc);

        //EVENT LISTENERS
        registerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
        });

        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
        });
    }
}
