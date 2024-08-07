package it.unibo.application.data.entities.builds;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;

public class StorageUsage {
    private int buildId;
    private int storageId;
    private int quantity;

    public StorageUsage(int buildId, int storageId, int quantity) {
        this.buildId = buildId;
        this.storageId = storageId;
        this.quantity = quantity;
    }

    public int getBuildId() {
        return buildId;
    }

    public int getStorageId() {
        return storageId;
    }

    public int getQuantity() {
        return quantity;
    }

    public final class DAO {
        public static void insertStorageUsage(final Connection connection, final StorageUsage storageUsage) {
            try (
                    var statement = DAOUtils.prepare(connection, Queries.INSERT_STORAGE_USAGE, storageUsage.getBuildId(), storageUsage.getStorageId(), storageUsage.getQuantity());
                ) {
                    statement.executeUpdate();
                } catch (final SQLException e) {
                    throw new DAOException(e);
            }
        }
    }
}
