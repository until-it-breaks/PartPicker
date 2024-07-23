package it.unibo.application.model.components;

public class CPU extends Component {
    private final String series;
    private final String family;
    private final Integer numberOfCores;
    private final Float maxClockFrequency;
    private final Integer tdp;
    private final Boolean capableOfSMT;
    private final Boolean hasGraphics;


    public CPU(final String series, final String family, final Integer numberOfCores, final Float maxClockFrequency, final Integer tdp,
            final Boolean capableOfSMT, final Boolean hasGraphics) {
        this.series = series;
        this.family = family;
        this.numberOfCores = numberOfCores;
        this.maxClockFrequency = maxClockFrequency;
        this.tdp = tdp;
        this.capableOfSMT = capableOfSMT;
        this.hasGraphics = hasGraphics;
    }

    public String getSeries() {
        return series;
    }
    public String getFamily() {
        return family;
    }
    public Integer getNumberOfCores() {
        return numberOfCores;
    }
    public Float getMaxClockFrequency() {
        return maxClockFrequency;
    }
    public Integer getTdp() {
        return tdp;
    }
    public Boolean getCapableOfSMT() {
        return capableOfSMT;
    }
    public Boolean getHasGraphics() {
        return hasGraphics;
    }

}
