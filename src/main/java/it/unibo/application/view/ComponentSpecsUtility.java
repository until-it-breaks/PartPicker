package it.unibo.application.view;
import java.util.Map;

import it.unibo.application.data.entities.components.Component;
import it.unibo.application.data.entities.enums.Specs;

import java.util.LinkedHashMap;
import java.util.Collections;

public final class ComponentSpecsUtility {

    public static Map<String, String> getCpuSpecs(final Component cpu) {
        final Map<String, String> map = new LinkedHashMap<>();
        final Map<Specs, String> specs = cpu.getSpecificAttributes();
        map.put("CPU Family", specs.get(Specs.CPU_FAMILY).toString());
        map.put("Core Count", specs.get(Specs.CPU_CORE_COUNT).toString());
        map.put("Frequency", specs.get(Specs.CPU_FREQUENCY) + " GHz");
        map.put("TDP", specs.get(Specs.CPU_TDP) + "w");
        map.put("SMT", specs.get(Specs.CPU_SMT).toString());
        map.put("Socket", specs.get(Specs.CPU_SOCKET_NAME).toString());
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getCaseSpecs(final Component _case) {
        final Map<String, String> map = new LinkedHashMap<>();
        final Map<Specs, String> specs = _case.getSpecificAttributes();
        map.put("Form Factor", specs.get(Specs.CASE_FORM_FACTOR).toString());
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getCoolerSpecs(final Component cooler) {
        final Map<String, String> map = new LinkedHashMap<>();
        final Map<Specs, String> specs = cooler.getSpecificAttributes();
        map.put("Cooler Fan RPM", specs.get(Specs.COOLER_RPM));
        map.put("Noise Level", specs.get(Specs.COOLER_NOISE_LEVEL) + " dB");
        map.put("Cooler Type", specs.get(Specs.COOLER_TYPE).toString());
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getGpuSpecs(final Component gpu) {
        final Map<String, String> map = new LinkedHashMap<>();
        final Map<Specs, String> specs = gpu.getSpecificAttributes();
        map.put("GPU Family", specs.get(Specs.GPU_FAMILY).toString());
        map.put("Memory Type", specs.get(Specs.GPU_MEMORY_TYPE).toString());
        map.put("Memory Amount", specs.get(Specs.GPU_MEMORY_AMOUNT) + " GB");
        map.put("Frequency", specs.get(Specs.GPU_FREQUENCY) + " MHz");
        map.put("TGP", specs.get(Specs.GPU_TGP)+ "w");
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getMotherboardSpecs(final Component motherboard) {
        final Map<String, String> map = new LinkedHashMap<>();
        final Map<Specs, String> specs = motherboard.getSpecificAttributes();
        map.put("Form Factor", specs.get(Specs.MOTHERBOARD_FORM_FACTOR).toString());
        map.put("Chipset", specs.get(Specs.MOTHERBOARD_CHIPSET).toString());
        map.put("Ram Slots", specs.get(Specs.MOTHERBOARD_RAM_SLOTS).toString());
        map.put("GPU Slots", specs.get(Specs.MOTHERBOARD_GPU_SLOTS).toString());
        map.put("WiFi", specs.get(Specs.MOTHERBOARD_WIFI).toString());
        map.put("Socket",specs.get(Specs.MOTHERBOARD_SOCKET).toString());
        map.put("Supported RAM", specs.get(Specs.MOTHERBOARD_RAM_GEN).toString());
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getPsuSpecs(final Component psu) {
        final Map<String, String> map = new LinkedHashMap<>();
        final Map<Specs, String> specs = psu.getSpecificAttributes();
        map.put("Form Factor", specs.get(Specs.PSU_FORM_FACTOR).toString());
        map.put("Efficiency", specs.get(Specs.PSU_EFFICIENCY).toString());
        map.put("Wattage", specs.get(Specs.PSU_WATTAGE) + "w");
        map.put("Modularity", specs.get(Specs.PSU_MODULARITY).toString());
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getRamSpecs(final Component ram) {
        final Map<String, String> map = new LinkedHashMap<>();
        final Map<Specs, String> specs = ram.getSpecificAttributes();
        map.put("Frequency", specs.get(Specs.RAM_FREQUENCY) + " MHz");
        map.put("Capacity", specs.get(Specs.RAM_CAPACITY) + " GB");
        map.put("Latency", specs.get(Specs.RAM_LATENCY).toString());
        map.put("ECC", specs.get(Specs.RAM_ECC));
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getStorageSpecs(final Component storage) {
        final Map<String, String> map = new LinkedHashMap<>();
        final Map<Specs, String> specs = storage.getSpecificAttributes();
        map.put("Capacity", specs.get(Specs.STORAGE_CAPACITY) + " GB");
        map.put("Storage RPM", specs.get(Specs.STORAGE_RPM));
        map.put("Cache Amount", specs.get(Specs.STORAGE_CACHE) + " MB");
        map.put("Storage Type", specs.get(Specs.STORAGE_TYPE).toString());
        return Collections.unmodifiableMap(map);
    }

    public static String formatSpecs(final Map<String, String> specs) {
        final StringBuilder builder = new StringBuilder();
        for (final Map.Entry<String, String> entry : specs.entrySet()) {
            builder.append(entry.getKey())
                .append(": ")
                .append(entry.getValue())
                .append("\n");
        }
        return builder.toString();
    }
}
