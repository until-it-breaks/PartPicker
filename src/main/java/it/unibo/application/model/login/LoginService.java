package it.unibo.application.model.login;

import java.sql.Connection;

import it.unibo.application.data.entities.login.User;

public class LoginService {
    public Connection connection;
    public LoginService(final Connection connection) {
        this.connection = connection;
    }

    public boolean login(final String username, final String password) {
        if (User.DAO.isUserBanned(connection, username)) {
            return false;
        }

        final User user = User.DAO.findByUsername(connection, username);
        if (user != null && user.password.equals(password)) {
            return true;
        }
        return false;
    }
}
