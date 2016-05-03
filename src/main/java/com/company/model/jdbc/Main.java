package com.company.model.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final int TEST_EMPLOYEE_ID = 1;
    private static final Logger LOGGER =  LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDao();

        System.out.println("All employees");
        employeeDao.getAll().forEach(System.out::println);

        System.out.println("Employee with id " + TEST_EMPLOYEE_ID);
        System.out.println(employeeDao.load(TEST_EMPLOYEE_ID));
    }
}
