package it.unibo.application.data.entities.components;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import it.unibo.application.data.entities.enums.Specs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Collections;

public class Cpu implements Component {
    private final BaseInfo baseInfo;
    private final Map<Specs, String> specificAttributes;

    public Cpu(final BaseInfo baseInfo, final Map<Specs, String> specificAttributes) {
        this.baseInfo = baseInfo;
        this.specificAttributes = specificAttributes;
    }

    public BaseInfo getBaseInfo() {
        return baseInfo;
    }

    public Map<Specs, String> getSpecificAttributes() {
        return specificAttributes;
    }

    @Override
    public String toString() {
        return baseInfo.getName();
    }

    @Override
    public Map<String, String> getFormattedAttributes() {
        final Map<String, String> map = new LinkedHashMap<>();
        map.put("CPU Family", specificAttributes.get(Specs.CPU_FAMILY).toString());
        map.put("Core Count", specificAttributes.get(Specs.CPU_CORE_COUNT).toString());
        map.put("Frequency", specificAttributes.get(Specs.CPU_FREQUENCY) + " GHz");
        map.put("TDP", specificAttributes.get(Specs.CPU_TDP) + "w");
        map.put("SMT", specificAttributes.get(Specs.CPU_SMT).toString());
        map.put("Socket", specificAttributes.get(Specs.CPU_SOCKET_NAME).toString());
        return Collections.unmodifiableMap(map);
    }

    public final class DAO {

        public static List<Component> getCpus(final Connection connection) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.GET_CPUS);
                var resultSet = statement.executeQuery();
            ) {
                final List<Component> cpus = new ArrayList<>();
                while (resultSet.next()) {
                    cpus.add(createCpuFromResultSet(resultSet));
                }
                return cpus;
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }

        public static Cpu findById(final Connection connection, final int id) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.FIND_CPU, id);
                var resultSet = statement.executeQuery();
            ) {
                if (resultSet.next()) {
                    return createCpuFromResultSet(resultSet);
                }
                return null;
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }

        private static Cpu createCpuFromResultSet(final ResultSet resultSet) throws SQLException {
            final var componentName = resultSet.getString(Specs.COMPONENT_NAME.getKey());
            final var launchYear = resultSet.getDate(Specs.COMPONENT_LAUNCH_YEAR.getKey()).toLocalDate().getYear();
            final var msrp = resultSet.getFloat(Specs.COMPONENT_MSRP.getKey());
            final var manufacturerName = resultSet.getString(Specs.COMPONENT_MANUFACTURER.getKey());
            final var cpuId = resultSet.getInt(Specs.COMPONENT_ID.getKey());
            final var cpuFamily = resultSet.getString(Specs.CPU_FAMILY.getKey());
            final var coreCount = resultSet.getString(Specs.CPU_CORE_COUNT.getKey());
            final var cpuFrequency = resultSet.getString(Specs.CPU_FREQUENCY.getKey());
            final var tdp = resultSet.getString(Specs.CPU_TDP.getKey());
            final var hasSmt = (resultSet.getBoolean(Specs.CPU_SMT.getKey()) == true) ? "Yes" : "No";
            final var socketName = resultSet.getString(Specs.CPU_SOCKET_NAME.getKey());

            final BaseInfo baseInfo = new BaseInfo(cpuId, componentName, launchYear, msrp, manufacturerName);
            final Map<Specs, String> specificAttributes = new HashMap<>();
            specificAttributes.put(Specs.CPU_FAMILY, cpuFamily);
            specificAttributes.put(Specs.CPU_CORE_COUNT, coreCount);
            specificAttributes.put(Specs.CPU_FREQUENCY, cpuFrequency);
            specificAttributes.put(Specs.CPU_TDP, tdp);
            specificAttributes.put(Specs.CPU_SMT, hasSmt);
            specificAttributes.put(Specs.CPU_SOCKET_NAME, socketName);

            return new Cpu(baseInfo, specificAttributes);
        }
    }
}
