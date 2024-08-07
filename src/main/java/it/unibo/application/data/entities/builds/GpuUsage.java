package it.unibo.application.data.entities.builds;

public class GpuUsage {
    private int buildId;
    private int gpuId;
    private int quantity;

    public GpuUsage(int buildId, int gpuId, int quantity) {
        this.buildId = buildId;
        this.gpuId = gpuId;
        this.quantity = quantity;
    }

    public int getBuildId() {
        return buildId;
    }

    public void setBuildId(int buildId) {
        this.buildId = buildId;
    }

    public int getGpuId() {
        return gpuId;
    }

    public void setGpuId(int gpuId) {
        this.gpuId = gpuId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
