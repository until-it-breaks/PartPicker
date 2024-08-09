package it.unibo.application.data.entities.compatibility;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import it.unibo.application.data.entities.components.Component;
import it.unibo.application.data.entities.enums.Specs;

import java.sql.Connection;
import java.sql.SQLException;

public class ComponentCompatibilityChecker {
    private Connection connection;

    public ComponentCompatibilityChecker(Connection connection) {
        this.connection = connection;
    }

    public boolean areRamMoboCompatible(Component ram, Component motherboard) {
        return ram.getSpecificAttributes().get(Specs.RAM_GEN).equals(motherboard.getSpecificAttributes().get(Specs.MOTHERBOARD_RAM_GEN));
    }

    public boolean areCpuMoboCompatible(Component cpu, Component motherboard) {
        return cpu.getSpecificAttributes().get(Specs.CPU_SOCKET_NAME).equals(motherboard.getSpecificAttributes().get(Specs.MOTHERBOARD_SOCKET));
    }

    public boolean checkCompatibility(final Component ram, final Component cpu) {
        try (
                var statement = DAOUtils.prepare(connection, Queries.RAM_CPU_MATCH,
                    ram.getSpecificAttributes().get(Specs.RAM_GEN), cpu.getBaseInfo().getId());
                var resultSet = statement.executeQuery();
            ) {
                if (resultSet.next()) {
                    return resultSet.getInt("Match") != 0;
                }
                return false;
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }
}
