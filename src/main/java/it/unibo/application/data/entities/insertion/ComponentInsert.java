package it.unibo.application.data.entities.insertion;

import java.time.LocalDate;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;

public class ComponentInsert {
    private final int id;
    private final String name;
    private final String type;
    private final LocalDate launchYear;
    private final float msrp;
    private final int manufacturerId;

    public ComponentInsert(final int id, final String name, final String type, final LocalDate launchYear, final float msrp, final int manufacturerId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.launchYear = launchYear;
        this.msrp = msrp;
        this.manufacturerId = manufacturerId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public LocalDate getLaunchYear() {
        return launchYear;
    }

    public float getMsrp() {
        return msrp;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public final class DAO {
        public static void insert(final Connection connection, final ComponentInsert componentInsert) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.INSERT_COMPONENT,
                    componentInsert.getId(), componentInsert.getName(), componentInsert.getType(),
                    componentInsert.getLaunchYear(), componentInsert.getMsrp(), componentInsert.getManufacturerId());
            ) {
                statement.executeUpdate();
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
