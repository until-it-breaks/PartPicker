package it.unibo.application.model.enums;

public enum Part {
    CPU("Cpu"),
    COOLER("Cooler"),
    MOTHERBOARD("Motherboard"),
    RAM("Ram"),
    STORAGE("Storage"),
    GPU("Gpu"),
    CASE("Case"),
    PSU("Psu");

    private final String partName;

    Part(final String partName) {
        this.partName = partName;
    }

    @Override
    public String toString() {
        return this.partName;
    }
}
