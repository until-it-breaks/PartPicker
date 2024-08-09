package it.unibo.application.data.entities.insertion;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;

import java.sql.Connection;
import java.sql.SQLException;

public class CaseInsert {
    private int id;
    private String formFactor;

    public CaseInsert(int id, String formFactor) {
        this.id = id;
        this.formFactor = formFactor;
    }

    public int getId() {
        return id;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public final class DAO {
        public static void insert(final Connection connection, final CaseInsert _case) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.INSERT_CASE,
                    _case.getId(), _case.getFormFactor())
            ) {
                statement.executeUpdate();
            } catch (final SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
