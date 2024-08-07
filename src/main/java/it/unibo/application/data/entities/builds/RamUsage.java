package it.unibo.application.data.entities.builds;

public class RamUsage {
    private int buildId;
    private int ramId;
    private int quantity;

    public RamUsage(int buildId, int ramId, int quantity) {
        this.buildId = buildId;
        this.ramId = ramId;
        this.quantity = quantity;
    }

    public int getBuildId() {
        return buildId;
    }

    public void setBuildId(int buildId) {
        this.buildId = buildId;
    }

    public int getRamId() {
        return ramId;
    }

    public void setRamId(int ramId) {
        this.ramId = ramId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
