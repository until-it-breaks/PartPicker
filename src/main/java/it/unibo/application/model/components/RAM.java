package it.unibo.application.model.components;

public class RAM extends Component {
    private final String RamGeneration;
    private final Integer frequency;
    private final String kitConfiguration;
    private final Integer capacity;
    private final String latency;
    private final Boolean isECC;

    public RAM(final String ramGeneration, final Integer frequency, final String kitConfiguration, final Integer capacity, final String latency,
            final Boolean isECC) {
        RamGeneration = ramGeneration;
        this.frequency = frequency;
        this.kitConfiguration = kitConfiguration;
        this.capacity = capacity;
        this.latency = latency;
        this.isECC = isECC;
    }

    public String getRamGeneration() {
        return RamGeneration;
    }
    public Integer getFrequency() {
        return frequency;
    }
    public String getKitConfiguration() {
        return kitConfiguration;
    }
    public Integer getCapacity() {
        return capacity;
    }
    public String getLatency() {
        return latency;
    }
    public Boolean getIsECC() {
        return isECC;
    }
}
