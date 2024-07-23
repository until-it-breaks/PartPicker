package it.unibo.application.model.components;

public class Storage extends Component {
    private final Integer capacity;
    private final String storageType;
    private final String interfaceType;
    private final Double dimension;

    public Storage(final Integer capacity, final String storageType, final String interfaceType, final Double dimension) {
        this.capacity = capacity;
        this.storageType = storageType;
        this.interfaceType = interfaceType;
        this.dimension = dimension;
    }

    public Integer getCapacity() {
        return capacity;
    }
    public String getStorageType() {
        return storageType;
    }
    public String getInterfaceType() {
        return interfaceType;
    }
    public Double getDimension() {
        return dimension;
    }

}
