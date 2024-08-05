package it.unibo.application.model.states;

import it.unibo.application.data.entities.enums.Part;
import it.unibo.application.data.entities.enums.State;

public class AppStateController {
    private State currentState;
    private Part desiredPart;

    public AppStateController() {
        this.currentState = State.WELCOME;
        this.desiredPart = null;
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
}
