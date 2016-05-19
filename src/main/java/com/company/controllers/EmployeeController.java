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

    private PlatformTransactionManager txManager;
    private EmployeeDao employeeDao;

    public void setTxManager(PlatformTransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> getAllEmployees() {
        TransactionStatus status = txManager.getTransaction(new DefaultTransactionDefinition(
                TransactionDefinition.PROPAGATION_REQUIRED));
        try {
            List<Employee> result = employeeDao.findAll();
            txManager.commit(status);

            return result;
        } catch (Exception e) {
            txManager.rollback(status);
            throw new RuntimeException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Employee getEmployeeById(int id) {
        return employeeDao.load(id);
    }
}
