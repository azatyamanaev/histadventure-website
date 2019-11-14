package ru.itis.repositories;

import ru.itis.entities.Event;
import ru.itis.entities.Role;
import ru.itis.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private Connection connection;

    //language=SQL
    private final String SQL_INSERT_USER = "insert into users " +
            "(first_name, last_name, email, login, password, role) " +
            "values (?, ?, ?, ?, ?, ?, ?);";

    //language=SQL
    private final String SQL_UPDATE_USER = "update users \n" +
            "set first_name = ?, last_name = ?, email = ?, login = ?, password = ?, role = ? \n" +
            "where id = ?;";

    public UsersRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

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

    @Override
    public Optional<User> findOneByEmail(String email) {
        User user = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users where email = \'" + email + "\';");
            if (resultSet.next()) {
                user = userRowMapper.mapRow(resultSet);
            }
            if (user != null) {
                user.setCreatedEvents(findCreatedEvents(user.getId()));
                user.setSubscribedEvents(findSubscribedEvents(user.getId()));
            }
            statement.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.ofNullable(user);

    }

    @Override
    public void save(User model) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, model.getFirstName());
            statement.setString(2, model.getLastName());
            statement.setString(3, model.getEmail());
            statement.setString(4, model.getLogin());
            statement.setString(5, model.getPassword());
            statement.setString(6, String.valueOf(model.getRole()));
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
    public void update(User model) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, model.getFirstName());
            statement.setString(2, model.getLastName());
            statement.setString(3, model.getEmail());
            statement.setString(4, model.getLogin());
            statement.setString(5, model.getPassword());
            statement.setString(6, String.valueOf(model.getRole()));
            statement.setLong(8, model.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("delete from users \n" +
                    "where id = " + id + ";");
            statement.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> find(Long id) {
        User user = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users where id = " + id + ";");

            if (resultSet.next()) {
                user = userRowMapper.mapRow(resultSet);
            }
            if (user != null) {
                user.setCreatedEvents(findCreatedEvents(user.getId()));
                user.setSubscribedEvents(findSubscribedEvents(user.getId()));
            }
            statement.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");

            while (resultSet.next()) {
                User user = userRowMapper.mapRow(resultSet);
                if (user != null) {
                    user.setCreatedEvents(findCreatedEvents(user.getId()));
                    user.setSubscribedEvents(findSubscribedEvents(user.getId()));
                }
                result.add(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return result;
    }

    private List<Event> findCreatedEvents(Long id) {
        List<Event> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from created_events join events e on created_events.eventid = e.id where userid = " + id);

            while (resultSet.next()) {
                Event event = eventRowMapper.mapRow(resultSet);
                result.add(event);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return result;
    }

    private List<Event> findSubscribedEvents(Long id) {
        List<Event> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from subscribed_events join events e on subscribed_events.eventid = e.id where userid = " + id);

            while (resultSet.next()) {
                Event event = eventRowMapper.mapRow(resultSet);
                result.add(event);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return result;
    }
}
