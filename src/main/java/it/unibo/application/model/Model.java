package it.unibo.application.model;

import java.sql.Connection;
import java.sql.SQLException;

import it.unibo.application.data.entities.ban.Ban;
import it.unibo.application.data.entities.builds.Build;
import it.unibo.application.data.entities.builds.Review;
import it.unibo.application.data.entities.compatibility.ComponentCompatibilityChecker;
import it.unibo.application.data.entities.components.Case;
import it.unibo.application.data.entities.components.Component;
import it.unibo.application.data.entities.components.Cooler;
import it.unibo.application.data.entities.components.Cpu;
import it.unibo.application.data.entities.components.Gpu;
import it.unibo.application.data.entities.components.Manufacturer;
import it.unibo.application.data.entities.components.Motherboard;
import it.unibo.application.data.entities.components.Psu;
import it.unibo.application.data.entities.components.Ram;
import it.unibo.application.data.entities.components.Storage;
import it.unibo.application.data.entities.enums.Part;
import it.unibo.application.data.entities.insertion.CaseInsert;
import it.unibo.application.data.entities.insertion.ComponentInsert;
import it.unibo.application.data.entities.insertion.CoolerInsert;
import it.unibo.application.data.entities.insertion.CpuInsert;
import it.unibo.application.data.entities.insertion.CpuRamInsert;
import it.unibo.application.data.entities.insertion.GpuInsert;
import it.unibo.application.data.entities.insertion.MotherboardInsert;
import it.unibo.application.data.entities.insertion.PsuInsert;
import it.unibo.application.data.entities.insertion.RamInsert;
import it.unibo.application.data.entities.insertion.StorageInsert;
import it.unibo.application.data.entities.login.User;
import it.unibo.application.data.entities.price.ComponentPrice;
import it.unibo.application.model.login.LoginService;
import java.util.List;

public final class Model {

    private final Connection connection;
    private final LoginService loginService;
    private final ComponentCompatibilityChecker componentCompatibilityChecker;

    public Model(final Connection connection) {
        this.connection = connection;
        this.loginService = new LoginService(connection);
        this.componentCompatibilityChecker = new ComponentCompatibilityChecker(connection);
    }

    public boolean login(final String username, final String password) {
        return loginService.login(username, password);
    }

    public boolean registerUser(final User user) {
        return User.DAO.insertUser(connection, user);
    }

    public User getLoggedUser() {
        return User.DAO.findByUsername(connection, loginService.getCurrentUser());
    }

    public List<Build> getBuilds() {
        return Build.DAO.getBuilds(connection);
    }

    public Build getBuildById(final int id) {
        return Build.DAO.findBuildById(connection, id);
    }

    public List<Component> getComponents(final Part part) {
        switch (part) {
            case CPU:
                return Cpu.DAO.getCpus(connection);
            case GPU:
                return Gpu.DAO.getGpus(connection);
            case MOTHERBOARD:
                return Motherboard.DAO.getMotherboards(connection);
            case PSU:
                return Psu.DAO.getPsus(connection);
            case RAM:
                return Ram.DAO.getRams(connection);
            case STORAGE:
                return Storage.DAO.getStorage(connection);
            case COOLER:
                return Cooler.DAO.getCoolers(connection);
            case CASE:
                return Case.DAO.getCases(connection);
            default:
                throw new IllegalArgumentException("Unknown part type: " + part);
        }
    }

    public void banUser(final Ban ban) {
        Ban.DAO.insertBan(connection, ban);
    }

    public List<Review> getReviewsByBuild(final int buildId) {
        return Review.DAO.getReviews(connection, buildId);
    }

    public void insertReview(final Review review) {
        Review.DAO.insertReview(connection, review);
    }

    public void updateReview(final Review review) {
        Review.DAO.updateReview(connection, review);
    }

    public int getLatestBuildId() {
        return Build.DAO.getLatestBuildId(connection);
    }

    public void insertBuild(final Build build, final User user) {
        Build.DAO.insertBuild(connection, build, user);
    }

    public ComponentPrice getScrapedPrice(final int componentId) {
        return ComponentPrice.DAO.getLatestLowestPriceById(connection, componentId);
    }

    public List<ComponentPrice> getRecentComponentPricesByReseller(final int componentId, final String reseller) {
        return ComponentPrice.DAO.getLastFourteenScrapedPricesByReseller(connection, componentId, reseller);
    }

    public ComponentCompatibilityChecker getComponentCompatibilityChecker() {
        return componentCompatibilityChecker;
    }

    public List<Manufacturer> getManufacturers() {
        return Manufacturer.DAO.getManufacturers(connection);
    }

    public void insertCpu(final CpuInsert cpu) {
        CpuInsert.DAO.insert(connection, cpu);
    }

    public void insertCooler(final CoolerInsert cooler) {
        CoolerInsert.DAO.insert(connection, cooler);
    }

    public void insertRam(final RamInsert ram) {
        RamInsert.DAO.insert(connection, ram);
    }

    public void insertCase(final CaseInsert _case) {
        CaseInsert.DAO.insert(connection, _case);
    }

    public void insertMotherboard(final MotherboardInsert motherboard) {
        MotherboardInsert.DAO.insert(connection, motherboard);
    }

    public void insertGpu(final GpuInsert gpu) {
        GpuInsert.DAO.insert(connection, gpu);
    }

    public void insertStorage(final StorageInsert storage) {
        StorageInsert.DAO.insert(connection, storage);
    }

    public void insertPsu(final PsuInsert psu) {
        PsuInsert.DAO.insert(connection, psu);
    }

    public void insertComponent(final ComponentInsert componentInsert) {
        ComponentInsert.DAO.insert(connection, componentInsert);
    }

    public void insertCpuRamCompatibility(final CpuRamInsert cpuRamInsert) {
        CpuRamInsert.DAO.insert(connection, cpuRamInsert);
    }

    public int getLatestComponendId() {
        return ComponentInsert.DAO.getLatestId(connection);
    }

    public User getUser(final String username) {
        return User.DAO.findByUsername(connection, username);
    } 

    public double getUserRating(final String username) {
        return User.DAO.getRating(connection, username);
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
