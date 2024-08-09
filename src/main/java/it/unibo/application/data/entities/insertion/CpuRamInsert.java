package it.unibo.application.data.entities.insertion;

import java.sql.Connection;
import java.sql.SQLException;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;

public class CpuRamInsert {
    private String ramGen;
    private int cpuId;

    public CpuRamInsert(String ramGen, int cpuId) {
        this.ramGen = ramGen;
        this.cpuId = cpuId;
    }

    public String getRamGen() {
        return ramGen;
    }

    public int getCpuId() {
        return cpuId;
    }

    public final class DAO {
        public static void insert(final Connection connection, final CpuRamInsert cpuRamInsert) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.INSERT_CPU_RAM_COMPATIBILITY,
                    cpuRamInsert.getRamGen(), cpuRamInsert.getCpuId())
            ) {
                statement.executeUpdate();
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
