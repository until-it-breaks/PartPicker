package it.unibo.application.data.entities;

public class Manufacturer {
    private int manufacturerId;
    private String manufacturerName;
    private String manufacturerCountry;

    public Manufacturer(int manufacturerId, String manufacturerName, String manufacturerCountry) {
        this.manufacturerId = manufacturerId;
        this.manufacturerName = manufacturerName;
        this.manufacturerCountry = manufacturerCountry;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getManufacturerCountry() {
        return manufacturerCountry;
    }

    public void setManufacturerCountry(String manufacturerCountry) {
        this.manufacturerCountry = manufacturerCountry;
    }
    
}
