package it.unibo.application.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;

public class AppGUI {
    private static final String APP_NAME = "Part Picker";
    private static final Dimension SIZE = new Dimension(1280, 720);
    private final JFrame frame;

    public AppGUI() {
        this.frame = new JFrame();
        frame.setTitle(APP_NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SIZE);
    
        frame.add(new WelcomePanel(this));
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public void switchPanel(JPanel newPanel) {
        this.frame.getContentPane().removeAll();
        this.frame.add(newPanel);
        this.frame.revalidate();
        frame.repaint();
    }
}
