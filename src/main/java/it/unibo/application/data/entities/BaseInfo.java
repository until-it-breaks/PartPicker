package it.unibo.application.data.entities;

public class BaseInfo {
    private final int id;
    private final String name;
    private final int launchYear;
    private final float msrp;
    private final String manufacturer;

    public BaseInfo(final int componentId, final String componentName, final int launchYear, final float msrp, final String manufacturer) {
        this.id = componentId;
        this.name = componentName;
        this.launchYear = launchYear;
        this.msrp = msrp;
        this.manufacturer = manufacturer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLaunchYear() {
        return launchYear;
    }

    public float getMsrp() {
        return msrp;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}