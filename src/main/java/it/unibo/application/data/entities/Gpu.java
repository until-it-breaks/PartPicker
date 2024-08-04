package it.unibo.application.data.entities;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Field;

public class Gpu extends Component {
    public int gpuId;
    public String gpuFamily;
    public String gpuMemoryType;
    public int gpuMemoryAmount;
    public int gpuFrequency;
    public int tgp;
    
    public Gpu(int componentId, String componentName, String componentType, int launchYear, float msrp,
            int manufacturerId, int gpuId, String gpuFamily, String gpuMemoryType, int gpuMemoryAmount,
            int gpuFrequency, int tgp) {
        super(componentId, componentName, componentType, launchYear, msrp, manufacturerId);
        this.gpuId = gpuId;
        this.gpuFamily = gpuFamily;
        this.gpuMemoryType = gpuMemoryType;
        this.gpuMemoryAmount = gpuMemoryAmount;
        this.gpuFrequency = gpuFrequency;
        this.tgp = tgp;
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
        public static Gpu findById(Connection connection, int id) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.FIND_GPU, id);
                var resultSet = statement.executeQuery();
            ) {
                if (resultSet.next()) {
                    var componentId = resultSet.getInt("CodiceComponente");
                    var componentName = resultSet.getString("NomeComponente");
                    var componentType = resultSet.getString("TipoComponente");
                    var launchYear = resultSet.getDate("AnnoLancio").getYear();
                    var msrp = resultSet.getFloat("PrezzoListino");
                    var manufacturerId = resultSet.getInt("CodiceProduttore");
                    var gpuId = resultSet.getInt("CodiceGpu");
                    var gpuFamily = resultSet.getString("FamigliaGpu");
                    var gpuMemoryType = resultSet.getString("TipoMemoriaGpu");
                    var gpuMemoryAmount = resultSet.getInt("QuantitaMemoria");
                    var gpuFrequency = resultSet.getInt("FrequenzaGpu");
                    var tgp = resultSet.getInt("Tgp");
                    Gpu gpu = new Gpu(componentId, componentName, componentType, launchYear, msrp, manufacturerId, gpuId, gpuFamily, gpuMemoryType, gpuMemoryAmount, gpuFrequency, tgp);
                    return gpu;
                }
                return null;
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
