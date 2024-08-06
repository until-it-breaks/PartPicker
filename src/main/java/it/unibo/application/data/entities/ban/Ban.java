package it.unibo.application.data.entities.ban;

import java.time.LocalDate;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;

public class Ban {
    private String bannedUser;
    private LocalDate startingDate;
    private LocalDate endingDate;
    private String description;
    private String assigner;

    public Ban(String bannedUser, LocalDate startingDate, LocalDate endingDate, String description, String assigner) {
        this.bannedUser = bannedUser;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.description = description;
        this.assigner = assigner;
    }

    public String getBannedUser() {
        return bannedUser;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public String getDescription() {
        return description;
    }

    public String getAssigner() {
        return assigner;
    }

    public final class DAO {
        public static void insertBan(final Connection connection, Ban ban) {
        try (
            var statement = DAOUtils.prepare(connection, Queries.INSERT_BAN, ban.getBannedUser(), ban.getStartingDate(), ban.getEndingDate(), ban.getDescription(), ban.getAssigner());
        ) {
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new DAOException(e);
            }
        }
    }
}
