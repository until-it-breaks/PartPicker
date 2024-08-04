package it.unibo.application.data.entities;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Field;

public class Psu extends Component {
    public int psuId;
    public String formFactor;
    public String efficiency;
    public int wattage;
    public String modularity;

    public Psu(int componentId, String componentName, String componentType, int launchYear, float msrp,
            int manufacturerId, int psuId, String formFactor, String efficiency, int wattage, String modularity) {
        super(componentId, componentName, componentType, launchYear, msrp, manufacturerId);
        this.psuId = psuId;
        this.formFactor = formFactor;
        this.efficiency = efficiency;
        this.wattage = wattage;
        this.modularity = modularity;
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
    public static Psu findById(Connection connection, int id) {
        try (
            var statement = DAOUtils.prepare(connection, Queries.FIND_PSU, id);
            var resultSet = statement.executeQuery();
        ) {
            if (resultSet.next()) {
                var componentId = resultSet.getInt("CodiceComponente");
                var componentName = resultSet.getString("NomeComponente");
                var componentType = resultSet.getString("TipoComponente");
                var launchYear = resultSet.getDate("AnnoLancio").getYear();
                var msrp = resultSet.getFloat("PrezzoListino");
                var manufacturerId = resultSet.getInt("CodiceProduttore");
                var psuId = resultSet.getInt("CodicePsu");
                var formFactor = resultSet.getString("FattoreFormaPsu");
                var efficiency = resultSet.getString("Efficienza");
                var wattage = resultSet.getInt("Wattaggio");
                var modularity = resultSet.getString("Modularita");
                Psu psu = new Psu(componentId, componentName, componentType, launchYear, msrp, manufacturerId, psuId, formFactor, efficiency, wattage, modularity);
                return psu;
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
    }
}
