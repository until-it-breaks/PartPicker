package it.unibo.application.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePanel extends JPanel{

    public WelcomePanel(AppGUI appGUI) {
        final JPanel upperPanel = new JPanel();
        final JPanel lowerPanel = new JPanel();

        JFrame frame = appGUI.getFrame();
        frame.setLayout(new BorderLayout());
        frame.getContentPane().add(upperPanel, BorderLayout.NORTH);
        frame.getContentPane().add(lowerPanel, BorderLayout.SOUTH);

        upperPanel.add(new JTextArea("Welcome"));

        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Login");

        registerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                appGUI.switchPanel(new RegisterPanel());
            }
            
        });

        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                appGUI.switchPanel(new LoginPanel());
            }
            
        });

        lowerPanel.add(registerButton);
        lowerPanel.add(loginButton);
    }
}
