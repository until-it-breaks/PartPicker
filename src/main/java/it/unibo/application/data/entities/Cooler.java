package it.unibo.application.data.entities;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;

public class Cooler {
    public int coolerId;
    public int coolerRpm;
    public float noiseLevel;
    public String coolerType;

    public Cooler(int coolerId, int coolerRpm, float noiseLevel, String coolerType) {
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
                    var coolerId = resultSet.getInt("CodiceCooler");
                    var coolerRpm = resultSet.getInt("RpmCooler");
                    var noiseLevel = resultSet.getFloat("LivelloRumore");
                    var coolerType = resultSet.getString("TipoCooler");
                    Cooler cooler = new Cooler(coolerId, coolerRpm, noiseLevel, coolerType);
                    return cooler;
                }
                return null;
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
