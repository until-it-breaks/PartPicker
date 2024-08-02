package it.unibo.application.data.entities;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;

public class Motherboard {
    public int motherboardId;
    public String formFactor;
    public String chipsetName;
    public int ramSlots;
    public int gpuSlots;
    public boolean hasWifi;
    public String socketName;
    public String ramGeneration;

    public Motherboard(int motherboardId, String formFactor, String chipsetName, int ramSlots, int gpuSlots,
            boolean hasWifi, String socketName, String ramGeneration) {
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
                    var motherboardId = resultSet.getInt("CodiceMotherboard");
                    var formFactor = resultSet.getString("FattoreFormaMotherboard");
                    //TODO
                }
                return null;
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
