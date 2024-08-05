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

public class Storage implements Component {
    private final BaseInfo baseInfo;
    private final Map<String, String> specificAttributes;

    public Storage(final BaseInfo baseInfo, final Map<String, String> specificAttributes) {
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

        public static List<Component> getStorage(final Connection connection) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.GET_STORAGE);
                var resultSet = statement.executeQuery();
            ) {
                final List<Component> storageList = new ArrayList<>();
                while (resultSet.next()) {
                    final var componentName = resultSet.getString("NomeComponente");
                    final var launchYear = resultSet.getDate("AnnoLancio").toLocalDate().getYear();
                    final var msrp = resultSet.getFloat("PrezzoListino");
                    final var manufacturerName = resultSet.getString("NomeProduttore");

                    final var storageId = resultSet.getInt("CodiceStorage");
                    final var storageCapacity = resultSet.getString("CapienzaStorage");
                    final var storageRpm = resultSet.getString("RpmStorage");
                    final var cacheAmount = resultSet.getString("QuantitaCache");
                    final var storageType = resultSet.getString("TipoStorage");

                    final BaseInfo baseInfo = new BaseInfo(storageId, componentName, launchYear, msrp, manufacturerName);
                    final Map<String, String> specificAttributes = new HashMap<>();
                    specificAttributes.put(Specs.STORAGE_CAPACITY.getKey(), storageCapacity);
                    specificAttributes.put(Specs.STORAGE_RPM.getKey(), storageRpm);
                    specificAttributes.put(Specs.STORAGE_CACHE.getKey(), cacheAmount);
                    specificAttributes.put(Specs.STORAGE_TYPE.getKey(), storageType);

                    storageList.add(new Storage(baseInfo, specificAttributes));
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
                    final var componentName = resultSet.getString("NomeComponente");
                    final var launchYear = resultSet.getDate("AnnoLancio").toLocalDate().getYear();
                    final var msrp = resultSet.getFloat("PrezzoListino");
                    final var manufacturerName = resultSet.getString("NomeProduttore");


                    final var storageId = resultSet.getInt("CodiceStorage");
                    final var storageCapacity = resultSet.getString("CapienzaStorage");
                    final var storageRpm = resultSet.getString("RpmStorage");
                    final var cacheAmount = resultSet.getString("QuantitaCache");
                    final var storageType = resultSet.getString("TipoStorage");

                    final BaseInfo baseInfo = new BaseInfo(storageId, componentName, launchYear, msrp, manufacturerName);
                    final Map<String, String> specificAttributes = new HashMap<>();
                    specificAttributes.put(Specs.STORAGE_CAPACITY.getKey(), storageCapacity);
                    specificAttributes.put(Specs.STORAGE_RPM.getKey(), storageRpm);
                    specificAttributes.put(Specs.STORAGE_CACHE.getKey(), cacheAmount);
                    specificAttributes.put(Specs.STORAGE_TYPE.getKey(), storageType);

                    return new Storage(baseInfo, specificAttributes);
                }
                return null;
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
