package it.unibo.application.data.entities;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Field;

public class Ram extends Component {
    public int ramId;
    public int ramFrequency;
    public int capacity;
    public String latency;
    public boolean isEcc;
    public String ramGeneration;

    public Ram(int componentId, String componentName, String componentType, int launchYear, float msrp,
            int manufacturerId, int ramId, int ramFrequency, int capacity, String latency, boolean isEcc,
            String ramGeneration) {
        super(componentId, componentName, componentType, launchYear, msrp, manufacturerId);
        this.ramId = ramId;
        this.ramFrequency = ramFrequency;
        this.capacity = capacity;
        this.latency = latency;
        this.isEcc = isEcc;
        this.ramGeneration = ramGeneration;
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
        public static Ram findById(Connection connection, int id) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.FIND_RAM, id);
                var resultSet = statement.executeQuery();
            ) {
                if (resultSet.next()) {
                    var componentId = resultSet.getInt("CodiceComponente");
                    var componentName = resultSet.getString("NomeComponente");
                    var componentType = resultSet.getString("TipoComponente");
                    var launchYear = resultSet.getDate("AnnoLancio").getYear();
                    var msrp = resultSet.getFloat("PrezzoListino");
                    var manufacturerId = resultSet.getInt("CodiceProduttore");
                    var ramId = resultSet.getInt("CodiceRam");
                    var ramFrequency = resultSet.getInt("FrequenzaRam");
                    var capacity = resultSet.getInt("CapienzaRam");
                    var latency = resultSet.getString("Latenza");
                    var isEcc = resultSet.getBoolean("Ecc");
                    var ramGeneration = resultSet.getString("NomeGenerazioneRam");
                    Ram ram = new Ram(componentId, componentName, componentType, launchYear, msrp, manufacturerId, ramId, ramFrequency, capacity, latency, isEcc, ramGeneration);
                    return ram;
                }
                return null;
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
