package it.unibo.application.data.entities;

public class Manufacturer {
    private final String manufacturerName;
    private final String manufacturerCountry;

    public Manufacturer(final String manufacturerName, final String manufacturerCountry) {
        this.manufacturerName = manufacturerName;
        this.manufacturerCountry = manufacturerCountry;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public String getManufacturerCountry() {
        return manufacturerCountry;
    }
}
