package it.unibo.application.model.states;

import it.unibo.application.data.entities.enums.Part;
import it.unibo.application.data.entities.enums.State;

public class AppStateController {
    private State currentState;
    private Part requestedPart;
    private int targetBuild;

    public AppStateController() {
        this.currentState = State.WELCOME;
        this.requestedPart = null;
        this.targetBuild = 0;
    }

    public State getState() {
        return this.currentState;
    }

    public void setState(final State newState) {
        this.currentState = newState;
    }

    public void setRequestedPart(final Part part) {
        this.requestedPart = part;
    }

    public Part getRequestedPart() {
        return this.requestedPart;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(final State currentState) {
        this.currentState = currentState;
    }

    public int getTargetBuild() {
        return targetBuild;
    }

    public void setTargetBuild(final int targetBuild) {
        this.targetBuild = targetBuild;
    }
}
