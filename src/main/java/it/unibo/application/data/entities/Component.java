package it.unibo.application.data.entities;

import java.time.Year;

public class Component {
    private int componentId;
    private String componentName;
    private String componentType;
    private Year launchYear;
    private float msrp;
    private int manufacturerId;

    public Component(int componentId, String componentName, String componentType, Year launchYear, float msrp,
            int manufacturerId) {
        this.componentId = componentId;
        this.componentName = componentName;
        this.componentType = componentType;
        this.launchYear = launchYear;
        this.msrp = msrp;
        this.manufacturerId = manufacturerId;
    }

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public Year getLaunchYear() {
        return launchYear;
    }

    public void setLaunchYear(Year launchYear) {
        this.launchYear = launchYear;
    }

    public float getMsrp() {
        return msrp;
    }

    public void setMsrp(float msrp) {
        this.msrp = msrp;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }
}