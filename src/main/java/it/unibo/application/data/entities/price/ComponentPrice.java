package it.unibo.application.data.entities.price;

import java.util.Date;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import it.unibo.application.data.entities.builds.StorageUsage;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class ComponentPrice {
    private final int componentId;
    private final String resellerName;
    private final LocalDate scrapeDate;
    private final double componentPrice;

    public ComponentPrice(final int componentId, final String resellerName,
            final LocalDate scrapeDate, final double componentPrice) {
        this.componentId = componentId;
        this.resellerName = resellerName;
        this.scrapeDate = scrapeDate;
        this.componentPrice = componentPrice;
    }

    public int getComponentId() {
        return componentId;
    }

    public String getResellerName() {
        return resellerName;
    }

    public LocalDate getScrapeDate() {
        return scrapeDate;
    }

    public double getComponentPrice() {
        return componentPrice;
    }

    public final class DAO {
        public static ComponentPrice getLatestLowestPriceById(final Connection connection, final int componentId) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.FIND_RECENT_LOWEST_PRICE, componentId);
                var resultSet = statement.executeQuery();
                ) {
                    if (resultSet.next()) {
                        var id = resultSet.getInt("CodiceComponente");
                        var resellerName = resultSet.getString("NomeRivenditore");
                        var scrapeDate = resultSet.getDate("DataRilevamentoPrezzo").toLocalDate();
                        var componentPrice = resultSet.getDouble("PrezzoComponente");
                        return new ComponentPrice(id, resellerName, scrapeDate, componentPrice);
                    }
                    return null;
                } catch (final SQLException e) {
                    throw new DAOException(e);
            }
        }
    }
}