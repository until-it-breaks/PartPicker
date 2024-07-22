package it.unibo.application.view;

import javax.swing.JFrame;

import it.unibo.application.controller.Controller;
import it.unibo.application.model.states.State;

import java.awt.Dimension;

public class AppGUI {
    private static final String APP_NAME = "Part Picker";
    private static final Dimension SIZE = new Dimension(1280, 720);
    private final JFrame frame;
    private Controller controller;

    public AppGUI(Controller controller) {
        this.frame = new JFrame();
        this.controller = controller;

        frame.setTitle(APP_NAME);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SIZE);
        frame.add(new WelcomePanel(this));
        frame.setVisible(true);
    }

    public void setState(State newState) {
        this.controller.setAppState(newState);
        this.switchPanel();
    }

    private void switchPanel() {
        this.frame.getContentPane().removeAll();

        switch (this.controller.getAppState()) {
            case State.WELCOME:
                this.frame.add(new WelcomePanel(this));
                break;
            default:
                break;
        }


        this.frame.revalidate();
        frame.repaint();
    }
}
