package it.unibo.application.model;

import java.sql.Connection;

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
import it.unibo.application.data.entities.login.User;
import it.unibo.application.data.entities.login.UserDetails;
import it.unibo.application.model.login.LoginService;
import java.util.List;

public final class Model {

    private final Connection connection;
    private final LoginService loginService;

    public Model(final Connection connection) {
        this.connection = connection;
        this.loginService = new LoginService(connection);
    }

    public boolean login(final String username, final String password) {
        return loginService.login(username, password);
    }

    public boolean registerUser(final User user) {
        return User.DAO.insertUser(connection, user);
    }

    public User getLoggedUser() {
        return User.DAO.findByUsername(connection, loginService.getCurrentUser());
    }

    public Cpu getCpuById(final int id) {
        return Cpu.DAO.findById(connection, id);
    }

    public Gpu getGpuById(final int id) {
        return Gpu.DAO.findById(connection, id);
    }

    public Storage getStorageById(final int id) {
        return Storage.DAO.findById(connection, id);
    }

    public Ram getRamById(final int id) {
        return Ram.DAO.findById(connection, id);
    }

    public Psu getPsuById(final int id) {
        return Psu.DAO.findById(connection, id);
    }

    public Case getCaseById(final int id) {
        return Case.DAO.findById(connection, id);
    }

    public Motherboard getMotherboardById(final int id) {
        return Motherboard.DAO.findById(connection, id);
    }

    public Cooler getCoolerById(final int id) {
        return Cooler.DAO.findById(connection, id);
    }

    public List<Build> getBuilds() {
        return Build.DAO.getBuilds(connection);
    }

    public Build getBuildById(final int id) {
        return Build.DAO.findBuildById(connection, id);
    }

    public List<Component> getComponents(final Part part) {
        switch (part) {
            case CPU:
                return Cpu.DAO.getCpus(connection);
            case GPU:
                return Gpu.DAO.getGpus(connection);
            case MOTHERBOARD:
                return Motherboard.DAO.getMotherboards(connection);
            case PSU:
                return Psu.DAO.getPsus(connection);
            case RAM:
                return Ram.DAO.getRams(connection);
            case STORAGE:
                return Storage.DAO.getStorage(connection);
            case COOLER:
                return Cooler.DAO.getCoolers(connection);
            case CASE:
                return Case.DAO.getCases(connection);
            default:
                throw new IllegalArgumentException("Unknown part type: " + part);
        }
    }

    public UserDetails getUserDetails(final String username) {
        return UserDetails.getUserDetails(connection, username);
    }
}
