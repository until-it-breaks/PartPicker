package it.unibo.application.controller;

import java.util.List;

import it.unibo.application.data.entities.Case;
import it.unibo.application.data.entities.Component;
import it.unibo.application.data.entities.Cooler;
import it.unibo.application.data.entities.Cpu;
import it.unibo.application.data.entities.Gpu;
import it.unibo.application.data.entities.Motherboard;
import it.unibo.application.data.entities.Psu;
import it.unibo.application.data.entities.Ram;
import it.unibo.application.data.entities.Storage;
import it.unibo.application.data.entities.User;
import it.unibo.application.model.Model;
import it.unibo.application.model.enums.Part;
import it.unibo.application.model.enums.State;
import it.unibo.application.model.states.AppStateController;
import it.unibo.application.view.View;

public class Controller {
    private final Model model;
    private final AppStateController appStateController;
    private final View view;

    public Controller(final Model model, final View view) {
        this.model = model;
        this.view = view;
        this.appStateController = new AppStateController();
    }

    public void setAppState(final State newState) {
        appStateController.setState(newState);
        view.switchPanel(newState);
    }

    public State getAppState() {
        return appStateController.getState();
    }

    public State getPreviousAppState() {
        return appStateController.getPreviousState();
    }

    public void loginAttempt(final String username, final String password) {
        if (model.login(username, password)) {
            view.showDialog("Login succesful");
            view.switchPanel(State.OVERVIEW);
        } else {
            view.showDialog("Login failed");
        }
    }

    public boolean registerUser(final User user) {
        return model.registerUser(user);
    }

    public List<Component> getComponentsByType(final Part part) {
        return model.getListOfComponentsByType(part);
    }

    public Cpu getCpuById(final int id) {
        return model.getCpuById(id);
    }

    public Gpu getGpuById(final int id) {
        return model.getGpuById(id);
    }

    public Storage getStorageById(final int id) {
        return model.getStorageById(id);
    }

    public Ram getRamById(final int id) {
        return model.getRamById(id);
    }

    public Psu getPsuById(final int id) {
        return model.getPsuById(id);
    }

    public Case getCaseById(final int id) {
        return model.getCaseById(id);
    }

    public Motherboard getMotherboardById(final int id) {
        return model.getMotherboardById(id);
    }

    public Cooler getCoolerById(final int id) {
        return model.getCoolerById(id);
    }
}
