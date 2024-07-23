package it.unibo.application.model.states;

import it.unibo.application.model.enums.State;

public class AppStateController {
    private State currentState;

    public AppStateController() {
        this.currentState = State.WELCOME;
    }

    public State getState() {
        return this.currentState;
    }

    public void setState(State newState) {
        this.currentState = newState;
    }
}
