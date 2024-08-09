package it.unibo.application.data.entities.insertion;

import java.sql.Connection;
import java.sql.SQLException;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;

public class GpuInsert {
    private int id;
    private String family;
    private String memoryType;
    private int memoryAmount;
    private int frequency;
    private int tgp;

    public GpuInsert(int id, String family, String memoryType, int memoryAmount, int frequency, int tgp) {
        this.id = id;
        this.family = family;
        this.memoryType = memoryType;
        this.memoryAmount = memoryAmount;
        this.frequency = frequency;
        this.tgp = tgp;
    }

    public int getId() {
        return id;
    }

    public String getFamily() {
        return family;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public int getMemoryAmount() {
        return memoryAmount;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getTgp() {
        return tgp;
    }

    public final class DAO {
        public static void insert(final Connection connection, GpuInsert gpu) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.INSERT_GPU,
                    gpu.getId(), gpu.getFamily(), gpu.getMemoryType(),
                    gpu.getMemoryAmount(), gpu.getFrequency(), gpu.getTgp())
            ) {
                statement.executeUpdate();
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
