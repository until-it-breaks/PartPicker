package it.unibo.application.model.login;

import it.unibo.application.data.entities.User;
import java.sql.Connection;
import java.util.Optional;

public class LoginService {
    public Connection connection;
    public LoginService(Connection connection) {
        this.connection = connection;
    }

    public boolean login(String username, String password) {
        if (User.DAO.isUserBanned(connection, username)) {
            return false;
        }

        Optional<User> user = User.DAO.findByUsername(connection, username);
        if (user.isPresent() && user.get().password.equals(password)) {
            return true;
        }
        return false;
    }
}
