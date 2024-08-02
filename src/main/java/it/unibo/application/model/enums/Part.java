package it.unibo.application.model.enums;

import java.util.List;

public enum Part {
    CPU("Cpu", List.of("Series", "Family", "Number Of Cores", "Frequency", "TDP", "SMT", "Integrated Graphics", "Cooler Included")),
    COOLER("Cooler", List.of("Fan Speed", "Noise level", "Max Heat")),
    MOTHERBOARD("Motherboard", List.of("Form Factor", "Chipset", "Ram Slots", "GPU Slots", "SATA Slots", "M.2 Slots", "Integrated WIFI")),
    RAM("Ram", List.of("Frequency", "Generation", "Capacity", "Latency", "ECC")),
    STORAGE("Storage", List.of("Capacity", "Interface", "Size")),
    GPU("Gpu", List.of("Memory Type", "Memory Amount", "GPU Clock", "TGP")),
    CASE("Case", List.of("Form Factor", "Material")),
    PSU("Psu", List.of("Form Factor", "Wattage", "Efficiency", "Modularity"));

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
