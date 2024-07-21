package it.unibo.application.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginPanel extends JPanel {
    public LoginPanel() {
        this.add(new JLabel("Username"));
        this.add(new JLabel("Password"));
        this.add(new JButton("Login"));
        this.add(new JButton("Back"));
    }
}
