package it.unibo.application.data.entities;

public class Gpu {
    private int gpuId;
    private String gpuFamily;
    private String gpuMemoryType;
    private int gpuMemoryAmount;
    private int gpuFrequency;
    private int tgp;

    public Gpu(int gpuId, String gpuFamily, String gpuMemoryType, int gpuMemoryAmount, int gpuFrequency, int tgp) {
        this.gpuId = gpuId;
        this.gpuFamily = gpuFamily;
        this.gpuMemoryType = gpuMemoryType;
        this.gpuMemoryAmount = gpuMemoryAmount;
        this.gpuFrequency = gpuFrequency;
        this.tgp = tgp;
    }

    public int getGpuId() {
        return gpuId;
    }

    public void setGpuId(int gpuId) {
        this.gpuId = gpuId;
    }

    public String getGpuFamily() {
        return gpuFamily;
    }

    public void setGpuFamily(String gpuFamily) {
        this.gpuFamily = gpuFamily;
    }

    public String getGpuMemoryType() {
        return gpuMemoryType;
    }

    public void setGpuMemoryType(String gpuMemoryType) {
        this.gpuMemoryType = gpuMemoryType;
    }

    public int getGpuMemoryAmount() {
        return gpuMemoryAmount;
    }

    public void setGpuMemoryAmount(int gpuMemoryAmount) {
        this.gpuMemoryAmount = gpuMemoryAmount;
    }

    public int getGpuFrequency() {
        return gpuFrequency;
    }

    public void setGpuFrequency(int gpuFrequency) {
        this.gpuFrequency = gpuFrequency;
    }

    public int getTgp() {
        return tgp;
    }

    public void setTgp(int tgp) {
        this.tgp = tgp;
    }
    
}
