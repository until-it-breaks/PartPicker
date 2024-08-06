package it.unibo.application.controller;

import java.util.List;

import it.unibo.application.data.entities.ban.Ban;
import it.unibo.application.data.entities.builds.Build;
import it.unibo.application.data.entities.components.Case;
import it.unibo.application.data.entities.components.Component;
import it.unibo.application.data.entities.components.Cooler;
import it.unibo.application.data.entities.components.Cpu;
import it.unibo.application.data.entities.components.Gpu;
import it.unibo.application.data.entities.components.Motherboard;
import it.unibo.application.data.entities.components.Psu;
import it.unibo.application.data.entities.components.Ram;
import it.unibo.application.data.entities.components.Storage;
import it.unibo.application.data.entities.enums.Part;
import it.unibo.application.data.entities.enums.State;
import it.unibo.application.data.entities.login.User;
import it.unibo.application.data.entities.login.UserDetails;
import it.unibo.application.model.Model;
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

    public Part getDesiredPart() {
        return appStateController.getDesiredPart();
    }


    public void setDesiredPart(final Part part) {
        appStateController.setDesiredPart(part);
    }

    public int getTargetBuild() {
        return appStateController.getTargetBuild();
    }

    public void setTargetBuild(final int id) {
        appStateController.setTargetBuild(id);
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

    public User getLoggedUser() {
        return model.getLoggedUser();
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

    public List<Component> getComponents(final Part part) {
        return model.getComponents(part);
    }

    public List<Build> getBuilds() {
        return model.getBuilds();
    }

    public Build findBuildById(final int id) {
        return model.getBuildById(id);
    }

    public UserDetails getUserDetails(final String username) {
        return model.getUserDetails(username);
    }

    public void banUser(final Ban ban) {
       model.banUser(ban);
    }
}
