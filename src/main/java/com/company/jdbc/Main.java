package com.company.jdbc;

import com.company.jdbc.controllers.EmployeeController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private EmployeeController employeeController;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        Main main = context.getBean(Main.class);
        main.start();
    }

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    private void start() {
        // employeeController.getAllEmployees().forEach(System.out::println);
        System.out.println(employeeController.getEmployeeById(1));
    }

}


