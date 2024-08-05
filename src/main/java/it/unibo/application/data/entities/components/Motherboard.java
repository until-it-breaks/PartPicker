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

public class Motherboard implements Component {
    private final BaseInfo baseInfo;
    private final Map<Specs, String> specificAttributes;

    public Motherboard(final BaseInfo baseInfo, final Map<Specs, String> specificAttributes) {
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

        public static List<Component> getMotherboards(final Connection connection) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.GET_MOTHERBOARDS);
                var resultSet = statement.executeQuery();
            ) {
                final List<Component> motherboards = new ArrayList<>();
                while (resultSet.next()) {
                    motherboards.add(createMotherboardFromResultSet(resultSet));
                }
                return motherboards;
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }

        public static Motherboard findById(final Connection connection, final int id) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.FIND_MOTHERBOARD, id);
                var resultSet = statement.executeQuery();
            ) {
                if (resultSet.next()) {
                    return createMotherboardFromResultSet(resultSet);
                }
                return null;
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }

        private static Motherboard createMotherboardFromResultSet(final ResultSet resultSet) throws SQLException {
            final var componentName = resultSet.getString(Specs.COMPONENT_NAME.getKey());
            final var launchYear = resultSet.getDate(Specs.COMPONENT_LAUNCH_YEAR.getKey()).toLocalDate().getYear();
            final var msrp = resultSet.getFloat(Specs.COMPONENT_MSRP.getKey());
            final var manufacturerName = resultSet.getString(Specs.COMPONENT_MANUFACTURER.getKey());

            final var motherboardId = resultSet.getInt(Specs.COMPONENT_ID.getKey());
            final var formFactor = resultSet.getString(Specs.MOTHERBOARD_FORM_FACTOR.getKey());
            final var chipsetName = resultSet.getString(Specs.MOTHERBOARD_CHIPSET.getKey());
            final var ramSlots = resultSet.getString(Specs.MOTHERBOARD_RAM_SLOTS.getKey());
            final var gpuSlots = resultSet.getString(Specs.MOTHERBOARD_GPU_SLOTS.getKey());
            final var hasWifi = (resultSet.getBoolean(Specs.MOTHERBOARD_WIFI.getKey()) == true) ? "Yes" : "No";
            final var socketName = resultSet.getString(Specs.MOTHERBOARD_SOCKET.getKey());
            final var ramGeneration = resultSet.getString(Specs.MOTHERBOARD_RAM_GEN.getKey());

            final BaseInfo baseInfo = new BaseInfo(motherboardId, componentName, launchYear, msrp, manufacturerName);
            final Map<Specs, String> specificAttributes = new HashMap<>();
            specificAttributes.put(Specs.MOTHERBOARD_FORM_FACTOR, formFactor);
            specificAttributes.put(Specs.MOTHERBOARD_CHIPSET, chipsetName);
            specificAttributes.put(Specs.MOTHERBOARD_RAM_SLOTS, ramSlots);
            specificAttributes.put(Specs.MOTHERBOARD_GPU_SLOTS, gpuSlots);
            specificAttributes.put(Specs.MOTHERBOARD_WIFI, hasWifi);
            specificAttributes.put(Specs.MOTHERBOARD_SOCKET, socketName);
            specificAttributes.put(Specs.MOTHERBOARD_RAM_GEN, ramGeneration);

            return new Motherboard(baseInfo, specificAttributes);
        }
    }
}
