package com.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";

    private static final Logger LOGGER =  LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        loadDriver();
        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/company")) {

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void loadDriver() {
        try {
            LOGGER.info("Loading JDBC driver: " + DRIVER_CLASS_NAME);
            Class.forName(DRIVER_CLASS_NAME);
            LOGGER.info("Driver loaded successfully");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Cannot find driver: " + DRIVER_CLASS_NAME, e);
            throw new RuntimeException(e);
        }
    }
}
