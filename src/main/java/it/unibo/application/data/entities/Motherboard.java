package it.unibo.application.data.entities;

public class Motherboard {
    private int motherboardId;
    private String formFactor;
    private String chipsetName;
    private int ramSlots;
    private int gpuSlots;
    private boolean hasWifi;
    private String socketName;
    private String ramGeneration;

    public Motherboard(int motherboardId, String formFactor, String chipsetName, int ramSlots, int gpuSlots,
            boolean hasWifi, String socketName, String ramGeneration) {
        this.motherboardId = motherboardId;
        this.formFactor = formFactor;
        this.chipsetName = chipsetName;
        this.ramSlots = ramSlots;
        this.gpuSlots = gpuSlots;
        this.hasWifi = hasWifi;
        this.socketName = socketName;
        this.ramGeneration = ramGeneration;
    }

    public int getMotherboardId() {
        return motherboardId;
    }

    public void setMotherboardId(int motherboardId) {
        this.motherboardId = motherboardId;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getChipsetName() {
        return chipsetName;
    }

    public void setChipsetName(String chipsetName) {
        this.chipsetName = chipsetName;
    }

    public int getRamSlots() {
        return ramSlots;
    }

    public void setRamSlots(int ramSlots) {
        this.ramSlots = ramSlots;
    }

    public int getGpuSlots() {
        return gpuSlots;
    }

    public void setGpuSlots(int gpuSlots) {
        this.gpuSlots = gpuSlots;
    }

    public boolean isHasWifi() {
        return hasWifi;
    }

    public void setHasWifi(boolean hasWifi) {
        this.hasWifi = hasWifi;
    }

    public String getSocketName() {
        return socketName;
    }

    public void setSocketName(String socketName) {
        this.socketName = socketName;
    }

    public String getRamGeneration() {
        return ramGeneration;
    }

    public void setRamGeneration(String ramGeneration) {
        this.ramGeneration = ramGeneration;
    }

}
