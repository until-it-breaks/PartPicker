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

    public void setController(final Controller controller) {
        this.controller = controller;
    }

    public void switchPanel(final State state) {
        this.frame.getContentPane().removeAll();
        switch (state) {
            case State.WELCOME:
                this.frame.add(new WelcomePage(controller));
                break;
            case State.OVERVIEW:
                this.frame.add(new OverviewPage(controller));
                break;
            case State.BUILDING:
                this.frame.add(new BuilderPage(controller));
                break;
            case State.PRODUCTS:
                this.frame.add(new ProductsPage(controller));
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

    public void showDialog(final String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
