package com.company.jdbc;

import com.company.jdbc.model.jdbc.JdbcEmployeeDao;
import com.company.jdbc.model.EmployeeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final int TEST_EMPLOYEE_ID = 1;
    private static final Logger LOGGER =  LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        EmployeeDao jdbcEmployeeDao = new JdbcEmployeeDao();

        System.out.println("All employees");
        jdbcEmployeeDao.findAll().forEach(System.out::println);

        System.out.println("Employee with id " + TEST_EMPLOYEE_ID);
        System.out.println(jdbcEmployeeDao.load(TEST_EMPLOYEE_ID));
    }
}
