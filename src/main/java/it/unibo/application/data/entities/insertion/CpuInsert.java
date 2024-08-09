package it.unibo.application.data.entities.insertion;

import java.sql.Connection;
import java.sql.SQLException;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;

public class CpuInsert {

    private final int id;
    private final String family;
    private final int coreCount;
    private final float frequency;
    private final int tdp;
    private final boolean smt;
    private final String socketName;

    public CpuInsert(final int id, final String family, final int coreCount, final float frequency, final int tdp, final boolean smt, final String socketName) {
        this.id = id;
        this.family = family;
        this.coreCount = coreCount;
        this.frequency = frequency;
        this.tdp = tdp;
        this.smt = smt;
        this.socketName = socketName;
    }

    public int getId() {
        return id;
    }

    public String getFamily() {
        return family;
    }

    public int getCoreCount() {
        return coreCount;
    }

    public float getFrequency() {
        return frequency;
    }

    public int getTdp() {
        return tdp;
    }

    public boolean isSmt() {
        return smt;
    }

    public String getSocketName() {
        return socketName;
    }

    public final class DAO {
        public static void insert(final Connection connection, final CpuInsert cpu) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.INSERT_CPU,
                    cpu.getId(), cpu.getFamily(), cpu.getCoreCount(),
                    cpu.getFrequency(), cpu.getTdp(), cpu.isSmt(), cpu.getSocketName());
            ) {
                statement.executeUpdate();
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
