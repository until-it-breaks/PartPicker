package it.unibo.application.data.entities.insertion;

import java.sql.Connection;
import java.sql.SQLException;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;

public class StorageInsert {
    private int id;
    private int capacity;
    private int rpm;
    private int cacheAmount;
    private String type;

    public StorageInsert(int id, int capacity, int rpm, int cacheAmount, String type) {
        this.id = id;
        this.capacity = capacity;
        this.rpm = rpm;
        this.cacheAmount = cacheAmount;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRpm() {
        return rpm;
    }

    public int getCacheAmount() {
        return cacheAmount;
    }

    public String getType() {
        return type;
    }

    public final class DAO {
        public static void insert(final Connection connection, StorageInsert storage) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.INSERT_STORAGE,
                    storage.getId(), storage.getCapacity(), storage.getRpm(),
                    storage.getCacheAmount(), storage.getType())
            ) {
                statement.executeUpdate();
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
