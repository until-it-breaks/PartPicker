package it.unibo.application.data.entities.builds;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import it.unibo.application.data.entities.components.Component;
import it.unibo.application.data.entities.components.Ram;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class RamUsage {
    private final int buildId;
    private final int ramId;
    private final int quantity;

    public RamUsage(final int buildId, final int ramId, final int quantity) {
        this.buildId = buildId;
        this.ramId = ramId;
        this.quantity = quantity;
    }

    public int getBuildId() {
        return buildId;
    }

    public int getRamId() {
        return ramId;
    }

    public int getQuantity() {
        return quantity;
    }

    public final class DAO {
        public static void insertRamUsage(final Connection connection, final RamUsage ramUsage) {
            try (
                    var statement = DAOUtils.prepare(connection, Queries.INSERT_RAM_USAGE,
                        ramUsage.getBuildId(), ramUsage.getRamId(),
                        ramUsage.getQuantity());
                ) {
                    statement.executeUpdate();
                } catch (final SQLException e) {
                    throw new DAOException(e);
            }
        }

        public static List<Component> getUsedRams(final Connection connection, final int buildId) {
            try (
                    var statement = DAOUtils.prepare(connection, Queries.FIND_USED_RAMS, buildId);
                    var resultSet = statement.executeQuery();
                ) {
                    final List<Component> rams = new ArrayList<>();
                    while (resultSet.next()) {
                        final var ramId = resultSet.getInt("CodiceRam");
                        final var quantity = resultSet.getInt("Quantita");
                        final var ram = Ram.DAO.findById(connection, ramId);
                        for (int i = 0; i < quantity; i++) {
                            rams.add(ram);
                        } 
                    }
                    return rams;
                } catch (final SQLException e) {
                    throw new DAOException(e);
            }
        }
    }
}
