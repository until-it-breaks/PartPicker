package it.unibo.application.model.selectors;

import it.unibo.application.data.entities.Build;

public class BuildSelector {
    private Build currentBuild;

    public BuildSelector() {
    }

    public Build getCurrentBuild() {
        return currentBuild;
    }

    public void setCurrentBuild(Build newBuild) {
        this.currentBuild = newBuild;
    }
}
