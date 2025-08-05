package com.revstox.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {

    private static final String PROPERTIES_FILE = "/application.properties";

    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;

    public DatabaseManager() {
        Properties props = loadProperties();
        this.dbUrl = props.getProperty("db.url");
        this.dbUser = props.getProperty("db.user");
        this.dbPassword = props.getProperty("db.password");
    }

    private Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input = getClass().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw new IOException("Unable to find " + PROPERTIES_FILE);
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database properties", e);
        }
        return props;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to establish DB connection", e);
        }
    }
}
