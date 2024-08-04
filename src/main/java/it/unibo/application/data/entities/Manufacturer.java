package it.unibo.application.data.entities;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;

public class Manufacturer {
    public int manufacturerId;
    public String manufacturerName;
    public String manufacturerCountry;

    public Manufacturer(int manufacturerId, String manufacturerName, String manufacturerCountry) {
        this.manufacturerId = manufacturerId;
        this.manufacturerName = manufacturerName;
        this.manufacturerCountry = manufacturerCountry;
    }

        public final class DAO {
        public static Manufacturer findById(Connection connection, int id) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.FIND_MANUFACTURER, id);
                var resultSet = statement.executeQuery();
            ) {
                if (resultSet.next()) {
                    var manufacturerId = resultSet.getInt("CodiceProduttore");
                    var manufacturerName = resultSet.getString("NomeProduttore");
                    var manufacturerCountry = resultSet.getString("PaeseProduttore");
                    Manufacturer manufacturer = new Manufacturer(manufacturerId, manufacturerName, manufacturerCountry);
                    return manufacturer;
                }
                return null;
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
