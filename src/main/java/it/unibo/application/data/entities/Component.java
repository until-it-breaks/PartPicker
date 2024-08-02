package it.unibo.application.data.entities;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;

public class Component {
    public int componentId;
    public String componentName;
    public String componentType;
    public int launchYear;
    public float msrp;
    public int manufacturerId;

    public Component(int componentId, String componentName, String componentType, int launchYear, float msrp,
            int manufacturerId) {
        this.componentId = componentId;
        this.componentName = componentName;
        this.componentType = componentType;
        this.launchYear = launchYear;
        this.msrp = msrp;
        this.manufacturerId = manufacturerId;
    }

    public final class DAO {
        public static List<Component> findByType(Connection connection, String type) {
            List<Component> components = new ArrayList<>();
            try (
                var statement = DAOUtils.prepare(connection, Queries.GET_LATEST_COMPONENTS, type);
                var resultSet = statement.executeQuery();
            ) {
                while (resultSet.next()) {
                    var componentId = resultSet.getInt("CodiceComponente");
                    var componentName = resultSet.getString("NomeComponente");
                    var componentType = resultSet.getString("TipoComponente");
                    var launchYear = resultSet.getDate("AnnoLancio").getYear();
                    var msrp = resultSet.getFloat("PrezzoListino");
                    var manufacturerId = resultSet.getInt("CodiceProduttore");
                    Component component = new Component(componentId, componentName, componentType, launchYear, msrp, manufacturerId);
                    components.add(component);
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            return components;
        }
    }
}