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

public class Cooler implements Component {
    private final BaseInfo baseInfo;
    private final Map<String, String> specificAttributes;

    public Cooler(final BaseInfo baseInfo, final Map<String, String> specificAttributes) {
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
        public static List<Component> getCoolers(final Connection connection) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.GET_COOLER);
                var resultSet = statement.executeQuery();
            ) {
                final List<Component> coolers = new ArrayList<>();
                while (resultSet.next()) {
                    final var componentName = resultSet.getString("NomeComponente");
                    final var launchYear = resultSet.getDate("AnnoLancio").toLocalDate().getYear();
                    final var msrp = resultSet.getFloat("PrezzoListino");
                    final var manufacturerName = resultSet.getString("NomeProduttore");
                    final var coolerId = resultSet.getInt("CodiceCooler");
                    final var coolerRpm = resultSet.getString("RpmCooler");
                    final var noiseLevel = resultSet.getString("LivelloRumore");
                    final var coolerType = resultSet.getString("TipoCooler");

                    final BaseInfo baseInfo = new BaseInfo(coolerId, componentName, launchYear, msrp, manufacturerName);
                    final Map<String, String> specificAttributes = new HashMap<>();
                    specificAttributes.put(Specs.COOLER_RPM.getKey(), coolerRpm);
                    specificAttributes.put(Specs.COOLER_NOISE_LEVEL.getKey(), noiseLevel);
                    specificAttributes.put(Specs.COOLER_TYPE.getKey(), coolerType);

                    coolers.add(new Cooler(baseInfo, specificAttributes));
                }
                return coolers;
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }

        public static Cooler findById(final Connection connection, final int id) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.FIND_COOLER, id);
                var resultSet = statement.executeQuery();
            ) {
                if (resultSet.next()) {
                    final var componentName = resultSet.getString("NomeComponente");
                    final var launchYear = resultSet.getDate("AnnoLancio").toLocalDate().getYear();
                    final var msrp = resultSet.getFloat("PrezzoListino");
                    final var manufacturerName = resultSet.getString("NomeProduttore");
                    final var coolerId = resultSet.getInt("CodiceCooler");
                    final var coolerRpm = resultSet.getString("RpmCooler");
                    final var noiseLevel = resultSet.getString("LivelloRumore");
                    final var coolerType = resultSet.getString("TipoCooler");

                    final BaseInfo baseInfo = new BaseInfo(coolerId, componentName, launchYear, msrp, manufacturerName);
                    final Map<String, String> specificAttributes = new HashMap<>();
                    specificAttributes.put(Specs.COOLER_RPM.getKey(), coolerRpm);
                    specificAttributes.put(Specs.COOLER_NOISE_LEVEL.getKey(), noiseLevel);
                    specificAttributes.put(Specs.COOLER_TYPE.getKey(), coolerType);

                    return new Cooler(baseInfo, specificAttributes);
                }
                return null;
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
