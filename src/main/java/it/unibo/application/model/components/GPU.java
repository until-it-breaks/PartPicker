package it.unibo.application.model.components;

public class GPU extends Component {
    private final String memoryType;
    private final Integer memoryAmount;
    private final Integer maxFrequency;
    private final Integer tgp;

    public GPU(final String memoryType, final Integer memoryAmount, final Integer maxFrequency, final Integer tgp) {
        this.memoryType = memoryType;
        this.memoryAmount = memoryAmount;
        this.maxFrequency = maxFrequency;
        this.tgp = tgp;
    }

    public String getMemoryType() {
        return memoryType;
    }
    public Integer getMemoryAmount() {
        return memoryAmount;
    }
    public Integer getMaxFrequency() {
        return maxFrequency;
    }
    public Integer getTgp() {
        return tgp;
    }

}
