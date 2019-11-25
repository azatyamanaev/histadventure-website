package ru.itis.DAO;

import ru.itis.entities.Event;
import ru.itis.models.ConnectionCreator;
import ru.itis.repositories.EventsRepository;
import ru.itis.repositories.EventsRepositoryJdbcImpl;

import java.sql.*;
import java.util.Optional;

public class SubscribedEventsDAO {
    private Connection connection;

    public SubscribedEventsDAO(Connection connection) {
        this.connection = connection;
    }

    //language=SQL
    private final String SQL_SUBSCRIBE = "insert into subscribed_events" +
            "(userid, eventid) values (?, ?);";

    public void save(Long userId, Long eventId) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SUBSCRIBE, Statement.RETURN_GENERATED_KEYS)) {
            Statement statement1 = connection.createStatement();
            ResultSet resultSet = statement1.executeQuery("select * from subscribed_events where userid = '" + userId + "' and  eventid = '" + eventId + "';");
            if (!resultSet.next()) {
                statement.setLong(1, userId);
                statement.setLong(2, eventId);
                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException();
                }
                EventsRepository eventsRepository = new EventsRepositoryJdbcImpl(ConnectionCreator.getConnection());
                Optional<Event> ev = eventsRepository.find(eventId);
                if (ev.isPresent()) {
                    Event event = ev.get();
                    event.setCountLike(event.getCountLike() + 1);
                    eventsRepository.update(event);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
