package com.company.jdbc;

import com.company.jdbc.model.jdbc.JdbcEmployeeDao;
import com.company.jdbc.model.EmployeeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private static final int TEST_EMPLOYEE_ID = 1;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");

        EmployeeDao jdbcEmployeeDao = applicationContext.getBean("employeeDao", JdbcEmployeeDao.class);

        System.out.println("All employees");
        jdbcEmployeeDao.findAll().forEach(System.out::println);
/*
        System.out.println("Employee with id " + TEST_EMPLOYEE_ID);
        System.out.println(jdbcEmployeeDao.load(TEST_EMPLOYEE_ID));
        */
    }
}
