package it.unibo.application.data.entities;

public class Cooler {
    private int coolerId;
    private int coolerRpm;
    private float noiseLevel;
    private String coolerType;

    public Cooler(int coolerId, int coolerRpm, float noiseLevel, String coolerType) {
        this.coolerId = coolerId;
        this.coolerRpm = coolerRpm;
        this.noiseLevel = noiseLevel;
        this.coolerType = coolerType;
    }

    public int getCoolerId() {
        return coolerId;
    }

    public void setCoolerId(int coolerId) {
        this.coolerId = coolerId;
    }

    public int getCoolerRpm() {
        return coolerRpm;
    }

    public void setCoolerRpm(int coolerRpm) {
        this.coolerRpm = coolerRpm;
    }

    public float getNoiseLevel() {
        return noiseLevel;
    }

    public void setNoiseLevel(float noiseLevel) {
        this.noiseLevel = noiseLevel;
    }

    public String getCoolerType() {
        return coolerType;
    }

    public void setCoolerType(String coolerType) {
        this.coolerType = coolerType;
    }

}
