package it.unibo.application.controller;

import it.unibo.application.model.enums.State;
import it.unibo.application.model.states.AppStateController;
import it.unibo.application.view.AppGUI;
import it.unibo.application.model.selectors.BuildSelector;
import it.unibo.application.model.selectors.ComponentSelector;
import it.unibo.application.model.selectors.ProfileSelector;

public class Controller {
    private AppStateController appStateController;
    private ComponentSelector componentSelector;
    private ProfileSelector profileSelector;
    private BuildSelector buildSelector;
    private AppGUI appGUI;

    public Controller() {
        this.appStateController = new AppStateController();
        this.componentSelector = new ComponentSelector();
        this.profileSelector = new ProfileSelector();
        this.buildSelector = new BuildSelector();
    }

    public void setAppState(State newState) {
        appStateController.setState(newState);
        appGUI.switchPanel(newState);
    }

    public State getAppState() {
        return appStateController.getState();
    }

    public State getPreviousAppState() {
        return appStateController.getPreviousState();
    }

    public ComponentSelector getComponentSelector() {
        return componentSelector;
    }

    public void setGUI(AppGUI gui) {
        this.appGUI = gui;
    }

    public ProfileSelector getProfileSelector() {
        return this.profileSelector;
    }

    public BuildSelector getBuildSelector() {
        return buildSelector;
    }
}
