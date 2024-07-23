package it.unibo.application.controller;

import it.unibo.application.model.building.ComponentSelector;
import it.unibo.application.model.enums.State;
import it.unibo.application.model.states.AppStateController;
import it.unibo.application.view.AppGUI;
import it.unibo.application.model.login.LoginHandler;

public class Controller {
    private AppStateController appStateController;
    private ComponentSelector componentSelector;
    private LoginHandler loginHandler;
    private AppGUI appGUI;

    public Controller() {
        this.appStateController = new AppStateController();
        this.componentSelector = new ComponentSelector();
        this.loginHandler = new LoginHandler();
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

    public void setGUI(AppGUI gui) {
        this.appGUI = gui;
    }

    public LoginHandler getLoginHandler() {
        return this.loginHandler;
    }
}
