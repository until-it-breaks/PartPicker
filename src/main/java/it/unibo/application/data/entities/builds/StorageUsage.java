package it.unibo.application.data.entities.builds;

public class StorageUsage {
    private int buildId;
    private int storageId;
    private int quantity;

    public StorageUsage(int buildId, int storageId, int quantity) {
        this.buildId = buildId;
        this.storageId = storageId;
        this.quantity = quantity;
    }

    public int getBuildId() {
        return buildId;
    }

    public void setBuildId(int buildId) {
        this.buildId = buildId;
    }

    public int getStorageId() {
        return storageId;
    }

    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
