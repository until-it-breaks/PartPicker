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

public class Case implements Component {
    private final BaseInfo baseInformation;
    private final Map<String, String> specificAttributes;

    public Case(final BaseInfo baseInformation, final Map<String, String> specificAttributes) {
        this.baseInformation = baseInformation;
        this.specificAttributes = specificAttributes;
    }

    public BaseInfo getBaseInfo() {
        return baseInformation;
    }

    public Map<String, String> getSpecificAttributes() {
        return specificAttributes;
    }

    public final class DAO {
        public static List<Component> getCases(final Connection connection) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.GET_CASES);
                var resultSet = statement.executeQuery();
            ) {
                final List<Component> cases = new ArrayList<>();
                while (resultSet.next()) {
                    cases.add(createCaseFromResultSet(resultSet));
                }
                return cases;
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }

        public static Case findById(final Connection connection, final int id) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.FIND_CASE, id);
                var resultSet = statement.executeQuery();
            ) {
                if (resultSet.next()) {
                    return createCaseFromResultSet(resultSet);
                }
                return null;
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }

        private static Case createCaseFromResultSet(final ResultSet resultSet) throws SQLException {
            final var componentName = resultSet.getString(Specs.COMPONENT_NAME.getKey());
            final var launchYear = resultSet.getDate(Specs.COMPONENT_LAUNCH_YEAR.getKey()).toLocalDate().getYear();
            final var msrp = resultSet.getFloat(Specs.COMPONENT_MSRP.getKey());
            final var manufacturerName = resultSet.getString(Specs.COMPONENT_MANUFACTURER.getKey());
            final var caseId = resultSet.getInt(Specs.COMPONENT_ID.getKey());
            final var formFactor = resultSet.getString(Specs.CASE_FORM_FACTOR.getKey());

            final BaseInfo baseInfo = new BaseInfo(caseId, componentName, launchYear, msrp, manufacturerName);
            final Map<String, String> specificAttributes = new HashMap<>();
            specificAttributes.put(Specs.CASE_FORM_FACTOR.getKey(), formFactor);

            return new Case(baseInfo, specificAttributes);
        }
    }
}
