package ru.itis.repositories;

import ru.itis.entities.Event;
import ru.itis.entities.Role;
import ru.itis.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventsRepositoryJdbcImpl implements EventsRepository {
    private Connection connection;
    //language=SQL
    private final String SQL_INSERT_EVENT = "insert into events " +
            "(name, description, participants, capacity, host, active, place, time_start, time_end, subscribed_users) " +
            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    //language=SQL
    private final String SQL_UPDATE_EVENT = "update events \n" +
            "set name = ?, description = ?, participants = ?, capacity = ?, host = ?, active = ?, place = ?, time_start = ?, time_end = ?, subscribed_users = ? \n" +
            "where id = ?;";

    public EventsRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    private RowMapper<Event> eventRowMapper = row -> {
        Long id = row.getLong("id");
        String name = row.getString("name");
        String description = row.getString("description");
        Integer capacity = row.getInt("capacity");
        String host = row.getString("host");
        Boolean active = row.getBoolean("active");
        String place = row.getString("place");
        String timeStart = row.getString("time_start");
        String timeEnd = row.getString("time_end");
        return new Event(id, name, description, capacity, host, active, place, timeStart, timeEnd);
    };

    private RowMapper<User> userRowMapper = row -> {
        Long userId = row.getLong("id");
        String firstName = row.getString("first_name");
        String lastName = row.getString("last_name");
        String email = row.getString("email");
        String login = row.getString("login");
        String password = row.getString("password");
        String role = row.getString("role");
        return new User(userId, firstName, lastName, email, login, password, Role.valueOf(role));
    };

    @Override
    public void save(Event model) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_EVENT,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, model.getName());
            statement.setString(2, model.getDescription());
            statement.setArray(3, (Array) model.getParticipants());
            statement.setInt(4, model.getCapacity());
            statement.setString(5, model.getHost());
            statement.setBoolean(6, model.isActive());
            statement.setString(7, model.getPlace());
            statement.setString(8, model.getTimeStart());
            statement.setString(9, model.getTimeEnd());
            statement.setArray(10, (Array) model.getSubscribedUsers());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException();
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    model.setId(generatedKeys.getLong("id"));
                } else {
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void update(Event model) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_EVENT,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, model.getName());
            statement.setString(2, model.getDescription());
            statement.setArray(3, (Array) model.getParticipants());
            statement.setInt(4, model.getCapacity());
            statement.setString(5, model.getHost());
            statement.setBoolean(6, model.isActive());
            statement.setString(7, model.getPlace());
            statement.setString(8, model.getTimeStart());
            statement.setString(9, model.getTimeEnd());
            statement.setArray(10, (Array) model.getSubscribedUsers());
            statement.setLong(11, model.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("delete from events \n" +
                    "where id = " + id + ";");
            statement.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<Event> find(Long id) {
        Event event = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from events where id = " + id + ";");

            if (resultSet.next()) {
                event = eventRowMapper.mapRow(resultSet);
            }
            if (event != null) {
                event.setParticipants(findParticipants(event.getId()));
                event.setSubscribedUsers(findSubscribedUsers(event.getId()));
            }
            statement.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.ofNullable(event);
    }

    @Override
    public List<Event> findAll() {
        List<Event> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from events");

            while (resultSet.next()) {
                Event event = eventRowMapper.mapRow(resultSet);
                if (event != null) {
                    event.setParticipants(findParticipants(event.getId()));
                    event.setSubscribedUsers(findSubscribedUsers(event.getId()));
                }
                result.add(event);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return result;
    }

    private List<User> findParticipants(Long id) {
        List<User> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from participants join users u on participants.userid = u.id where eventid = " + id);

            while (resultSet.next()) {
                User user = userRowMapper.mapRow(resultSet);
                result.add(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return result;
    }

    public List<User> findSubscribedUsers(Long id) {
        List<User> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from subscribed_events join users u on subscribed_events.userid = u.id where eventid = " + id);

            while (resultSet.next()) {
                User user = userRowMapper.mapRow(resultSet);
                result.add(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return result;
    }
}
