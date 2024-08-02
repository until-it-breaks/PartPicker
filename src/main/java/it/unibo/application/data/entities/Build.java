package it.unibo.application.data.entities;

public class Build {
    public int buildId;
    public int coolerId;
    public int caseId;
    public int psuId;
    public int cpuId;
    public int motherboardId;

    public Build(int buildId, int coolerId, int caseId, int psuId, int cpuId, int motherboardId) {
        this.buildId = buildId;
        this.coolerId = coolerId;
        this.caseId = caseId;
        this.psuId = psuId;
        this.cpuId = cpuId;
        this.motherboardId = motherboardId;
    }
}
