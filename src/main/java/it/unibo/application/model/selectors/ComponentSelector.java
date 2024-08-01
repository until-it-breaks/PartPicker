package it.unibo.application.model.selectors;

import it.unibo.application.model.components.PCComponent;
import it.unibo.application.model.enums.Part;

public class ComponentSelector {
    Part currentCategory;
    PCComponent selectedComponent;

    public ComponentSelector() {
    }
    
    public void setCategory(Part part) {
        this.currentCategory = part;
    }

    public void setSelectedComponent(PCComponent component) {
        this.selectedComponent = component;
    }

    public Part getCurrentCategory() {
        return currentCategory;
    }

    public PCComponent getSelectedComponent() {
        return selectedComponent;
    }
}
