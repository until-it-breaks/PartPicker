package it.unibo.application.model.components;

public class CPUCooler extends ComponentImpl {
    private final Integer maxSustainableHeat;

    public CPUCooler(final Integer maxSustainableHeat) {
        this.maxSustainableHeat = maxSustainableHeat;
    }
    public Integer getMaxSustainableHeat() {
        return maxSustainableHeat;
    }
}
