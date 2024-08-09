package it.unibo.application.data.entities.insertion;

import java.sql.Connection;
import java.sql.SQLException;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;

public class PsuInsert {
    private int id;
    private String formFactor;
    private String efficiency;
    private int wattage;
    private String modularity;

    public PsuInsert(int id, String formFactor, String efficiency, int wattage, String modularity) {
        this.id = id;
        this.formFactor = formFactor;
        this.efficiency = efficiency;
        this.wattage = wattage;
        this.modularity = modularity;
    }

    public int getId() {
        return id;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public String getEfficiency() {
        return efficiency;
    }

    public int getWattage() {
        return wattage;
    }

    public String getModularity() {
        return modularity;
    }

    public final class DAO {
        public static void insert(final Connection connection, PsuInsert psu) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.INSERT_PSU, psu.getId(),
                    psu.getFormFactor(), psu.getEfficiency(), psu.getWattage(), psu.getModularity())
            ) {
                statement.executeUpdate();
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
