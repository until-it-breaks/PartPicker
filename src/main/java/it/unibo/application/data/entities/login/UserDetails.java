package it.unibo.application.data.entities.login;

import java.time.LocalDate;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;

public class UserDetails {
    private String username;
    private LocalDate registrationDate;
    private String email;
    private Boolean isModerator;
    private Double averageRating;
    private int buildCount;

    public UserDetails(String username, LocalDate registrationDate, String email, Boolean isModerator, Double averageRating,
            int buildCount) {
        this.username = username;
        this.registrationDate = registrationDate;
        this.email = email;
        this.isModerator = isModerator;
        this.averageRating = averageRating;
        this.buildCount = buildCount;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getIsModerator() {
        return isModerator;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public int getBuildCount() {
        return buildCount;
    }

    public static UserDetails getUserDetails(Connection connection, String username) {
        try (
            var statement = DAOUtils.prepare(connection, Queries.FIND_USER_DETAILS, username);
            var resultSet = statement.executeQuery();
        ) {
            if (resultSet.next()) {
                var name = resultSet.getString("Username");
                var registrationDate = resultSet.getDate("DataRegistrazione").toLocalDate();
                var email = resultSet.getString("Email");
                var isModerator = resultSet.getBoolean("Moderatore");
                var averageRating = resultSet.getDouble("RatingMedio");
                var buildCount = resultSet.getInt("NumeroBuild");
                return new UserDetails(name, registrationDate, email, isModerator, averageRating, buildCount);
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException(e);
    }
}
}
