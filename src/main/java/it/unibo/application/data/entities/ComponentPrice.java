package it.unibo.application.data.entities;

import java.util.Date;

public class ComponentPrice {
    private int componentId;
    private int resellerName;
    private Date scrapeDate;
    private float componentPrice;

    public ComponentPrice(int componentId, int resellerName, Date scrapeDate, float componentPrice) {
        this.componentId = componentId;
        this.resellerName = resellerName;
        this.scrapeDate = scrapeDate;
        this.componentPrice = componentPrice;
    }

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    public int getResellerName() {
        return resellerName;
    }

    public void setResellerName(int resellerName) {
        this.resellerName = resellerName;
    }

    public Date getScrapeDate() {
        return scrapeDate;
    }

    public void setScrapeDate(Date scrapeDate) {
        this.scrapeDate = scrapeDate;
    }

    public float getComponentPrice() {
        return componentPrice;
    }

    public void setComponentPrice(float componentPrice) {
        this.componentPrice = componentPrice;
    }
}