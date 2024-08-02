package it.unibo.application.data.entities;

public class Ram {
    private int ramId;
    private int ramFrequency;
    private int capacity;
    private String latency;
    private boolean isEcc;
    private String ramGeneration;

    public Ram(int ramId, int ramFrequency, int capacity, String latency, boolean isEcc, String ramGeneration) {
        this.ramId = ramId;
        this.ramFrequency = ramFrequency;
        this.capacity = capacity;
        this.latency = latency;
        this.isEcc = isEcc;
        this.ramGeneration = ramGeneration;
    }

    public int getRamId() {
        return ramId;
    }

    public void setRamId(int ramId) {
        this.ramId = ramId;
    }

    public int getRamFrequency() {
        return ramFrequency;
    }

    public void setRamFrequency(int ramFrequency) {
        this.ramFrequency = ramFrequency;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLatency() {
        return latency;
    }

    public void setLatency(String latency) {
        this.latency = latency;
    }

    public boolean isEcc() {
        return isEcc;
    }

    public void setEcc(boolean isEcc) {
        this.isEcc = isEcc;
    }

    public String getRamGeneration() {
        return ramGeneration;
    }

    public void setRamGeneration(String ramGeneration) {
        this.ramGeneration = ramGeneration;
    }

}
