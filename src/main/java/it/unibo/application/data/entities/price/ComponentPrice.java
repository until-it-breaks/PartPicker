package it.unibo.application.data.entities.price;

import java.util.Date;

public class ComponentPrice {
    private final int componentId;
    private final int resellerName;
    private final Date scrapeDate;
    private final float componentPrice;

    public ComponentPrice(final int componentId, final int resellerName,
            final Date scrapeDate, final float componentPrice) {
        this.componentId = componentId;
        this.resellerName = resellerName;
        this.scrapeDate = scrapeDate;
        this.componentPrice = componentPrice;
    }

    public int getComponentId() {
        return componentId;
    }

    public int getResellerName() {
        return resellerName;
    }

    public Date getScrapeDate() {
        return scrapeDate;
    }

    public float getComponentPrice() {
        return componentPrice;
    }
}