package it.unibo.application.controller;

import it.unibo.application.view.AppGUI;

public class LaunchApp {
    public static void main(String[] args) {
        Controller controller = new Controller();
        new AppGUI(controller);
    }
}
