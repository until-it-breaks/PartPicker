package it.unibo.application.data.entities.components;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import it.unibo.application.data.entities.BaseInfo;
import it.unibo.application.data.entities.enums.Specs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

public class Gpu implements Component {
    private final BaseInfo baseInfo;
    private final Map<Specs, String> specificAttributes;

    public Gpu(final BaseInfo baseInfo, final Map<Specs, String> specificAttributes) {
        this.baseInfo = baseInfo;
        this.specificAttributes = specificAttributes;
    }

    public BaseInfo getBaseInfo() {
        return baseInfo;
    }

    public Map<Specs, String> getSpecificAttributes() {
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
                    gpus.add(createGpuFromResultSet(resultSet));
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
                    return createGpuFromResultSet(resultSet);
                }
                return null;
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }

        private static Gpu createGpuFromResultSet(final ResultSet resultSet) throws SQLException {
            final var componentName = resultSet.getString(Specs.COMPONENT_NAME.getKey());
            final var launchYear = resultSet.getDate(Specs.COMPONENT_LAUNCH_YEAR.getKey()).toLocalDate().getYear();
            final var msrp = resultSet.getFloat(Specs.COMPONENT_MSRP.getKey());
            final var manufacturerName = resultSet.getString(Specs.COMPONENT_MANUFACTURER.getKey());
            final var gpuId = resultSet.getInt(Specs.COMPONENT_ID.getKey());
            final var gpuFamily = resultSet.getString(Specs.GPU_FAMILY.getKey());
            final var gpuMemoryType = resultSet.getString(Specs.GPU_MEMORY_TYPE.getKey());
            final var gpuMemoryAmount = resultSet.getString(Specs.GPU_MEMORY_AMOUNT.getKey());
            final var gpuFrequency = resultSet.getString(Specs.GPU_FREQUENCY.getKey());
            final var tgp = resultSet.getString(Specs.GPU_TGP.getKey());

            final BaseInfo baseInfo = new BaseInfo(gpuId, componentName, launchYear, msrp, manufacturerName);
            final Map<Specs, String> specificAttributes = new HashMap<>();
            specificAttributes.put(Specs.GPU_FAMILY, gpuFamily);
            specificAttributes.put(Specs.GPU_MEMORY_TYPE, gpuMemoryType);
            specificAttributes.put(Specs.GPU_MEMORY_AMOUNT, gpuMemoryAmount);
            specificAttributes.put(Specs.GPU_FREQUENCY, gpuFrequency);
            specificAttributes.put(Specs.GPU_TGP, tgp);

            return new Gpu(baseInfo, specificAttributes);
        }
    }
}
