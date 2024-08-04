package it.unibo.application.view;
import java.util.Map;

import it.unibo.application.data.entities.Case;
import it.unibo.application.data.entities.Component;
import it.unibo.application.data.entities.Cooler;
import it.unibo.application.data.entities.Cpu;
import it.unibo.application.data.entities.Gpu;
import it.unibo.application.data.entities.Manufacturer;
import it.unibo.application.data.entities.Motherboard;
import it.unibo.application.data.entities.Psu;
import it.unibo.application.data.entities.Ram;
import it.unibo.application.data.entities.Storage;
import it.unibo.application.model.enums.Part;

import java.util.LinkedHashMap;
import java.util.Collections;

public final class ComponentSpecsUtility {

    public static Map<String, String> getSpecs(final Component component, final Part part) {
        switch (part) {
            case Part.CPU:
                return getCpuSpecs((Cpu)component);
            case Part.COOLER:
                return getCoolerSpecs((Cooler)component);
            case Part.MOTHERBOARD:
                return getMotherboardSpecs((Motherboard)component);
            case Part.RAM:
                return getRamSpecs((Ram)component);
            case Part.STORAGE:
                return getStorageSpecs((Storage)component);
            case Part.GPU:
                return getGpuSpecs((Gpu)component);
            case Part.CASE:
                return getCaseSpecs((Case)component);
            case Part.PSU:
                return getPsuSpecs((Psu)component);
            default:
                throw new IllegalStateException();
        }
    }

    public static Map<String, String> getCpuSpecs(final Cpu cpu) {
        final Map<String, String> map = new LinkedHashMap<>();
        map.put("CPU Family", cpu.cpuFamily);
        map.put("Core Count", String.valueOf(cpu.coreCount));
        map.put("Frequency", cpu.cpuFrequency + " GHz");
        map.put("TDP", cpu.tdp + "w");
        map.put("SMT", cpu.hasSmt == true ? "Yes" : "No");
        map.put("Socket", cpu.socketName);
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getCaseSpecs(final Case _case) {
        final Map<String, String> map = new LinkedHashMap<>();
        map.put("Form Factor", _case.formFactor);
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getCoolerSpecs(final Cooler cooler) {
        final Map<String, String> map = new LinkedHashMap<>();
        map.put("Cooler Fan RPM", cooler.coolerRpm + " RPM");
        map.put("Noise Level", cooler.noiseLevel + " dB");
        map.put("Cooler Type", cooler.coolerType);
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getGpuSpecs(final Gpu gpu) {
        final Map<String, String> map = new LinkedHashMap<>();
        map.put("GPU Family", gpu.gpuFamily);
        map.put("Memory Type", gpu.gpuMemoryType);
        map.put("Memory Amount", gpu.gpuMemoryAmount + " GB");
        map.put("Frequency", gpu.gpuFrequency + " MHz");
        map.put("TGP", gpu.tgp + "w");
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getMotherboardSpecs(final Motherboard motherboard) {
        final Map<String, String> map = new LinkedHashMap<>();
        map.put("Form Factor", motherboard.formFactor);
        map.put("Chipset", motherboard.chipsetName);
        map.put("Ram Slots", String.valueOf(motherboard.ramSlots));
        map.put("GPU Slots", String.valueOf(motherboard.gpuSlots));
        map.put("WiFi", motherboard.hasWifi == true ? "Yes" : "No");
        map.put("Socket", motherboard.socketName);
        map.put("Supported RAM", motherboard.ramGeneration);
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getPsuSpecs(final Psu psu) {
        final Map<String, String> map = new LinkedHashMap<>();
        map.put("Form Factor", psu.formFactor);
        map.put("Efficiency", psu.efficiency);
        map.put("Wattage", psu.wattage + "w");
        map.put("Modularity", psu.modularity);
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getRamSpecs(final Ram ram) {
        final Map<String, String> map = new LinkedHashMap<>();
        map.put("Frequency", ram.ramFrequency + " MHz");
        map.put("Capacity", ram.capacity + " GB");
        map.put("Latency", ram.latency);
        map.put("ECC", ram.isEcc == true ? "Yes" : "No");
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getStorageSpecs(final Storage storage) {
        final Map<String, String> map = new LinkedHashMap<>();
        map.put("Capacity", storage.storageCapacity + " GB");
        map.put("Storage RPM", storage.storageRpm + " RPM");
        map.put("Cache Amount", storage.cacheAmount + " MB");
        map.put("Storage Type", storage.storageType);
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getComponentSpecs(final Component component) {
        final Map<String, String> map = new LinkedHashMap<>();
        map.put("ID", String.valueOf(component.componentId));
        map.put("Name", component.componentName);
        map.put("Launch Year", String.valueOf(component.launchYear));
        map.put("MSRP", component.msrp + "$");
        return map;
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
