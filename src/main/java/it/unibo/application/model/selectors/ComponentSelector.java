package it.unibo.application.model.selectors;

import it.unibo.application.data.entities.Component;
import it.unibo.application.model.enums.Part;

public class ComponentSelector {
    Part currentCategory;
    Component selectedComponent;

    public ComponentSelector() {
    }
    
    public void setCategory(Part part) {
        this.currentCategory = part;
    }

    public void setSelectedComponent(Component component) {
        this.selectedComponent = component;
    }

    public Part getCurrentCategory() {
        return currentCategory;
    }

    public Component getSelectedComponent() {
        return selectedComponent;
    }
}
