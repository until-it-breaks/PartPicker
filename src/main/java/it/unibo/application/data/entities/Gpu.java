package it.unibo.application.data.entities;

public class Gpu {
    public int gpuId;
    public String gpuFamily;
    public String gpuMemoryType;
    public int gpuMemoryAmount;
    public int gpuFrequency;
    public int tgp;

    public Gpu(int gpuId, String gpuFamily, String gpuMemoryType, int gpuMemoryAmount, int gpuFrequency, int tgp) {
        this.gpuId = gpuId;
        this.gpuFamily = gpuFamily;
        this.gpuMemoryType = gpuMemoryType;
        this.gpuMemoryAmount = gpuMemoryAmount;
        this.gpuFrequency = gpuFrequency;
        this.tgp = tgp;
    }
}
