package it.unibo.application.model.components;

public class PSU extends ComponentImpl {
    private final String formFactor;
    private final Integer maxOutput;
    private final String efficiency;
    private final Boolean isModular;

    public PSU(final String formFactor, final Integer maxOutput, final String efficiency, final Boolean isModular) {
        this.formFactor = formFactor;
        this.maxOutput = maxOutput;
        this.efficiency = efficiency;
        this.isModular = isModular;
    }

    public String getFormFactor() {
        return formFactor;
    }
    public Integer getMaxOutput() {
        return maxOutput;
    }
    public String getEfficiency() {
        return efficiency;
    }
    public Boolean getIsModular() {
        return isModular;
    }

}
