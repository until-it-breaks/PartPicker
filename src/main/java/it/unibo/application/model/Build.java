package it.unibo.application.model;

import java.util.List;

import it.unibo.application.model.components.CPU;
import it.unibo.application.model.components.CPUCooler;
import it.unibo.application.model.components.Case;
import it.unibo.application.model.components.GPU;
import it.unibo.application.model.components.Motherboard;
import it.unibo.application.model.components.PSU;
import it.unibo.application.model.components.RAM;
import it.unibo.application.model.components.Storage;

public class Build {
    private CPU cpu;
    private PSU psu;
    private Motherboard motherboard;
    private Case pcCase;
    private CPUCooler cpuCooler;
    private List<GPU> gpuList;
    private List<Storage> storageList;
    private List<RAM> ramList;

    public void wipeBuild() {
        cpu = null;
        psu = null;
        motherboard = null;
        pcCase = null;
        cpuCooler = null;
        gpuList.clear();
        storageList.clear();
        ramList.clear();
    }

    public boolean isBuildComplete() {
        return cpu != null && psu != null && motherboard != null && pcCase != null && cpuCooler != null && gpuList.isEmpty() == false && storageList.isEmpty() == false && ramList.isEmpty() == false;
    }

    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public PSU getPsu() {
        return psu;
    }

    public void setPsu(PSU psu) {
        this.psu = psu;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(Motherboard motherboard) {
        this.motherboard = motherboard;
    }

    public Case getPcCase() {
        return pcCase;
    }

    public void setPcCase(Case pcCase) {
        this.pcCase = pcCase;
    }

    public CPUCooler getCpuCooler() {
        return cpuCooler;
    }

    public void setCpuCooler(CPUCooler cpuCooler) {
        this.cpuCooler = cpuCooler;
    }

    public List<GPU> getGpuList() {
        return gpuList;
    }

    public void setGpuList(List<GPU> gpuList) {
        this.gpuList = gpuList;
    }

    public List<Storage> getStorageList() {
        return storageList;
    }

    public void setStorageList(List<Storage> storageList) {
        this.storageList = storageList;
    }

    public List<RAM> getRamList() {
        return ramList;
    }

    public void setRamList(List<RAM> ramList) {
        this.ramList = ramList;
    }

    public void uploadBuild() {
        if (isBuildComplete()) {
            System.out.println("Build uploaded");
        } else {
            System.out.println("You need to complete the build");
        }
    }
}
