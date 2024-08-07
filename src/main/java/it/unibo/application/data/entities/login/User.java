package it.unibo.application.data.entities.login;

import java.util.Date;
import java.sql.Connection;
import java.sql.SQLException;
import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;

public class User {
    private final String username;    
    private final String password;
    private final Date signUpDate;
    private final String email;
    private final Boolean isModerator;

    public User(final String username, final String password,
            final Date signUpDate, final String email,
            final Boolean isModerator) {
        this.username = username;
        this.password = password;
        this.signUpDate = signUpDate;
        this.email = email;
        this.isModerator = isModerator;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getSignUpDate() {
        return signUpDate;
    }

    public String getEmail() {
        return email;
    }

    public Boolean isModerator() {
        return isModerator;
    }

    public final class DAO {
        public static User findByUsername(final Connection connection, final String username) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.FIND_USER, username);
                var resultSet = statement.executeQuery();
            ) {
                if (resultSet.next()) {
                    final var userName = resultSet.getString("username");
                    final var password = resultSet.getString("password");
                    final var signUpDate = resultSet.getDate("dataRegistrazione");
                    final var email = resultSet.getString("email");
                    final var isModerator = resultSet.getBoolean("moderatore");
                    final var user = new User(userName, password, signUpDate, email, isModerator);
                    return user;
                }
                return null;
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }

        public static boolean isUserBanned(final Connection connection, final String username) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.CHECK_BAN, username);
                var resultSet = statement.executeQuery();
            ) {
                return resultSet.next();
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }

        public static boolean insertUser(final Connection connection, final User user) {

            try (
                var statement = DAOUtils.prepare(connection, Queries.REGISTER_USER, user.username, user.password, user.signUpDate, user.email, user.isModerator);
            ) {
                return statement.executeUpdate() > 0;
            } catch (final SQLException e) {
                if (e.getErrorCode() == 1062) { // MySQL error code for duplicate entry
                    return false;
                }
                throw new DAOException(e);
            } catch (final Exception e) {
                throw new DAOException(e);
            }
        }
    }
}
