package it.unibo.application.data.entities;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Field;

public class Case extends Component {
    public int caseId;
    public String formFactor;

    public Case(int componentId, String componentName, String componentType, int launchYear, float msrp,
            int manufacturerId, int caseId, String formFactor) {
        super(componentId, componentName, componentType, launchYear, msrp, manufacturerId);
        this.caseId = caseId;
        this.formFactor = formFactor;
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
        public static Case findById(Connection connection, int id) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.FIND_CASE, id);
                var resultSet = statement.executeQuery();
            ) {
                if (resultSet.next()) {
                    var componentId = resultSet.getInt("CodiceComponente");
                    var componentName = resultSet.getString("NomeComponente");
                    var componentType = resultSet.getString("TipoComponente");
                    var launchYear = resultSet.getDate("AnnoLancio").getYear();
                    var msrp = resultSet.getFloat("PrezzoListino");
                    var manufacturerId = resultSet.getInt("CodiceProduttore");
                    var caseId = resultSet.getInt("CodiceCase");
                    var formFactor = resultSet.getString("FattoreFormaCase");
                    Case _case = new Case(componentId, componentName, componentType, launchYear, msrp, manufacturerId, caseId, formFactor);
                    return _case;
                }
                return null;
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
