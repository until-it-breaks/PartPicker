package it.unibo.application.data.entities.builds;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;

public class RamUsage {
    private int buildId;
    private int ramId;
    private int quantity;

    public RamUsage(int buildId, int ramId, int quantity) {
        this.buildId = buildId;
        this.ramId = ramId;
        this.quantity = quantity;
    }

    public int getBuildId() {
        return buildId;
    }

    public int getRamId() {
        return ramId;
    }

    public int getQuantity() {
        return quantity;
    }

    public final class DAO {
        public static void insertRamUsage(final Connection connection, final RamUsage ramUsage) {
            try (
                    var statement = DAOUtils.prepare(connection, Queries.INSERT_RAM_USAGE, ramUsage.getBuildId(), ramUsage.getRamId(), ramUsage.getQuantity());
                ) {
                    statement.executeUpdate();
                } catch (final SQLException e) {
                    throw new DAOException(e);
            }
        }
    }
}
