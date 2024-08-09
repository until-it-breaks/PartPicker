package it.unibo.application.data.entities.insertion;

import java.sql.Connection;
import java.sql.SQLException;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;

public class RamInsert {
    private int id;
    private int frequency;
    private int capacity;
    private String latency;
    private boolean isEcc;
    private String ramGen;

    public RamInsert(int id, int frequency, int capacity, String latency, boolean isEcc, String ramGen) {
        this.id = id;
        this.frequency = frequency;
        this.capacity = capacity;
        this.latency = latency;
        this.isEcc = isEcc;
        this.ramGen = ramGen;
    }

    public int getId() {
        return id;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getLatency() {
        return latency;
    }

    public boolean isEcc() {
        return isEcc;
    }

    public String getRamGen() {
        return ramGen;
    }

    public final class DAO {
        public static void insert(final Connection connection, RamInsert ram) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.INSERT_RAM,
                    ram.getId(), ram.getFrequency(), ram.getCapacity(),
                    ram.getLatency(), ram.isEcc(), ram.getRamGen());
            ) {
                statement.executeUpdate();
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
