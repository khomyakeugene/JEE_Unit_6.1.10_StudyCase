package com.company;

public class Main {

    public static void main(String[] args) {
        loadDriver();

    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
