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

public class Psu implements Component {
    private final BaseInfo baseInfo;
    private final Map<Specs, String> specificAttributes;

    public Psu(final BaseInfo baseInfo, final Map<Specs, String> specificAttributes) {
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

        public static List<Component> getPsus(final Connection connection) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.GET_PSU);
                var resultSet = statement.executeQuery();
            ) {
                final List<Component> psus = new ArrayList<>();
                while (resultSet.next()) {
                    psus.add(createPsuFromResultSet(resultSet));
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
                    return createPsuFromResultSet(resultSet);
                }
                return null;
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }

        private static Psu createPsuFromResultSet(final ResultSet resultSet) throws SQLException {
            final var componentName = resultSet.getString(Specs.COMPONENT_NAME.getKey());
            final var launchYear = resultSet.getDate(Specs.COMPONENT_LAUNCH_YEAR.getKey()).toLocalDate().getYear();
            final var msrp = resultSet.getFloat(Specs.COMPONENT_MSRP.getKey());
            final var manufacturerName = resultSet.getString(Specs.COMPONENT_MANUFACTURER.getKey());

            final var psuId = resultSet.getInt(Specs.COMPONENT_ID.getKey());
            final var formFactor = resultSet.getString(Specs.PSU_FORM_FACTOR.getKey());
            final var efficiency = resultSet.getString(Specs.PSU_EFFICIENCY.getKey());
            final var wattage = resultSet.getString(Specs.PSU_WATTAGE.getKey());
            final var modularity = resultSet.getString(Specs.PSU_MODULARITY.getKey());

            final BaseInfo baseInfo = new BaseInfo(psuId, componentName, launchYear, msrp, manufacturerName);
            final Map<Specs, String> specificAttributes = new HashMap<>();
            specificAttributes.put(Specs.PSU_FORM_FACTOR, formFactor);
            specificAttributes.put(Specs.PSU_EFFICIENCY, efficiency);
            specificAttributes.put(Specs.PSU_WATTAGE, wattage);
            specificAttributes.put(Specs.PSU_MODULARITY, modularity);

            return new Psu(baseInfo, specificAttributes);
        }
    }
}
