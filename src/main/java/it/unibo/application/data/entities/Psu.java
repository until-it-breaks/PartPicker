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

public class Psu implements Component {
    private final BaseInfo baseInfo;
    private final Map<String, String> specificAttributes;

    public Psu(final BaseInfo baseInfo, final Map<String, String> specificAttributes) {
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

        public static List<Component> getPsus(final Connection connection) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.GET_PSU);
                var resultSet = statement.executeQuery();
            ) {
                final List<Component> psus = new ArrayList<>();
                while (resultSet.next()) {
                    final var componentName = resultSet.getString("NomeComponente");
                    final var launchYear = resultSet.getDate("AnnoLancio").toLocalDate().getYear();
                    final var msrp = resultSet.getFloat("PrezzoListino");
                    final var manufacturerName = resultSet.getString("NomeProduttore");

                    final var psuId = resultSet.getInt("CodicePsu");
                    final var formFactor = resultSet.getString("FattoreFormaPsu");
                    final var efficiency = resultSet.getString("Efficienza");
                    final var wattage = resultSet.getString("Wattaggio");
                    final var modularity = resultSet.getString("Modularita");

                    final BaseInfo baseInfo = new BaseInfo(psuId, componentName, launchYear, msrp, manufacturerName);
                    final Map<String, String> specificAttributes = new HashMap<>();
                    specificAttributes.put(Specs.PSU_FORM_FACTOR.getKey(), formFactor);
                    specificAttributes.put(Specs.PSU_EFFICIENCY.getKey(), efficiency);
                    specificAttributes.put(Specs.PSU_WATTAGE.getKey(), wattage);
                    specificAttributes.put(Specs.PSU_MODULARITY.getKey(), modularity);

                    psus.add(new Psu(baseInfo, specificAttributes));
                }
                return psus;
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }

        public static Psu findById(final Connection connection, final int id) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.FIND_PSU, id);
                var resultSet = statement.executeQuery();
            ) {
                if (resultSet.next()) {
                    final var componentName = resultSet.getString("NomeComponente");
                    final var launchYear = resultSet.getDate("AnnoLancio").toLocalDate().getYear();
                    final var msrp = resultSet.getFloat("PrezzoListino");
                    final var manufacturerName = resultSet.getString("NomeProduttore");

                    final var psuId = resultSet.getInt("CodicePsu");
                    final var formFactor = resultSet.getString("FattoreFormaPsu");
                    final var efficiency = resultSet.getString("Efficienza");
                    final var wattage = resultSet.getString("Wattaggio");
                    final var modularity = resultSet.getString("Modularita");

                    final BaseInfo baseInfo = new BaseInfo(psuId, componentName, launchYear, msrp, manufacturerName);
                    final Map<String, String> specificAttributes = new HashMap<>();
                    specificAttributes.put(Specs.PSU_FORM_FACTOR.getKey(), formFactor);
                    specificAttributes.put(Specs.PSU_EFFICIENCY.getKey(), efficiency);
                    specificAttributes.put(Specs.PSU_WATTAGE.getKey(), wattage);
                    specificAttributes.put(Specs.PSU_MODULARITY.getKey(), modularity);

                    return new Psu(baseInfo, specificAttributes);
                }
                return null;
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
