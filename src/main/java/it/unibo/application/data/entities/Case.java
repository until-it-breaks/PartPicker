package it.unibo.application.data.entities;

import it.unibo.application.data.DAOException;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.data.Queries;
import java.sql.Connection;
import java.sql.SQLException;

public class Case {
    public int caseId;
    public String formFactor;

    public Case(int caseId, String formFactor) {
        this.caseId = caseId;
        this.formFactor = formFactor;
    }

    public final class DAO {
        public static Case findById(Connection connection, int id) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.FIND_CASE, id);
                var resultSet = statement.executeQuery();
            ) {
                if (resultSet.next()) {
                    var caseId = resultSet.getInt("CodiceCase");
                    var formFactor = resultSet.getString("FattoreFormaCase");
                    var _case = new Case(caseId, formFactor);
                    return _case;
                }
                return null;
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
