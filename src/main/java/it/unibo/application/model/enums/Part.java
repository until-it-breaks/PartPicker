package it.unibo.application.model.enums;

public enum Part {
    CPU("CPU"),
    CPU_COOLER("CPU Cooler"),
    MOTHERBOARD("Motherboard"),
    MEMORY("Memory"),
    STORAGE("Storage"),
    VIDEO_CARD("Video Card"),
    CASE("Case"),
    POWER_SUPPLY("Power Supply");

    private final String partName;

    @Override
    public String toString() {
        return partName;
    }

    Part(final String partName) {
        this.partName = partName;
    }
}
