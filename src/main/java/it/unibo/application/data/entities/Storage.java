package it.unibo.application.data.entities;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Field;

public class Storage extends Component {
    public int storageId;
    public int storageCapacity;
    public int storageRpm;
    public int cacheAmount;
    public String storageType;

    public Storage(int componentId, String componentName, String componentType, int launchYear, float msrp,
            int manufacturerId, int storageId, int storageCapacity, int storageRpm, int cacheAmount,
            String storageType) {
        super(componentId, componentName, componentType, launchYear, msrp, manufacturerId);
        this.storageId = storageId;
        this.storageCapacity = storageCapacity;
        this.storageRpm = storageRpm;
        this.cacheAmount = cacheAmount;
        this.storageType = storageType;
    }

    public Map<String, String> toStringMap() {
        Map<String, String> map = new HashMap<>();
        Field[] fields = this.getClass().getFields();

        for (Field field : fields) {
            try {
                Object value = field.get(this);
                map.put(field.getName(), value != null ? value.toString() : "null");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public final class DAO {
    public static Storage findById(Connection connection, int id) {
        try (
            var statement = DAOUtils.prepare(connection, Queries.FIND_STORAGE, id);
            var resultSet = statement.executeQuery();
        ) {
            if (resultSet.next()) {
                var componentId = resultSet.getInt("CodiceComponente");
                var componentName = resultSet.getString("NomeComponente");
                var componentType = resultSet.getString("TipoComponente");
                var launchYear = resultSet.getDate("AnnoLancio").getYear();
                var msrp = resultSet.getFloat("PrezzoListino");
                var manufacturerId = resultSet.getInt("CodiceProduttore");
                var storageId = resultSet.getInt("CodiceStorage");
                var storageCapacity = resultSet.getInt("CapienzaStorage");
                var storageRpm = resultSet.getInt("RpmStorage");
                var cacheAmount = resultSet.getInt("QuantitaCache");
                var storageType = resultSet.getString("TipoStorage");
                Storage storage = new Storage(componentId, componentName, componentType, launchYear, msrp, manufacturerId, storageId, storageCapacity, storageRpm, cacheAmount, storageType);
                return storage;
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
    }
}
