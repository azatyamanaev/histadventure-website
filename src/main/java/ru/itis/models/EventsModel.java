package ru.itis.models;

import ru.itis.entities.Event;
import ru.itis.repositories.EventsRepositoryJdbcImpl;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class EventsModel {
    private static EventsModel instance = new EventsModel();

    private List<Event> model;

    public static EventsModel getInstance() {
        return instance;
    }

    private EventsModel() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("db.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        String url = properties.getProperty("db.url");

        Connection connection;

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        EventsRepositoryJdbcImpl eventsRepositoryJdbc = new EventsRepositoryJdbcImpl(connection);
        model = eventsRepositoryJdbc.findAll();
    }

    public List<String> list() {
        return model.stream()
                .map(Event::getName)
                .collect(Collectors.toList());
    }
}
