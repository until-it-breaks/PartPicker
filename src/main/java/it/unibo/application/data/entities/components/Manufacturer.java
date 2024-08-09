package it.unibo.application.data.entities.components;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;

import java.sql.Connection;
import java.sql.SQLException;

public class Manufacturer {
    private final int id;
    private final String name;
    private final String country;

    public Manufacturer(final int id, final String name, final String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public final class DAO {
        public static Manufacturer getManufacturer(final Connection connection, final String manufacturer) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.GET_MANUFACTURER_BY_NAME, manufacturer);
                var resultSet = statement.executeQuery();
                ) {
                    if (resultSet.next()) {
                        var id = resultSet.getInt("CodiceProduttore");
                        var name = resultSet.getString("NomeProduttore");
                        var country = resultSet.getString("PaeseProduttore");
                        return new Manufacturer(id, name, country);
                    }
                    return null;
                } catch (final SQLException e) {
                    throw new DAOException(e);
            }
        }
    }
}
