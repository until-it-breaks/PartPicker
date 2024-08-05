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

public class Cpu implements Component {
    private final BaseInfo baseInfo;
    private final Map<String, String> specificAttributes;

    public Cpu(final BaseInfo baseInfo, final Map<String, String> specificAttributes) {
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

        public static List<Component> getCpus(final Connection connection) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.GET_CPUS);
                var resultSet = statement.executeQuery();
            ) {
                final List<Component> cpus = new ArrayList<>();
                while (resultSet.next()) {
                    final var componentName = resultSet.getString("NomeComponente");
                    final var launchYear = resultSet.getDate("AnnoLancio").toLocalDate().getYear();
                    final var msrp = resultSet.getFloat("PrezzoListino");
                    final var manufacturerName = resultSet.getString("NomeProduttore");
                    final var cpuId = resultSet.getInt("CodiceCpu");
                    final var cpuFamily = resultSet.getString("FamigliaCpu");
                    final var coreCount = resultSet.getString("NumeroCore");
                    final var cpuFrequency = resultSet.getString("FrequenzaCpu");
                    final var tdp = resultSet.getString("Tdp");
                    final var hasSmt = resultSet.getString("Smt");
                    final var socketName = resultSet.getString("NomeSocket");

                    final BaseInfo baseInfo = new BaseInfo(cpuId, componentName, launchYear, msrp, manufacturerName);
                    final Map<String, String> specificAttributes = new HashMap<>();
                    specificAttributes.put(Specs.CPU_FAMILY.getKey(), cpuFamily);
                    specificAttributes.put(Specs.CPU_CORE_COUNT.getKey(), coreCount);
                    specificAttributes.put(Specs.CPU_FREQUENCY.getKey(), cpuFrequency);
                    specificAttributes.put(Specs.CPU_TDP.getKey(), tdp);
                    specificAttributes.put(Specs.CPU_SMT.getKey(), hasSmt);
                    specificAttributes.put(Specs.CPU_SOCKET_NAME.getKey(), socketName);
                    cpus.add(new Cpu(baseInfo, specificAttributes));
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
                    final var componentName = resultSet.getString("NomeComponente");
                    final var launchYear = resultSet.getDate("AnnoLancio").toLocalDate().getYear();
                    final var msrp = resultSet.getFloat("PrezzoListino");
                    final var manufacturerName = resultSet.getString("NomeProduttore");
                    final var cpuId = resultSet.getInt("CodiceCpu");
                    final var cpuFamily = resultSet.getString("FamigliaCpu");
                    final var coreCount = resultSet.getString("NumeroCore");
                    final var cpuFrequency = resultSet.getString("FrequenzaCpu");
                    final var tdp = resultSet.getString("Tdp");
                    final var hasSmt = resultSet.getString("Smt");
                    final var socketName = resultSet.getString("NomeSocket");

                    final BaseInfo baseInfo = new BaseInfo(cpuId, componentName, launchYear, msrp, manufacturerName);
                    final Map<String, String> specificAttributes = new HashMap<>();
                    specificAttributes.put(Specs.CPU_FAMILY.getKey(), cpuFamily);
                    specificAttributes.put(Specs.CPU_CORE_COUNT.getKey(), coreCount);
                    specificAttributes.put(Specs.CPU_FREQUENCY.getKey(), cpuFrequency);
                    specificAttributes.put(Specs.CPU_TDP.getKey(), tdp);
                    specificAttributes.put(Specs.CPU_SMT.getKey(), hasSmt);
                    specificAttributes.put(Specs.CPU_SOCKET_NAME.getKey(), socketName);

                    return new Cpu(baseInfo, specificAttributes);
                }
                return null;
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
