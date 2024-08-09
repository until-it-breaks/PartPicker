package it.unibo.application.data.entities.price;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
                        final var id = resultSet.getInt("CodiceComponente");
                        final var resellerName = resultSet.getString("NomeRivenditore");
                        final var scrapeDate = resultSet.getDate("DataRilevamentoPrezzo").toLocalDate();
                        final var componentPrice = resultSet.getDouble("PrezzoComponente");
                        return new ComponentPrice(id, resellerName, scrapeDate, componentPrice);
                    }
                    return new ComponentPrice(componentId, null, null, 0);
                } catch (final SQLException e) {
                    throw new DAOException(e);
            }
        }

        public static List<ComponentPrice> getLastFourteenScrapedPricesByReseller(final Connection connection,
                final int componentId, final String resellerName) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.GET_LAST_14_SCRAPED_PRICES, componentId, resellerName);
                var resultSet = statement.executeQuery();
                ) {
                    final List<ComponentPrice> componentPrices = new ArrayList<>();
                    while (resultSet.next()) {
                        final var id = resultSet.getInt("CodiceComponente");
                        final var name = resultSet.getString("NomeRivenditore");
                        final var scrapeDate = resultSet.getDate("DataRilevamentoPrezzo").toLocalDate();
                        final var componentPrice = resultSet.getDouble("PrezzoComponente");
                        componentPrices.add(new ComponentPrice(id, name, scrapeDate, componentPrice));
                    }
                    return componentPrices;
                } catch (final SQLException e) {
                    throw new DAOException(e);
            }
        }
    }
}