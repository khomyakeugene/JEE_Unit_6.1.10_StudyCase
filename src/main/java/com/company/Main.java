package com.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final String DATABASE_DRIVER_CLASS_NAME = "org.postgresql.Driver";
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/company";
    private static final String DATABASE_USER_NAME = "user";
    private static final String DATABASE_USER_PASSWORD = "1111";

    private static final Logger LOGGER =  LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        loadDriver();
        LOGGER.info("Connecting to DB");
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER_NAME,
                DATABASE_USER_PASSWORD)) {
            LOGGER.info("Successfully connected to DB ");

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB: " + DATABASE_URL, e);
        }

    }

    private static void loadDriver() {
        try {
            LOGGER.info("Loading JDBC driver: " + DATABASE_DRIVER_CLASS_NAME);
            Class.forName(DATABASE_DRIVER_CLASS_NAME);
            LOGGER.info("Driver loaded successfully");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Cannot find driver: " + DATABASE_DRIVER_CLASS_NAME, e);
            throw new RuntimeException(e);
        }
    }
}
