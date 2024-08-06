package it.unibo.application.model.states;

import it.unibo.application.data.entities.enums.Part;
import it.unibo.application.data.entities.enums.State;

public class AppStateController {
    private State currentState;
    private Part desiredPart;
    private int targetBuild;

    public AppStateController() {
        this.currentState = State.WELCOME;
        this.desiredPart = null;
        this.targetBuild = 0;
    }

    public State getState() {
        return this.currentState;
    }

    public void setState(final State newState) {
        this.currentState = newState;
    }

    public void setDesiredPart(final Part part) {
        this.desiredPart = part;
    }

    public Part getDesiredPart() {
        return this.desiredPart;
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
