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

public class Storage implements Component {
    private final BaseInfo baseInfo;
    private final Map<Specs, String> specificAttributes;

    public Storage(final BaseInfo baseInfo, final Map<Specs, String> specificAttributes) {
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

        public static List<Component> getStorage(final Connection connection) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.GET_STORAGE);
                var resultSet = statement.executeQuery();
            ) {
                final List<Component> storageList = new ArrayList<>();
                while (resultSet.next()) {
                    storageList.add(createStorageFromResultSet(resultSet));
                }
                return storageList;
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }

        public static Storage findById(final Connection connection, final int id) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.FIND_STORAGE, id);
                var resultSet = statement.executeQuery();
            ) {
                if (resultSet.next()) {
                    return createStorageFromResultSet(resultSet);
                }
                return null;
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }

        private static Storage createStorageFromResultSet(final ResultSet resultSet) throws SQLException {
            final var componentName = resultSet.getString(Specs.COMPONENT_NAME.getKey());
            final var launchYear = resultSet.getDate(Specs.COMPONENT_LAUNCH_YEAR.getKey()).toLocalDate().getYear();
            final var msrp = resultSet.getFloat(Specs.COMPONENT_MSRP.getKey());
            final var manufacturerName = resultSet.getString(Specs.COMPONENT_MANUFACTURER.getKey());

            final var storageId = resultSet.getInt(Specs.COMPONENT_ID.getKey());
            final var storageCapacity = resultSet.getString(Specs.STORAGE_CAPACITY.getKey());
            final var storageRpm = resultSet.getString(Specs.STORAGE_RPM.getKey());
            final var cacheAmount = resultSet.getString(Specs.STORAGE_CACHE.getKey());
            final var storageType = resultSet.getString(Specs.STORAGE_TYPE.getKey());

            final BaseInfo baseInfo = new BaseInfo(storageId, componentName, launchYear, msrp, manufacturerName);
            final Map<Specs, String> specificAttributes = new HashMap<>();
            specificAttributes.put(Specs.STORAGE_CAPACITY, storageCapacity);
            specificAttributes.put(Specs.STORAGE_RPM, storageRpm);
            specificAttributes.put(Specs.STORAGE_CACHE, cacheAmount);
            specificAttributes.put(Specs.STORAGE_TYPE, storageType);

            return new Storage(baseInfo, specificAttributes);
        }
    }
}
