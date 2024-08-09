package it.unibo.application.data.entities.components;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

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
        public static List<Manufacturer> getManufacturers(final Connection connection) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.GET_MANUFACTURERS);
                var resultSet = statement.executeQuery();
                ) {
                    List<Manufacturer> manufacturers = new ArrayList<>();
                    while (resultSet.next()) {
                        var id = resultSet.getInt("CodiceProduttore");
                        var name = resultSet.getString("NomeProduttore");
                        var country = resultSet.getString("PaeseProduttore");
                        manufacturers.add(new Manufacturer(id, name, country));
                    }
                    return manufacturers;
                } catch (final SQLException e) {
                    throw new DAOException(e);
            }
        }
    }
}
