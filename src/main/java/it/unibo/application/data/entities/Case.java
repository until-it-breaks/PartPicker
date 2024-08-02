package it.unibo.application.data.entities;

public class Case {
    private int caseId;
    private String formFactor;

    public Case(int caseId, String formFactor) {
        this.caseId = caseId;
        this.formFactor = formFactor;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }
}
