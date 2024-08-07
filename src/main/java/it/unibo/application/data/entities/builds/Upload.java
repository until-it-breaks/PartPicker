package it.unibo.application.data.entities.builds;

import java.util.Date;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;

public class Upload {
    private final int buildId;
    private final String username;
    private final Date lastEditDate;

    public Upload(final int buildId, final String username, final Date lastEditDate) {
        this.buildId = buildId;
        this.username = username;
        this.lastEditDate = lastEditDate;
    }

    public int getBuildId() {
        return buildId;
    }

    public String getUsername() {
        return username;
    }

    public Date getLastEditDate() {
        return lastEditDate;
    }
    
    public final class DAO {
        public static void insertUpload(final Connection connection, final Upload upload) {
            try (
                    var statement = DAOUtils.prepare(connection, Queries.INSERT_UPLOAD, upload.getBuildId(), upload.getUsername(), upload.getLastEditDate());
                ) {
                    statement.executeUpdate();
                } catch (final SQLException e) {
                    throw new DAOException(e);
            }
        }
    }
}
