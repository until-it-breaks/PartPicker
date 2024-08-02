package it.unibo.application.data.entities;

public class Storage {
    private int storageId;
    private int storageCapacity;
    private int storageRpm;
    private int cacheAmount;
    private String storageType;

    public Storage(int storageId, int storageCapacity, int storageRpm, int cacheAmount, String storageType) {
        this.storageId = storageId;
        this.storageCapacity = storageCapacity;
        this.storageRpm = storageRpm;
        this.cacheAmount = cacheAmount;
        this.storageType = storageType;
    }
    public int getStorageId() {
        return storageId;
    }
    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }
    public int getStorageCapacity() {
        return storageCapacity;
    }
    public void setStorageCapacity(int storageCapacity) {
        this.storageCapacity = storageCapacity;
    }
    public int getStorageRpm() {
        return storageRpm;
    }
    public void setStorageRpm(int storageRpm) {
        this.storageRpm = storageRpm;
    }
    public int getCacheAmount() {
        return cacheAmount;
    }
    public void setCacheAmount(int cacheAmount) {
        this.cacheAmount = cacheAmount;
    }
    public String getStorageType() {
        return storageType;
    }
    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }


}
