package it.unibo.application.data.entities.builds;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import it.unibo.application.data.entities.components.Storage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

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
                var statement = DAOUtils.prepare(connection, Queries.INSERT_STORAGE_USAGE,
                    storageUsage.getBuildId(), storageUsage.getStorageId(),
                    storageUsage.getQuantity());
                ) {
                    statement.executeUpdate();
                } catch (final SQLException e) {
                    throw new DAOException(e);
            }
        }

        public static List<Storage> getUsedStorage(final Connection connection, final int buildId) {
            try (
                    var statement = DAOUtils.prepare(connection, Queries.FIND_USED_STORAGE, buildId);
                    var resultSet = statement.executeQuery();
                ) {
                    final List<Storage> storages = new ArrayList<>();
                    while (resultSet.next()) {
                        final var storageId = resultSet.getInt("CodiceStorage");
                        final var quantity = resultSet.getInt("Quantita");
                        final var storage = Storage.DAO.findById(connection, storageId);
                        for (int i = 0; i < quantity; i++) {
                            storages.add(storage);
                        } 
                    }
                    return storages;
                } catch (final SQLException e) {
                    throw new DAOException(e);
            }
        }
    }
}
