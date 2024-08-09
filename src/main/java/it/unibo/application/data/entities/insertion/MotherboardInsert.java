package it.unibo.application.data.entities.insertion;

import java.sql.Connection;
import java.sql.SQLException;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;

public class MotherboardInsert {
    private int id;
    private String formFactor;
    private String chipsetName;
    private int ramSlot;
    private int gpuSlot;
    private boolean wifi;
    private String socketName;
    private String ramGen;

    public MotherboardInsert(int id, String formFactor, String chipsetName, int ramSlot, int gpuSlot, boolean wifi,
            String socketName, String ramGen) {
        this.id = id;
        this.formFactor = formFactor;
        this.chipsetName = chipsetName;
        this.ramSlot = ramSlot;
        this.gpuSlot = gpuSlot;
        this.wifi = wifi;
        this.socketName = socketName;
        this.ramGen = ramGen;
    }

    public int getId() {
        return id;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public String getChipsetName() {
        return chipsetName;
    }

    public int getRamSlot() {
        return ramSlot;
    }

    public int getGpuSlot() {
        return gpuSlot;
    }

    public boolean isWifi() {
        return wifi;
    }

    public String getSocketName() {
        return socketName;
    }

    public String getRamGen() {
        return ramGen;
    }

    public final class DAO {
        public static void insert(final Connection connection, MotherboardInsert motherboard) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.INSERT_MOTHERBOARD,
                    motherboard.getId(), motherboard.getFormFactor(), motherboard.getChipsetName(),
                    motherboard.getRamSlot(), motherboard.getGpuSlot(), motherboard.isWifi(),
                    motherboard.getSocketName(), motherboard.getRamGen())
            ) {
                statement.executeUpdate();
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
