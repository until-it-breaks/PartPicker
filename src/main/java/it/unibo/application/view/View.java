package it.unibo.application.view;

import it.unibo.application.controller.Controller;
import it.unibo.application.model.enums.State;

import javax.swing.*;
import java.awt.*;

public class View {
    private static final String APP_NAME = "Part Picker";
    private static final Dimension SIZE = new Dimension(1280, 720);
    private final JFrame frame = new JFrame();
    private Controller controller;

    public View() {
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void switchPanel(State newState) {
        this.frame.getContentPane().removeAll();

        switch (newState) {
            case State.WELCOME:
                this.frame.add(new WelcomePage(controller));
                break;
            case State.OVERVIEW:
                this.frame.add(new OverviewPage(controller));
                break;
            case State.BUILDING:
                this.frame.add(new BuilderPage(controller));
                break;
            case State.VIEWING_PART:
                this.frame.add(new ProductsPage(controller, controller.getComponentSelector().getCurrentCategory()));
                break;
            case State.VIEWING_BUILD:
                this.frame.add(new BuildPage(controller));
                break;
            case State.VIEWING_PROFILE:
                this.frame.add(new ProfilePage(controller));
                break;
            default:
                break;
        }
        this.frame.revalidate();
        frame.repaint();
    }

    public void setUp() {
        frame.setTitle(APP_NAME);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SIZE);
        frame.add(new WelcomePage(controller));
        frame.setVisible(true);
    }

    public void showDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}