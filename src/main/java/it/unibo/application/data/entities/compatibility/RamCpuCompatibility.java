package it.unibo.application.data.entities.compatibility;

public class RamCpuCompatibility {
    private String ramGeneration;
    private int cpuId;

    public RamCpuCompatibility(final String ramGeneration, final int cpuId) {
        this.ramGeneration = ramGeneration;
        this.cpuId = cpuId;
    }

    public String getRamGeneration() {
        return ramGeneration;
    }

    public void setRamGeneration(final String ramGeneration) {
        this.ramGeneration = ramGeneration;
    }

    public int getCpuId() {
        return cpuId;
    }

    public void setCpuId(final int cpuId) {
        this.cpuId = cpuId;
    }
}
