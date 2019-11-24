package ru.itis.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ParticipantsDAO {
    private Connection connection;
    public ParticipantsDAO(Connection connection) {
        this.connection = connection;
    }
    //language=SQL
    private final String SQL_INSERT_LIKE = "insert into participants" +
            "(eventid, userid) values (?, ?);";
    public void save(Long eventId, Long userId) {
        try(PreparedStatement statement = connection.prepareStatement(SQL_INSERT_LIKE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, eventId);
            statement.setLong(2, userId);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
