package it.unibo.application.data.entities.compatibility;

public class RamCpuCompatibility {
    private String ramGeneration;
    private int cpuId;

    public RamCpuCompatibility(String ramGeneration, int cpuId) {
        this.ramGeneration = ramGeneration;
        this.cpuId = cpuId;
    }

    public String getRamGeneration() {
        return ramGeneration;
    }

    public void setRamGeneration(String ramGeneration) {
        this.ramGeneration = ramGeneration;
    }

    public int getCpuId() {
        return cpuId;
    }

    public void setCpuId(int cpuId) {
        this.cpuId = cpuId;
    }

}
