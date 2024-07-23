package it.unibo.application.model.components;

import java.time.Year;
import java.util.Currency;
import java.util.UUID;

public class Component {
    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }
    public void setUuid(final UUID uuid) {
        this.uuid = uuid;
    }
    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public Year getLaunchYear() {
        return launchYear;
    }
    public void setLaunchYear(final Year launchYear) {
        this.launchYear = launchYear;
    }
    public Currency getMsrp() {
        return msrp;
    }
    public void setMsrp(final Currency msrp) {
        this.msrp = msrp;
    }
    private String name;
    private Year launchYear;
    private Currency msrp;
}
