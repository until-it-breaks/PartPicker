package it.unibo.application.model.enums;

import java.util.List;

public enum Part {
    CPU("CPU", List.of("Series", "Family", "Number Of Cores", "Frequency", "TDP", "SMT", "Integrated Graphics", "Cooler Included")),
    CPU_COOLER("CPU Cooler", List.of("Fan Speed", "Noise level", "Max Heat")),
    MOTHERBOARD("Motherboard", List.of("Form Factor", "Chipset", "Ram Slots", "GPU Slots", "SATA Slots", "M.2 Slots", "Integrated WIFI")),
    MEMORY("Memory", List.of("Frequency", "Generation", "Capacity", "Latency", "ECC")),
    STORAGE("Storage", List.of("Capacity", "Interface", "Size")),
    VIDEO_CARD("Video Card", List.of("Memory Type", "Memory Amount", "GPU Clock", "TGP")),
    CASE("Case", List.of("Form Factor", "Material")),
    POWER_SUPPLY("Power Supply", List.of("Form Factor", "Wattage", "Efficiency", "Modularity"));

    private final String partName;
    private final List<String> specifications;

    @Override
    public String toString() {
        return this.partName;
    }

    public List<String> getSpecifications() {
        return this.specifications;
    }

    Part(final String partName, final List<String> specifications) {
        this.partName = partName;
        this.specifications = specifications;
    }
}
