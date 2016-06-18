package com.company.controllers;

import com.company.model.Employee;
import com.company.model.EmployeeDao;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

/**
 * Created by Yevhen on 14.05.2016.
 */
public class EmployeeController {

    private EmployeeDao employeeDao;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Employee> getAllEmployees() {
        List<Employee> result = employeeDao.findAll();

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Employee getEmployeeById(int id) {
        return employeeDao.load(id);
    }
}
