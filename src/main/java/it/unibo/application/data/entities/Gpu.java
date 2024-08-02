package it.unibo.application.data.entities;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;

public class Gpu {
    public int gpuId;
    public String gpuFamily;
    public String gpuMemoryType;
    public int gpuMemoryAmount;
    public int gpuFrequency;
    public int tgp;

    public Gpu(int gpuId, String gpuFamily, String gpuMemoryType, int gpuMemoryAmount, int gpuFrequency, int tgp) {
        this.gpuId = gpuId;
        this.gpuFamily = gpuFamily;
        this.gpuMemoryType = gpuMemoryType;
        this.gpuMemoryAmount = gpuMemoryAmount;
        this.gpuFrequency = gpuFrequency;
        this.tgp = tgp;
    }

    public final class DAO {
        public static Gpu findById(Connection connection, int id) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.FIND_GPU, id);
                var resultSet = statement.executeQuery();
            ) {
                if (resultSet.next()) {
                    var gpuId = resultSet.getInt("CodiceGpu");
                    var gpuFamily = resultSet.getString("FamigliaGpu");
                    var gpuMemoryType = resultSet.getString("TipoMemoriaGpu");
                    var gpuMemoryAmount = resultSet.getInt("QuantitaMemoria");
                    var gpuFrequency = resultSet.getInt("FrequenzaGpu");
                    var tgp = resultSet.getInt("Tgp");
                    Gpu gpu = new Gpu(gpuId, gpuFamily, gpuMemoryType, gpuMemoryAmount, gpuFrequency, tgp);
                    return gpu;
                }
                return null;
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
