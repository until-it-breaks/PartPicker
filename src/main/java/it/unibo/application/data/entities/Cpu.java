package it.unibo.application.data.entities;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;

public class Cpu {
    public int cpuId;
    public String cpuFamily;
    public int coreCount;
    public int cpuFrequency;
    public int tdp;
    public boolean hasSmt;
    public String socketName;

    public Cpu(int cpuId, String cpuFamily, int coreCount, int cpuFrequency, int tdp, boolean hasSmt,
            String socketName) {
        this.cpuId = cpuId;
        this.cpuFamily = cpuFamily;
        this.coreCount = coreCount;
        this.cpuFrequency = cpuFrequency;
        this.tdp = tdp;
        this.hasSmt = hasSmt;
        this.socketName = socketName;
    }

    public final class DAO {
        public static Cpu findById(Connection connection, int id) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.FIND_CPU, id);
                var resultSet = statement.executeQuery();
            ) {
                if (resultSet.next()) {
                    var cpuId = resultSet.getInt("CodiceCpu");
                    var cpuFamily = resultSet.getString("FamigliaCpu");
                    var coreCount = resultSet.getInt("NumeroCore");
                    var cpuFrequency = resultSet.getInt("FrequenzaCpu");
                    var tdp = resultSet.getInt("Tdp");
                    var hasSmt = resultSet.getBoolean("Smt");
                    var socketName = resultSet.getString("NomeSocket");
                    Cpu cpu = new Cpu(cpuId, cpuFamily, coreCount, cpuFrequency, tdp, hasSmt, socketName);
                    return cpu;
                }
                return null;
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
