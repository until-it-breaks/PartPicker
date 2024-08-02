package it.unibo.application.data.entities;

public class Cpu {
    private int cpuId;
    private String cpuFamily;
    private int coreCount;
    private int cpuFrequency;
    private int tdp;
    private boolean hasSmt;
    private String socketName;

    public Cpu(int cpuId, String cpuFamily, int coreCount, int cpuFrequency, int tdp, boolean hasSmt,
            String socketName) {
        this.cpuId = cpuId;
        this.cpuFamily = cpuFamily;
        this.coreCount = coreCount;
        this.cpuFrequency = cpuFrequency;
        this.tdp = tdp;
        this.hasSmt = hasSmt;
        this.socketName = socketName;
    }

    public int getCpuId() {
        return cpuId;
    }

    public void setCpuId(int cpuId) {
        this.cpuId = cpuId;
    }

    public String getCpuFamily() {
        return cpuFamily;
    }

    public void setCpuFamily(String cpuFamily) {
        this.cpuFamily = cpuFamily;
    }

    public int getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(int coreCount) {
        this.coreCount = coreCount;
    }

    public int getCpuFrequency() {
        return cpuFrequency;
    }

    public void setCpuFrequency(int cpuFrequency) {
        this.cpuFrequency = cpuFrequency;
    }

    public int getTdp() {
        return tdp;
    }

    public void setTdp(int tdp) {
        this.tdp = tdp;
    }

    public boolean isHasSmt() {
        return hasSmt;
    }

    public void setHasSmt(boolean hasSmt) {
        this.hasSmt = hasSmt;
    }

    public String getSocketName() {
        return socketName;
    }

    public void setSocketName(String socketName) {
        this.socketName = socketName;
    }

}
