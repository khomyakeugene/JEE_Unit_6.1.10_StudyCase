package com.company.jdbc.model;

import java.util.List;

/**
 * Created by Yevhen on 03.05.2016.
 */
public interface EmployeeDao {
    Employee load(int id);

    List<Employee> findAll();
}
