package it.unibo.application.data.entities;

public class Psu {
    private int psuId;
    private String formFactor;
    private String efficiency;
    private int wattage;
    private String modularity;

    public Psu(int psuId, String formFactor, String efficiency, int wattage, String modularity) {
        this.psuId = psuId;
        this.formFactor = formFactor;
        this.efficiency = efficiency;
        this.wattage = wattage;
        this.modularity = modularity;
    }

    public int getPsuId() {
        return psuId;
    }
    public void setPsuId(int psuId) {
        this.psuId = psuId;
    }
    public String getFormFactor() {
        return formFactor;
    }
    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }
    public String getEfficiency() {
        return efficiency;
    }
    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }
    public int getWattage() {
        return wattage;
    }
    public void setWattage(int wattage) {
        this.wattage = wattage;
    }
    public String getModularity() {
        return modularity;
    }
    public void setModularity(String modularity) {
        this.modularity = modularity;
    }
}
