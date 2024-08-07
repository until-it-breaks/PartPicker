package it.unibo.application.data.entities.builds;

import java.time.LocalDate;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;

public class Review {
    private final int buildId;
    private final String username;
    private final int reviewRating;
    private final String comment;
    private final LocalDate lastEditDate;

    public Review(final int buildId, final String username, final int reviewRating,
            final String comment, final LocalDate lastEditDate) {
        this.buildId = buildId;
        this.username = username;
        this.reviewRating = reviewRating;
        this.comment = comment;
        this.lastEditDate = lastEditDate;
    }

    public int getBuildId() {
        return buildId;
    }

    public String getUsername() {
        return username;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public String getComment() {
        return comment;
    }

    public LocalDate getLastEditDate() {
        return lastEditDate;
    }

    public final class DAO {

        public static List<Review> getReviews(final Connection connection, final int buildId) {
            try (
                    var statement = DAOUtils.prepare(connection, Queries.GET_REVIEWS, buildId);
                    var resultSet = statement.executeQuery();
                ) {
                    final List<Review> reviews = new ArrayList<>();
                    while (resultSet.next()) {
                        final var id = resultSet.getInt("CodiceBuild");
                        final var username = resultSet.getString("Username");
                        final var rating = resultSet.getInt("RatingRecensione");
                        final var comment = resultSet.getString("Commento");
                        final var lastEditDate = resultSet.getDate("DataModificaRecensione").toLocalDate();
                        reviews.add(new Review(id, username, rating, comment, lastEditDate));
                    }
                    return reviews;
                } catch (final SQLException e) {
                    throw new DAOException(e);
            }
        }

        public static void insertReview(final Connection connection, final Review review) {
            try (
                    var statement = DAOUtils.prepare(connection, Queries.INSERT_REVIEW,
                        review.getBuildId(), review.getUsername(),
                        review.getReviewRating(), review.getComment(),
                        review.getLastEditDate());
                ) {
                    statement.executeUpdate();
                } catch (final SQLException e) {
                    throw new DAOException(e);
            }
        }

        public static void updateReview(final Connection connection, final Review review) {
            try (
                    var statement = DAOUtils.prepare(connection, Queries.UPDATE_REVIEW,
                        review.getReviewRating(), review.getComment(),
                        review.getLastEditDate(), review.getBuildId(),
                        review.getUsername());
                ) {
                    statement.executeUpdate();
                } catch (final SQLException e) {
                    throw new DAOException(e);
            }
        }
    }
}
