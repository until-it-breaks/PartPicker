package it.unibo.application.data.entities;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Field;

public class Cpu extends Component {

    public int cpuId;
    public String cpuFamily;
    public int coreCount;
    public int cpuFrequency;
    public int tdp;
    public boolean hasSmt;
    public String socketName;

    public Cpu(int componentId, String componentName, String componentType, int launchYear, float msrp,
            int manufacturerId, int cpuId, String cpuFamily, int coreCount, int cpuFrequency, int tdp, boolean hasSmt,
            String socketName) {
        super(componentId, componentName, componentType, launchYear, msrp, manufacturerId);
        this.cpuId = cpuId;
        this.cpuFamily = cpuFamily;
        this.coreCount = coreCount;
        this.cpuFrequency = cpuFrequency;
        this.tdp = tdp;
        this.hasSmt = hasSmt;
        this.socketName = socketName;
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
        public static Cpu findById(Connection connection, int id) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.FIND_CPU, id);
                var resultSet = statement.executeQuery();
            ) {
                if (resultSet.next()) {
                    var componentId = resultSet.getInt("CodiceComponente");
                    var componentName = resultSet.getString("NomeComponente");
                    var componentType = resultSet.getString("TipoComponente");
                    var launchYear = resultSet.getDate("AnnoLancio").getYear();
                    var msrp = resultSet.getFloat("PrezzoListino");
                    var manufacturerId = resultSet.getInt("CodiceProduttore");
                    var cpuId = resultSet.getInt("CodiceCpu");
                    var cpuFamily = resultSet.getString("FamigliaCpu");
                    var coreCount = resultSet.getInt("NumeroCore");
                    var cpuFrequency = resultSet.getInt("FrequenzaCpu");
                    var tdp = resultSet.getInt("Tdp");
                    var hasSmt = resultSet.getBoolean("Smt");
                    var socketName = resultSet.getString("NomeSocket");
                    Cpu cpu = new Cpu(componentId, componentName, componentType, launchYear, msrp, manufacturerId, cpuId, cpuFamily, coreCount, cpuFrequency, tdp, hasSmt, socketName);
                    return cpu;
                }
                return null;
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
