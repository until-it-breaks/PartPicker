package it.unibo.application.data.entities;

public class Build {
    private int buildId;
    private int coolerId;
    private int caseId;
    private int psuId;
    private int cpuId;
    private int motherboardId;

    public Build(int buildId, int coolerId, int caseId, int psuId, int cpuId, int motherboardId) {
        this.buildId = buildId;
        this.coolerId = coolerId;
        this.caseId = caseId;
        this.psuId = psuId;
        this.cpuId = cpuId;
        this.motherboardId = motherboardId;
    }

    public int getBuildId() {
        return buildId;
    }

    public void setBuildId(int buildId) {
        this.buildId = buildId;
    }

    public int getCoolerId() {
        return coolerId;
    }

    public void setCoolerId(int coolerId) {
        this.coolerId = coolerId;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public int getPsuId() {
        return psuId;
    }

    public void setPsuId(int psuId) {
        this.psuId = psuId;
    }

    public int getCpuId() {
        return cpuId;
    }

    public void setCpuId(int cpuId) {
        this.cpuId = cpuId;
    }

    public int getMotherboardId() {
        return motherboardId;
    }

    public void setMotherboardId(int motherboardId) {
        this.motherboardId = motherboardId;
    }

    
}
