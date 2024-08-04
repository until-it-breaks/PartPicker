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
    private Model model;
    private AppStateController appStateController;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.appStateController = new AppStateController();
    }

    public void setAppState(State newState) {
        appStateController.setState(newState);
        view.switchPanel(newState);
    }

    public State getAppState() {
        return appStateController.getState();
    }

    public State getPreviousAppState() {
        return appStateController.getPreviousState();
    }

    public void loginAttempt(String username, String password) {
        if (model.login(username, password)) {
            view.showDialog("Login succesful");
            view.switchPanel(State.OVERVIEW);
        } else {
            view.showDialog("Login failed");
        }
    }

    public boolean registerUser(User user) {
        return model.registerUser(user);
    }

    public List<Component> getComponentsByType(Part part) {
        return model.getListOfComponentsByType(part);
    }

    public Cpu getCpuById(int id) {
        return model.getCpuById(id);
    }

    public Gpu getGpuById(int id) {
        return model.getGpuById(id);
    }

    public Storage getStorageById(int id) {
        return model.getStorageById(id);
    }

    public Ram getRamById(int id) {
        return model.getRamById(id);
    }

    public Psu getPsuById(int id) {
        return model.getPsuById(id);
    }

    public Case getCaseById(int id) {
        return model.getCaseById(id);
    }

    public Motherboard getMotherboardById(int id) {
        return model.getMotherboardById(id);
    }

    public Cooler getCoolerById(int id) {
        return model.getCoolerById(id);
    }
}
