package it.unibo.application.data.entities;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import it.unibo.application.model.enums.Specs;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

public class Gpu implements Component {
    private final BaseInfo baseInfo;
    private final Map<String, String> specificAttributes;

    public Gpu(final BaseInfo baseInfo, final Map<String, String> specificAttributes) {
        this.baseInfo = baseInfo;
        this.specificAttributes = specificAttributes;
    }

    public BaseInfo getBaseInfo() {
        return baseInfo;
    }

    public Map<String, String> getSpecificAttributes() {
        return specificAttributes;
    }

    public final class DAO {
        public static List<Component> getGpus(final Connection connection) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.GET_GPUS);
                var resultSet = statement.executeQuery();
            ) {
                final List<Component> gpus = new ArrayList<>();
                while (resultSet.next()) {
                    final var componentName = resultSet.getString("NomeComponente");
                    final var launchYear = resultSet.getDate("AnnoLancio").toLocalDate().getYear();
                    final var msrp = resultSet.getFloat("PrezzoListino");
                    final var manufacturerName = resultSet.getString("NomeProduttore");

                    final var gpuId = resultSet.getInt("CodiceGpu");
                    final var gpuFamily = resultSet.getString("FamigliaGpu");
                    final var gpuMemoryType = resultSet.getString("TipoMemoriaGpu");
                    final var gpuMemoryAmount = resultSet.getString("QuantitaMemoriaGpu");
                    final var gpuFrequency = resultSet.getString("FrequenzaGpu");
                    final var tgp = resultSet.getString("Tgp");

                    final BaseInfo baseInfo = new BaseInfo(gpuId, componentName, launchYear, msrp, manufacturerName);
                    final Map<String, String> specificAttributes = new HashMap<>();
                    specificAttributes.put(Specs.GPU_FAMILY.getKey(), gpuFamily);
                    specificAttributes.put(Specs.GPU_MEMORY_TYPE.getKey(), gpuMemoryType);
                    specificAttributes.put(Specs.GPU_MEMORY_AMOUNT.getKey(), gpuMemoryAmount);
                    specificAttributes.put(Specs.GPU_FREQUENCY.getKey(), gpuFrequency);
                    specificAttributes.put(Specs.GPU_TGP.getKey(), tgp);

                    gpus.add(new Gpu(baseInfo, specificAttributes));
                }
                return gpus;
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }

        public static Gpu findById(final Connection connection, final int id) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.FIND_GPU, id);
                var resultSet = statement.executeQuery();
            ) {
                if (resultSet.next()) {
                    final var componentName = resultSet.getString("NomeComponente");
                    final var launchYear = resultSet.getDate("AnnoLancio").toLocalDate().getYear();
                    final var msrp = resultSet.getFloat("PrezzoListino");
                    final var manufacturerName = resultSet.getString("NomeProduttore");

                    final var gpuId = resultSet.getInt("CodiceGpu");
                    final var gpuFamily = resultSet.getString("FamigliaGpu");
                    final var gpuMemoryType = resultSet.getString("TipoMemoriaGpu");
                    final var gpuMemoryAmount = resultSet.getString("QuantitaMemoriaGpu");
                    final var gpuFrequency = resultSet.getString("FrequenzaGpu");
                    final var tgp = resultSet.getString("Tgp");

                    final BaseInfo baseInfo = new BaseInfo(gpuId, componentName, launchYear, msrp, manufacturerName);
                    final Map<String, String> specificAttributes = new HashMap<>();
                    specificAttributes.put(Specs.GPU_FAMILY.getKey(), gpuFamily);
                    specificAttributes.put(Specs.GPU_MEMORY_TYPE.getKey(), gpuMemoryType);
                    specificAttributes.put(Specs.GPU_MEMORY_AMOUNT.getKey(), gpuMemoryAmount);
                    specificAttributes.put(Specs.GPU_FREQUENCY.getKey(), gpuFrequency);
                    specificAttributes.put(Specs.GPU_TGP.getKey(), tgp);

                    return new Gpu(baseInfo, specificAttributes);
                }
                return null;
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
