package it.unibo.application.controller;

import it.unibo.application.model.ComponentSelector;
import it.unibo.application.model.states.AppStateController;
import it.unibo.application.model.states.State;
import it.unibo.application.view.AppGUI;

public class Controller {
    private AppStateController appStateController;
    private ComponentSelector componentSelector;
    private AppGUI appGUI;

    public Controller() {
        this.appStateController = new AppStateController();
        this.componentSelector = new ComponentSelector();
    }

    public void setAppState(State newState) {
        appStateController.setState(newState);
        appGUI.switchPanel(newState);
    }

    public State getAppState() {
        return appStateController.getState();
    }

    public ComponentSelector getComponentSelector() {
        return componentSelector;
    }

    public void setComponentSelector(ComponentSelector componentSelector) {
        this.componentSelector = componentSelector;
    }

    public void setGUI(AppGUI gui) {
        this.appGUI = gui;
    }
}
