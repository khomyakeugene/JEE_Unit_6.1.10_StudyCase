package com.company.jdbc.model.jdbc;

import com.company.jdbc.model.Employee;
import com.company.jdbc.model.EmployeeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yevhen on 03.05.2016.
 */
public class JdbcEmployeeDao implements EmployeeDao {
    private static final String CONNECT_DB_ERROR_PATTERN = "Exception occurred while connecting to DB";
    private static final String CANNOT_FIND_EMPLOYEE_PATTERN = "Cannot find employee with id %d";

    private static final String SQL_QUERY_1 = "SELECT * FROM employee";
    private static final String SQL_QUERY_2 = "SELECT * FROM employee WHERE id = ?";

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDao.class);

    private DataSource dataSource;

    private void databaseConnectError(Exception e) {
        LOGGER.error(CONNECT_DB_ERROR_PATTERN, e);

        throw new RuntimeException(e);
    }

    @Override
    @Transactional (propagation = Propagation.MANDATORY)
    public Employee load(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_2)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createEmployee(resultSet);
            } else  {
                throw new RuntimeException(String.format(CANNOT_FIND_EMPLOYEE_PATTERN , id));
            }
        } catch (SQLException e) {
            databaseConnectError(e);
        }
        return null;
    }

    @Override
    @Transactional (propagation = Propagation.MANDATORY)
    public List<Employee> findAll() {
        List<Employee> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_QUERY_1);

            while (resultSet.next()) {
                Employee employee = createEmployee(resultSet);

                result.add(employee);
            }

        } catch (SQLException e) {
            databaseConnectError(e);
        }

        return result;
    }

    private Employee createEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();

        employee.setId(resultSet.getInt("id"));
        employee.setName(resultSet.getString("name"));
        employee.setAge(resultSet.getInt("age"));
        employee.setAddress(resultSet.getString("address"));
        employee.setSalary(resultSet.getFloat("salary"));
        employee.setJoinDate(resultSet.getString("join_date"));

        return employee;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
