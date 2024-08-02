package it.unibo.application.data.entities;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;

public class Ram {
    public int ramId;
    public int ramFrequency;
    public int capacity;
    public String latency;
    public boolean isEcc;
    public String ramGeneration;

    public Ram(int ramId, int ramFrequency, int capacity, String latency, boolean isEcc, String ramGeneration) {
        this.ramId = ramId;
        this.ramFrequency = ramFrequency;
        this.capacity = capacity;
        this.latency = latency;
        this.isEcc = isEcc;
        this.ramGeneration = ramGeneration;
    }

    public final class DAO {
        public static Ram findById(Connection connection, int id) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.FIND_RAM, id);
                var resultSet = statement.executeQuery();
            ) {
                if (resultSet.next()) {
                    var ramId = resultSet.getInt("CodiceRam");
                    var ramFrequency = resultSet.getInt("FrequenzaRam");
                    var capacity = resultSet.getInt("CapienzaRam");
                    var latency = resultSet.getString("Latenza");
                    var isEcc = resultSet.getBoolean("Ecc");
                    var ramGeneration = resultSet.getString("NomeGenerazioneRam");
                    Ram ram = new Ram(ramId, ramFrequency, capacity, latency, isEcc, ramGeneration);
                    return ram;
                }
                return null;
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
