package it.unibo.application.model;

import java.sql.Connection;

import it.unibo.application.data.entities.Component;
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
}
