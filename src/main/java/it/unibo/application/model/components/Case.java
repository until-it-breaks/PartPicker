package it.unibo.application.model.components;

public class Case extends ComponentImpl {
    private final String formFactor;

    public Case(final String formFactor) {
        this.formFactor = formFactor;
    }
    
    public String getFormFactor() {
        return formFactor;
    }
}
