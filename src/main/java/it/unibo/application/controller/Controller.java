package it.unibo.application.controller;

import it.unibo.application.model.states.AppStateController;
import it.unibo.application.model.states.State;

public class Controller {
    private AppStateController appStateController;

    public Controller() {
        this.appStateController = new AppStateController();
    }

    public void setAppState(State newState) {
        appStateController.setState(newState);
    }

    public State getAppState() {
        return appStateController.getState();
    }
}
