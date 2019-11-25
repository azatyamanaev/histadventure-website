package ru.itis.DAO;

import java.sql.*;

public class ParticipantsDAO {
    private Connection connection;

    public ParticipantsDAO(Connection connection) {
        this.connection = connection;
    }

    //language=SQL
    private final String SQL_INSERT_LIKE = "insert into participants" +
            "(eventid, userid) values (?, ?);";

    public void save(Long eventId, Long userId) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_LIKE, Statement.RETURN_GENERATED_KEYS)) {
            Statement statement1 = connection.createStatement();
            ResultSet resultSet = statement1.executeQuery("select * from participants where eventid = '" + eventId + "' and userid = '" + userId + "';");
            if (!resultSet.next()) {
                statement.setLong(1, eventId);
                statement.setLong(2, userId);
                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
