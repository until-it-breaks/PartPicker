package it.unibo.application.model.login;

import it.unibo.application.data.entities.User;
import java.sql.Connection;

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
