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

public class Motherboard implements Component {
    private final BaseInfo baseInfo;
    private final Map<String, String> specificAttributes;

    public Motherboard(final BaseInfo baseInfo, final Map<String, String> specificAttributes) {
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

        public static List<Component> getMotherboards(final Connection connection) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.GET_MOTHERBOARDS);
                var resultSet = statement.executeQuery();
            ) {
                final List<Component> motherboards = new ArrayList<>();
                while (resultSet.next()) {
                    final var componentName = resultSet.getString("NomeComponente");
                    final var launchYear = resultSet.getDate("AnnoLancio").toLocalDate().getYear();
                    final var msrp = resultSet.getFloat("PrezzoListino");
                    final var manufacturerName = resultSet.getString("NomeProduttore");

                    final var motherboardId = resultSet.getInt("CodiceMotherboard");
                    final var formFactor = resultSet.getString("FattoreFormaMotherboard");
                    final var chipsetName = resultSet.getString("NomeChipset");
                    final var ramSlots = resultSet.getString("SlotRam");
                    final var gpuSlots = resultSet.getString("SlotGpu");
                    final var hasWifi = resultSet.getString("Wifi");
                    final var socketName = resultSet.getString("NomeSocket");
                    final var ramGeneration = resultSet.getString("NomeGenerazioneRam");

                    final BaseInfo baseInfo = new BaseInfo(motherboardId, componentName, launchYear, msrp, manufacturerName);
                    final Map<String, String> specificAttributes = new HashMap<>();
                    specificAttributes.put(Specs.MOTHERBOARD_FORM_FACTOR.getKey(), formFactor);
                    specificAttributes.put(Specs.MOTHERBOARD_CHIPSET.getKey(), chipsetName);
                    specificAttributes.put(Specs.MOTHERBOARD_RAM_SLOTS.getKey(), ramSlots);
                    specificAttributes.put(Specs.MOTHERBOARD_GPU_SLOTS.getKey(), gpuSlots);
                    specificAttributes.put(Specs.MOTHERBOARD_WIFI.getKey(), hasWifi);
                    specificAttributes.put(Specs.MOTHERBOARD_SOCKET.getKey(), socketName);
                    specificAttributes.put(Specs.MOTHERBOARD_RAM_GEN.getKey(), ramGeneration);

                    motherboards.add(new Motherboard(baseInfo, specificAttributes));
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
                    final var componentName = resultSet.getString("NomeComponente");
                    final var launchYear = resultSet.getDate("AnnoLancio").toLocalDate().getYear();
                    final var msrp = resultSet.getFloat("PrezzoListino");
                    final var manufacturerName = resultSet.getString("NomeProduttore");

                    final var motherboardId = resultSet.getInt("CodiceMotherboard");
                    final var formFactor = resultSet.getString("FattoreFormaMotherboard");
                    final var chipsetName = resultSet.getString("NomeChipset");
                    final var ramSlots = resultSet.getString("SlotRam");
                    final var gpuSlots = resultSet.getString("SlotGpu");
                    final var hasWifi = resultSet.getString("Wifi");
                    final var socketName = resultSet.getString("NomeSocket");
                    final var ramGeneration = resultSet.getString("NomeGenerazioneRam");

                    final BaseInfo baseInfo = new BaseInfo(motherboardId, componentName, launchYear, msrp, manufacturerName);
                    final Map<String, String> specificAttributes = new HashMap<>();
                    specificAttributes.put(Specs.MOTHERBOARD_FORM_FACTOR.getKey(), formFactor);
                    specificAttributes.put(Specs.MOTHERBOARD_CHIPSET.getKey(), chipsetName);
                    specificAttributes.put(Specs.MOTHERBOARD_RAM_SLOTS.getKey(), ramSlots);
                    specificAttributes.put(Specs.MOTHERBOARD_GPU_SLOTS.getKey(), gpuSlots);
                    specificAttributes.put(Specs.MOTHERBOARD_WIFI.getKey(), hasWifi);
                    specificAttributes.put(Specs.MOTHERBOARD_SOCKET.getKey(), socketName);
                    specificAttributes.put(Specs.MOTHERBOARD_RAM_GEN.getKey(), ramGeneration);

                    return new Motherboard(baseInfo, specificAttributes);
                }
                return null;
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
