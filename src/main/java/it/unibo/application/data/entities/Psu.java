package it.unibo.application.data.entities;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;

public class Psu {
    public int psuId;
    public String formFactor;
    public String efficiency;
    public int wattage;
    public String modularity;

    public Psu(int psuId, String formFactor, String efficiency, int wattage, String modularity) {
        this.psuId = psuId;
        this.formFactor = formFactor;
        this.efficiency = efficiency;
        this.wattage = wattage;
        this.modularity = modularity;
    }

    public final class DAO {
    public static Psu findById(Connection connection, int id) {
        try (
            var statement = DAOUtils.prepare(connection, Queries.FIND_PSU, id);
            var resultSet = statement.executeQuery();
        ) {
            if (resultSet.next()) {
                var psuId = resultSet.getInt("CodicePsu");
                var formFactor = resultSet.getString("FattoreFormaPsu");
                var efficiency = resultSet.getString("Efficienza");
                var wattage = resultSet.getInt("Wattaggio");
                var modularity = resultSet.getString("Modularita");
                Psu psu = new Psu(psuId, formFactor, efficiency, wattage, modularity);
                return psu;
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
    }
}
