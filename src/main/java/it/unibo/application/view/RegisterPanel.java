package it.unibo.application.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RegisterPanel extends JPanel {
    public RegisterPanel() {
        this.add(new JLabel("Username"));
        this.add(new JLabel("Password"));
        this.add(new JButton("Register"));
        this.add(new JButton("Back"));
    }
}
