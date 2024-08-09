package it.unibo.application.data.entities.insertion;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;

public class CoolerInsert {
    private final int id;
    private final int rpm;
    private final float noiseLevel;
    private final String type;

    public CoolerInsert(final int id, final int rpm, final float noiseLevel, final String type) {
        this.id = id;
        this.rpm = rpm;
        this.noiseLevel = noiseLevel;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public int getRpm() {
        return rpm;
    }

    public float getNoiseLevel() {
        return noiseLevel;
    }

    public String getType() {
        return type;
    }

    public final class DAO {
        public static void insert(final Connection connection, final CoolerInsert cooler) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.INSERT_COOLER,
                    cooler.getId(), cooler.getRpm(),
                    cooler.getNoiseLevel(), cooler.getType());
            ) {
                statement.executeUpdate();
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
