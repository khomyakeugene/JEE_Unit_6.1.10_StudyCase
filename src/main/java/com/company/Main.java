package com.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;

public class Main {
    private static final Logger LOGGER =  LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.getAll().forEach(System.out::println);
    }
}
