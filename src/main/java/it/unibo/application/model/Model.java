package it.unibo.application.model;

import java.sql.Connection;

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
import it.unibo.application.model.enums.Part;
import it.unibo.application.model.login.LoginService;
import java.util.List;

public final class Model {

    private final Connection connection;
    private LoginService loginService;

    public Model(Connection connection) {
        this.connection = connection;
        this.loginService = new LoginService(connection);
    }

    public boolean login(String username, String password) {
        return loginService.login(username, password);
    }

    public boolean registerUser(User user) {
        return User.DAO.insertUser(connection, user);
    }

    public List<Component> getListOfComponentsByType(Part part) {
        return Component.DAO.findByType(connection, part.toString());
    }

    public Cpu getCpuById(int id) {
        return Cpu.DAO.findById(connection, id);
    }

    public Gpu getGpuById(int id) {
        return Gpu.DAO.findById(connection, id);
    }

    public Storage getStorageById(int id) {
        return Storage.DAO.findById(connection, id);
    }

    public Ram getRamById(int id) {
        return Ram.DAO.findById(connection, id);
    }

    public Psu getPsuById(int id) {
        return Psu.DAO.findById(connection, id);
    }

    public Case getCaseById(int id) {
        return Case.DAO.findById(connection, id);
    }

    public Motherboard getMotherboardById(int id) {
        return Motherboard.DAO.findById(connection, id);
    }

    public Cooler getCoolerById(int id) {
        return Cooler.DAO.findById(connection, id);
    }
}
