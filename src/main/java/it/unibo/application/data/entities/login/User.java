package it.unibo.application.data.entities.login;

import java.util.Date;
import java.sql.Connection;
import java.sql.SQLException;
import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;

public class User {
    public  String username;    
    public  String password;
    public  Date signUpDate;
    public  String email;
    public  Boolean isModerator;

    public User(String username, String password, Date signUpDate, String email, Boolean isModerator) {
        this.username = username;
        this.password = password;
        this.signUpDate = signUpDate;
        this.email = email;
        this.isModerator = isModerator;
    }

    public final class DAO {
        public static User findByUsername(Connection connection, String username) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.FIND_USER, username);
                var resultSet = statement.executeQuery();
            ) {
                if (resultSet.next()) {
                    var userName = resultSet.getString("username");
                    var password = resultSet.getString("password");
                    var signUpDate = resultSet.getDate("dataRegistrazione");
                    var email = resultSet.getString("email");
                    var isModerator = resultSet.getBoolean("moderatore");
                    var user = new User(userName, password, signUpDate, email, isModerator);
                    return user;
                }
                return null;
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }

        public static boolean isUserBanned(Connection connection, String username) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.CHECK_BAN, username);
                var resultSet = statement.executeQuery();
            ) {
                return resultSet.next();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }

        public static boolean insertUser(Connection connection, User user) {

            try (
                var statement = DAOUtils.prepare(connection, Queries.REGISTER_USER, user.username, user.password, user.signUpDate, user.email, user.isModerator);
            ) {
                return statement.executeUpdate() > 0;
            } catch (SQLException e) {
                if (e.getErrorCode() == 1062) { // MySQL error code for duplicate entry
                    return false;
                }
                throw new DAOException(e);
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }
    }
}
