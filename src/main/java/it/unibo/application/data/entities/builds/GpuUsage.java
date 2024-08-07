package it.unibo.application.data.entities.builds;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;

public class GpuUsage {
    private int buildId;
    private int gpuId;
    private int quantity;

    public GpuUsage(int buildId, int gpuId, int quantity) {
        this.buildId = buildId;
        this.gpuId = gpuId;
        this.quantity = quantity;
    }

    public int getBuildId() {
        return buildId;
    }

    public int getGpuId() {
        return gpuId;
    }

    public int getQuantity() {
        return quantity;
    }

    public final class DAO {
        public static void insertGpuUsage(final Connection connection, final GpuUsage gpuUsage) {
            try (
                    var statement = DAOUtils.prepare(connection, Queries.INSERT_GPU_USAGE, gpuUsage.getBuildId(), gpuUsage.getGpuId(), gpuUsage.getQuantity());
                ) {
                    statement.executeUpdate();
                } catch (final SQLException e) {
                    throw new DAOException(e);
            }
        }
    }
}
