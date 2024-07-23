package it.unibo.application.model.components;

public class Motherboard extends ComponentImpl {
    private final String formFactor;
    private final String chipsetName;
    private final Integer numberOfRamSlots;
    private final Integer numberOfGPUSlots;
    private final Integer numberOfSATASlots;
    private final Integer numberOfM2Slots;
    private final Boolean hasWifi;

    public Motherboard(final String formFactor, final String chipsetName, final Integer numberOfRamSlots, final Integer numberOfGPUSlots,
            final Integer numberOfSATASlots, final Integer numberOfM2Slots, final Boolean hasWifi) {
        this.formFactor = formFactor;
        this.chipsetName = chipsetName;
        this.numberOfRamSlots = numberOfRamSlots;
        this.numberOfGPUSlots = numberOfGPUSlots;
        this.numberOfSATASlots = numberOfSATASlots;
        this.numberOfM2Slots = numberOfM2Slots;
        this.hasWifi = hasWifi;
    }

    public String getFormFactor() {
        return formFactor;
    }
    public String getChipsetName() {
        return chipsetName;
    }
    public Integer getNumberOfRamSlots() {
        return numberOfRamSlots;
    }
    public Integer getNumberOfGPUSlots() {
        return numberOfGPUSlots;
    }
    public Integer getNumberOfSATASlots() {
        return numberOfSATASlots;
    }
    public Integer getNumberOfM2Slots() {
        return numberOfM2Slots;
    }
    public Boolean getHasWifi() {
        return hasWifi;
    }
}
