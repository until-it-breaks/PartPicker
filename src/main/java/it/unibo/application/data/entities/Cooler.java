package it.unibo.application.data.entities;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;

public class Cooler extends Component {
    public int coolerId;
    public int coolerRpm;
    public float noiseLevel;
    public String coolerType;
    
    public Cooler(int componentId, String componentName, String componentType, int launchYear, float msrp,
            int manufacturerId, int coolerId, int coolerRpm, float noiseLevel, String coolerType) {
        super(componentId, componentName, componentType, launchYear, msrp, manufacturerId);
        this.coolerId = coolerId;
        this.coolerRpm = coolerRpm;
        this.noiseLevel = noiseLevel;
        this.coolerType = coolerType;
    }

    public final class DAO {
        public static Cooler findById(Connection connection, int id) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.FIND_COOLER, id);
                var resultSet = statement.executeQuery();
            ) {
                if (resultSet.next()) {
                    var componentId = resultSet.getInt("CodiceComponente");
                    var componentName = resultSet.getString("NomeComponente");
                    var componentType = resultSet.getString("TipoComponente");
                    var launchYear = resultSet.getDate("AnnoLancio").getYear();
                    var msrp = resultSet.getFloat("PrezzoListino");
                    var manufacturerId = resultSet.getInt("CodiceProduttore");
                    var coolerId = resultSet.getInt("CodiceCooler");
                    var coolerRpm = resultSet.getInt("RpmCooler");
                    var noiseLevel = resultSet.getFloat("LivelloRumore");
                    var coolerType = resultSet.getString("TipoCooler");
                    Cooler cooler = new Cooler(componentId, componentName, componentType, launchYear, msrp, manufacturerId, coolerId, coolerRpm, noiseLevel, coolerType);
                    return cooler;
                }
                return null;
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
