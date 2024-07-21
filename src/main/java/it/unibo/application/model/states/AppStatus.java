package it.unibo.application.model.states;

public class AppStatus {
    private State currentState;

    public AppStatus() {
        this.currentState = State.WELCOME;
    }

    public State getState() {
        return this.currentState;
    }

    public void setState(State newState) {
        this.currentState = newState;
    }
}
