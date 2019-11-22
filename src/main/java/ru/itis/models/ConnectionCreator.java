package ru.itis.models;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {
    public static Connection getConnection() {
        Connection connection;
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("/home/monitor/Рабочий стол/Project(dev1)/histadventure-website/src/main/db.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        String url = properties.getProperty("db.url");

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return connection;
    }
}
