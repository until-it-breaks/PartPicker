package it.unibo.application.data.entities.builds;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import it.unibo.application.data.entities.components.Case;
import it.unibo.application.data.entities.components.Cooler;
import it.unibo.application.data.entities.components.Cpu;
import it.unibo.application.data.entities.components.Gpu;
import it.unibo.application.data.entities.components.Motherboard;
import it.unibo.application.data.entities.components.Psu;
import it.unibo.application.data.entities.components.Ram;
import it.unibo.application.data.entities.components.Storage;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;

public class Build {
    private final int buildId;
    private final Cooler cooler;
    private final Case _case;
    private final Psu psu;
    private final Cpu cpu;
    private final Motherboard motherboard;
    private final List<Gpu> gpus;
    private final List<Ram> rams;
    private final List<Storage> storage;
    private final String author;

    public Build(final int build, final Cooler cooler, final Case _case, final Psu psu, final Cpu cpu, final Motherboard motherboard, final List<Gpu> gpu,
            final List<Ram> ram, final List<Storage> storage, final String author) {
        this.buildId = build;
        this.cooler = cooler;
        this._case = _case;
        this.psu = psu;
        this.cpu = cpu;
        this.motherboard = motherboard;
        this.gpus = gpu;
        this.rams = ram;
        this.storage = storage;
        this.author = author;
    }

    public int getBuildId() {
        return buildId;
    }

    public Cooler getCooler() {
        return cooler;
    }

    public Case get_case() {
        return _case;
    }

    public Psu getPsu() {
        return psu;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public List<Gpu> getGpus() {
        return gpus;
    }

    public List<Ram> getRams() {
        return rams;
    }

    public List<Storage> getStorage() {
        return storage;
    }

    public String getAuthor() {
        return author;
    }

    public final class DAO {
        public static List<Build> getBuilds(final Connection connection) {
            try (
                    var statement = DAOUtils.prepare(connection, Queries.GET_BUILDS);
                    var resultSet = statement.executeQuery();
                ) {
                    final List<Build> builds = new ArrayList<>();
                    while (resultSet.next()) {
                        final var author = resultSet.getString("Username");
                        final var buildId = resultSet.getInt("CodiceBuild");
                        final var caseId = resultSet.getInt("CodiceCase");
                        final var coolerId = resultSet.getInt("CodiceCooler");
                        final var cpuId = resultSet.getInt("CodiceCpu");
                        final var motherboardId = resultSet.getInt("CodiceMotherboard");
                        final var psuId = resultSet.getInt("CodicePsu");
                        final var _case = Case.DAO.findById(connection, caseId);
                        final var cooler = Cooler.DAO.findById(connection, coolerId);
                        final var cpu = Cpu.DAO.findById(connection, cpuId);
                        final var psu = Psu.DAO.findById(connection, psuId);
                        final var motherboard = Motherboard.DAO.findById(connection, motherboardId);
                        builds.add(new Build(buildId, cooler, _case, psu, cpu, motherboard, getUsedGpus(connection, buildId), getUsedRams(connection, buildId), getUsedStorage(connection, buildId), author));
                    }
                    return builds;
                } catch (final SQLException e) {
                    throw new DAOException(e);
            }
        }

        public static List<Gpu> getUsedGpus(final Connection connection, final int buildId) {
            try (
                    var statement = DAOUtils.prepare(connection, Queries.FIND_USED_GPUS, buildId);
                    var resultSet = statement.executeQuery();
                ) {
                    final List<Gpu> gpus = new ArrayList<>();
                    while (resultSet.next()) {
                        final var gpuId = resultSet.getInt("CodiceGpu");
                        final var quantity = resultSet.getInt("Quantita");
                        final var gpu = Gpu.DAO.findById(connection, gpuId);
                        for (int i = 0; i < quantity; i++) {
                            gpus.add(gpu);
                        } 
                    }
                    return gpus;
                } catch (final SQLException e) {
                    throw new DAOException(e);
            }
        }

        public static List<Ram> getUsedRams(final Connection connection, final int buildId) {
            try (
                    var statement = DAOUtils.prepare(connection, Queries.FIND_USED_RAMS, buildId);
                    var resultSet = statement.executeQuery();
                ) {
                    final List<Ram> rams = new ArrayList<>();
                    while (resultSet.next()) {
                        final var ramId = resultSet.getInt("CodiceRam");
                        final var quantity = resultSet.getInt("Quantita");
                        final var ram = Ram.DAO.findById(connection, ramId);
                        for (int i = 0; i < quantity; i++) {
                            rams.add(ram);
                        } 
                    }
                    return rams;
                } catch (final SQLException e) {
                    throw new DAOException(e);
            }
        }

        public static List<Storage> getUsedStorage(final Connection connection, final int buildId) {
            try (
                    var statement = DAOUtils.prepare(connection, Queries.FIND_USED_STORAGE, buildId);
                    var resultSet = statement.executeQuery();
                ) {
                    final List<Storage> storages = new ArrayList<>();
                    while (resultSet.next()) {
                        final var storageId = resultSet.getInt("CodiceStorage");
                        final var quantity = resultSet.getInt("Quantita");
                        final var storage = Storage.DAO.findById(connection, storageId);
                        for (int i = 0; i < quantity; i++) {
                            storages.add(storage);
                        } 
                    }
                    return storages;
                } catch (final SQLException e) {
                    throw new DAOException(e);
            }
        }
    }
}
