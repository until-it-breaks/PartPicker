package it.unibo.application.model;

import java.sql.Connection;

import it.unibo.application.data.entities.Case;
import it.unibo.application.data.entities.Component;
import it.unibo.application.data.entities.Cooler;
import it.unibo.application.data.entities.Cpu;
import it.unibo.application.data.entities.Gpu;
import it.unibo.application.data.entities.Manufacturer;
import it.unibo.application.data.entities.Motherboard;
import it.unibo.application.data.entities.Psu;
import it.unibo.application.data.entities.Ram;
import it.unibo.application.data.entities.Storage;
import it.unibo.application.data.entities.User;
import it.unibo.application.model.enums.Part;
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

    public List<Component> getListOfComponentsByType(final Part part) {
        return Component.DAO.findByType(connection, part.toString());
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

    public Manufacturer getManufactureById(final int id) {
        return Manufacturer.DAO.findById(connection, id);
    }
}
