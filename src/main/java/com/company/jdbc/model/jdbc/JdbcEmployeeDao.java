package com.company.jdbc.model.jdbc;

import com.company.jdbc.model.Employee;
import com.company.jdbc.model.EmployeeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yevhen on 03.05.2016.
 */
public class JdbcEmployeeDao implements EmployeeDao {
    private static final String CONNECT_DB_ERROR_PATTERN = "Exception occurred while connecting to DB: %s";
    private static final String CANNOT_FIND_EMPLOYEE_PATTERN = "Cannot find employee with id %d";

    private static final String DATABASE_DRIVER_CLASS_NAME = "org.postgresql.Driver";
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/company";
    private static final String DATABASE_USER_NAME = "user";
    private static final String DATABASE_USER_PASSWORD = "1111";
    private static final String SQL_QUERY_1 = "SELECT * FROM employee";
    private static final String SQL_QUERY_2 = "SELECT * FROM employee WHERE id = ?";

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDao.class);

    private String url = DATABASE_URL;
    private String user = DATABASE_USER_NAME;
    private String password = DATABASE_USER_PASSWORD;

    public JdbcEmployeeDao() {
        loadDriver();
    }

    private void databaseConnectError(Exception e) {
        LOGGER.error(String.format(CONNECT_DB_ERROR_PATTERN, url), e);

        throw new RuntimeException(e);
    }

    @Override
    public Employee load(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
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
    public List<Employee> getAll() {
        List<Employee> result = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, password);
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

    private void loadDriver() {
        try {
            LOGGER.info("Loading JDBC driver: " + DATABASE_DRIVER_CLASS_NAME);
            Class.forName(DATABASE_DRIVER_CLASS_NAME);
            LOGGER.info("Driver loaded successfully");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Cannot find driver: " + DATABASE_DRIVER_CLASS_NAME, e);
            throw new RuntimeException(e);
        }
    }

}
