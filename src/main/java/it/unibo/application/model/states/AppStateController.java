package it.unibo.application.model.states;

import it.unibo.application.model.enums.State;

public class AppStateController {
    private State previousState;
    private State currentState;

    public AppStateController() {
        this.currentState = State.WELCOME;
    }

    public State getState() {
        return this.currentState;
    }

    public State getPreviousState() {
        return this.previousState;
    }

    public void setState(final State newState) {
        this.previousState = currentState;
        this.currentState = newState;
    }
}
