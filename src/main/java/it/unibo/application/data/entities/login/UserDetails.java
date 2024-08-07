package it.unibo.application.data.entities.login;

import java.time.LocalDate;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;

public class UserDetails {
    private final String username;
    private final LocalDate registrationDate;
    private final String email;
    private final Boolean isModerator;
    private final Double averageRating;
    private final int buildCount;

    public UserDetails(final String username, final LocalDate registrationDate,
            final String email, final Boolean isModerator,
            final Double averageRating, final int buildCount) {
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

    public static UserDetails getUserDetails(final Connection connection, final String username) {
        try (
            var statement = DAOUtils.prepare(connection, Queries.FIND_USER_DETAILS, username);
            var resultSet = statement.executeQuery();
        ) {
            if (resultSet.next()) {
                final var name = resultSet.getString("Username");
                final var registrationDate = resultSet.getDate("DataRegistrazione").toLocalDate();
                final var email = resultSet.getString("Email");
                final var isModerator = resultSet.getBoolean("Moderatore");
                final var averageRating = resultSet.getDouble("RatingMedio");
                final var buildCount = resultSet.getInt("NumeroBuild");
                return new UserDetails(name, registrationDate, email, isModerator,
                    averageRating, buildCount);
            }
            return null;
        } catch (final SQLException e) {
            throw new DAOException(e);
    }
}
}
