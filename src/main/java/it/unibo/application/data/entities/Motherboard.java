package it.unibo.application.data.entities;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;

public class Motherboard extends Component {
    public int motherboardId;
    public String formFactor;
    public String chipsetName;
    public int ramSlots;
    public int gpuSlots;
    public boolean hasWifi;
    public String socketName;
    public String ramGeneration;
    
    public Motherboard(int componentId, String componentName, String componentType, int launchYear, float msrp,
            int manufacturerId, int motherboardId, String formFactor, String chipsetName, int ramSlots, int gpuSlots,
            boolean hasWifi, String socketName, String ramGeneration) {
        super(componentId, componentName, componentType, launchYear, msrp, manufacturerId);
        this.motherboardId = motherboardId;
        this.formFactor = formFactor;
        this.chipsetName = chipsetName;
        this.ramSlots = ramSlots;
        this.gpuSlots = gpuSlots;
        this.hasWifi = hasWifi;
        this.socketName = socketName;
        this.ramGeneration = ramGeneration;
    }

    public final class DAO {
        public static Motherboard findById(Connection connection, int id) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.FIND_MOTHERBOARD, id);
                var resultSet = statement.executeQuery();
            ) {
                if (resultSet.next()) {
                    var componentId = resultSet.getInt("CodiceComponente");
                    var componentName = resultSet.getString("NomeComponente");
                    var componentType = resultSet.getString("TipoComponente");
                    var launchYear = resultSet.getDate("AnnoLancio").getYear();
                    var msrp = resultSet.getFloat("PrezzoListino");
                    var manufacturerId = resultSet.getInt("CodiceProduttore");
                    var motherboardId = resultSet.getInt("CodiceMotherboard");
                    var formFactor = resultSet.getString("FattoreFormaMotherboard");
                    var chipsetName = resultSet.getString("NomeChipset");
                    var ramSlots = resultSet.getInt("SlotRam");
                    var gpuSlots = resultSet.getInt("SlotGpu");
                    var hasWifi = resultSet.getBoolean("Wifi");
                    var socketName = resultSet.getString("NomeSocket");
                    var ramGeneration = resultSet.getString("NomeGenerazioneRam");
                    Motherboard motherboard = new Motherboard(componentId, componentName, componentType, launchYear, msrp, manufacturerId, motherboardId, formFactor, chipsetName, ramSlots, gpuSlots, hasWifi, socketName, ramGeneration);
                    return motherboard;
                }
                return null;
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
