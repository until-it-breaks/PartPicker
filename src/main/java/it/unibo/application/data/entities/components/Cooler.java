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

public class Cooler implements Component {
    private final BaseInfo baseInfo;
    private final Map<Specs, String> specificAttributes;

    public Cooler(final BaseInfo baseInfo, final Map<Specs, String> specificAttributes) {
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
        public static List<Component> getCoolers(final Connection connection) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.GET_COOLER);
                var resultSet = statement.executeQuery();
            ) {
                final List<Component> coolers = new ArrayList<>();
                while (resultSet.next()) {
                    coolers.add(createCoolerFromResultSet(resultSet));
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
                    return createCoolerFromResultSet(resultSet);
                }
                return null;
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }

        private static Cooler createCoolerFromResultSet(final ResultSet resultSet) throws SQLException {
            final var componentName = resultSet.getString(Specs.COMPONENT_NAME.getKey());
            final var launchYear = resultSet.getDate(Specs.COMPONENT_LAUNCH_YEAR.getKey()).toLocalDate().getYear();
            final var msrp = resultSet.getFloat(Specs.COMPONENT_MSRP.getKey());
            final var manufacturerName = resultSet.getString(Specs.COMPONENT_MANUFACTURER.getKey());
            final var coolerId = resultSet.getInt(Specs.COMPONENT_ID.getKey());
            final var coolerRpm = resultSet.getString(Specs.COOLER_RPM.getKey());
            final var noiseLevel = resultSet.getString(Specs.COOLER_NOISE_LEVEL.getKey());
            final var coolerType = resultSet.getString(Specs.COOLER_TYPE.getKey());

            final BaseInfo baseInfo = new BaseInfo(coolerId, componentName, launchYear, msrp, manufacturerName);
            final Map<Specs, String> specificAttributes = new HashMap<>();
            specificAttributes.put(Specs.COOLER_RPM, coolerRpm);
            specificAttributes.put(Specs.COOLER_NOISE_LEVEL, noiseLevel);
            specificAttributes.put(Specs.COOLER_TYPE, coolerType);

            return new Cooler(baseInfo, specificAttributes);
        }
    }
}
