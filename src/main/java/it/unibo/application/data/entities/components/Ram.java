package it.unibo.application.data.entities.components;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import it.unibo.application.data.entities.BaseInfo;
import it.unibo.application.model.enums.Specs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

public class Ram implements Component {
    private final BaseInfo baseInfo;
    private final Map<String, String> specificAttributes;

    public Ram(final BaseInfo baseInfo, final Map<String, String> specificAttributes) {
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

        public static List<Component> getRams(final Connection connection) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.GET_RAMS);
                var resultSet = statement.executeQuery();
            ) {
                final List<Component> rams = new ArrayList<>();
                while (resultSet.next()) {
                    rams.add(createRamFromResultSet(resultSet));
                }
                return rams;
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }

        public static Ram findById(final Connection connection, final int id) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.FIND_RAM, id);
                var resultSet = statement.executeQuery();
            ) {
                if (resultSet.next()) {
                    return createRamFromResultSet(resultSet);
                }
                return null;
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }

        private static Ram createRamFromResultSet(final ResultSet resultSet) throws SQLException {
            final var componentName = resultSet.getString(Specs.COMPONENT_NAME.getKey());
            final var launchYear = resultSet.getDate(Specs.COMPONENT_LAUNCH_YEAR.getKey()).toLocalDate().getYear();
            final var msrp = resultSet.getFloat(Specs.COMPONENT_MSRP.getKey());
            final var manufacturerName = resultSet.getString(Specs.COMPONENT_MANUFACTURER.getKey());

            final var ramId = resultSet.getInt(Specs.COMPONENT_ID.getKey());
            final var ramFrequency = resultSet.getString(Specs.RAM_FREQUENCY.getKey());
            final var capacity = resultSet.getString(Specs.RAM_CAPACITY.getKey());
            final var latency = resultSet.getString(Specs.RAM_LATENCY.getKey());
            final var isEcc = resultSet.getString(Specs.RAM_ECC.getKey());
            final var ramGeneration = resultSet.getString(Specs.RAM_GEN.getKey());

            final BaseInfo baseInfo = new BaseInfo(ramId, componentName, launchYear, msrp, manufacturerName);
            final Map<String, String> specificAttributes = new HashMap<>();
            specificAttributes.put(Specs.RAM_FREQUENCY.getKey(), ramFrequency);
            specificAttributes.put(Specs.RAM_CAPACITY.getKey(), capacity);
            specificAttributes.put(Specs.RAM_LATENCY.getKey(), latency);
            specificAttributes.put(Specs.RAM_ECC.getKey(), isEcc);
            specificAttributes.put(Specs.RAM_GEN.getKey(), ramGeneration);

            return new Ram(baseInfo, specificAttributes);
        }
    }
}
