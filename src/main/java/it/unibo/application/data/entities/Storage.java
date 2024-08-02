package it.unibo.application.data.entities;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;

public class Storage {
    public int storageId;
    public int storageCapacity;
    public int storageRpm;
    public int cacheAmount;
    public String storageType;

    public Storage(int storageId, int storageCapacity, int storageRpm, int cacheAmount, String storageType) {
        this.storageId = storageId;
        this.storageCapacity = storageCapacity;
        this.storageRpm = storageRpm;
        this.cacheAmount = cacheAmount;
        this.storageType = storageType;
    }

    public final class DAO {
    public static Storage findById(Connection connection, int id) {
        try (
            var statement = DAOUtils.prepare(connection, Queries.FIND_STORAGE, id);
            var resultSet = statement.executeQuery();
        ) {
            if (resultSet.next()) {
                var storageId = resultSet.getInt("CodiceStorage");
                var storageCapacity = resultSet.getInt("CapienzaStorage");
                var storageRpm = resultSet.getInt("RpmStorage");
                var cacheAmount = resultSet.getInt("QuantitaCache");
                var storageType = resultSet.getString("TipoStorage");
                Storage storage = new Storage(storageId, storageCapacity, storageRpm, cacheAmount, storageType);
                return storage;
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
    }
}
