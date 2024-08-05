package it.unibo.application.view;
import java.util.Map;

import it.unibo.application.data.entities.components.Component;
import it.unibo.application.model.enums.Specs;

import java.util.LinkedHashMap;
import java.util.Collections;

public final class ComponentSpecsUtility {

    public static Map<String, String> getCpuSpecs(final Component cpu) {
        final Map<String, String> map = new LinkedHashMap<>();
        Map<String, String> specs = cpu.getSpecificAttributes();
        map.put("CPU Family", specs.get(Specs.CPU_FAMILY.getKey()).toString());
        map.put("Core Count", specs.get(Specs.CPU_CORE_COUNT.getKey()).toString());
        map.put("Frequency", specs.get(Specs.CPU_FREQUENCY.getKey()) + " GHz");
        map.put("TDP", specs.get(Specs.CPU_TDP.getKey()) + "w");
        map.put("SMT", specs.get(Specs.CPU_SMT.getKey()).toString() == "1" ? "Yes" : "No");
        map.put("Socket", specs.get(Specs.CPU_SOCKET_NAME.getKey()).toString());
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getCaseSpecs(final Component _case) {
        final Map<String, String> map = new LinkedHashMap<>();
        Map<String, String> specs = _case.getSpecificAttributes();
        map.put("Form Factor", specs.get(Specs.CASE_FORM_FACTOR.getKey()).toString());
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getCoolerSpecs(final Component cooler) {
        final Map<String, String> map = new LinkedHashMap<>();
        Map<String, String> specs = cooler.getSpecificAttributes();
        map.put("Cooler Fan RPM", specs.get(Specs.COOLER_RPM.getKey()));
        map.put("Noise Level", specs.get(Specs.COOLER_NOISE_LEVEL.getKey()) + " dB");
        map.put("Cooler Type", specs.get(Specs.COOLER_TYPE.getKey()).toString());
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getGpuSpecs(final Component gpu) {
        final Map<String, String> map = new LinkedHashMap<>();
        Map<String, String> specs = gpu.getSpecificAttributes();
        map.put("GPU Family", specs.get(Specs.GPU_FAMILY.getKey()).toString());
        map.put("Memory Type", specs.get(Specs.GPU_MEMORY_TYPE.getKey()).toString());
        map.put("Memory Amount", specs.get(Specs.GPU_MEMORY_AMOUNT.getKey()) + " GB");
        map.put("Frequency", specs.get(Specs.GPU_FREQUENCY.getKey()) + " MHz");
        map.put("TGP", specs.get(Specs.GPU_TGP.getKey())+ "w");
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getMotherboardSpecs(final Component motherboard) {
        final Map<String, String> map = new LinkedHashMap<>();
        Map<String, String> specs = motherboard.getSpecificAttributes();
        map.put("Form Factor", specs.get(Specs.MOTHERBOARD_FORM_FACTOR.getKey()).toString());
        map.put("Chipset", specs.get(Specs.MOTHERBOARD_CHIPSET.getKey()).toString());
        map.put("Ram Slots", specs.get(Specs.MOTHERBOARD_RAM_SLOTS.getKey()).toString());
        map.put("GPU Slots", specs.get(Specs.MOTHERBOARD_GPU_SLOTS.getKey()).toString());
        map.put("WiFi", specs.get(Specs.MOTHERBOARD_WIFI.getKey()).toString());
        map.put("Socket",specs.get(Specs.MOTHERBOARD_SOCKET.getKey()).toString());
        map.put("Supported RAM", specs.get(Specs.MOTHERBOARD_RAM_GEN.getKey()).toString());
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getPsuSpecs(final Component psu) {
        final Map<String, String> map = new LinkedHashMap<>();
        final Map<String, String> specs = psu.getSpecificAttributes();
        map.put("Form Factor", specs.get(Specs.PSU_FORM_FACTOR.getKey()).toString());
        map.put("Efficiency", specs.get(Specs.PSU_EFFICIENCY.getKey()).toString());
        map.put("Wattage", specs.get(Specs.PSU_WATTAGE.getKey()) + "w");
        map.put("Modularity", specs.get(Specs.PSU_MODULARITY.getKey()).toString());
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getRamSpecs(final Component ram) {
        final Map<String, String> map = new LinkedHashMap<>();
        final Map<String, String> specs = ram.getSpecificAttributes();
        map.put("Frequency", specs.get(Specs.RAM_FREQUENCY.getKey()) + " MHz");
        map.put("Capacity", specs.get(Specs.RAM_CAPACITY.getKey()) + " GB");
        map.put("Latency", specs.get(Specs.RAM_LATENCY.getKey()).toString());
        map.put("ECC", specs.get(Specs.RAM_ECC.getKey()) == "1" ? "Yes" : "No");
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getStorageSpecs(final Component storage) {
        final Map<String, String> map = new LinkedHashMap<>();
        final Map<String, String> specs = storage.getSpecificAttributes();
        map.put("Capacity", specs.get(Specs.STORAGE_CAPACITY.getKey()) + " GB");
        map.put("Storage RPM", specs.get(Specs.STORAGE_RPM.getKey()));
        map.put("Cache Amount", specs.get(Specs.STORAGE_CACHE.getKey()) + " MB");
        map.put("Storage Type", specs.get(Specs.STORAGE_TYPE.getKey()).toString());
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
