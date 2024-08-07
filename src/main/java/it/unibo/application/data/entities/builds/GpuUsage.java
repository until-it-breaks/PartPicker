package it.unibo.application.data.entities.builds;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import it.unibo.application.data.entities.components.Gpu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class GpuUsage {
    private final int buildId;
    private final int gpuId;
    private final int quantity;

    public GpuUsage(final int buildId, final int gpuId, final int quantity) {
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
                    var statement = DAOUtils.prepare(connection, Queries.INSERT_GPU_USAGE,
                        gpuUsage.getBuildId(), gpuUsage.getGpuId(),
                        gpuUsage.getQuantity());
                ) {
                    statement.executeUpdate();
                } catch (final SQLException e) {
                    throw new DAOException(e);
            }
        }

        public static List<Gpu> getUsedGpus(final Connection connection, final int buildId) {
            try (
                    var statement = DAOUtils.prepare(connection, Queries.FIND_USED_GPUS, buildId);
                    var resultSet = statement.executeQuery();
                ) {
                    final List<Gpu> gpus = new ArrayList<>();
                    while (resultSet.next()) {
                        final var gpuId = resultSet.getInt("CodiceGpu");
                        final var quantity = resultSet.getInt("Quantita");
                        final var gpu = Gpu.DAO.findById(connection, gpuId);
                        for (int i = 0; i < quantity; i++) {
                            gpus.add(gpu);
                        } 
                    }
                    return gpus;
                } catch (final SQLException e) {
                    throw new DAOException(e);
            }
        }
    }
}
