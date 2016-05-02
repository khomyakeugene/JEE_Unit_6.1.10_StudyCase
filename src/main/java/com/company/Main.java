package com.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
    private static final Logger LOGGER =  LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        loadDriver();

    }

    private static void loadDriver() {
        try {
            Class.forName(DRIVER_CLASS_NAME);
        } catch (ClassNotFoundException e) {
            LOGGER.error("Cannot find driver: " + DRIVER_CLASS_NAME, e);
            throw new RuntimeException(e);
        }
    }
}
