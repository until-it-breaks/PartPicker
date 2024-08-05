package it.unibo.application.model.enums;

public enum Specs {
    COMPONENT_ID("CodiceComponente", "Id", null),
    COMPONENT_NAME("NomeComponente", "Component Name", null),
    COMPONENT_LAUNCH_YEAR("AnnoLancio", "Launch Year", null),
    COMPONENT_MSRP("PrezzoListino", "MSRP", null),
    COMPONENT_MANUFACTURER("NomeProduttore", "Manufacturer", null),
    CASE_FORM_FACTOR("FattoreFormaCase", "Form Factor", null),
    COOLER_RPM("RpmCooler", "RPM", "RPM"),
    COOLER_NOISE_LEVEL("LivelloRumore", "Noise Level", "dB"),
    COOLER_TYPE("TipoCooler", "Type", null),
    CPU_FAMILY("FamigliaCpu", "Family", null),
    CPU_CORE_COUNT("NumeroCore", "Core Count", ""),
    CPU_FREQUENCY("FrequenzaCpu", "Frequency", "GHz"),
    CPU_TDP("Tdp", "Tdp", "w"),
    CPU_SMT("Smt", "SMT", null),
    CPU_SOCKET_NAME("NomeSocket", "Socket Name", null),
    GPU_FAMILY("FamigliaGpu", "Family", null),
    GPU_MEMORY_TYPE("TipoMemoriaGpu", "Memory Type", null),
    GPU_MEMORY_AMOUNT("QuantitaMemoriaGpu", "Memory Amount", "GB"),
    GPU_FREQUENCY("FrequenzaGpu", "Frequency", "MHz"),
    GPU_TGP("Tgp", "Tgp", "w"),
    MOTHERBOARD_FORM_FACTOR("FattoreFormaMotherboard", "Form Factor", null),
    MOTHERBOARD_CHIPSET("NomeChipset", "Chipset", null),
    MOTHERBOARD_RAM_SLOTS("SlotRam", "Ram Slots", null),
    MOTHERBOARD_GPU_SLOTS("SlotGpu", "Gpu Slots", null),
    MOTHERBOARD_WIFI("WiFi", "WiFi", null),
    MOTHERBOARD_SOCKET("NomeSocket", "Socket", null),
    MOTHERBOARD_RAM_GEN("NomeGenerazioneRam", "Ram Gen", null),
    PSU_FORM_FACTOR("FattoreFormaPsu", "Form Factor", null),
    PSU_EFFICIENCY("Efficienza", "Efficiency", null),
    PSU_WATTAGE("Wattaggio", "Wattage", "w"),
    PSU_MODULARITY("Modularita", "Modularity", null),
    RAM_FREQUENCY("FrequenzaRam", "Frequency", "MHz"),
    RAM_CAPACITY("CapienzaRam", "Capacity", "GB"),
    RAM_LATENCY("Latenza", "Latency", null),
    RAM_ECC("Ecc", "ECC", null),
    RAM_GEN("NomeGenerazioneRam", "Ram Gen", null),
    STORAGE_CAPACITY("CapienzaStorage", "Capacity", "GB"),
    STORAGE_RPM("RpmStorage", "RPM", "RPM"),
    STORAGE_CACHE("QuantitaCache", "Cache Amount", "MB"),
    STORAGE_TYPE("TipoStorage", "Storage Type", null);

    private final String key;
    private final String fieldName;
    private final String suffix;

    private Specs(final String columnName, final String fieldName, final String suffix) {
        this.key = columnName;
        this.fieldName = fieldName;
        this.suffix = suffix;
    }

    public String getKey() {
        return key;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getSuffix() {
        return suffix;
    }

    public String toString() {
        return fieldName;
    }
}
