package it.unibo.application.model.components;

import java.time.Year;
import java.util.Currency;
import java.util.UUID;

public class ComponentImpl implements PCComponent {
    private UUID uuid;

    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Year getLaunchYear() {
        return launchYear;
    }

    @Override
    public Currency getMsrp() {
        return msrp;
    }

    private String name;
    private Year launchYear;
    private Currency msrp;
}
